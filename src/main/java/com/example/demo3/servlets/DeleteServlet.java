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

@WebServlet(name = "DeleteServlet", value = "/delete")
public class DeleteServlet extends HttpServlet {

    @EJB
    private DBManager dbManager;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");

        PrintWriter writer = response.getWriter();
        writer.println("Method GET from DELETE Servlet\n");
        writer.println("Будем УДАЛЯТЬ клиента с номером " + request.getParameter("clientid"));

        AddressEntity addressEntity = dbManager.readUserById(Integer.valueOf(request.getParameter("clientid")));
        ClientEntity clientEntity = addressEntity.getClientid();

        dbManager.deleteAddress(addressEntity);
        //TODO Нужна проверка привязки клиента к другому адресу
        if(true){
            dbManager.deleteClient(clientEntity);
        }

        String contextPath = request.getContextPath();
        response.sendRedirect(contextPath + "/viewList?mode=full");
    }
}
