package com.example.demo3.repositories.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "client")
public class ClientEntity implements java.io.Serializable {
    private static final long serialVersionUID = -2475931424403681430L;
    @Id
    @Column(name = "clientid", nullable = false)
    private Integer id;

    @Column(name = "client_name", nullable = false)
    private String clientName;

    @Column(name = "type")
    private String type;

    @Column(name = "added")
    private LocalDate added;

}