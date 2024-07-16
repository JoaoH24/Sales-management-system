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
public class modelPedido {
    private Connection myConn = null;
    private PreparedStatement myStamt = null;
    private Statement myStamtQ = null;
    public String query;
    
    /* INSERT */
    public void crearPedido(int cliente, int empleado) throws SQLException {
        ConnectionSQLServer SQL = new ConnectionSQLServer();
        myConn = SQL.connectSQL();
        query = "INSERT INTO Pedido (cliente_id, empleado_id, fecha_pedido, estado) VALUES (?, ?, GETDATE(), 'Pendiente');";
        
        myStamt = myConn.prepareStatement(query);
        myStamt.setInt(1, cliente);
        myStamt.setInt(2, empleado);
        
        int response = myStamt.executeUpdate();
        System.out.println(response + "filas afectadas");
        myConn.close();
    }
    
    /* READ */
    public void leerPedido() throws SQLException {
        ConnectionSQLServer SQL = new ConnectionSQLServer();
        myConn = SQL.connectSQL();
        query = "SELECT * from Pedido;";
        
        myStamtQ = myConn.createStatement();
        
        ResultSet response = myStamtQ.executeQuery(query);
        System.out.println(response);
        
        myConn.close();
    }
    
    /* UPDATE */
    public void actualizarPedido(int id, String estado) throws SQLException {
        ConnectionSQLServer SQL = new ConnectionSQLServer();
        myConn = SQL.connectSQL();
        query = "UPDATE Pedido SET estado = ? WHERE pedido_id = ?;";
        
        myStamt = myConn.prepareStatement(query);
        myStamt.setString(1, estado);
        myStamt.setInt(2, id);
        
        int response = myStamt.executeUpdate();
        System.out.println(response + "filas afectadas");
        
        myConn.close();
    }
    
    /* DELETE */
    public void eliminarPedido(int id) throws SQLException {
        ConnectionSQLServer SQL = new ConnectionSQLServer();
        myConn = SQL.connectSQL();
        query = "DELETE FROM Pedido WHERE pedido_id = ?;";
        
        myStamt = myConn.prepareStatement(query);
        myStamt.setInt(1, id);
        
        int response = myStamt.executeUpdate();
        System.out.println(response + "filas afectadas");
        
        myConn.close();
    }  
}
