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
    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");

    @PersistenceContext
    private EntityManager em;

    public List<ClientEntity> readAllUsersList() {
        return em.createQuery("SELECT c FROM ClientEntity c", ClientEntity .class).getResultList();
    }

    public void writeClient(ClientEntity client) {
        client.setId(3);
        em.persist(client);
    }
}
