package com.example.demo3.servlets;

import com.example.demo3.DBManager;
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
import java.util.List;

@WebServlet(name = "ViewListServlet", value = "/viewList")
public class ViewListServlet extends HttpServlet {

    @EJB
    private ClientRepository clientRepository;

    @EJB
    private DBManager dbManager;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        //PrintWriter writer = response.getWriter();
        PrintWriter out = response.getWriter();
        try {
            String url = "jdbc:postgresql://localhost:5432/postgres";
            String username = "postgres";
            String password = "1q2w3E4R";
            Object obj = Class.forName("org.postgresql.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, username, password)) {
                //writer.println("Connection to ProductDB succesfull!");

                //Statement statement = conn.createStatement();
                //ResultSet resultSet = statement.executeQuery("SELECT * FROM client");
                //ResultSet resultSet =
                List<ClientEntity> clientEntities = clientRepository.read();
//                List<ClientObject> clientsAll = new ArrayList<>();
//                for (ClientEntity clientEntity : clientEntities) {
//                    clientsAll.add(clientEntity.toClientObject());
//                }
                //clientEntities = clientRepository.read();
//                while (resultSet.next()) {
//                    ClientTest clientTest = new ClientTest();
//                    clientTest.setClientId(resultSet.getInt(1));
//                    clientTest.setClientName(resultSet.getString(2));
//                    clientTest.setClientType(resultSet.getString(3));
//                    clientTest.setClientAddedData(resultSet.getString(4));
//                    clientEntities.add(clientTest);
//                    //writer.println("<br>" + id + " " + name + " " + type + " " + addedDate);
//                    //Client client = new Client(id, name, type);
//                    //clients.add(client);
//                }

                out.println("<html><head>");
                out.println("<meta charset=\"UTF-8\">");
                out.println("<title>ViewList</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Таблица всех пользователей</h1>");

                //out.println("<form action=\"auth\" method=\"post\">");
                //out.println("<label>Логин</label>");
                //out.println("<input type=\"text\" name=\"login\" /><br><br>");
                //out.println("<p hidden=\"false\">Логин не валиден</p>");
                //out.println("<label>Пароль</label>");
                //out.println("<input type=\"password\" name=\"password\" /><br><br>");
                //out.println("<input type=\"submit\" name=\"singin\" value=\"Войти\">");
                //out.println("</form>");

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
                    if(Integer.parseInt(cl.getType()) == 1){
                        out.println("<td>" + "Физическое лицо" + "</td>");
                    }
                    else{
                        out.println("<td>" + "Юридическое лицо" + "</td>");
                    }
                    out.println("<td bgcolor=\"#C0C0C0\">" + "Адрес" + "</td>");
                    //out.println("<td>" + "<a href=\"update?clientNumber=" + cl.getClientId() + ">Редактировать</a>" + "</td>");
                    out.println("<td>" + "<a href=\"update?clientNumber=" + cl.getId() +"\">Редактировать</a>" + "</td>");
                    out.println("<td>" + "<a href=\"delete?clientNumber=" + cl.getId() +"\">Удалить</a>" + "</td>");
                    out.println("</tr>");
                }
                //конец таблицы
                out.println("</table>");


                out.println("</body></html>");
            }
        } catch (Exception ex) {

            out.println("Connection failed...\n");
            out.println(ex);
        } finally {
            out.close();
        }
    }
}
