
package br.fipp.DAO;

import br.fipp.entidade.Atividade;
import br.fipp.entidade.Funcionario;
import br.fipp.sql.Conexao;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class AtividadeDAO 
{
    public boolean insere (Atividade a)
    {
        String sql= "insert into atividade (ati_descricao, ati_dtinicio, ati_dtfim, fun_codigo, sta_codigo, sol_email)"
                + " values (?,?,?,?,?,?);";
      
        try(Connection conn = Conexao.abre()) {
            if(conn!=null){
                try(PreparedStatement ps = conn.prepareStatement(sql)){
                    ps.setString(1, a.getDescricao());
                    ps.setDate(2, a.getDtinicio());
                    ps.setDate(3, a.getDtfim());
                    ps.setInt(4, a.getFunCodigo());
                    ps.setInt(5, a.getStaCodigo());
                    ps.setString(6, a.getSolEmail());
                    ps.executeQuery();
                    return true;    
                }
            }  
        }
        catch(SQLException ex){
        }  
        return false;
    }
    public boolean altera(Atividade a)
    {
        String sql="update atividade set ati_descricao=?, ati_dtinicio=?, ati_dtfim=?, fun_codigo=?, "
                + "sta_codigo=?, sol_email=? where ati_codigo=?;";
        try(Connection conn = Conexao.abre()){
            if(conn!=null){
                try(PreparedStatement ps = conn.prepareStatement(sql)){
                    ps.setString(1, a.getDescricao());
                    ps.setDate(2, a.getDtinicio());
                    ps.setDate(3, a.getDtfim());
                    ps.setInt(4, a.getFunCodigo());
                    ps.setInt(5, a.getStaCodigo());
                    ps.setString(6, a.getSolEmail());
                    ps.setInt(7, a.getCodigo());
                    ps.executeUpdate();
                    return true;
                }
            }
        }
        catch(SQLException ex){
        }
        return false;
    }
      public boolean exclui(int a) {
        String sql = "delete from atividade where ati_codigo = ?;";
        try (Connection conn = Conexao.abre()) {
            if (conn != null) {
                try (PreparedStatement ps = conn.prepareStatement(sql)) {
                    ps.setInt(1, a);
                    ps.executeUpdate();
                    return true;
                }
            }
        } catch (SQLException ex) {
        }
        return false;
    }
       public Atividade busca (String login) 
    {
        String sql = "select ati_codigo, ati_descricao, ati_dtinicio, ati_dtfim, fun_codigo, sta_codigo, sol_email from atividade where ati_descricao = ?;";
        try (Connection conn = Conexao.abre()){
            if (conn!=null){
                try(PreparedStatement ps = conn.prepareStatement(sql)){
                   ps.setString(1, login);
                   try(ResultSet rs = ps.executeQuery()){
                       if(rs.next())
                       {
                           return new Atividade (
                                   rs.getInt("ati_codigo"),
                                   rs.getString("ati_descricao"),
                                   rs.getDate("ati_dtinicio"),
                                   rs.getDate("ati_dtfim"),
                                   rs.getInt("fun_codigo"),
                                   rs.getInt("sta_codigo"),
                                   rs.getString("sol_email")
                           );
                       }
                   }
                }
            }  
        }   
        catch(SQLException ex){
        }   
        return null;
        
    }
    public Atividade buscaCod (int login) 
    {
        String sql = "select ati_codigo, ati_descricao, ati_dtinicio, ati_dtfim, fun_codigo, sta_codigo, sol_email from atividade where ati_codigo = ?;";
        try (Connection conn = Conexao.abre()){
            if (conn!=null){
                try(PreparedStatement ps = conn.prepareStatement(sql)){
                   ps.setInt(1, login);
                   try(ResultSet rs = ps.executeQuery()){
                       if(rs.next())
                       {
                           return new Atividade (
                                   rs.getInt("ati_codigo"),
                                   rs.getString("ati_descricao"),
                                   rs.getDate("ati_dtinicio"),
                                   rs.getDate("ati_dtfim"),
                                   rs.getInt("fun_codigo"),
                                   rs.getInt("sta_codigo"),
                                   rs.getString("sol_email")
                           );
                       }
                   }
                }
            }  
        }   
        catch(SQLException ex){
        }   
        return null;
        
    }
        public List<Atividade> lista() {
        String sql = "select ati_codigo, ati_descricao, ati_dtinicio, ati_dtfim, fun_codigo, sta_codigo, sol_email from atividade order by ati_descricao;";
        List <Atividade> resp = new ArrayList();
        try (Connection conn = Conexao.abre()){
            if (conn!=null){
                try(Statement ps = conn.createStatement()){
                   try(ResultSet rs = ps.executeQuery(sql)){
                       while(rs.next())
                       {
                           resp.add( new Atividade (
                                   rs.getInt("ati_codigo"),
                                   rs.getString("ati_descricao"),
                                   rs.getDate("ati_dtinicio"),
                                   rs.getDate("ati_dtfim"),
                                   rs.getInt("fun_codigo"),
                                   rs.getInt("sta_codigo"),
                                   rs.getString("sol_email")
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
        
      public List<Atividade> listaEncerrados() {
        String sql = "select ati_codigo, ati_descricao, ati_dtinicio, ati_dtfim, fun_codigo, sta_codigo, sol_email from atividade order by ati_descricao;";
        Date dt=null;
        List <Atividade> resp = new ArrayList();
        try (Connection conn = Conexao.abre()){
            if (conn!=null){
                try(Statement ps = conn.createStatement()){
                   try(ResultSet rs = ps.executeQuery(sql)){
                       while(rs.next())
                       {
                           dt = rs.getDate("ati_dtfim");
                           if (dt!=null){
                           resp.add( new Atividade (
                                   rs.getInt("ati_codigo"),
                                   rs.getString("ati_descricao"),
                                   rs.getDate("ati_dtinicio"),
                                   rs.getDate("ati_dtfim"),
                                   rs.getInt("fun_codigo"),
                                   rs.getInt("sta_codigo"),
                                   rs.getString("sol_email")
                           ));
                           }
                       }
                   }
                }
            }  
        }   
        catch(SQLException ex){
        }   
        return resp;
    }
        
    public List<Atividade> listaFuncionario (int cod) 
    {
        String sql = "select ati_codigo, ati_descricao, ati_dtinicio, ati_dtfim, fun_codigo, sta_codigo, sol_email from atividade where fun_codigo = ?;";
        Date dt = null;
        List<Atividade>resp = new ArrayList();
        try (Connection conn = Conexao.abre()){
            if (conn!=null){
                try(PreparedStatement ps = conn.prepareStatement(sql)){
                   ps.setInt(1, cod);
                   try(ResultSet rs = ps.executeQuery()){
                       while(rs.next())
                       {
                           dt = rs.getDate("ati_dtfim");
                           if (dt==null){
                           resp.add(new Atividade (
                                   rs.getInt("ati_codigo"),
                                   rs.getString("ati_descricao"),
                                   rs.getDate("ati_dtinicio"),
                                   rs.getDate("ati_dtfim"),
                                   rs.getInt("fun_codigo"),
                                   rs.getInt("sta_codigo"),
                                   rs.getString("sol_email")
                           ));}
                       }
                   }
                }
            }  
        }   
        catch(SQLException ex){
        }   
        return resp;
        
    }
        
        
   public int UltimoCara() {
        String sql = "select ati_codigo from atividade";
        int i=0;
        try (Connection conn = Conexao.abre()){
            if (conn!=null){
                try(Statement ps = conn.createStatement()){
                   try(ResultSet rs = ps.executeQuery(sql)){
                       while(rs.next())
                       {
                           i = rs.getInt("ati_codigo");
                           
                       }
                   }
                }
            }  
        }   
        catch(SQLException ex){
        }   
        return i;
    }      
        
        
        
        
        
        
        
        
        
        
        
        
}
