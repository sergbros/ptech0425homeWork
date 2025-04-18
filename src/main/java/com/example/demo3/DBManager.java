package com.example.demo3;

import com.example.demo3.repositories.entities.ClientEntity;
import jakarta.ejb.Singleton;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceContext;
import lombok.extern.java.Log;

import java.util.List;

@Log
@Singleton
public class DBManager {
    @PersistenceContext
    private static EntityManager em;

    public static List<ClientEntity> readClientFullList(){
        return em.createQuery("SELECT c FROM ClientEntity c", ClientEntity.class).getResultList();
    }
    public static void retrieveRecord() { }
    public static void updateRecord() { }
    public static void deleteRecord() { }
}
