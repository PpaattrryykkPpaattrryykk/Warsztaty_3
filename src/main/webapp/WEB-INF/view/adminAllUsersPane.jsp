<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: patryk
  Date: 15.09.18
  Time: 20:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Lista użytkowników</title>
</head>
<body>
<jsp:include page="header.jsp"/><br>
<h1>Zarządzanie użytkownikami</h1>
<a href="/adminAddUser"><b>Dodaj nowego użytkownika</b></a>
<p>Lista użytkowników</p>
<table border="1" cellpadding="5">
    <tr>
        <th>Nazwa użytkownika</th>
        <th>Działania</th>
    </tr>
    <c:forEach var="user" items="${users}">
        <tr>
            <td>${user.userName}</td>
            <td><a href="/adminDeleteUser?id=${user.id}">usuń</a> &emsp; <a href="/adminEditUser?id=${user.id}">edytuj</a></td>
        </tr>
    </c:forEach>
</table>
<jsp:include page="footer.jsp"/>
</body>
</html>
