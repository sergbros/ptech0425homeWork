package com.example.demo3.repositories.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "adress")
public class AdressEntity {
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "clientid")
    private ClientEntity clientid;

    @Column(name = "ip")
    private String ip;

    @Column(name = "mac", nullable = false)
    private String mac;

}