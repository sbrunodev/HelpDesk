package br.fipp.DAO;


import br.fipp.entidade.Solicitante;
import br.fipp.sql.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SolicitanteDAO {

    // Table solicitante | sol_email | sol_nome | sol_telefone | sol_observacao 
      public boolean insere (Solicitante s){
        String sql="insert into solicitante (sol_email,sol_nome,sol_telefone,sol_observacao) values (?,?,?,?);";
  
        try(Connection conn = Conexao.abre()){
            if (conn!=null){
                try(PreparedStatement ps = conn.prepareStatement(sql)){
                    ps.setString(1,s.getEmail());
                    ps.setString(2,s.getNome());
                    ps.setString(3,s.getTelefone());
                    ps.setString(4,s.getObservacao());
                    ps.executeQuery();
                    return true;
                }
            }
        }
        catch(SQLException ex){
        }
        return false;
    }
      
    // Table solicitante | sol_email | sol_nome | sol_telefone | sol_observacao 
     public boolean altera (Solicitante s){
    
         String sql="update solicitante set sol_nome=?, sol_telefone, sol_observacao where sol_email=?;";
        try(Connection conn = Conexao.abre()){
            if (conn!=null){
                try(PreparedStatement ps = conn.prepareStatement(sql)){
                    ps.setString(1, s.getNome());
                    ps.setString(2, s.getTelefone());
                    ps.setString(3, s.getObservacao());
                    ps.setString(4, s.getEmail());
                    ps.executeUpdate();
                    return true;
                }
            }
        }
        catch(SQLException ex){
        
        }
        return false;
    } 
     
    public boolean exclui (Solicitante s){
    
        String sql="delete from solicitante where sol_email=?;";
        try(Connection conn = Conexao.abre()){
            if (conn!=null){
                try(PreparedStatement ps = conn.prepareStatement(sql)){
                    ps.setString(1,s.getEmail());
                    ps.executeUpdate();
                }
            }
        }
        catch(SQLException ex){
        
        }
        return false;
    }
    
    
    public Solicitante busca(String email){
        
        String sql="select sol_email, sol_nome, sol_telefone, sol_observacao from solicitante where sol_email=?;";
        
        try(Connection conn = Conexao.abre()){
            if (conn!=null){
                try (PreparedStatement ps = conn.prepareStatement(sql)){
                    ps.setString(1, email);
                    try (ResultSet rs = ps.executeQuery()){
                        if (rs.next()){
                            return new Solicitante (
                                   rs.getString("sol_email"),
                                   rs.getString("sol_nome"),
                                   rs.getString("sol_telefone"),
                                   rs.getString("sol_observacao"));
                        
                        }
                    
                    }
                }
            
            }
        
        }catch(SQLException ex){
        }
        
        return null;
    }
    public List<Solicitante> lista() {
        String sql = "select sol_email, sol_nome, sol_telefone, sol_observacao from solicitante order by sol_email;";
        List <Solicitante> resp = new ArrayList();
        try (Connection conn = Conexao.abre()){
            if (conn!=null){
                try(Statement ps = conn.createStatement()){
                   try(ResultSet rs = ps.executeQuery(sql)){
                       while(rs.next())
                       {
                           resp.add( new Solicitante (
                                   rs.getString("sol_email"),
                                   rs.getString("sol_nome"),
                                   rs.getString("sol_telefone"),
                                   rs.getString("sol_observacao")
                                   
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
    
    
    
}
