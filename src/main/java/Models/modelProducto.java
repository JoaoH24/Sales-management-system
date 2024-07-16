package Models;

import Components.Producto;
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
public class modelProducto {
    private static Connection myConn = null;
    private static PreparedStatement myStamt = null;
    private static Statement myStamtQ = null;
    public static String query;
    
    /* INSERT */
    public static void crearProducto(String nombre, String descripcion, float precio, int stock, int categoria) throws SQLException {
        ConnectionSQLServer SQL = new ConnectionSQLServer();
        myConn = SQL.connectSQL();
        query = "INSERT INTO Producto (nombre, descripcion, precio, stock, categoria_id) VALUES (?,?,?,?,?);";
        
        myStamt = myConn.prepareStatement(query);
        myStamt.setString(1, nombre);
        myStamt.setString(2, descripcion);
        myStamt.setFloat(3, precio);
        myStamt.setInt(4, stock);
        myStamt.setInt(5, categoria);
        
        int response = myStamt.executeUpdate();
        System.out.println(response + "filas afectadas");
        myConn.close();
    }
    
    /* READ */
    public static ArrayList<Producto> leerProducto() throws SQLException {
        ConnectionSQLServer SQL = new ConnectionSQLServer();
        myConn = SQL.connectSQL();
        query = "SELECT * from Producto;";
        
        myStamtQ = myConn.createStatement();
        
        ResultSet response = myStamtQ.executeQuery(query);
        
        ArrayList<Producto> listaClientes = new ArrayList<>();
        System.out.println(response);
        
        while (response.next()) {
            int id = response.getInt("producto_id");
            String nombre = response.getString("nombre");
            String descripcion = response.getString("descripcion");
            float precio = response.getFloat("precio");
            int stock = response.getInt("stock");
            int categoria = response.getInt("categoria_id");
            
            listaClientes.add(new Producto(id, nombre, descripcion, precio, stock, categoria));
        }
        
        myConn.close();
        return listaClientes;
    }
    
    /* UPDATE */
    public static void actualizarProducto(int id, String nombre, String descripcion, float precio, int stock, int categoria) throws SQLException {
        ConnectionSQLServer SQL = new ConnectionSQLServer();
        myConn = SQL.connectSQL();
        query = "UPDATE Producto SET nombre = ?, descripcion = ?, precio = ?, stock = ?, categoria_id = ? WHERE producto_id = ?;";
        
        myStamt = myConn.prepareStatement(query);
        myStamt.setString(1, nombre);
        myStamt.setString(2, descripcion);
        myStamt.setFloat(3, precio);
        myStamt.setInt(4, stock);
        myStamt.setInt(5, categoria);
        myStamt.setInt(6, id);
        
        int response = myStamt.executeUpdate();
        System.out.println(response + "filas afectadas");
        
        myConn.close();
    }
    
    /* DELETE */
    public static void eliminarProducto(int id) throws SQLException {
        ConnectionSQLServer SQL = new ConnectionSQLServer();
        myConn = SQL.connectSQL();
        query = "DELETE FROM Producto WHERE producto_id = ?;";
        myStamt = myConn.prepareStatement(query);
        myStamt.setInt(1, id);
        
        int response = myStamt.executeUpdate();
        System.out.println(response + "filas afectadas");
        
        myConn.close();
    }    
}
