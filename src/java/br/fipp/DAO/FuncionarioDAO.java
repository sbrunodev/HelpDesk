package br.fipp.DAO;

import br.fipp.entidade.Funcionario;
import br.fipp.sql.Conexao;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Bruno
 */
public class FuncionarioDAO {

    
    public FuncionarioDAO(){
    }
    
    public boolean insere (Funcionario f)
    {
        String sql= "insert into funcionario (fun_nome, fun_dtcontratacao, fun_dtdemissao, fun_ativo, fun_senha, fun_tipo)"
                + " values (?,?,?,?,?,?);";
        try(Connection conn = Conexao.abre()) {
            if(conn!=null){
                try(PreparedStatement ps = conn.prepareStatement(sql)){
                    ps.setString(1, f.getNome());
                    ps.setDate(2, f.getDtcontratacao());
                    ps.setDate(3, f.getDtdemissao());
                    ps.setString(4, f.getAtivo());
                    ps.setString(5, f.getSenha());
                    ps.setString(6, f.getTipo());
                    ps.executeQuery();
                    return true;    
                }
            }  
        }
        catch(SQLException ex){
        }  
        return false;
    }

     
    public boolean altera(Funcionario f)
    {
        String sql="update funcionario set fun_nome=?, fun_dtcontratacao=?, fun_dtdemissao=?, fun_ativo=?, "
                + "fun_senha=?, fun_tipo=? where fun_codigo=?;";
        try(Connection conn = Conexao.abre()){
            if(conn!=null){
                try(PreparedStatement ps = conn.prepareStatement(sql)){
                    ps.setString(1, f.getNome());
                    ps.setDate(2, f.getDtcontratacao());
                    ps.setDate(3, f.getDtdemissao());
                    ps.setString(4, f.getAtivo());
                    ps.setString(5, f.getSenha());
                    ps.setString(6, f.getTipo());
                    ps.setInt(7, f.getCodigo());
                    ps.executeUpdate();
                    return true;
                }
            }
        }
        catch(SQLException ex){
        }
        return false;
    }
    
    public boolean exclui(int f) {
        String sql = "delete from funcionario where fun_codigo = ?;";
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
    
    public Funcionario busca (String login) 
    {
        String sql = "select fun_codigo, fun_nome, fun_dtcontratacao, fun_dtdemissao, fun_ativo, fun_senha, fun_tipo from funcionario where fun_nome = ?;";
        try (Connection conn = Conexao.abre()){
            if (conn!=null){
                try(PreparedStatement ps = conn.prepareStatement(sql)){
                   ps.setString(1, login);
                   try(ResultSet rs = ps.executeQuery()){
                       if(rs.next())
                       {
                           return new Funcionario (
                                   rs.getInt("fun_codigo"),
                                   rs.getString("fun_nome"),
                                   rs.getDate("fun_dtcontratacao"),
                                   rs.getDate("fun_dtdemissao"),
                                   rs.getString("fun_ativo"),
                                   rs.getString("fun_senha"),
                                   rs.getString("fun_tipo")
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
    
     public Funcionario buscaCod (int login) 
    {
        String sql = "select fun_codigo, fun_nome, fun_dtcontratacao, fun_dtdemissao, fun_ativo, fun_senha, fun_tipo from funcionario where fun_codigo = ?;";
        try (Connection conn = Conexao.abre()){
            if (conn!=null){
                try(PreparedStatement ps = conn.prepareStatement(sql)){
                   ps.setInt(1, login);
                   try(ResultSet rs = ps.executeQuery()){
                       if(rs.next())
                       {
                           return new Funcionario (
                                   rs.getInt("fun_codigo"),
                                   rs.getString("fun_nome"),
                                   rs.getDate("fun_dtcontratacao"),
                                   rs.getDate("fun_dtdemissao"),
                                   rs.getString("fun_ativo"),
                                   rs.getString("fun_senha"),
                                   rs.getString("fun_tipo")
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

    public List<Funcionario> lista() {
        String sql = "select fun_codigo, fun_nome, fun_dtcontratacao, fun_dtdemissao, fun_ativo, fun_senha, fun_tipo from funcionario order by fun_nome;";
        List <Funcionario> resp = new ArrayList();
        try (Connection conn = Conexao.abre()){
            if (conn!=null){
                try(Statement ps = conn.createStatement()){
                   try(ResultSet rs = ps.executeQuery(sql)){
                       while(rs.next())
                       {
                           resp.add( new Funcionario (
                                   rs.getInt("fun_codigo"),
                                   rs.getString("fun_nome"),
                                   rs.getDate("fun_dtcontratacao"),
                                   rs.getDate("fun_dtdemissao"),
                                   rs.getString("fun_ativo"),
                                   rs.getString("fun_senha"),
                                   rs.getString("fun_tipo")
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
