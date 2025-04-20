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
        ClientEntity client = new ClientEntity(request);
        //Получаем из БД максимальный существующий Id, чтобы занять следующий
        Integer clientId = dbManager.getMaxIdFromDataBase() + 1;
        client.setClientid(clientId);

        //Создаём адрес
        AddressEntity address = new AddressEntity(request, client);

        //TODO Проверка корректности заполнения полей

        dbManager.writeClient(client);
        dbManager.writeAddress(address);

        String contextPath = request.getContextPath();
        response.sendRedirect(contextPath + "/viewList?mode=full");
    }
}
