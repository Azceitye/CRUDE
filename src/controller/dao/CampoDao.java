
package controller.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.bean.CampoText;
import model.bean.campoInfo;

public class CampoDao {
    private Connection conn;
    
    public CampoDao(Connection conn) {
        this.conn = conn;
    }
    
    public Long create(campoInfo campo) {
        String sql = "INSERT INTO `tb_campo`(`label_CAMPO`, `desc_CAMPO`, `tipo_CAMPO`, `isOpcional_CAMPO`, `prioridade_CAMPO`)" +
                     "VALUES (?, ?, ?, ?, ?)";
        Long id = null;
        
        try(PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, campo.getLabel());
            stmt.setString(2, campo.getDescricao());
            stmt.setString(3, campo.getTipo().toUpperCase());
            stmt.setString(4, campo.iseObrigatorio() ? "1" : "0");
            stmt.setInt(5, campo.getPrioridade());
            int row = stmt.executeUpdate();            
            
            if(row > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if(rs.next()) {
                    id = rs.getLong(1);
                }
            }
            
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(CampoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return id;
    }
    
    public boolean createForm(long cmpID, long userID, String input) {
        String sql = "INSERT INTO `tb_form`(`tb_CAMPO_ID_CAMPO`, `tb_USUARIO_ID_USUARIO`, `texto_FORM`) "
                    + "VALUES (?, ?, ?)";
        boolean result=false;
        
        try(PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, cmpID);
            stmt.setLong(2, userID);
            stmt.setString(3, input);
            result = stmt.executeUpdate() > 0;
            
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(CampoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return result;
    }
    
    
    public List<CampoText> buscar() {
        String sql = "SELECT `ID_CAMPO`, `label_CAMPO`, `desc_CAMPO`, `tipo_CAMPO`, `isOpcional_CAMPO`, `prioridade_CAMPO` FROM `tb_campo`";
        List<CampoText> cmps = new ArrayList<>();
        
        try(PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()) {
                campoInfo info = new campoInfo(
                    rs.getLong("ID_CAMPO"),
                    rs.getString("label_CAMPO"),
                    rs.getString("desc_CAMPO"),
                    rs.getString("tipo_CAMPO"),
                    rs.getInt("prioridade_CAMPO"),
                    rs.getString("isOpcional_CAMPO").equals("1")
                );
                    
                cmps.add( new CampoText(info) );
            }
        } catch (SQLException ex) {
            Logger.getLogger(CampoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return cmps;
    }
}
