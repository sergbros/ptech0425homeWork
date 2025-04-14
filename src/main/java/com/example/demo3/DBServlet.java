package com.example.demo3;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

@WebServlet(name = "DBServlet", value = "/db")
public class DBServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //ArrayList<Client> clients = new ArrayList<Client>();
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();
        try{
            //String url = "jdbc:mysql://localhost/productdb?serverTimezone=Europe/Moscow&useSSL=false";
            String url = "jdbc:postgresql://localhost:5432/postgres";
            String username = "postgres";
            String password = "1q2w3E4R";
            Object obj = Class.forName("org.postgresql.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, username, password)){
                writer.println("Connection to ProductDB succesfull!");

                Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM client");
                while(resultSet.next()) {

                    int id = resultSet.getInt(1);
                    String name = resultSet.getString(2);
                    int type = resultSet.getInt(3);
                    String addedDate = resultSet.getString(4);

                    writer.println("<br>" + id + " " + name + " " + type + " " + addedDate);
                    //Client client = new Client(id, name, type);
                    //clients.add(client);
                }
            }
        }
        catch(Exception ex){
            writer.println("Connection failed...\n");
            writer.println(ex);
        }
        finally {
            writer.close();
        }
    }

}
