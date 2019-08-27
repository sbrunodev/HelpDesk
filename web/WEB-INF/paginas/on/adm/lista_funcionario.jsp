<%-- 
    Document   : lista_funcionario
    Created on : 14/11/2015, 15:01:16
    Author     : Bruno
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>HelpDesk - Lista Funcionarios</title>
        <link href="${pageContext.request.contextPath}/css/estilo-menu.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/css/estilo-table.css" rel="stylesheet" type="text/css"/>
    
    
    </head>
    <body style="font-size: 12px;">
        
        <jsp:include  page="../_menu.jsp"/>
        
       <br><br><br><br><br><br><br>
       <h1 class="logo">Lista Funcionarios</h1>
       
        <jsp:include page="../../_erro.jsp"/>
        
        <table class="zebra"  >
            <tr >
                <th>Código</th>
                <th>Nome</th>
                <th>Data de Contratação</th>
                <th>Data de Demissão</th>
                <th>Ativo</th>
                <th>Senha</th>
                <th>Tipo</th>
                <th>Selecionar</th>
                <th>Excluir</th>
            </tr>
            
            <c:forEach var="fun" items="${funcionarios}">
                <tr >
                    <td>${fun.codigo}</td>
                    <td>${fun.nome}</td>
                    <td>${fun.dtcontratacao}</td>
                    <td>${fun.dtdemissao}</td>
                    <td>${fun.ativo}</td>
                    <td>${fun.senha}</td>
                    <td>${fun.tipo}</td>
                    <td><a style="color:#9999ff;" href="cad_funcionario.do?sel=${fun.codigo}">Selecionar </a> </td>
                    
                    <td>
                        <a style="color:#cc0000;" href="lista_funcionario.do?del=${fun.codigo}"
                           onclick="return confirm('Deseja excluir ${fun.nome}?');">
                            Excluir
                        </a>
                    </td>
                </tr>
            </c:forEach>
                
        </table>
        </div>
                            
    </body>
</html>
