package com.example.demo3.servlets;

import com.example.demo3.DBManager;
import com.example.demo3.beans.UpdateService;
import com.example.demo3.repositories.entities.AddressEntity;
import com.example.demo3.repositories.entities.ClientEntity;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "CreateServlet", value = "/create")
public class CreateServlet extends HttpServlet {

    @EJB
    private DBManager dbManager;

    @EJB
    private UpdateService updateService;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter writer = response.getWriter();
        writer.println("Method GET from AddServlet");
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        //Создаём клиента
        ClientEntity client = new ClientEntity(request);
        //Получаем из БД максимальный существующий Id, чтобы занять следующий
        Integer clientId = dbManager.getMaxIdFromDataBase() + 1;
        client.setClientid(clientId);

        //Создаём адрес
        AddressEntity address = new AddressEntity(request, client);

        //Проверка корректности заполнения полей
        String errorMessage = updateService.check(address);

        if(errorMessage.length() < 0) {
            dbManager.writeClient(client);
            dbManager.writeAddress(address);

            String contextPath = request.getContextPath();
            response.sendRedirect(contextPath + "/viewList?mode=full");
        }
        else {
            PrintWriter writer = response.getWriter();
            writer.println("<jsp:include page=\"webapp\\menu.jsp\">");

            //Форма редактирования клиента
            writer.println("<br>");
            writer.println(errorMessage);
            writer.println("<br>");
            String contextPath = request.getContextPath();
            writer.println("<form action=\"" + contextPath + "/update\" method=\"POST\">");
            writer.println("<p>Имя: <input type=\"text\" size=\"20\" name=\"name\" value=\"" + address.getClientid().getClientName() + "\"></p>");
            writer.println("<p>Тип клиента: <select name=\"clientType\" size=\"1\">");
            if(address.getClientid().getType().equals("1")) {
                writer.println("<option selected>Физическое лицо</option>");
                writer.println("<option>Юридическое лицо</option>");
            }
            else{
                writer.println("<option>Физическое лицо</option>");
                writer.println("<option selected>Юридическое лицо</option>");
            }
            writer.println("</select></p>");
            writer.println("<p>id Клиента: <input type=\"text\" size=\"25\" name=\"clientid\" hidden value=\"" + address.getClientid().getClientid() + "\"></p>");
            writer.println("<p>дата добавления: <input type=\"text\" size=\"25\" name=\"added\" hidden value=\"" + address.getClientid().getAdded() + "\"></p>");
            writer.println("<p>Сетевой IP адрес устройства: <input type=\"text\" size=\"25\" name=\"ipAddress\" value=\"" + address.getClientid().getClientName() + "\"></p>");
            writer.println("<p>Физический адрес устройства (MAC): <input type=\"text\" size=\"25\" name=\"macAddress\" value=\"" + address.getMac() + "\"></p>");
            writer.println("<p>Модель устройства: <input type=\"text\" size=\"25\" name=\"model\" value=\"" + address.getModel() + "\"></p>");
            writer.println("<p>Адрес места нахождения: <input type=\"text\" size=\"25\" name=\"postaddress\" value=\"" + address.getPostaddress() + "\"></p>");
            writer.println("<p><input type=\"submit\" name=\"Edit\" value=\"Применить изменения\"></p>");
            writer.println("</form>");
        }
    }
}
