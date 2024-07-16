package Models;

import Components.Cliente;
import Controllers.ConnectionSQLServer;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Joao
 */
public class modelCliente {
    private static Connection myConn = null;
    private static PreparedStatement myStamt = null;
    private static Statement myStamtQ = null;
    private static String query;
    private static int response;
    
    /* INSERT */
    public static void crearCliente(String nombre, String apellido, String correo, String telefono, String direccion) throws SQLException {
        ConnectionSQLServer SQL = new ConnectionSQLServer();
        myConn = SQL.connectSQL();
        query = "INSERT INTO Cliente (nombre, apellido, correo, telefono, direccion, fecha_registro) VALUES (?,?,?,?,?, GETDATE());";
        
        myStamt = myConn.prepareStatement(query);
        myStamt.setString(1, nombre);
        myStamt.setString(2, apellido);
        myStamt.setString(3, correo);
        myStamt.setString(4, telefono);
        myStamt.setString(5, direccion);
        
        response = myStamt.executeUpdate();
        System.out.println(response + "filas afectadas");
        myConn.close();
    }
    
    /* READ */
    public static ArrayList<Cliente> leerCliente() throws SQLException {
        ConnectionSQLServer SQL = new ConnectionSQLServer();
        myConn = SQL.connectSQL();
        query = "SELECT * from Cliente;";
        
        myStamtQ = myConn.createStatement();
        
        ResultSet response = myStamtQ.executeQuery(query);
        ArrayList<Cliente> listaClientes = new ArrayList<>();
        System.out.println(response);
        
        while (response.next()) {
            int id = response.getInt("cliente_id");
            String nombre = response.getString("nombre");
            String apellido = response.getString("apellido");
            String correo = response.getString("correo");
            String telefono = response.getString("telefono");
            String direccion = response.getString("direccion");
            String fecha = response.getString("fecha_registro");
            
            listaClientes.add(new Cliente(id, nombre, apellido, correo, telefono, direccion, fecha));
        }
        
        myConn.close();
        
        return listaClientes;
    }
    
    /* UPDATE */
    public static void actualizarCliente(int id, String nombre, String apellido, String correo, String telefono, String direccion) throws SQLException {
        ConnectionSQLServer SQL = new ConnectionSQLServer();
        myConn = SQL.connectSQL();
        query = "UPDATE Cliente SET nombre = ?, apellido = ?, correo = ?, telefono = ?, direccion = ?  WHERE cliente_id = ?;";
        
        myStamt = myConn.prepareStatement(query);
        myStamt.setString(1, nombre);
        myStamt.setString(2, apellido);
        myStamt.setString(3, correo);
        myStamt.setString(4, telefono);
        myStamt.setString(5, direccion);
        myStamt.setInt(6, id);
        
        int response = myStamt.executeUpdate();
        System.out.println(response + "filas afectadas");
        
        myConn.close();
    }
    
    /* DELETE */
    public static void eliminarCliente(int id) throws SQLException {
        ConnectionSQLServer SQL = new ConnectionSQLServer();
        myConn = SQL.connectSQL();
        query = "DELETE FROM Cliente WHERE cliente_id = ?;";
        myStamt = myConn.prepareStatement(query);
        myStamt.setInt(1, id);
        
        int response = myStamt.executeUpdate();
        System.out.println(response + "filas afectadas");
        
        myConn.close();
    }
}
