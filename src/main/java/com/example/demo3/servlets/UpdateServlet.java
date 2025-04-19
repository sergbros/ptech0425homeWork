package com.example.demo3.servlets;

import com.example.demo3.DBManager;
import com.example.demo3.beans.UpdateService;
import com.example.demo3.repositories.ClientRepository;
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
    private ClientRepository clientRepository;

    @EJB
    private DBManager dbManager;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");

        PrintWriter writer = response.getWriter();
        writer.println("Method GET from UpdateAddServlet\n");
        writer.println("<br>");
        writer.println("Будем редактировать клиента с номером " + request.getParameter("clientNumber"));
        writer.println("<br>");
        writer.println("<jsp:include page=\"webapp\\menu.jsp\">");

        if(updateService.update(Integer.parseInt(request.getParameter("clientNumber")))) {
            writer.println("<br>");
            writer.println("Похоже, номер клиента 1.");
        }
        else {
            writer.println("<br>");
            writer.println("Похоже, номер клиента НЕ 1.");
        }

        writer.println(clientRepository.read());
        writer.println(dbManager.readAllUsersList());

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        //Client client = new Client();
        //client.setClientName(request.getParameter("name"));
        //client.setClientType(request.getParameter("clientType"));

        PrintWriter writer = response.getWriter();
        writer.println("Method POST from UpdateAddServlet\n");
        //writer.println(client.getClientName() + "\n" + client.getClientType());

    }
}
