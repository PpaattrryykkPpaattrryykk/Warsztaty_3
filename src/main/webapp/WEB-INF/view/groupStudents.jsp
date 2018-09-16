<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: patryk
  Date: 14.09.18
  Time: 23:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>List of students</title>
</head>
<body>
    <jsp:include page="header.jsp"/>
        <h1>Lista studentów grupy ${className}</h1>
        <table border="1" cellpadding="5">
            <tr>
                <th>Nazwa użytkownika</th>
                <th>Szczegóły użytkownika</th>
            </tr>
            <c:forEach var="user" items="${users}">
                <tr>
                    <td>${user.userName}</td>
                    <td><a href="/userDetails?id=${user.id}">Szczegóły użytkownika</a></td>
                </tr>
            </c:forEach>
        </table>
    <jsp:include page="footer.jsp"/>
</body>
</html>
