<%-- 
    Document   : cad_classificacao
    Created on : 15/11/2015, 11:12:12
    Author     : Bruno
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>HelpDesk - Classificação</title>
         <link href="${pageContext.request.contextPath}/css/estilo-form.css" rel="stylesheet" type="text/css"/>
         <link href="${pageContext.request.contextPath}/css/estilo-menu.css" rel="stylesheet" type="text/css"/>
    
    
    </head>
    <body>
         
        <jsp:include  page="../_menu.jsp"/>
        
        <div class="CorpoCadastro">
            
            <form method="post" action="cad_classificacao.do">
                  
                    <h1 class="logo">Cadastro Classificação</h1>
                     <jsp:include page="../../_erro.jsp"/>
                                        
                    <c:if test="${requestScope.alterando}">
                    <input type="text" name="txtCodigo" value="${c.codigo}" placeholder="Codigo" maxlength="5"  
                           readonly="readonly" size="7" maxlength="5"/> 
                   
                    <br><br>
                    </c:if>
                    
                    <input type="text" name="txtNome" value="${empty erros? c.nome : param.txtNome}" placeholder="Nome" 
                           size="15" maxlength="20"/> 
                    <br><br>
                 
                    Ativo<br>
                    <select name="selAtivo">
                        <option value="0" selected> </option>
                        <option value="S"
                         <c:if test="${c.ativa == 'S'}">
                                selected
                         </c:if>
                                >Ativo </option>
                        <option value="N"
                         <c:if test="${c.ativa == 'N'}">
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
                   
                    <a href="lista_classificacao.do" style="color:black;"> Pesquisar</a>
                </p>
            </form>
       
         </div>
    </body>
</html>
