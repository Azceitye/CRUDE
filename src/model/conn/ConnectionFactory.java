
package model.conn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    private final String dbNome = "dbcrude";
    private final String dbUser = "root";
    private final String dbSenha = "";
    
    public Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch(ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        
        String url = "jdbc:mysql://localhost/" + dbNome;
        return DriverManager.getConnection(url, dbUser, dbSenha);
    }
}