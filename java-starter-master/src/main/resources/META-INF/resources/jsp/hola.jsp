<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head lang="en">
    <meta charset="UTF-8" />
    <title>HELLO</title>
</head>
<body>
    <h1 id="titulo">Mensajes</h1>
    <ul>
        <c:forEach items="${mensajes}" var="mensaje">
            <li class="msj">${mensaje}</li>
        </c:forEach>
    </ul>

    <form action="/mensajes" method="post">
        <input name="mensaje">
        <input type="submit">
    </form>
</body>
</html>