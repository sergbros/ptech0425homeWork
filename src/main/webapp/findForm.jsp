<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<form style="background-color:#a9cce3" action="${pageContext.request.contextPath}/viewList?mode=find" method="POST">
    <br>
    <p>Имя или адрес клиента: <input type="text" size="20" name="searchName"></p>
    <p>Тип клиента: <select name="searchClientType" size="1">
        <option selected>Физическое лицо</option>
        <option>Юридическое лицо</option>
    </select></p>
    <p><input type="submit" name="F1" value="Найти"></p>
    <br>
</form>

