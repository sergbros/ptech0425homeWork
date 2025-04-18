package com.example.demo3;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ClientObject {
    private Integer clientId;
    private String clientName;
    private String clientType;
    //TODO переделать дату на тип Дата
    private LocalDate clientAddedData;
    //private List<ClientAddress> clientAdresses = new ArrayList<>();

}
