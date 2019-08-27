
package br.fipp.servlet;

import br.fipp.DAO.AtividadeClassificacaoDAO;
import br.fipp.DAO.AtividadeDAO;
import br.fipp.DAO.ClassificacaoDAO;
import br.fipp.DAO.FuncionarioDAO;
import br.fipp.DAO.SolicitanteDAO;
import br.fipp.DAO.StatusDAO;
import br.fipp.entidade.Atividade;
import br.fipp.entidade.AtividadeClassificacao;
import br.fipp.entidade.Classificacao;
import br.fipp.entidade.Funcionario;
import br.fipp.entidade.Solicitante;
import br.fipp.entidade.Status;
import java.io.IOException;
import java.io.PrintWriter;
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


@WebServlet(name = "cad_atividade", urlPatterns = {"/cad_atividade.do"})
public class cad_atividade extends HttpServlet {

  
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
          Atividade ati = new Atividade();
          Solicitante sol = new Solicitante ();
          SolicitanteDAO SolDAO= new SolicitanteDAO();
          AtividadeDAO AtDAO= new AtividadeDAO();
          AtividadeClassificacaoDAO ACDAO= new AtividadeClassificacaoDAO ();
          
          boolean inserir = request.getParameter("bInserir") != null;
          boolean alterar = request.getParameter("bAlterar") != null;
          boolean alterando = false;
          List<String> erros = new ArrayList();
       // public Atividade(int codigo, String descricao, Date dtinicio, Date dtfim, int funCodigo, int staCodigo, String solEmail) 
           if (request.getParameter("sel") != null) {
                try {
                    int cod = Integer.parseInt(request.getParameter("sel"));
                    ati = AtDAO.buscaCod(cod);
                    if (ati==null) {
                        erros.add("Uso inválido");
                    } else {
                        alterando = true;
                        sol = SolDAO.busca(ati.getSolEmail());
                        request.setAttribute("entsol", sol);
                        request.setAttribute("ati", ati);
                    }
                } catch (NumberFormatException ex) {
                    erros.add("Uso inválido.");
                }
           }
          
        if( inserir || alterar)
        {
            
             //------- DADAOS ATIVIDADE --------//
             String codigo = request.getParameter("txtCodigo");    
             String descricao = request.getParameter("txtDescricao");
             String dtInicio = request.getParameter("txtDtInicio");
             String dtFim   = request.getParameter("txtDtFim");
             String status = request.getParameter("selStatus"); 
             
              if(descricao.isEmpty()|| descricao == null)
                 erros.add("Nome invalido");
              
              if(dtInicio.isEmpty() || dtInicio == null)
                 erros.add("Data invalida");
              
              int codsta = Integer.parseInt(status);
              
              // CHECKED   
            String [] selecionados = request.getParameterValues("clas");
            if (selecionados == null){
                  erros.add("Selecione as classificações");
               } 
               
             
             //------- DADOS SOLICITANTE --------//
             String solemail = request.getParameter("txtSol_Email");
             String solnome = request.getParameter("txtSol_Nome");
             String solfone = request.getParameter("txtSol_Telefone");
             String solobservacao = request.getParameter("txtSol_Observacao");
             
             
              
              if(solemail.isEmpty() || solemail == null)
                 erros.add("Email invalido");
              
              if(solnome.isEmpty() || solnome == null)
                 erros.add("Nome do Solicitante invalido");
              
            //TRATA DATA
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
            
            java.sql.Date dt = null;  
            try {
                dt = new java.sql.Date(format.parse(dtInicio).getTime());
            } catch (ParseException ex) {
                erros.add("Informe a Data corretamente ! Ano-Mes-Dia ");
            }
            
            if (erros.isEmpty())
            {  
               HttpSession session = request.getSession();
       
               Funcionario fun = (Funcionario)session.getAttribute("FunLogado");
               session.setAttribute("FunLogado", fun);
                
              if (inserir)
              {
                  //
                   sol = SolDAO.busca(solemail);
                   if (sol==null){ // CASO NÃO ACHE O SOLICITANTE CADASTRA ! 
                     
                     sol= new Solicitante (solemail,solnome,solfone,solobservacao);
                     if (SolDAO.insere(sol))
                          erros.add("Erro Solicitante");
                     
                  }
                  
                   // int codigo, String descricao, Date dtinicio, Date dtfim, int funCodigo, int staCodigo, String solEmail) 
                  
                   
                  ati = new Atividade (0,descricao,dt,null,fun.getCodigo(),codsta,solemail);
                  if(AtDAO.insere(ati))
                    erros.add("Erro Atividade");
                
                  // GRAVA NAS ATIVIDADES CÓDIGOS
                 int aticod=AtDAO.UltimoCara();
                  for (int i=0; i<selecionados.length; i++)
                        ACDAO.insere(new AtividadeClassificacao(aticod, Integer.parseInt(selecionados[i])));
              
                
              } // FIM INSERIR 
              else // Altera
              {
                  
                  int codi = Integer.parseInt(request.getParameter("txtCodigo"));
                  java.sql.Date dtf = null;  
                  if (!dtFim.isEmpty()){
                    try {
                        dtf = new java.sql.Date(format.parse(dtInicio).getTime());
                    } catch (ParseException ex) {
                        erros.add("Informe a Data corretamente ! Ano-Mes-Dia ");
                    }
                 }
                  
                  ati = new Atividade (codi,descricao,dt,dtf,fun.getCodigo(),codsta,solemail);
                  if(!AtDAO.altera(ati))
                    erros.add("Erro Atividade");
              }
            }// ERROS . IS EMPTY  
        }// FIM INSERE OU ALTERA
          ClassificacaoDAO ClaDAO= new ClassificacaoDAO();
          StatusDAO StDAO = new StatusDAO();
          List<Classificacao> c = ClaDAO.listaAtivos();
          List<Status> status = StDAO.lista();
         
        request.setAttribute("status", status);  
        request.setAttribute("classificacao", c);
        request.setAttribute("alterando", alterando);
        request.setAttribute("erros", erros);
        
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/paginas/on/cad_atividade.jsp");
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
