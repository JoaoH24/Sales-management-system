package Views;

import Models.modelCategoriaProducto;
import Models.modelCliente;
import Models.modelPedido;
import Models.modelProducto;
import java.sql.SQLException;

/**
 *
 * @author Joao
 */
public class maintest {
    public static void main(String[] args) {
        try {
            modelPedido model = new modelPedido();
            
            // Test de creación
            System.out.println("Probando creación de categoría...");
            model.crearPedido(1,1);
            System.out.println("Creación completada.");
            
            // Test de lectura
            //System.out.println("Probando lectura de categorías...");
            //model.leerPedido();
            //System.out.println("Lectura completada.");
            
            // Test de actualización
            //System.out.println("Probando actualización de categoría...");
            //model.actualizarPedido(2, "Entregado");
            //System.out.println("Actualización completada.");
            
            //Test de eliminación
            //System.out.println("Probando eliminación de categoría...");
            //model.eliminarPedido(2); // Suponiendo que el id de la categoría es 1
            //System.out.println("Eliminación completada.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}
