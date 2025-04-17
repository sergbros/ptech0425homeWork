package com.example.demo3;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Setter
@Getter
@Entity @Table(name="client242423423")
public class Client234234234 {

    @Id
    @GeneratedValue
    @Column(name="clientid", unique = true, nullable = false)
    private Integer clientId;

    @Column(name="client_name", length=100)
    private String client_name;

    @Column(name="type")
    private String clientType;

    @Column(name="added")
    private Date added;
}
