<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Principal</title>
        <link href="${pageContext.request.contextPath}/css/estilo-menu.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/css/estilo-form.css" rel="stylesheet" type="text/css"/>
    </head>
    <body style="font-size: 12px;">
     
        <jsp:include  page="_menu.jsp"/>
    
        <div class="CorpoCadastro">
            <h1 class="logo">Atividades</h1>
            
            <c:if test="${not empty requestScope.atividades}">
                <h1 class="logo" style="font-size:30px;">${FunLogado.nome}, você tem atividades !<h1>
                        
                        <c:forEach var="ati" items="${requestScope.atividades}">
                            <a class="Palavras" href="cad_atividade.do?sel=${ati.codigo}" style="color:red;"
                               > - ${ati.descricao}</a>
                             </br>
                        </c:forEach>
                             
                           
            </c:if>    
            
            <c:if test="${empty requestScope.atividades}">
                <h1 class="Palavras">${FunLogado.nome}, você não tem atividades !<h1>
            </c:if> 
            
            
            
            
            
        <div>
        
        
    </body>
</html>
