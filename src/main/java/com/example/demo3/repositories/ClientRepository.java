package com.example.demo3.repositories;

import com.example.demo3.repositories.entities.ClientEntity;
import jakarta.ejb.Singleton;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

@Singleton
public class ClientRepository {

    @PersistenceContext
    private EntityManager em;

    public List<ClientEntity> read(){
        return em.createNativeQuery("SELECT * FROM public.client", ClientEntity.class).getResultList();
    }

}

