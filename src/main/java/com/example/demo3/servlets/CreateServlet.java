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

        ClientEntity client = new ClientEntity(request);

        if(!(request.getParameter("key").equals("addaddress"))){
            //Получаем из БД максимальный существующий Id, чтобы занять следующий
            //Если это не второй адрес для существующего уже пользователя
            Integer clientId = dbManager.getMaxClientIdFromDataBase() + 1;
            client.setClientid(clientId);
        }

        //Создаём адрес
        Integer addressId = dbManager.getMaxAddressIdFromDataBase() + 1;
        AddressEntity address = new AddressEntity(request, client, addressId);

        //Проверка корректности заполнения полей
        String errorMessage = updateService.check(address);

        if(errorMessage.length() <= 0) {
            if(!(request.getParameter("key").equals("addaddress"))) {
                dbManager.writeClient(client);
            }
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

            updateService.update(request, response, address);
        }
    }
}
