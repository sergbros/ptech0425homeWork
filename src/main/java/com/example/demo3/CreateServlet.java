package com.example.demo3;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "CreateServlet", value = "/create")
public class CreateServlet extends HttpServlet {

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
        Client client = new Client();
        client.setClientName(request.getParameter("name"));
        client.setClientType(request.getParameter("clientType"));

        PrintWriter writer = response.getWriter();
        writer.println("Method POST 11a from AddServlet\n");
        writer.println(client.getClientName() + "\n" + client.getClientType());

    }
}
