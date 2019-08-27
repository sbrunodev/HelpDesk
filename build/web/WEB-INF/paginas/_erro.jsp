<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:if test="${not empty requestScope.erros}">
    <div class="erro" style="color:red">
        
            <c:forEach var="mensagem" items="${requestScope.erros}">
                
                <c:out value="• ${mensagem}"/>
                <br>
            </c:forEach>
        
    </div>
    <br>
</c:if>