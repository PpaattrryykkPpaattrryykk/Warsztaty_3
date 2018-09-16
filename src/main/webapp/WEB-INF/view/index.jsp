<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: artur
  Date: 08.09.18
  Time: 15:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Homepage</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<p>Ostatnie rozwiązania</p>
<table border="1" cellpadding="5">
    <tr>
        <th>Exercise ID</th>
        <th>User ID</th>
        <th>Created</th>
        <th>Solution description</th>
    </tr>
    <c:forEach var="solution" items="${solutions}">
        <tr>
            <td>${solution.exerciseId}</td>
            <td>${solution.userId}</td>
            <td>${solution.created}</td>
            <c:if test="${not empty solution.description}"><td><a href="/description?id=${solution.id}">Szczegóły</a></td></c:if>
        </tr>
    </c:forEach>
</table>

<jsp:include page="footer.jsp"/>
</body>
</html>