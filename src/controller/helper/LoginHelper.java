
package controller.helper;

import controller.dao.UsuarioDao;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.bean.Usuario;
import model.conn.ConnectionFactory;
import view.login;

public class LoginHelper {
    
    private final login view;
    
    
    public LoginHelper(login view) {
        this.view = view;
    }
    
    public void logar() {
        String apelido = view.getjFieldApelido().getText().toUpperCase();
        String senha = view.getjFieldSenha().getText();
        Connection conn = null;
        
        try {
            conn = new ConnectionFactory().getConnection();
            UsuarioDao dao = new UsuarioDao( conn );
            
            Usuario usuario = dao.read(apelido, senha);
        } catch(SQLException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }   
        
        try {
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(LoginHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
