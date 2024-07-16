package Models;

import Components.Empleado;
import Controllers.ConnectionSQLServer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Joao
 */
public class modelEmpleado {
    private static Connection myConn = null;
    private static PreparedStatement myStamt = null;
    private static Statement myStamtQ = null;
    public static String query;
    
    /* INSERT */
    public static void crearEmpleado(String nombre, String apellido, String correo, String telefono, String direccion, String posicion, float salario, String username, String password) throws SQLException {
        ConnectionSQLServer SQL = new ConnectionSQLServer();
        myConn = SQL.connectSQL();
        query = "INSERT INTO Empleado (nombre, apellido, correo, telefono, direccion, fecha_contratacion, posicion, salario, username, password) VALUES (?, ?, ?, ?, ?, GETDATE(), ?, ?, ?, ?);";
        
        myStamt = myConn.prepareStatement(query);
        myStamt.setString(1, nombre);
        myStamt.setString(2, apellido);
        myStamt.setString(3, correo);
        myStamt.setString(4, telefono);
        myStamt.setString(5, direccion);
        myStamt.setString(6, posicion);
        myStamt.setFloat(7, salario);
        myStamt.setString(8, username);
        myStamt.setString(9, password);
        
        int response = myStamt.executeUpdate();
        System.out.println(response + "filas afectadas");
        myConn.close();
    }
    
    /* READ */
    public static ArrayList<Empleado> leerEmpleado() throws SQLException {
        ConnectionSQLServer SQL = new ConnectionSQLServer();
        myConn = SQL.connectSQL();
        query = "SELECT * from Empleado;";
        
        myStamtQ = myConn.createStatement();
        
        ResultSet response = myStamtQ.executeQuery(query);
        ArrayList<Empleado> listaEmpleados = new ArrayList<>();
        System.out.println(response);
        
        while (response.next()) {
            int id = response.getInt("empleado_id");
            String nombre = response.getString("nombre");
            String apellido = response.getString("apellido");
            String correo = response.getString("correo");
            String telefono = response.getString("telefono");
            String direccion = response.getString("direccion");
            String fecha = response.getString("fecha_contratacion");
            String posicion = response.getString("posicion");
            float salario = response.getFloat("salario");
            String username = response.getString("username");
            String password = response.getString("password");
            
            listaEmpleados.add(new Empleado(id, nombre, apellido, correo, telefono, direccion, fecha, posicion, salario, username, password));
        }
        myConn.close();
        
        return listaEmpleados;
    }
    
    /* UPDATE */
    public static void actualizarEmpleado(int id, String nombre, String apellido, String correo, String telefono, String direccion, String posicion, float salario, String username, String password) throws SQLException {
        ConnectionSQLServer SQL = new ConnectionSQLServer();
        myConn = SQL.connectSQL();
        query = "UPDATE Empleado SET nombre = ?, apellido = ?, correo = ?, telefono = ?, direccion = ?, posicion = ?, salario = ?, username = ?, password = ?  WHERE empleado_id=?;";
        
        myStamt = myConn.prepareStatement(query);
        myStamt.setString(1, nombre);
        myStamt.setString(2, apellido);
        myStamt.setString(3, correo);
        myStamt.setString(4, telefono);
        myStamt.setString(5, direccion);
        myStamt.setString(6, posicion);
        myStamt.setFloat(7, salario);
        myStamt.setString(8, username);
        myStamt.setString(9, password);
        myStamt.setInt(10, id);
        
        int response = myStamt.executeUpdate();
        System.out.println(response + "filas afectadas");

        myConn.close();
    }
    
    /* DELETE */
    public static void eliminarEmpleado(int id) throws SQLException {
        ConnectionSQLServer SQL = new ConnectionSQLServer();
        myConn = SQL.connectSQL();
        query = "DELETE FROM Empleado WHERE empleado_id = ?;";
        
        myStamt = myConn.prepareStatement(query);
        myStamt.setInt(1, id);
        
        int response = myStamt.executeUpdate();
        System.out.println(response + "filas afectadas");
        
        myConn.close();
    }   
}
