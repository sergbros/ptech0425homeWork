package com.example.demo3.servlets;

import com.example.demo3.DBManager;
import com.example.demo3.beans.UpdateService;
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
        writer.println("Method GET from UpdateAddServlet\n");
        writer.println("<br>");
        writer.println("Будем редактировать клиента с номером " + request.getParameter("clientId"));
        writer.println("<br>");
        writer.println("<jsp:include page=\"webapp\\menu.jsp\">");

        //Форма редактирования клиента
        writer.println("<br>");
        writer.println("<form action=\"${pageContext.request.contextPath}/update\" method=\"POST\">");
        writer.println("<p>Имя: <input type=\"text\" size=\"20\" name=\"name\" value=\"dfdf\"></p>");
        writer.println("<p>Тип клиента: <select name=\"clientType\" size=\"1\">");
        writer.println("<option selected>Физическое лицо</option>");
        writer.println("<option>Юридическое лицо</option>");
        writer.println("</select></p>");
        writer.println("<p>Сетевой IP адрес устройства: <input type=\"text\" size=\"25\" name=\"ipAddress\"></p>");
        writer.println("<p>Сетевой IP адрес устройства: <input type=\"text\" size=\"25\" name=\"ipAddress\"></p>");
        writer.println("<p>Физический адрес устройства (MAC): <input type=\"text\" size=\"25\" name=\"macAddress\"></p>");
        writer.println("<p>Модель устройства: <input type=\"text\" size=\"25\" name=\"model\"></p>");
        writer.println("<p>Адрес места нахождения: <input type=\"text\" size=\"25\" name=\"postaddress\"></p>");
        writer.println("<p><input type=\"submit\" name=\"Edit\" value=\"Применить изменения\"></p>");
        writer.println("</form>");

        writer.println(dbManager.readAllAddressesList());

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
