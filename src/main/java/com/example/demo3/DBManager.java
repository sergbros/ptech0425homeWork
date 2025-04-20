package com.example.demo3;

import com.example.demo3.repositories.entities.AddressEntity;
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

    public List<AddressEntity> readAllAddressesList() {
        return em.createQuery("SELECT DISTINCT a FROM AddressEntity a " +
                "LEFT JOIN FETCH a.clientid", AddressEntity.class).getResultList();
    }

    public List<AddressEntity> readAllUsersByName(String clientName) {
        String pattern = "%" + clientName.trim() + "%";
        return em.createQuery("SELECT DISTINCT a FROM AddressEntity a " +
                "LEFT JOIN FETCH a.clientid WHERE a.clientid.clientName LIKE :pattern", AddressEntity.class)
                .setParameter("pattern", pattern)
                .getResultList();
    }

    public List<AddressEntity> readAllUsersByNameOrAddressAndType(String namePart, String typeClient) {
        if (namePart == null) namePart = "";
        String pattern = "%" + namePart + "%";
        return em.createQuery("SELECT DISTINCT a FROM AddressEntity a " +
                        "LEFT JOIN FETCH a.clientid " +
                        "WHERE (a.clientid.clientName LIKE :pattern OR a.postaddress LIKE :pattern) " +
                        "AND a.clientid.type = :typeClient" , AddressEntity.class)
                .setParameter("pattern", pattern)
                .setParameter("typeClient", typeClient)
                .getResultList();
    }

    public List<ClientEntity> readAllUsersListOld() {
        return em.createQuery("SELECT c FROM ClientEntity c", ClientEntity.class).getResultList();
    }

    public List<ClientEntity> readTop10LastUsersList() {
        return em.createQuery("SELECT c FROM ClientEntity c ORDER BY c.clientid DESC", ClientEntity.class)
                .setMaxResults(10)
                .getResultList();
    }

    public ClientEntity readUserById(int clientId) {
        return em.createQuery("SELECT c FROM ClientEntity c WHERE c.clientid = :clientId", ClientEntity.class)
                .setParameter("clientId", clientId)
                .getSingleResult();
    }

    public void writeClient(ClientEntity client) {
        em.persist(client);
    }

    public void writeAddress(AddressEntity address) {
        em.persist(address);
    }

    public Integer getMaxIdFromDataBase() {
        return em.createQuery("SELECT MAX(c.clientid) FROM ClientEntity c", Integer.class).getSingleResult();
    }
}
