<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>HelpDesk - Status</title>
         <link href="${pageContext.request.contextPath}/css/estilo-menu.css" rel="stylesheet" type="text/css"/>
         <link href="${pageContext.request.contextPath}/css/estilo-table.css" rel="stylesheet" type="text/css"/>
    
    </head>
    <body style="font-size: 12px;">
        
        <jsp:include  page="../_menu.jsp"/>
        
       <br><br><br><br><br><br><br>
        <h1 class="logo">Lista Classificação</h1>
       
        <jsp:include page="../../_erro.jsp"/>
        
        <table class="zebra" style="margin: 0 auto;">
            <tr>
                <th>Código</th>
                <th>Nome</th>
                <th>Ativa</th>
                <th>Selecionar</th>
                <th>Excluir</th>
                
            </tr>
            
            <c:forEach var="s" items="${classificacao}">
                <tr>
                    <td>${s.codigo}</td>
                    <td>${s.nome}</td>
                    <td>${s.ativa}</td>
                    <td><a style="color:#9999ff;" href="cad_classificacao.do?sel=${s.codigo}">Selecionar </a> </td>
                    
                    <td>
                        <a style="color:#cc0000;" href="lista_classificacao.do?del=${s.codigo}"
                           onclick="return confirm('Deseja excluir ${s.nome}?');">
                            Excluir
                        </a>
                    </td>
                </tr>
            </c:forEach>
                
        </table>
       
    </body>
</html>
