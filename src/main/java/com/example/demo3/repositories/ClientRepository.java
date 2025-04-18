package com.example.demo3.repositories;

import com.example.demo3.DBManager;
import com.example.demo3.beans.DBManagerBean;
import com.example.demo3.repositories.entities.ClientEntity;
import jakarta.ejb.EJB;
import jakarta.ejb.Singleton;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

@Singleton
public class ClientRepository {

    @EJB
    private DBManagerBean dbManagerBean;

    public List<ClientEntity> read(){
        return dbManagerBean.readClientFullList();
    }

}

