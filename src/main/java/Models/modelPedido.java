package Models;

import Components.Pedido;
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
public class modelPedido {
    private static Connection myConn = null;
    private static PreparedStatement myStamt = null;
    private static Statement myStamtQ = null;
    public static String query;
    
    /* INSERT */
    public static void crearPedido(int cliente, int empleado) throws SQLException {
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
    public static ArrayList<Pedido> leerPedido() throws SQLException {
        ConnectionSQLServer SQL = new ConnectionSQLServer();
        myConn = SQL.connectSQL();
        query = "SELECT pedido_id, Pedido.cliente_id, Pedido.empleado_id, Cliente.nombre, Empleado.nombre, fecha_pedido, estado from Pedido "
                + "join Empleado on Pedido.empleado_id = Empleado.empleado_id "
                + "join Cliente on Pedido.cliente_id = Cliente.cliente_id;";
        
        myStamtQ = myConn.createStatement();
        
        ResultSet response = myStamtQ.executeQuery(query);
        ArrayList<Pedido> listaPedidos = new ArrayList<>();
        System.out.println(response);
        
        while (response.next()) {
            int id = response.getInt("pedido_id");
            int idCliente = response.getInt("cliente_id");
            int idEmpleado = response.getInt("empleado_id");
            String nombreCliente = response.getString("nombre");
            String nombreEmpleado = response.getString("nombre");
            String fechaPedido = response.getString("fecha_pedido");
            String estadoPedido = response.getString("estado");
            
            listaPedidos.add(new Pedido(id, idEmpleado, idCliente, nombreEmpleado, nombreCliente, fechaPedido, estadoPedido));
        }
        myConn.close();
        
        return listaPedidos;
    }
    
    /* UPDATE */
    public static void actualizarPedido(int id) throws SQLException {
        ConnectionSQLServer SQL = new ConnectionSQLServer();
        myConn = SQL.connectSQL();
        query = "UPDATE Pedido SET estado = 'Atendido' WHERE pedido_id = ?;";
        
        myStamt = myConn.prepareStatement(query);
        myStamt.setInt(1, id);
        
        int response = myStamt.executeUpdate();
        System.out.println(response + "filas afectadas");
        
        myConn.close();
    }
    
    /* DELETE */
    public static void eliminarPedido(int id) throws SQLException {
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
