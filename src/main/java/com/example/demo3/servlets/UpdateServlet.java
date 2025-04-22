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

@WebServlet(name = "UpdateServlet", value = "/update")
public class UpdateServlet extends HttpServlet {

    @EJB
    private UpdateService updateService;

    @EJB
    private DBManager dbManager;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");

        PrintWriter writer = response.getWriter();

        writer.println("<jsp:include page=\"webapp\\menu.jsp\">");

        AddressEntity addressEntity = dbManager.readAddresById(Integer.valueOf(request.getParameter("addr")));
        updateService.update(request, response, addressEntity);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        //Создаём клиента
        ClientEntity client = new ClientEntity(request);
        //Создаём адрес
        Integer reqClientId = Integer.valueOf(request.getParameter("addressid"));
        AddressEntity address = new AddressEntity(request, client, reqClientId);

        //Проверка корректности заполнения полей
        String errorMessage = updateService.check(address);

        if(errorMessage.length() <= 0) {
            dbManager.updateClient(client);
            dbManager.updateAddress(address);

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
