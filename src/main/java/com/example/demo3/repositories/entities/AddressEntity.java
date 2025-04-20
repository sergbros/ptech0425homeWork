package com.example.demo3.repositories.entities;

import jakarta.persistence.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "adress")
public class AddressEntity {
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "clientid")
    private ClientEntity clientid;

    @Column(name = "ip")
    private String ip;

    @Column(name = "mac")
    private String mac;

    @Column(name = "model")
    private String model;

    @Column(name = "postaddress")
    private String postaddress;

    public AddressEntity(HttpServletRequest request, ClientEntity clientEntity) {
        //Создаём адрес
        this.setIp(request.getParameter("ipAddress"));
        this.setMac(request.getParameter("macAddress"));
        this.setModel(request.getParameter("model"));
        this.setPostaddress(request.getParameter("postaddress"));
        this.setClientid(clientEntity);
    }

    public AddressEntity() {

    }
}