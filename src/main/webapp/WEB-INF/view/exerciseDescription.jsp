<%--
  Created by IntelliJ IDEA.
  User: patryk
  Date: 08.09.18
  Time: 16:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Exercise Description</title>
</head>
<body>
    <jsp:include page="header.jsp"/><br>
        Zadanie o id: ${solution.id}<br>
        Opis rozwiązania: ${solution.description}<br>
    <jsp:include page="footer.jsp"/>
</body>
</html>
