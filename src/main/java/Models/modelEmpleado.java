package Models;

import Controllers.ConnectionSQLServer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Joao
 */
public class modelEmpleado {
    private Connection myConn = null;
    private PreparedStatement myStamt = null;
    private Statement myStamtQ = null;
    public String query;
    
    /* INSERT */
    public void crearEmpleado(String nombre, String descripcion, float precio, int stock, int categoria) throws SQLException {
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
    public void leerEmpleado() throws SQLException {
        ConnectionSQLServer SQL = new ConnectionSQLServer();
        myConn = SQL.connectSQL();
        query = "SELECT * from Producto;";
        
        myStamtQ = myConn.createStatement();
        
        ResultSet response = myStamtQ.executeQuery(query);
        System.out.println(response);
        
        myConn.close();
    }
    
    /* UPDATE */
    public void actualizarEmpleado() throws SQLException {
        ConnectionSQLServer SQL = new ConnectionSQLServer();
        myConn = SQL.connectSQL();
        query = "UPDATE Producto set nombre=? WHERE id=?;";
        
        myConn.close();
    }
    
    /* DELETE */
    public void eliminarEmpleado(int id) throws SQLException {
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
