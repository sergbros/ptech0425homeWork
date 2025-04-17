package com.example.demo3.servlets;

import com.example.demo3.ClientTest;
import com.example.demo3.repositories.ClientRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

import static com.example.demo3.DBManager.createRecord;

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
        ClientTest clientTest = new ClientTest();
        clientTest.setClientName(request.getParameter("name"));
        clientTest.setClientType(request.getParameter("clientType"));

        PrintWriter writer = response.getWriter();
        writer.println("Method POST 11a from AddServlet\n");
        writer.println(clientTest.getClientName() + "\n" + clientTest.getClientType());
    }
}
