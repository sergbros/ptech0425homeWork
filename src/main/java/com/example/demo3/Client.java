package com.example.demo3;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class Client {
    private Integer clientId;
    private String clientName;
    private String clientType;
    //TODO переделать дату на тип Дата
    private String clientAddedData;
    //private List<ClientAddress> clientAdresses = new ArrayList<>();

}
