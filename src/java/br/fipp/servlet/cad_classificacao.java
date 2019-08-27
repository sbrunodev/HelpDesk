package br.fipp.servlet;

import br.fipp.DAO.ClassificacaoDAO;
import br.fipp.entidade.Classificacao;
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


@WebServlet(urlPatterns = {"/cad_classificacao.do"})
public class cad_classificacao extends HttpServlet {

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
        
        
        ClassificacaoDAO ClaDAO = new ClassificacaoDAO();
        List <String> erros = new ArrayList();
        boolean alterando=false;
        
        boolean inserir = request.getParameter("bInserir")!=null;
        boolean alterar = request.getParameter("bAlterar")!=null;
            
          if (request.getParameter("sel") != null) {
            try {
                int cod = Integer.parseInt(request.getParameter("sel"));
                Classificacao c = ClassificacaoDAO.busca(cod);
                if (c==null) {
                    erros.add("Uso inválido");
                } else {
                    alterando = true;
                    request.setAttribute("c", c);
                }
            } catch (NumberFormatException ex) {
                erros.add("Uso inválido.");
            }
        }
        
        
        if (inserir || alterar){
            String codigo = request.getParameter("txtCodigo");
            String nome = request.getParameter("txtNome");
            String ativa = request.getParameter("selAtivo");
            
            if (nome.isEmpty() || nome==null)
                erros.add("Informe o nome corretamente ! ");
            
            if(ativa.equals("0"))
                erros.add("Informe o Ativo Corretamente");
                
            
            if (erros.isEmpty()){
                Classificacao c = new Classificacao(0,nome,ativa);
   
                if(inserir){
                    if(ClaDAO.insere(c))
                        erros.add("Erro");
                    
                }
                else{ // Alterando
                    try{
                        int cod = Integer.parseInt(codigo);
                        c.setCodigo(cod);
                        if (!ClaDAO.altera(c)){
                            erros.add("erros");
                        }
                    }
                    catch(NumberFormatException ex){
                        erros.add("Erro");
                    }
                }
            
            }
        
        }
       // public Status(int codigo, String status, String ativo)
        request.setAttribute("alterando", alterando);
        request.setAttribute("erros", erros);
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/paginas/on/adm/cad_classificacao.jsp");
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
