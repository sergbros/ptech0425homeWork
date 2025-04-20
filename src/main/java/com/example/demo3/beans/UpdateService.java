package com.example.demo3.beans;

import com.example.demo3.repositories.entities.AddressEntity;

import java.util.List;

public interface UpdateService {
    String check(AddressEntity addressEntities);
    void update(AddressEntity addressEntities);
}
