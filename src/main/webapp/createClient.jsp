<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>

<head>
    <meta http-equiv="Content-Type"
          content="text/html; charset=UTF-8">
    <title>Добавление клиента</title>
</head>

<body>
<h1><%= "Добавление вымышленного клиента" %>
</h1>
<form action="${pageContext.request.contextPath}/create" method="POST">
    <p>Имя: <input type="text" size="20" name="name"></p>
    <p>Тип клиента: <select name="clientType" size="1">
        <option selected>Физическое лицо</option>
        <option>Юридическое лицо</option>
    </select></p>
    <p><input type="hidden" name="clientid" value="0"></p>
    <p><input type="hidden" name="added" value=""></p>
    <p>Сетевой IP адрес устройства (например, 127.0.0.1): <input type="text" size="25" name="ipAddress"></p>
    <p>Физический адрес устройства (MAC, например 00-AB-CD-EF-11-22): <input type="text" size="25" name="macAddress"></p>
    <p>Модель устройства: <input type="text" size="25" name="model"></p>
    <p>Адрес места нахождения: <input type="text" size="25" name="postaddress"></p>
    <p><input type="submit" name="B1" value="Создать"></p>
</form>

<jsp:include page="menu.jsp"/>
</body>
</html>
