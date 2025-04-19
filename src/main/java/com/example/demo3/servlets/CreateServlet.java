package com.example.demo3.servlets;

import com.example.demo3.ClientObject;
import com.example.demo3.DBManager;
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
        ClientEntity client = new ClientEntity();
        client.setClientName(request.getParameter("name"));
        client.setType(request.getParameter("clientType"));

        PrintWriter writer = response.getWriter();
        writer.println("Method POST 11a from AddServlet\n");
        writer.println(client.getClientName() + "\n" + client.getType());

        dbManager.writeClient(client);
    }
}
