package com.example.demo3.beans;

import com.example.demo3.repositories.entities.ClientEntity;
import jakarta.ejb.Singleton;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

@Singleton
public class DBManagerBean implements DBManagerService{
    @PersistenceContext
    private EntityManager em;

    public List<ClientEntity> readClientFullList(){
        return em.createQuery("SELECT c FROM ClientEntity c", ClientEntity.class).getResultList();
    }
}
