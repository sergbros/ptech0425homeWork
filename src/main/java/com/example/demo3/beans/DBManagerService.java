package com.example.demo3.beans;

import com.example.demo3.repositories.entities.ClientEntity;

import java.util.List;

public interface DBManagerService {
    public List<ClientEntity> readClientFullList();
}
