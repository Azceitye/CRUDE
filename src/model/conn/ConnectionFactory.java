
package model.conn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionFactory {
    private final String dbNome = "dbform";
    private final String dbUser = "root";
    private final String dbSenha = "";
    
    public Connection getConnection() {
        String url = "jdbc:mysql://localhost/" + dbNome;
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
        
            return DriverManager.getConnection(url, dbUser, dbSenha);
        } catch(ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
}