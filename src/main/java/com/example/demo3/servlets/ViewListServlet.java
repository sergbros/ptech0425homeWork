package com.example.demo3.servlets;

import com.example.demo3.DBManager;
import com.example.demo3.beans.ViewListService;
import com.example.demo3.repositories.entities.AddressEntity;
import com.example.demo3.repositories.entities.ClientEntity;
import jakarta.ejb.EJB;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
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

        if(request.getParameter("mode").equals("error")){
            String mode = String.valueOf(request.getParameter("mode"));
            String error = String.valueOf(request.getParameter("error"));
            viewListService.viewList(request, response, mode, error);
        }
        else{
            String mode = viewListService.viewList(String.valueOf(request.getParameter("mode")));
            viewListService.viewList(request, response, mode);
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");

        String mode = viewListService.viewList(String.valueOf(request.getParameter("mode")));
        viewListService.viewList(request, response, mode);
    }
}
