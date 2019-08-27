
package br.fipp.DAO;

import br.fipp.entidade.AtividadeClassificacao;
import br.fipp.sql.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class AtividadeClassificacaoDAO 
{
    
     public boolean insere (AtividadeClassificacao ac){
        
        String sql="insert into atividadeclassificacao (ati_codigo,cla_codigo) values (?,?);";
  
        try(Connection conn = Conexao.abre()){
            if (conn!=null){
                try(PreparedStatement ps = conn.prepareStatement(sql)){
                    ps.setInt(1,ac.getAtiCodigo());
                    ps.setInt(2,ac.getClaCodigo());
                    ps.executeQuery();
                    return true;
                }
            }
        }
        catch(SQLException ex){
        }
        return false;
    }
        public boolean exclui (int s){
    
        String sql="delete from atividadeclassificacao where ati_codigo=?;";
        try(Connection conn = Conexao.abre()){
            if (conn!=null){
                try(PreparedStatement ps = conn.prepareStatement(sql)){
                    ps.setInt(1,s);
                    ps.executeUpdate();
                    return true;
                }
            }
        }
        catch(SQLException ex){
        
        }
        return false;
    }
    
    
    
}
