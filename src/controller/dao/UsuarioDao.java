
package controller.dao;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.bean.Usuario;

public class UsuarioDao {
    private final Connection connection;
    
    
    public UsuarioDao(Connection connection) {
        this.connection = connection;
    }
    
    public boolean create(String apelido, String senha) {
        String sql = "INSERT INTO `tb_usuario`(`apelido_USUARIO`, `senha_USUARIO`) VALUES (?,?)";
        boolean result=false;
        
        try (PreparedStatement stmt = this.connection.prepareStatement(sql)) {
            stmt.setString(1, apelido.toUpperCase());
            stmt.setString(2, senha);
            
            result = stmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return result;
    }
    
    // Para o LOGIN
    public Usuario read(String apelido, String senha) throws SQLException {
        Usuario usuario = null;
        String sql = "SELECT * FROM `tb_usuario` WHERE `apelido_USUARIO` = ? AND `senha_USUARIO` = ?";
        
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
