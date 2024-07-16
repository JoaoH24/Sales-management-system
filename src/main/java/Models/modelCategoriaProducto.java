package Models;

import Components.CategoriaProducto;
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
public class modelCategoriaProducto {
    private static Connection myConn = null;
    private static PreparedStatement myStamt = null;
    private static Statement myStamtQ = null;
    private static String query;
    private static int response;
    
    /* INSERT */
    public static void crearCategoriaProducto(String nombre, String descripcion) throws SQLException {
        ConnectionSQLServer SQL = new ConnectionSQLServer();
        myConn = SQL.connectSQL();
        query = "INSERT INTO CategoriaProducto (nombre, descripcion) VALUES (?,?);";
        
        myStamt = myConn.prepareStatement(query);
        myStamt.setString(1, nombre);
        myStamt.setString(2, descripcion);
        
        response = myStamt.executeUpdate();
        System.out.println(response + "filas afectadas");
        myConn.close();
    }
    
    /* READ */
    public static ArrayList<CategoriaProducto> leerCategoriaProducto() throws SQLException {
        ConnectionSQLServer SQL = new ConnectionSQLServer();
        myConn = SQL.connectSQL();
        query = "SELECT * from CategoriaProducto;";
        
        myStamtQ = myConn.createStatement();
        
        ResultSet response = myStamtQ.executeQuery(query);
        System.out.println(response);
        
        ArrayList<CategoriaProducto> listaCategoriaProductos = new ArrayList<>();
        System.out.println(response);
        
        while (response.next()) {
            int id = response.getInt("categoria_id");
            String nombre = response.getString("nombre");
            String descripcion = response.getString("descripcion");
    
            listaCategoriaProductos.add(new CategoriaProducto(id, nombre, descripcion));
        }
        
        myConn.close();
        
        return listaCategoriaProductos;
    }
    
    /* UPDATE */
    public static void actualizarCategoriaProducto(int id, String nombre, String descripcion) throws SQLException {
        ConnectionSQLServer SQL = new ConnectionSQLServer();
        myConn = SQL.connectSQL();
        query = "UPDATE CategoriaProducto SET nombre = ?, descripcion = ? WHERE categoria_id = ?;";
        
        myStamt = myConn.prepareStatement(query);
        myStamt.setString(1, nombre);
        myStamt.setString(2, descripcion);
        myStamt.setInt(3, id);
        
        response = myStamt.executeUpdate();
        System.out.println(response + "filas afectadas");
        
        myConn.close();
    }
    
    /* DELETE */
    public static void eliminarCategoriaProducto(int id) throws SQLException {
        ConnectionSQLServer SQL = new ConnectionSQLServer();
        myConn = SQL.connectSQL();
        query = "DELETE FROM CategoriaProducto WHERE categoria_id = ?;";
        myStamt = myConn.prepareStatement(query);
        myStamt.setInt(1, id);
        
        int response = myStamt.executeUpdate();
        System.out.println(response + "filas afectadas");
        
        myConn.close();
    }
}
