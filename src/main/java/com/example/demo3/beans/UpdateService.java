package com.example.demo3.beans;

import com.example.demo3.repositories.entities.AddressEntity;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public interface UpdateService {
    String check(AddressEntity addressEntities);
    void update(HttpServletRequest request, HttpServletResponse response, AddressEntity addressEntity) throws IOException, ServletException;
}
