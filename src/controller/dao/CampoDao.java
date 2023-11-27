
package controller.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.CallableStatement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.bean.CampoText;
import model.bean.CampoInfo;
import view.Formulario;

public class CampoDao {
    private Connection conn;
    
    public CampoDao(Connection conn) {
        this.conn = conn;
    }
    
    public Long create(CampoInfo campo) {
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
    
    public int createForm(long cmpID, long userID, String input) {
        String sql = "CALL `create_form`(?,?,?,?)";
        int result=0;
        
        try(CallableStatement stmt = conn.prepareCall(sql)) {
            stmt.setLong(1, cmpID);
            stmt.setLong(2, userID);
            stmt.setString(3, input);
            stmt.registerOutParameter(4, Types.INTEGER);
            stmt.execute();
            
            result = stmt.getInt(4);
            
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(CampoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return result;
    }
    
    
    public List<CampoText> buscar(long userID, Formulario form) {
        String sql = "SELECT `ID_CAMPO`, `label_CAMPO`, `desc_CAMPO`, `tipo_CAMPO`, `isOpcional_CAMPO`, `prioridade_CAMPO`, `texto_FORM` FROM `tb_campo` LEFT JOIN `tb_form` ON `ID_CAMPO` = `tb_CAMPO_ID_CAMPO` AND `tb_USUARIO_ID_USUARIO` = ?";
        List<CampoText> cmps = new ArrayList<>();
        
        try(PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, userID);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()) {
                CampoInfo info = new CampoInfo(
                    rs.getLong("ID_CAMPO"),
                    rs.getString("label_CAMPO"),
                    rs.getString("desc_CAMPO"),
                    rs.getString("tipo_CAMPO"),
                    rs.getInt("prioridade_CAMPO"),
                    rs.getString("isOpcional_CAMPO").equals("1")
                );
                
                CampoText cmpText = new CampoText(info, form);
                String lastInput = rs.getString("texto_FORM");
                cmpText.setTextoInput(lastInput);
                cmps.add(cmpText);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CampoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return cmps;
    }
    
    
    public void apagar(long id) {
        String sql = "DELETE FROM `tb_campo` WHERE `ID_CAMPO` = ?";
        
        try(PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.execute();
            
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(CampoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void atualizar(CampoInfo info) {
        String sql = "UPDATE `tb_campo` SET `label_CAMPO` = ?, `desc_CAMPO` = ?, `tipo_CAMPO` = ?, `isOpcional_CAMPO` = ?, `prioridade_CAMPO` = ? WHERE `ID_CAMPO` = ?";
        
        try(PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, info.getLabel());
            stmt.setString(2, info.getDescricao());
            stmt.setString(3, info.getTipo().toUpperCase());
            stmt.setString(4, info.iseObrigatorio() ? "1" : "0");
            stmt.setInt(5, info.getPrioridade());
            stmt.setLong(6, info.getID());
            
            stmt.execute();
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(CampoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
