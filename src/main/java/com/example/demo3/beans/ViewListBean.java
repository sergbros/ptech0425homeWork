package com.example.demo3.beans;

import jakarta.ejb.Stateless;

@Stateless
public class ViewListBean implements ViewListService {

    public boolean viewList(Integer clientId){
        return clientId==1;
    }
}
