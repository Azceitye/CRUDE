
package controller.dao;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.bean.Usuario;

public class UsuarioDao {
    private final String tbNome = "tb_usuario";
    private final Connection connection;
    
    
    public UsuarioDao(Connection connection) {
        this.connection = connection;
    }
    
    public void create(String apelido, String senha) {
        String sql = "INSERT INTO `" + tbNome + "`(`apelido_USUARIO`, `senha_USUARIO`) VALUES (?,?)";
        
        try (PreparedStatement stmt = this.connection.prepareStatement(sql)) {
            stmt.setString(1, apelido.toUpperCase());
            stmt.setString(2, senha);
            
            stmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    // Para o LOGIN
    public Usuario read(String apelido, String senha) {
        Usuario usuario = null;
        String sql = "SELECT * FROM `" + tbNome + "` WHERE `apelido_USUARIO` = ? AND `senha_USUARIO` = ?";
        
        try (PreparedStatement stmt = this.connection.prepareStatement(sql)) {
            stmt.setString(1, apelido);
            stmt.setString(2, senha);
            ResultSet rs = stmt.executeQuery();
            
            if(rs.first()) {
                usuario = new Usuario(
                        rs.getInt("ID_USUARIO"),
                        rs.getString("apelido_USUARIO")
                );
            } 
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return usuario;
    }
    
    public boolean update(int ID, Usuario usuario) {
        return true;
    }
    
    public boolean delete(int ID) {
        return true;
    }
}
