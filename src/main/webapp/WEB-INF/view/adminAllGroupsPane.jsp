<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: patryk
  Date: 15.09.18
  Time: 16:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Lista grup</title>
</head>
<body>
<jsp:include page="header.jsp"/>
    <h1>Zarządzanie grupami użytkowników</h1>
    <a href="/adminAddGroup"><b>Dodaj nową grupę</b></a>
    <p>Lista grup</p>
        <table border="1" cellpadding="5">
            <tr>
                <th>Nazwa grupy</th>
                <th>Działania</th>
            </tr>
        <c:forEach var="group" items="${groups}">
            <tr>
                <td>${group.className}</td>
                <td><a href="/adminDeleteGroup?id=${group.id}">usuń</a> &emsp; <a href="/adminEditGroup?id=${group.id}">edytuj</a></td>
            </tr>
        </c:forEach>
</table>
<jsp:include page="footer.jsp"/>
</body>
</html>
