<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: patryk
  Date: 14.09.18
  Time: 23:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Student's details</title>
</head>
<body>
<jsp:include page="header.jsp"/>

<h1>Szczegóły użytkownika: ${user.userName}</h1>
<p>Nazwa: <b>${user.userName}</b></p>
<p>Email: ${user.email}</p>
<b>Dodane rozwiązania zadań:</b><br><br>
<table border="1" cellpadding="5">
    <tr>
        <th>Tytuł zadania</th>
        <th>Data dodania</th>
        <th>Szczegóły</th>
    </tr>
    <c:forEach var="solution" items="${solutions}">
        <c:if test="${not empty solution.updated}">
            <tr>
                <td>${solution.exerciseId}</td>
                <td>${solution.updated}</td>
                <td><a href="/description?id=${solution.id}">Szczegóły</a></td>
            </tr>
        </c:if>
    </c:forEach>
</table>


<jsp:include page="footer.jsp"/>
</body>
</html>
