<%--
  Created by IntelliJ IDEA.
  User: patryk
  Date: 15.09.18
  Time: 16:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Dodawanie grupy</title>
</head>
<body>
<jsp:include page="header.jsp"/><br>
    <b>Podaj nazwę nowej grupy</b> <br>
    <form action="/adminAddGroup" method="post">
        <input type="text" name="className"><br>
        <input type="submit" value="Dodaj">
    </form>
<jsp:include page="footer.jsp"/>
</body>
</html>
