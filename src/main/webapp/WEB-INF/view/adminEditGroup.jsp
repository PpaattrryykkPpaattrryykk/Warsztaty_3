<%--
  Created by IntelliJ IDEA.
  User: patryk
  Date: 15.09.18
  Time: 19:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edycja grupy</title>
</head>
<body>
<jsp:include page="header.jsp"/><br>
    <h1>Edycja grupy</h1>
    Wprowadź nową nazwę grupy:<br>
    <form action="/adminEditGroup" method="post">
        <input type="text" name="className"><br>
        <input type="submit" value="Zatwierdź"><br>
    </form>
<jsp:include page="footer.jsp"/>
</body>
</html>
