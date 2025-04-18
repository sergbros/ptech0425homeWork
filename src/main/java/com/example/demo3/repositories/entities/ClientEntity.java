package com.example.demo3.repositories.entities;

import com.example.demo3.ClientObject;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "client")
public class ClientEntity{
    @Id
    @Column(name = "clientid", nullable = false)
    private Integer id;

    @Column(name = "client_name", nullable = false)
    private String clientName;

    @Column(name = "type")
    private String type;

    @Column(name = "added")
    private LocalDate added;

    @Override
    public String toString() {
        return "ClientEntity{" +
                "clientid=" + id +
                ", clientName='" + clientName + '\'' +
                // добавьте другие поля
                '}';
    }

//    public ClientObject toClientObject(){
//        return new ClientObject(id, clientName, type, added);
//    }
}