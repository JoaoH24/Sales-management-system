package Controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Joao
 */
public class ConnectionSQLServer {

    public String nameDatabase = "Gestion_ventas";
    public String hostname = "localhost";
    public String port = "1433";
    public String username = "admin";
    public String password = "joa24sil";
    
    public String url = "jdbc:sqlserver://" + hostname + ":" + port + ";" + "databaseName="+ nameDatabase +";encrypt=true;trustServerCertificate=true;";
    
    public Connection connectSQL() throws SQLException {
        Connection conn = null;
        conn = DriverManager.getConnection(url,username, password);
        return conn;
    }      
}
