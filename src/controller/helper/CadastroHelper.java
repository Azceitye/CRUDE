/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.helper;

import view.Cadastro;
import controller.dao.UsuarioDao;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.conn.ConnectionFactory;
import view.Formulario;
        

public class CadastroHelper {
    
    private final Cadastro view;
    
    public CadastroHelper(Cadastro view) {
        this.view = view;
    }
    
    public void cadastrar() {
        final String apelido = view.getjFieldApelido().getText().toUpperCase();
        final String senha = view.getjFieldSenha().getText();
        Long ID=null;
        if( apelido.isEmpty() || senha.isEmpty() || apelido == null || senha == null)
            return;
        
        try (Connection conn = new ConnectionFactory().getConnection()){
            UsuarioDao dao = new UsuarioDao(conn);
            ID = dao.create(apelido, senha);
            
            conn.close();
        } catch(SQLException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
        
        if(ID != null) {
            Formulario form = new Formulario(ID);
            form.setVisible(true);
            view.dispose();
        }
    }
}
