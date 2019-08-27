package br.fipp.filtro;

import br.fipp.entidade.Funcionario;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Filtro para checar se um usuário está logado.
 *
 * @author Aluno
 */
//@WebFilter(filterName = "FiltroUsuarioLogado",
//        urlPatterns = {"/on/*"}) 
public class UsuarioLogado
        implements Filter {

    private String contextPath;

    @Override
    public void init(FilterConfig filterConfig)
            throws ServletException {
        this.contextPath = filterConfig.getServletContext().getContextPath();
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp,
            FilterChain chain)
            throws IOException, ServletException { 
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        HttpSession session = request.getSession();
     
        Funcionario user = (Funcionario) session.getAttribute("FunLogado");
        session.setAttribute("FunLogado", user);
        if (user == null) {
            session.invalidate();
            response.sendRedirect(contextPath + "/index.do");
            return;
        }
        chain.doFilter(req, resp);
        /*
        Funcionario fun = (Funcionario)session.getAttribute("FunLogado");
        session.setAttribute("FunLogado", fun);
        String nomeFun = fun.getNome();
        //--------------------------------
        if(fun == null) {
            session.invalidate();
            response.sendRedirect("index.do");
            return ;
        } else {
            session.setAttribute("FunLogado", fun);
        }*/
        
        
    }

    @Override
    public void destroy() {
    }

}
