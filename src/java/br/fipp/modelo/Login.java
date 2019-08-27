
package br.fipp.modelo;

import br.fipp.DAO.FuncionarioDAO;
import br.fipp.entidade.Funcionario;
import java.util.ArrayList;
import java.util.List;


public class Login {

    ArrayList <String> erros = new ArrayList ();

    public Login() {
    }
    
    public final boolean possuiErros() {
        return !erros.isEmpty();
    }

    public final List<String> getErros() {
        return erros;
    }  

    public Funcionario efetuaLogin(String login, String senha) {
              
        FuncionarioDAO FunDAO = new FuncionarioDAO();
        Funcionario f = FunDAO.busca(login);
        
        if (f == null) {
            erros.add("Usuário não cadastrado.");
        } 
        else {
            if (f.getAtivo().equalsIgnoreCase("N") || f.getDtdemissao()!=null) {
                erros.add("Funcionario desativado/demitido, entre em contado com o Admin.");
            }
            else
                if(!f.getSenha().equalsIgnoreCase(senha))
                    erros.add("Senha Incorreta");
        }
        return f;
    }    
}
