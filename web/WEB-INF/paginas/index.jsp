<%-- 
    Document   : index
    Created on : 12/11/2015, 14:40:11
    Author     : Bruno
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>HelpDesk - Login</title>
        <link href="${pageContext.request.contextPath}/css/estilo-login.css" rel="stylesheet" type="text/css"/>
    
    </head>
    <body>
        <div class="login-card">
            <h1><span style="color:red;">Help</span>Desk</h1><br>
            
             <form method="post" action="index.do">
                    <input type="text" name="txtLogin" value="${param.txtLogin}" placeholder="Login" maxlength="70"  />
                    <input type="password" name="txtSenha" placeholder="Password" maxlength="15"/>
                    <input type="submit" name="bEntrar" class="login login-submit" value="login"/>
              </form>
              <div class="login-help">
              <a style="font-size:15px; color:red;"><jsp:include  page="_erro.jsp"/></a> 
              </div>
        </div>
        
    </body>
</html>
