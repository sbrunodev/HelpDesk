<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="${pageContext.request.contextPath}/css/estilo-menu.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/css/estilo-table.css" rel="stylesheet" type="text/css"/>
    
        <title>Lista de Atividades - Encerradas</title>
    </head>
    <body style="font-size: 12px;">
        
       <jsp:include  page="../_menu.jsp"/>
        
       <br><br><br><br><br><br><br>
       <h1 class="logo">Lista de Atividades - Encerradas</h1>
       
        <jsp:include page="../../_erro.jsp"/>
        
        <table class="zebra"  >
            <tr >
                <!--int codigo, String descricao, Date dtinicio, Date dtfim, int funCodigo, int staCodigo, String solEmail) {
   -->
                <th>Código</th>
                <th>Descrição</th>
                <th>Data de Inicio</th>
                <th>Data de Fim</th>
                <th>Fun Codigo</th>
                <th>Sta Codigo</th>
                <th>Email - Solicitante</th>

            </tr>
            <c:forEach var="e" items="${requestScope.encerradas}">
                <tr >
                    <td>${e.codigo}</td>
                    <td>${e.descricao}</td>
                    <td>${e.dtinicio}</td>
                    <td>${e.dtfim}</td>
                    <td>${e.funCodigo}</td>
                    <td>${e.staCodigo}</td>
                    <td>${e.solEmail}</td>
                </tr>
            </c:forEach>
               
        </table>
        
    </body>
</html>
