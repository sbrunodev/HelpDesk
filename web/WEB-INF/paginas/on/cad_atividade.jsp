<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Atividade</title>
         <link href="${pageContext.request.contextPath}/css/estilo-form.css" rel="stylesheet" type="text/css"/>         
         <link href="${pageContext.request.contextPath}/css/estilo-menu.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
       
         <jsp:include  page="_menu.jsp"/>
        
         <div style="padding: 10px; position: absolute; margin-left: 70px; margin-top: 88px;"> 
                   
            <jsp:include page="../_erro.jsp"/> 
         </div>       
         
         
        <div class="CorpoCadastro" style="width: 700px;">
        
            <h1 class="logo"> Atividade </h1>
              
            <form method="post" action="cad_atividade.do">

            <c:if test="${requestScope.alterando}">
              <input type="text" name="txtCodigo" value="${ati.codigo}" placeholder="Código" size="7" 
                     maxlength="7"  readonly="readonly" />
              <br><br>
            </c:if> 
               
             <!---- DADOS ATIVIDADE --->
              <input type="text" name="txtDescricao" value="${empty erros ? ati.descricao : param.txtDescricao}" 
                     placeholder="Descriçao" size="25" maxlength="70" /> <a class="Ponto">*</a>
              <br><br>
              <input type="text" name="txtDtInicio" value="${empty erros ? ati.dtinicio : param.txtDtInicio}" 
                     placeholder="Data de Inicio (Ano-Mes-Dia)" size="27" maxlength="10" />  <a class="Ponto">*</a>
                
              <br><br>
              <c:if test="${requestScope.alterando}">
              <input type="text" name="txtDtFim" value="${empty erros ? ati.dtfim : param.txtDtDemissao}" 
                     placeholder="Date de Encerramento (Ano-Mes-Dia)"  size="27" maxlength="10" /> 
              </c:if>
              <br><br>
             <!---- DADOS ATIVIDADE --->
           
             <!---- DADOS SOLICITANTE --->
               <div style="background-color: #e3e3e3;padding: 10px; position: absolute; margin-left: 350px; margin-top: -98px;"> 
                    Solicitante <br>
                   <input type="text" name="txtSol_Email" value="${empty erros ? entsol.email : param.txtSol_Email}" placeholder="E-mail" maxlength="70"  size="25"/> <a class="Ponto">*</a>
                   <br><br>
                   <input type="text" name="txtSol_Nome" value="${empty erros ? entsol.nome : param.txtSol_Nome}" placeholder="Nome" maxlength="70"  size="25"/> <a class="Ponto">*</a>
                   <br><br>
                   <input type="text" name="txtSol_Telefone" value="${empty erros ? entsol.telefone : param.txtSol_Telefone}" placeholder="Telefone" maxlength="20"  size="20"/> 
                   <br><br>
                   <textarea name="txtSol_Observacao" value="${empty erros ? entsol.observacao : param.txtSol_Observacao}" rows="10" cols="50" placeholder="Observações"> </textarea>
              </div>
              <!---- DADOS SOLICITANTE ---> 
                <br>Classificação <a class="Ponto">*</a> <br><br> 
                
                    <c:forEach var="c" items="${classificacao}">
                        <input type="checkbox" name="clas" value="${c.codigo}"> ${c.nome} <br> 
                    </c:forEach>
                
                <br><br>
                <!------------------------------------- ----------------------->
                <!--------------------- STATUS ------------------->
                Status<br>
                    <select name="selStatus">
                        <option value="0" selected> </option>
                        <c:forEach var="s" items="${status}">
                            <option value="${s.codigo}" > ${s.status} </option>
                       </c:forEach>
                    </select><br><br>
                <!--------------------- STATUS ------------------->
                
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
