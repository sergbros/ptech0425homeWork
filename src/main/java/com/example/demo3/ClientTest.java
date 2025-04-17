package com.example.demo3;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientTest {
    private Integer clientId;
    private String clientName;
    private String clientType;
    //TODO переделать дату на тип Дата
    private String clientAddedData;
    //private List<ClientAddress> clientAdresses = new ArrayList<>();

}
