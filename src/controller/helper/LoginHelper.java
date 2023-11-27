
package controller.helper;

import controller.dao.UsuarioDao;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.bean.Usuario;
import model.conn.ConnectionFactory;
import view.Formulario;
import view.Login;

public class LoginHelper {
    
    private final Login view;
    
    
    public LoginHelper(Login view) {
        this.view = view;
    }
    
    public void logar() {
        String apelido = view.getjFieldApelido().getText().toUpperCase();
        String senha = view.getjFieldSenha().getText();
        Connection conn = null;
        Usuario usuario = null;
        
        try {
            conn = new ConnectionFactory().getConnection();
            UsuarioDao dao = new UsuarioDao( conn );
            
            usuario = dao.read(apelido, senha);
            conn.close();
        } catch(SQLException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
        
        if(usuario != null) {
            Formulario form = new Formulario(usuario.getID());
            form.setVisible(true);
            view.dispose();
        }
    }
}
