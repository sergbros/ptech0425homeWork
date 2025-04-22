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
import java.util.List;

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

        //AddressEntity addressEntity = dbManager.readUserById(Integer.valueOf(request.getParameter("clientid")));
        AddressEntity addressEntity = dbManager.readAddresById(Integer.valueOf(request.getParameter("addr")));
        ClientEntity clientEntity = addressEntity.getClientid();

        List<AddressEntity> clientAddressesList = dbManager.readAddressListByClientId(clientEntity.getClientid());

        dbManager.deleteAddress(addressEntity);
        //Проверка привязки клиента к другому адресу
        if(clientAddressesList.size() < 2){
            dbManager.deleteClient(clientEntity);
        }

        String contextPath = request.getContextPath();
        response.sendRedirect(contextPath + "/viewList?mode=full");
    }
}
