package com.example.demo3.beans;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface ViewListService {

    String viewList(String mode);

    void viewList(HttpServletRequest request, HttpServletResponse response, String mode) throws ServletException, IOException;

}
