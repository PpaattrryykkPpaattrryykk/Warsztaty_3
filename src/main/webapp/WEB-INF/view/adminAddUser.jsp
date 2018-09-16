<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: patryk
  Date: 16.09.18
  Time: 20:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Dodawanie użytkownika</title>
</head>
<body>
<jsp:include page="header.jsp"/><br>
<h1>Dodawanie użytkownika</h1>
    <form action="/adminAddUser" method="post">
        Podaj nazwę użytkownika<br>
        <input type="text" name="userName"><br>
        Podaj hasło użytkownika<br>
        <input type="text" name="password"><br>
        Podaj e-mail użytkownika<br>
        <input type="text" name="email"><br>
        Wybierz grupę<br>
        <select name="userGroupId">
        <c:forEach var="group" items="${groups}">
                <option value="${group.id}">${group.className}</option>
        </c:forEach>
        </select><br><br>
        <input type="submit" value="Wyślij">
    </form><br>
<jsp:include page="footer.jsp"/>
</body>
</html>
