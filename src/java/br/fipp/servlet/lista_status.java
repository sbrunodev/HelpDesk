package br.fipp.servlet;

import br.fipp.DAO.StatusDAO;
import br.fipp.entidade.Funcionario;
import br.fipp.entidade.Status;
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
 *
 * @author Bruno
 */
@WebServlet(name = "lista_status", urlPatterns = {"/lista_status.do"})
public class lista_status extends HttpServlet {

   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        Funcionario F = (Funcionario) request.getSession().getAttribute("FunLogado");
        if (F==null){
            session.invalidate();response.sendRedirect("index.do"); return;  
        }else
            if (F.getTipo().equals("2")){response.sendRedirect("principal.do");return; }
        //______________________________________________________________________
        List<String> erros = new ArrayList<>();
         StatusDAO StDAO = new StatusDAO(); 
        
       if (request.getParameter("del") != null) {
            try {
                int c = Integer.parseInt(request.getParameter("del"));
                if(!StDAO.exclui(c)){
                        erros.add("Erro ao excluir, pode fazer parte de alguma Atividade ! ");
                }
            } catch (NumberFormatException ex) {
                erros.add("Uso inválido.");
            }
        }
        
       
        request.setAttribute("status", StDAO.lista());
        request.setAttribute("erros", erros);
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/paginas/on/adm/lista_status.jsp");
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
