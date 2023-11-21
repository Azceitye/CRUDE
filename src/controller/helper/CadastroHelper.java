/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.helper;

import view.cadastro;
import controller.dao.UsuarioDao;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.conn.ConnectionFactory;
        

public class CadastroHelper {
    
    private final cadastro view;
    
    public CadastroHelper(cadastro view) {
        this.view = view;
    }
    
    public void cadastrar() {
        final String apelido = view.getjFieldApelido().getText().toUpperCase();
        final String senha = view.getjFieldSenha().getText();
        
        if( apelido.isEmpty() || senha.isEmpty() || apelido == null || senha == null)
            return;
        
        try {
            Connection conn = new ConnectionFactory().getConnection();
            UsuarioDao dao = new UsuarioDao(conn);
            
            dao.create(apelido, senha);
        } catch(SQLException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
    }
}
