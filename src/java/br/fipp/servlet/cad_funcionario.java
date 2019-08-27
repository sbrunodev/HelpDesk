package br.fipp.servlet;

import br.fipp.DAO.FuncionarioDAO;
import br.fipp.entidade.Funcionario;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
@WebServlet(name = "cad_funcionario", urlPatterns = {"/cad_funcionario.do"})
public class cad_funcionario extends HttpServlet {
    private Object DateTimeFormat;

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
        
        List <String> erros = new ArrayList();
        Funcionario Fun;
        FuncionarioDAO FunDAO = new FuncionarioDAO();
        boolean inserir = request.getParameter("bInserir")!=null;
        boolean alterar = request.getParameter("bAlterar")!=null; 
        boolean alterando = false;
        int codigoSelecionado = 0;
        if (request.getParameter("sel") != null) {
            try {
                codigoSelecionado = Integer.parseInt(request.getParameter("sel"));
                Funcionario fun = FunDAO.buscaCod(codigoSelecionado);
                if (fun==null) {
                    erros.add("Uso inválido");
                } else {
                    alterando = true;
                    request.setAttribute("Selecionado",codigoSelecionado);
                    request.setAttribute("fun", fun);
                }
            } catch (NumberFormatException ex) {
                erros.add("Uso inválido.");
            }
        }
        //Funcionario
        //(int codigo, String nome, Date dtcontratacao, Date dtdemissao, String ativo, String senha, String tipo)
        if (inserir || alterar){
            
            String nome = request.getParameter("txtNome");
            String dtcontratacao = request.getParameter("txtDtcontratacao");
            String dtdemissao = request.getParameter("txtDtDemissao");
            String ativo = request.getParameter("selAtivo");
            String senha = request.getParameter("txtSenha");
            String tipo = request.getParameter("selTipo");
            
            
            
            if (nome.isEmpty() || nome==null)
                erros.add("Informe o nome corretamente !");
            
            if (dtcontratacao.isEmpty() || dtcontratacao==null)
                erros.add("Informe a Data de Contratação corretamente !");
            
            if(ativo.equals("0"))
                erros.add("Informe o Ativo Corretamente");
            
            if (senha.isEmpty() || senha==null)
                erros.add("Informe a Senha corretamente !");
            
            if (tipo.equals("0"))
                erros.add("Informe o tipo corretamente !");
            
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
            
            java.sql.Date dt = null;  
            try {
                dt = new java.sql.Date(format.parse(dtcontratacao).getTime());
            } catch (ParseException ex) {
                erros.add("Informe a Data corretamente ! Ano-Mes-Dia ");
            }

          if (erros.isEmpty()){
  
                Fun = new Funcionario(0,nome,dt,null,ativo,senha,tipo);
                if(inserir){
                    Funcionario Valida = FunDAO.busca(nome);
                    if (Valida==null)
                    {
                       if(FunDAO.insere(Fun))
                           erros.add("Erros ao Inserir no banco!");
                    }
                    else
                       erros.add("Já existe um Funcionario usando esse nome");
               }
               else{  // Altera
                      // CASO SEJA DEMITIDO
                        java.sql.Date dtd;
                        if (dtdemissao.isEmpty() || dtdemissao==null)
                            dtd = null;
                        else
                        {
                            try {
                                  dtd = new java.sql.Date(format.parse(dtcontratacao).getTime());
                                  Fun.setDtdemissao(dtd);
                                } catch (ParseException ex) {
                                   erros.add("Informe a Data corretamente ! Ano-Mes-Dia ");
                                   Fun.setDtdemissao(null);
                                }
                        }
                        
                      Fun.setCodigo(Integer.parseInt(request.getParameter("txtCodigo")));
                      if (erros.isEmpty()){
                         if(!FunDAO.altera(Fun))
                            erros.add("Erros ao Alterar no banco!"); 
                      }
                      //else
                        
                              
                    }    
            }
            else // Se tem erro e for alterar
            if(alterar){
                alterando=true;
                Funcionario fun = FunDAO.buscaCod(Integer.parseInt((String) request.getAttribute("Selecionado")));
            
                request.setAttribute("fun", fun);
            }
                
               
            
                 
        }
       
        request.setAttribute("alterando", alterando);
        request.setAttribute("erros", erros);
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/paginas/on/adm/cad_funcionario.jsp");
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
