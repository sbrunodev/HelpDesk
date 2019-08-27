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

@WebServlet(urlPatterns = {"/cad_status.do"})
public class cad_status extends HttpServlet {

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
        
        StatusDAO StDAO = new StatusDAO();
        List <String> erros = new ArrayList();
        boolean alterando=false;
        
          if (request.getParameter("sel") != null) {
            try {
                int c = Integer.parseInt(request.getParameter("sel"));
                Status s = StDAO.busca(c);
                if (s==null) {
                    erros.add("Uso inválido");
                } else {
                    alterando = true;
                    request.setAttribute("s", s);
                }
            } catch (NumberFormatException ex) {
                erros.add("Uso inválido.");
            }
        }
          
        boolean inserir = request.getParameter("bInserir")!=null;
        boolean alterar = request.getParameter("bAlterar")!=null;
        
        if (inserir || alterar){
            String codigo = request.getParameter("txtCodigo");
            String status = request.getParameter("txtStatus");
            String ativo = request.getParameter("selAtivo");
            
            if (status.isEmpty() || status==null)
                  erros.add("Informe o status corretamente ! ");
            
            if(ativo.equals("0"))
                erros.add("Informe o Ativo Corretamente");
        
            
            if (erros.isEmpty()){
                Status s = new Status(0,status,ativo);
                StatusDAO StaDAO = null;
                if(inserir){
                    if(StaDAO.insere(s))
                        erros.add("Erro");
                    
                }
                else{ // Alterando
                    s.setCodigo(Integer.parseInt(codigo));
                    if(!StDAO.altera(s))
                        erros.add("Erros ao Alterar");
                }
            
            }
        
        }
       // public Status(int codigo, String status, String ativo)
        request.setAttribute("alterando", alterando);
        request.setAttribute("erros", erros);
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/paginas/on/adm/cad_status.jsp");
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
