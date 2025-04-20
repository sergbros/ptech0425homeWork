package com.example.demo3.servlets;

import com.example.demo3.DBManager;
import com.example.demo3.beans.UpdateService;
import com.example.demo3.repositories.entities.AddressEntity;
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

        AddressEntity addressEntity = dbManager.readUserById(Integer.valueOf(request.getParameter("clientid")));
        //Форма редактирования клиента
        writer.println("<br>");
        String contextPath = request.getContextPath();
        writer.println("<form action=\"" + contextPath + "/update\" method=\"POST\">");
        writer.println("<p>Имя: <input type=\"text\" size=\"20\" name=\"name\" value=\"" + addressEntity.getClientid().getClientName() + "\"></p>");
        writer.println("<p>Тип клиента: <select name=\"clientType\" size=\"1\">");
        if(addressEntity.getClientid().getType().equals("1")) {
            writer.println("<option selected>Физическое лицо</option>");
            writer.println("<option>Юридическое лицо</option>");
        }
        else{
            writer.println("<option>Физическое лицо</option>");
            writer.println("<option selected>Юридическое лицо</option>");
        }
        writer.println("</select></p>");
        writer.println("<p>id Клиента: <input type=\"text\" size=\"25\" name=\"clientid\" disabled value=\"" + addressEntity.getClientid().getClientid() + "\"></p>");
        writer.println("<p>дата добавления: <input type=\"text\" size=\"25\" name=\"added\" disabled value=\"" + addressEntity.getClientid().getAdded() + "\"></p>");
        writer.println("<p>Сетевой IP адрес устройства: <input type=\"text\" size=\"25\" name=\"ipAddress\" value=\"" + addressEntity.getClientid().getClientName() + "\"></p>");
        writer.println("<p>Физический адрес устройства (MAC): <input type=\"text\" size=\"25\" name=\"macAddress\" value=\"" + addressEntity.getMac() + "\"></p>");
        writer.println("<p>Модель устройства: <input type=\"text\" size=\"25\" name=\"model\" value=\"" + addressEntity.getModel() + "\"></p>");
        writer.println("<p>Адрес места нахождения: <input type=\"text\" size=\"25\" name=\"postaddress\" value=\"" + addressEntity.getPostaddress() + "\"></p>");
        writer.println("<p><input type=\"submit\" name=\"Edit\" value=\"Применить изменения\"></p>");
        writer.println("</form>");
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
