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
public class modelDetallePedido {
    private Connection myConn = null;
    private PreparedStatement myStamt = null;
    private Statement myStamtQ = null;
    public String query;
    
    /* INSERT */
    public void crearDetallePedido(int pedido, int producto, int cantidad) throws SQLException {
        ConnectionSQLServer SQL = new ConnectionSQLServer();
        myConn = SQL.connectSQL();
        query = "INSERT INTO DetallePedido (pedido_id, producto_id, cantidad) VALUES (?, ?, ?);";
        
        myStamt = myConn.prepareStatement(query);
        myStamt.setInt(1, pedido);
        myStamt.setInt(2, producto);
        myStamt.setInt(3, cantidad);
        
        int response = myStamt.executeUpdate();
        System.out.println(response + "filas afectadas");
        myConn.close();
    }
    
    /* READ */
    public void leerDetallePedido() throws SQLException {
        ConnectionSQLServer SQL = new ConnectionSQLServer();
        myConn = SQL.connectSQL();
        query = "SELECT * from DetallePedido;";
        
        myStamtQ = myConn.createStatement();
        
        ResultSet response = myStamtQ.executeQuery(query);
        System.out.println(response);
        
        myConn.close();
    }
    
    /* UPDATE */
    public void actualizarDetallePedido(int id, int pedido, int producto, int cantidad) throws SQLException {
        ConnectionSQLServer SQL = new ConnectionSQLServer();
        myConn = SQL.connectSQL();
        query = "UPDATE DetallePedido SET pedido_id = ?, producto_id = ?, cantidad = ? WHERE detalle_pedido_id = ?;";
        
        myStamt = myConn.prepareStatement(query);
        myStamt.setInt(1, pedido);
        myStamt.setInt(2, producto);
        myStamt.setInt(3, cantidad);
        myStamt.setInt(4, id);
        
        int response = myStamt.executeUpdate();
        System.out.println(response + "filas afectadas");
        
        myConn.close();
    }
    
    /* DELETE */
    public void eliminarDetallePedido(int id) throws SQLException {
        ConnectionSQLServer SQL = new ConnectionSQLServer();
        myConn = SQL.connectSQL();
        query = "DELETE FROM DetallePedido WHERE detalle_pedido_id = ?;";
        
        myStamt = myConn.prepareStatement(query);
        myStamt.setInt(1, id);
        
        int response = myStamt.executeUpdate();
        System.out.println(response + "filas afectadas");
        
        myConn.close();
    }  
}
