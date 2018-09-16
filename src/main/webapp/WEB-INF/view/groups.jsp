<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: patryk
  Date: 14.09.18
  Time: 22:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>All Groups</title>
</head>
<body>
    <jsp:include page="header.jsp"/>
    <p>Lista grup</p>
    <table border="1" cellpadding="5">
    <tr>
        <th>Nazwa grupy</th>
        <th>Pokaż użytkowników</th>
    </tr>
    <c:forEach var="group" items="${groups}">
        <tr>
            <td>${group.className}</td>
            <td><a href="/groupdetails?id=${group.id}&className=${group.className}">kursanci</a></td>
        </tr>
    </c:forEach>
    </table>
    <jsp:include page="footer.jsp"/>
</body>
</html>
