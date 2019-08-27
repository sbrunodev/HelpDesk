package br.fipp.DAO;

import br.fipp.entidade.Classificacao;
import br.fipp.entidade.Funcionario;
import br.fipp.sql.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ClassificacaoDAO {

    public boolean insere (Classificacao c){
    
        String sql="insert into classificacao (cla_nome,cla_ativa) values (?,?);";
  
        try(Connection conn = Conexao.abre()){
            if (conn!=null){
                try(PreparedStatement ps = conn.prepareStatement(sql)){
                    ps.setString(1,c.getNome() );
                    ps.setString(2,c.getAtiva());
                    ps.executeQuery();
                    return true;
                }
            }
        }
        catch(SQLException ex){
        }
        return false;
    }
    
     public List<Classificacao> lista() {
         String sql="select cla_codigo, cla_nome, cla_ativa from classificacao order by cla_nome;";
       
        List <Classificacao> resp = new ArrayList();
        try (Connection conn = Conexao.abre()){
            if (conn!=null){
                try(Statement ps = conn.createStatement()){
                   try(ResultSet rs = ps.executeQuery(sql)){
                       while(rs.next())
                       {
                           resp.add( new Classificacao (
                                   rs.getInt("cla_codigo"),
                                   rs.getString("cla_nome"),
                                   rs.getString("cla_ativa")
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
     
      public List<Classificacao> listaAtivos() {
        String sql="select cla_codigo, cla_nome, cla_ativa from classificacao order by cla_nome;";
        String Aux="";
        List <Classificacao> resp = new ArrayList();
        try (Connection conn = Conexao.abre()){
            if (conn!=null){
                try(Statement ps = conn.createStatement()){
                   try(ResultSet rs = ps.executeQuery(sql)){
                       while(rs.next())
                       {
                           Aux=rs.getString("cla_ativa");
                           if (Aux.equalsIgnoreCase("S"))
                           {

                                resp.add( new Classificacao (
                                        rs.getInt("cla_codigo"),
                                        rs.getString("cla_nome"),
                                        rs.getString("cla_ativa")
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
    
     
     
    public boolean altera (Classificacao c){
    
         String sql="update classificacao set cla_nome=?, cla_ativa=? where cla_codigo=?;";
        try(Connection conn = Conexao.abre()){
            if (conn!=null){
                try(PreparedStatement ps = conn.prepareStatement(sql)){
                    ps.setString(1, c.getNome());
                    ps.setString(2, c.getAtiva());
                    ps.setInt(3, c.getCodigo());
                    ps.executeUpdate();
                    return true;
                }
            }
        }
        catch(SQLException ex){
        
        }
        return false;
    }
    
       
    public boolean exclui (int c){
    
        String sql="delete from classificacao where cla_codigo=?;";
        try(Connection conn = Conexao.abre()){
            if (conn!=null){
                try(PreparedStatement ps = conn.prepareStatement(sql)){
                    ps.setInt(1,c);
                    ps.executeUpdate();
                }
            }
        }
        catch(SQLException ex){
        
        }
        return false;
    }
    
    public static Classificacao busca(int Cod){
        
        String sql="select cla_codigo, cla_nome, cla_ativa from classificacao where cla_codigo=?;";
        
        try(Connection conn = Conexao.abre()){
            if (conn!=null){
                try (PreparedStatement ps = conn.prepareStatement(sql)){
                    ps.setInt(1, Cod);
                    try (ResultSet rs = ps.executeQuery()){
                        if (rs.next()){
                            return new Classificacao (
                                    rs.getInt("cla_codigo"),
                                    rs.getString("cla_nome"),
                                    rs.getString("cla_ativa")
                            );
                        
                        }
                    
                    }
                }
            
            }
        
        }catch(SQLException ex){
        }
        
        return null;
    }
    
}
