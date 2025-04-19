<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "База клиентов вымышленного провайдера" %>
</h1>
<br/>
<br/>
<br/>
<a href="viewList?mode=last10">Список 10 последних пользователей</a>
<br/>
<a href="viewList?mode=full">Список всех пользователей</a>
<br/>
<a href="createClient.jsp">Новый пользователь</a>
</body>
</html>