
package br.fipp.DAO;

import br.fipp.entidade.Status;
import br.fipp.sql.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StatusDAO {
    
    // Table status | sta_codigo | sta_status | sta_ativo
    public List<Status> lista() {
         String sql="select sta_codigo, sta_status, sta_ativo from status order by sta_status;";
        List <Status> resp = new ArrayList();
        try (Connection conn = Conexao.abre()){
            if (conn!=null){
                try(Statement ps = conn.createStatement()){
                   try(ResultSet rs = ps.executeQuery(sql)){
                       while(rs.next())
                       {
                           resp.add( new Status (
                                   rs.getInt("sta_codigo"),
                                   rs.getString("sta_status"),
                                   rs.getString("sta_ativo")
                                   
                           ));
                       }
                   }
                }
            }  
        }   
        catch(SQLException ex){
        }   
        return resp;
    }
    
    
       public StatusDAO(){}
       public Status busca(int Cod){
        
        String sql="select sta_codigo, sta_status, sta_ativo from status where sta_codigo=?;";
        
        try(Connection conn = Conexao.abre()){
            if (conn!=null){
                try (PreparedStatement ps = conn.prepareStatement(sql)){
                    ps.setInt(1, Cod);
                    try (ResultSet rs = ps.executeQuery()){
                        if (rs.next()){
                            return new Status (
                                    rs.getInt("sta_codigo"),
                                    rs.getString("sta_status"),
                                    rs.getString("sta_ativo")
                            );
                        }                  
                    }
                }
            }
        }catch(SQLException ex){
        }
        
        return null;
    }
       
    public boolean exclui(int f) {
        String sql = "delete from status where sta_codigo = ?;";
        try (Connection conn = Conexao.abre()) {
            if (conn != null) {
                try (PreparedStatement ps = conn.prepareStatement(sql)) {
                    ps.setInt(1, f);
                    ps.executeUpdate();
                    return true;
                }
            }
        } catch (SQLException ex) {
        }
        return false;
    }
    
    public boolean altera (Status s){
    
         String sql="update status set sta_status=?, sta_ativo=? where sta_codigo=?";
        try(Connection conn = Conexao.abre()){
            if (conn!=null){
                try(PreparedStatement ps = conn.prepareStatement(sql)){
                    ps.setString(1, s.getStatus());
                    ps.setString(2, s.getAtivo());
                    ps.setInt(3, s.getCodigo());
                    ps.executeUpdate();
                    return true;
                }
            }
        }
        catch(SQLException ex){
        
        }
        return false;
    }
    
    
     public boolean insere (Status s){
    
        String sql="insert into status (sta_status,sta_ativo) values (?,?);";
  
        try(Connection conn = Conexao.abre()){
            if (conn!=null){
                try(PreparedStatement ps = conn.prepareStatement(sql)){
                    ps.setString(1,s.getStatus() );
                    ps.setString(2,s.getAtivo());
                    ps.executeQuery();
                    return true;
                }
            }
        }
        catch(SQLException ex){
        }
        return false;
    }
    
}
