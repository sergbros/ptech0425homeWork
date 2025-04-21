package com.example.demo3;

import com.example.demo3.repositories.entities.AddressEntity;
import com.example.demo3.repositories.entities.ClientEntity;
import jakarta.ejb.Singleton;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.extern.java.Log;

import java.util.List;

@Log
@Singleton
public class DBManager {

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

    public AddressEntity readUserById(Integer clientid) {
        return em.createQuery("SELECT DISTINCT a FROM AddressEntity a " +
                        "LEFT JOIN FETCH a.clientid WHERE a.clientid.clientid = :clientid", AddressEntity.class)
                .setParameter("clientid", clientid)
                .getSingleResult();
    }

    public List<AddressEntity> readAllUsersByNameOrAddressAndType(String namePart, String typeClient) {
        if (namePart == null) namePart = "";
        String pattern = "%" + namePart.toLowerCase() + "%";
        return em.createQuery("SELECT DISTINCT a FROM AddressEntity a " +
                        "LEFT JOIN FETCH a.clientid " +
                        "WHERE (LOWER(a.clientid.clientName) LIKE LOWER(:pattern) OR LOWER(a.postaddress) LIKE LOWER(:pattern)) " +
                        "AND a.clientid.type = :typeClient" , AddressEntity.class)
                .setParameter("pattern", pattern)
                .setParameter("typeClient", typeClient)
                .getResultList();
    }

    public void writeClient(ClientEntity client) {
        em.persist(client);
    }

    public void updateClient(ClientEntity client) {
        em.merge(client);
    }

    public void deleteClient(ClientEntity client) {
        ClientEntity cl = em.find(ClientEntity.class, client.getClientid());
        em.remove(cl);
    }

    public void writeAddress(AddressEntity address) {
        em.persist(address);
    }

    public void updateAddress(AddressEntity address) {
        em.merge(address);
    }

    public void deleteAddress(AddressEntity address) {
        AddressEntity addr = em.find(AddressEntity.class, address.getAddressid());
        em.remove(addr);
    }

    public Integer getMaxClientIdFromDataBase() {
        if(em.createQuery("SELECT MAX(c.clientid) FROM ClientEntity c", Integer.class).getSingleResult() != null) {
            return em.createQuery("SELECT MAX(c.clientid) FROM ClientEntity c", Integer.class).getSingleResult();
        }
        else return 0;
    }

    public Integer getMaxAddressIdFromDataBase() {
        if(em.createQuery("SELECT MAX(adr.addressid) FROM AddressEntity adr", Integer.class).getSingleResult() != null) {
            return em.createQuery("SELECT MAX(adr.addressid) FROM AddressEntity adr", Integer.class).getSingleResult();
        }
        else return 0;
    }
}
