package br.fipp.servlet;

import br.fipp.DAO.FuncionarioDAO;
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
 *
 * @author Bruno
 */
@WebServlet(name = "lista_funcionario", urlPatterns = {"/lista_funcionario.do"})
public class lista_funcionario extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //___________________________________________________________________________
        HttpSession session = request.getSession();
        Funcionario F = (Funcionario) request.getSession().getAttribute("FunLogado");
        if (F==null){
            session.invalidate();response.sendRedirect("index.do"); return;  
        }else
            if (F.getTipo().equals("2")){response.sendRedirect("principal.do");return; }
        //___________________________________________________________________________
        
        FuncionarioDAO FunDAO = new FuncionarioDAO();
        List<String> erros = new ArrayList<>();
       
        if (request.getParameter("del") != null) {
            try {
                int c = Integer.parseInt(request.getParameter("del"));
                if(!FunDAO.exclui(c)){
                    erros.add("Erro");
                }
            } catch (NumberFormatException ex) {
                erros.add("Uso inválido.");
            }
        }
       //Funcionario fun = new Funcionario();
       //request.getSession().setAttribute("FunLogado", fun);
        request.setAttribute("funcionarios", FunDAO.lista());
        request.setAttribute("erros", erros);
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/paginas/on/adm/lista_funcionario.jsp");
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
