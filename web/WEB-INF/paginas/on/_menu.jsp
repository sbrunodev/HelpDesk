<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div style="background-color:#e3e3e3;width: 100%;height: 85px;position:fixed;z-index:1;top:0;left:0;">
    
       <h1 class="logo"><span style="color:red;">Help</span>Desk - ${FunLogado.nome}</h1> 
              <ul>
                  <li><a href="principal.do">Home</a></li>
                  
                            <li>Atividades
                                <ul>
                                    <li><a href="cad_atividade.do">Cadastrar</a></li>
                                    <li><a href="lista_atividade.do">At. Fechadas</a></li>
                                    <c:if test="${FunLogado.tipo == '1'}">   
                                    <li><a href="#">Visualizar Todas Atividades</a></li>
                                    </c:if>
                                </ul>    

                            </li>
                  <c:if test="${FunLogado.tipo == '1'}">   
                            <li>
                              Cadastro
                              <ul>
                                <li><a href="cad_funcionario.do">Funcionario</a></li>
                                <li><a href="cad_status.do">Status</a></li>
                                <li><a href="cad_classificacao.do">Classificação</a></li>
                              </ul>
                            </li>

                            <li>
                              Consulta
                              <ul>
                                <li><a href="lista_funcionario.do">Funcionario</a></li>
                                <li><a href="lista_status.do">Status</a></li>
                                <li><a href="lista_classificacao.do">Classificação</a></li>
                              </ul>
                            </li>
                   </c:if>
                  
                  <li><a href="index.do">Sair</a></li>
            </ul>
    </div> 
