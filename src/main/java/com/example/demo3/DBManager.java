package com.example.demo3;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceContext;
import lombok.extern.java.Log;



@Log
public class DBManager {
    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");

    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public static void createRecord() {
        try {
            EntityManager entityManager = getEntityManager();
            entityManager.getTransaction().begin();

            Client ce = new Client();
            ce.setClient_name("Client1" + System.currentTimeMillis());

            entityManager.persist(ce);
            entityManager.getTransaction().commit();
        }
        catch (Exception e) {
            log.info(e.getMessage());
        }
    }
    public static void retrieveRecord() { }
    public static void updateRecord() { }
    public static void deleteRecord() { }
}
