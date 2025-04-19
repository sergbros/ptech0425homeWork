package com.example.demo3.servlets;

import com.example.demo3.DBManager;
import com.example.demo3.beans.ViewListService;
import com.example.demo3.repositories.ClientRepository;
import com.example.demo3.repositories.entities.ClientEntity;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ViewListServlet", value = "/viewList")
public class ViewListServlet extends HttpServlet {

    @EJB
    private ViewListService viewListService;

    @EJB
    private DBManager dbManager;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");

        List<ClientEntity> clientEntities = new ArrayList<>();

        switch (viewListService.viewList(String.valueOf(request.getParameter("mode")))){
            case ("full"):
                clientEntities = dbManager.readAllUsersList();
                break;
            case ("last10"):
                clientEntities = dbManager.readTop10LastUsersList();
                break;
            default:
                clientEntities = dbManager.readAllUsersList();
                break;
        }

        PrintWriter out = response.getWriter();

        out.println("<html><head>");
        out.println("<meta charset=\"UTF-8\">");
        out.println("<title>ViewList</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Таблица всех пользователей</h1>");

        //Таблица
        out.println("<table border=\"0\">");
        //Заголовок
        out.println("<tr>");
        out.println("<td><p align=\"center\">Number</td>");
        out.println("<td bgcolor=\"#C0C0C0\"><p align=\"center\">Name</td>");
        out.println("<td><p align=\"center\">Type</td>");
        out.println("<td bgcolor=\"#C0C0C0\"><p align=\"center\">Address</td>");
        out.println("<td><p align=\"center\">Edit</td>");
        out.println("<td bgcolor=\"#C0C0C0\"><p align=\"center\">Удаление</td>");
        out.println("</tr>");
        //Строка
        for (ClientEntity cl : clientEntities) {
            out.println("<tr>");
            out.println("<td>" + cl.getId() + "</td>");
            out.println("<td bgcolor=\"#C0C0C0\">" + cl.getClientName() + "</td>");
            if (Integer.parseInt(cl.getType()) == 1) {
                out.println("<td>" + "Физическое лицо" + "</td>");
            } else {
                out.println("<td>" + "Юридическое лицо" + "</td>");
            }
            out.println("<td bgcolor=\"#C0C0C0\">" + "Адрес" + "</td>");
            //out.println("<td>" + "<a href=\"update?clientNumber=" + cl.getClientId() + ">Редактировать</a>" + "</td>");
            out.println("<td>" + "<a href=\"update?clientNumber=" + cl.getId() + "\">Редактировать</a>" + "</td>");
            out.println("<td>" + "<a href=\"delete?clientNumber=" + cl.getId() + "\">Удалить</a>" + "</td>");
            out.println("</tr>");
        }
        //конец таблицы
        out.println("</table>");


        out.println("</body></html>");
    }
}
