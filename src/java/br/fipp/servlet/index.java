package br.fipp.servlet;

import br.fipp.entidade.Funcionario;
import br.fipp.modelo.Login;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Bruno
 */
@WebServlet(name = "index", urlPatterns = {"/index.do"})
public class index extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            
         List<String> erros = new ArrayList();

         if (request.getParameter("bEntrar")!=null)
         {
             String login, senha;
             login = request.getParameter("txtLogin");
             senha = request.getParameter("txtSenha");
             
             if (login.isEmpty() || login== null )
                 erros.add("Login não informado corretamente! ");
             
             if (senha.isEmpty() || senha == null )
                  erros.add("Senha não informada corretamente! ");
             
             if (erros.isEmpty())
             {
                Login modelo = new Login();
                Funcionario fun = modelo.efetuaLogin(login, senha);
                if (modelo.possuiErros()) {
                    erros.addAll(modelo.getErros());
                } else {
                    request.getSession().setAttribute("FunLogado", fun);
                    response.sendRedirect("principal.do");
                    return;
                }
             }
         }
         request.getSession().invalidate();
       
        request.setAttribute("erros", erros);
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/paginas/index.jsp");
        rd.forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
