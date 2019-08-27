<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>HelpDesk - Status</title>
         <link href="${pageContext.request.contextPath}/css/estilo-form.css" rel="stylesheet" type="text/css"/>
         <link href="${pageContext.request.contextPath}/css/estilo-menu.css" rel="stylesheet" type="text/css"/>
    
    </head>
    <body>
        <!-- public Status(int codigo, String status, String ativo)-->
        <jsp:include  page="../_menu.jsp"/>
       
        <div class="CorpoCadastro">
       
            <form method="post" action="cad_status.do">
                    <h1 class="logo">Cadastro Status</h1>
                    <jsp:include page="../../_erro.jsp"/>
                    
                    <c:if test="${requestScope.alterando}">
                        <input type="text" name="txtCodigo" value="${s.codigo}" placeholder="Codigo" maxlength="5"  
                        readonly="readonly" size="7" maxlength="5"/> 

                        <br><br>
                    </c:if>   
                        
                    <input type="text" name="txtStatus" value="${empty erros? s.status : param.txtStatus}" placeholder="Status" 
                           size="15" maxlength="20"/> 
                           
                    <br><br>
                    
                    Ativo<br>
                    <select name="selAtivo">
                        <option value="0" selected> </option>
                        <option value="S"
                                
                         <c:if test="${s.ativo == 'S'}">
                                selected
                         </c:if>
                                >Ativo </option>
                        <option value="N"
                                
                         <c:if test="${s.ativo == 'N'}">
                                selected
                         </c:if>
                                >Desativo </option>
                    </select>


               <p class="submit">
                    <input type="submit" name="bInserir" value="Inserir" 
                           ${requestScope.alterando ? "disabled=\"disabled\"" : ""}/>
                    <input type="submit" name="bAlterar" value="Alterar" 
                           ${requestScope.alterando ? "" : "disabled=\"disabled\""}/>
                    <input type="submit" name="bLimpar" value="Limpar"/>

                    <a href="lista_status.do" style="color:black;">Pesquisar</a>
                </p>
            </form>
        </div>      
       
            
       
      
        
        
    </body>
</html>
