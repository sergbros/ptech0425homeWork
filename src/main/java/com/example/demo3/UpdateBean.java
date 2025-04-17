package com.example.demo3;

import jakarta.ejb.Singleton;

@Singleton
public class UpdateBean implements UpdateService {

    public boolean update(Integer clientId){
        return clientId==1;
    }
}
