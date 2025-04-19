package com.example.demo3.servlets;

import com.example.demo3.DBManager;
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
import java.time.LocalDate;

@WebServlet(name = "CreateServlet", value = "/create")
public class CreateServlet extends HttpServlet {

    @EJB
    private DBManager dbManager;


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
        ClientEntity client = new ClientEntity();
        //Получаем из БД максимальный существующий Id, чтобы занять следующий
        Integer clientId = dbManager.getMaxIdFromDataBase() + 1;
        client.setClientName(request.getParameter("name"));
        client.setType(request.getParameter("clientType"));
        if(request.getParameter("clientType").equals("Физическое лицо")){
            client.setType("1");
        }
        else{
            client.setType("2");
        }
        //Добавляем сегодняшнюю дату
        client.setAdded(LocalDate.now());
        client.setClientid(clientId);

        //Создаём адрес
        AddressEntity address = new AddressEntity();
        address.setIp(request.getParameter("ipAddress"));
        address.setMac(request.getParameter("macAddress"));
        address.setModel(request.getParameter("model"));
        address.setPostaddress(request.getParameter("postaddress"));
        address.setClientid(client);

        PrintWriter writer = response.getWriter();
        writer.println("Method POST 11a from AddServlet\n");
        writer.println(client.getClientName() + "\n" + client.getType());

        dbManager.writeClient(client);
        dbManager.writeAddress(address);

        String contextPath = request.getContextPath();
        response.sendRedirect(contextPath + "/viewList?mode=last10");
    }
}
