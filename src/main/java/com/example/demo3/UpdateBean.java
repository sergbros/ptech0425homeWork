package com.example.demo3;

import jakarta.ejb.Singleton;
import jakarta.ejb.Stateless;

@Stateless
public class UpdateBean implements UpdateService {

    public boolean update(Integer clientId){
        return clientId==1;
    }
}
