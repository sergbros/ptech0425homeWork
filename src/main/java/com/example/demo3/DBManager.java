package com.example.demo3;

import com.example.demo3.repositories.entities.ClientEntity;
import jakarta.ejb.Singleton;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceContext;
import lombok.extern.java.Log;

import java.util.List;

import static java.rmi.server.LogStream.log;

@Log
@Singleton
public class DBManager {
    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");

    @PersistenceContext
    private EntityManager em;

    public List<ClientEntity> readAllUsersList() {
        log("DBManager: Read all users");
        return em.createQuery("SELECT c FROM ClientEntity c", ClientEntity.class).getResultList();
    }

    public void writeClient(ClientEntity client) {
        log("DBManager: Write client");
        client.setId(getMaxIdFromDataBase() + 1);
        em.persist(client);
    }

    public Integer getMaxIdFromDataBase() {
        log("DBManager: getMaxIdFromDataBase");
        return em.createQuery("SELECT MAX(c.id) FROM ClientEntity c", Integer.class).getSingleResult();
    }
}
