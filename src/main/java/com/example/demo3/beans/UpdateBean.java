package com.example.demo3.beans;

import jakarta.ejb.Stateless;

@Stateless
public class UpdateBean implements UpdateService {

    public boolean update(Integer clientId){
        return clientId==1;
    }
}
