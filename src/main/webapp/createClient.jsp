<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>

<head>
    <meta http-equiv="Content-Type"
          content="text/html; charset=UTF-8">
    <title>Добавление клиента </title>
</head>

<body>
<form action="${pageContext.request.contextPath}/create" method="POST">
    <p>Имя: <input type="text" size="20" name="name"></p>
    <p>Тип клиента: <select name="clientType" size="1">
        <option selected>Физическое лицо</option>
        <option>Юридическое лицо</option>
    </select></p>
    <p><input type="submit" name="B1" value="Создать"></p>
</form>

<jsp:include page="menu.jsp"/>
</body>
</html>
