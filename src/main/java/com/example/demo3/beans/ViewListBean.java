package com.example.demo3.beans;

import jakarta.ejb.Stateless;

@Stateless
public class ViewListBean implements ViewListService {

    public String viewList(String mode){

        return mode;
    }
}
