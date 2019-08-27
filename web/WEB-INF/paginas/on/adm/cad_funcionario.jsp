
<%-- 
    Document   : cad_funcionario
    Created on : 14/11/2015, 13:50:38
    Author     : Bruno
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Help Desk - Cadastro Funcionario</title>
         <link href="${pageContext.request.contextPath}/css/estilo-form.css" rel="stylesheet" type="text/css"/>         
         <link href="${pageContext.request.contextPath}/css/estilo-menu.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        
        <!-- Funcionario(int codigo, String nome, Date dtcontratacao, Date dtdemissao, String ativo, String senha, String tipo) -->
        <jsp:include  page="../_menu.jsp"/>
        
            <div class="CorpoCadastro">
        
            <h1 class="logo"> Cadastro de Funcionario </h1>
            
            <jsp:include page="../../_erro.jsp"/>
            
            <!------------------------------------- --------------------------->
            
            <form method="post" action="cad_funcionario.do">

                <c:if test="${requestScope.alterando}">
                    <input type="text" name="txtCodigo" value="${fun.codigo}" placeholder="Codigo" maxlength="5"  
                    readonly="readonly" size="7" maxlength="5"/> 
                  
                    <br><br>
                </c:if> 
                
              <input type="text" name="txtNome" value="${empty erros ? fun.nome : param.txtNome}" placeholder="Nome" size="28" maxlength="70" />
              <br><br>
              
              
              <input type="text" name="txtDtcontratacao" value="${empty erros ? fun.dtcontratacao : param.txtDtcontratacao}"
                     placeholder="Dt. de Contratação (Ano-Mes-Dia)" size="27" maxlength="10"/>
                   
                       
                
                  
              <c:if test="${requestScope.alterando}">
                <br><br> 
              <input type="text" name="txtDtDemissao" value="${empty erros ? fun.dtdemissao : param.txtDtDemissao}" 
                     placeholder="Dt. de Demissão (Ano-Mes-Dia)"  size="27" maxlength="10" />
              </c:if>
              <br><br>

              Ativo<br>
                    <select name="selAtivo">
                        <option value="0" selected> </option>
                        <option value="S"
                         <c:if test="${fun.ativo == 'S'}">
                                selected
                         </c:if>
                                >Ativo </option>
                        <option value="N"
                         <c:if test="${fun.tipo == 'N'}">
                                selected
                         </c:if>
                                >Desativo </option>
                    </select><br><br>
              <input type="text" name="txtSenha" value="${empty erros ? fun.senha : param.txtSenha}" placeholder="Senha" maxlength="70"  size="17" maxlength="15" />

              <br><br>
              
                Tipo<br>
                    <select name="selTipo">
                        <option value="0" selected> </option>
                        <option value="1" 
                                
                         <c:if test="${fun.tipo == '1'}">
                                selected
                         </c:if>
                                > Administrador </option>
                        
                        <option value="2"
                        <c:if test="${fun.tipo == '2'}">
                                selected
                         </c:if>
                                >Funcionario </option>
                    </select>
              
              
              
               <p class="submit">
                    <input type="submit" name="bInserir" value="Inserir" 
                           ${requestScope.alterando ? "disabled=\"disabled\"" : ""}/>
                    <input type="submit" name="bAlterar" value="Alterar" 
                           ${requestScope.alterando ? "" : "disabled=\"disabled\""}/>
                    <input type="submit" name="bLimpar" value="Limpar"/>

                    <a href="lista_funcionario.do" style="color:black;">Pesquisar</a>
                </p>
            </form>
            </div>
    </body>
</html>
