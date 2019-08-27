
package br.fipp.servlet;

import br.fipp.DAO.AtividadeDAO;
import br.fipp.entidade.Atividade;
import br.fipp.entidade.Funcionario;
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
import javax.servlet.http.HttpSession;

/**
 * lista_atividades.jsp
 * @author Aluno
 */
@WebServlet(name = "principal", urlPatterns = {"/principal.do"})
public class principal extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
        //___________________________________________________________________________
        HttpSession session = request.getSession();
        Funcionario fun = (Funcionario) request.getSession().getAttribute("FunLogado");
        if (fun==null){
            session.invalidate();response.sendRedirect("index.do"); return;  
        }else
            session.setAttribute("FunLogado", fun); 
        //___________________________________________________________________________
        
        AtividadeDAO ATDAO = new AtividadeDAO();
        
        List <Atividade> atividades = ATDAO.listaFuncionario(fun.getCodigo());
        
        request.setAttribute("atividades", atividades);
        
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/paginas/on/principal.jsp");
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
