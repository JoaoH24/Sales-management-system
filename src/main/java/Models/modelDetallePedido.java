package Models;

import Components.DetallePedido;
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
public class modelDetallePedido {
    private static Connection myConn = null;
    private static PreparedStatement myStamt = null;
    private static Statement myStamtQ = null;
    public static String query;
    
    /* INSERT */
    public static void crearDetallePedido(int pedido, int producto, int cantidad) throws SQLException {
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
    public static ArrayList<DetallePedido> leerDetallePedido(int idPedido) throws SQLException {
        ConnectionSQLServer SQL = new ConnectionSQLServer();
        myConn = SQL.connectSQL();
        query = "SELECT detalle_pedido_id, DetallePedido.pedido_id, DetallePedido.producto_id, Producto.nombre, Producto.precio, cantidad from DetallePedido "
                + "join Producto on DetallePedido.producto_id = Producto.producto_id "
                + "join Pedido on Pedido.pedido_id = DetallePedido.pedido_id "
                + "WHERE Pedido.pedido_id = ?;";
        
        myStamt = myConn.prepareStatement(query);
        myStamt.setInt(1, idPedido);
        
        ResultSet response = myStamt.executeQuery();
        ArrayList<DetallePedido> listaProductos = new ArrayList<>();
        System.out.println(response);
        
        while (response.next()) {
            int id = response.getInt("detalle_pedido_id");
            int idPedidoProducto = response.getInt("pedido_id");
            int idProducto = response.getInt("producto_id");
            String nombreProducto = response.getString("nombre");
            float precioProducto = response.getFloat("precio");
            int cantidadProducto = response.getInt("cantidad");
            
            listaProductos.add(new DetallePedido(id, idPedidoProducto, idProducto, nombreProducto, precioProducto, cantidadProducto));
        }
        myConn.close();
        
        return listaProductos;

    }
    
    /* UPDATE */
    public static void actualizarDetallePedido(int id, int pedido, int producto, int cantidad) throws SQLException {
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
    public static void eliminarDetallePedido(int id) throws SQLException {
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
