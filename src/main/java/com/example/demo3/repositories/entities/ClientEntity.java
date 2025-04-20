package com.example.demo3.repositories.entities;

import jakarta.persistence.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "client")
public class ClientEntity{
    @Id
    @Column(name = "clientid", nullable = false)
    private Integer clientid;

    @Column(name = "client_name", nullable = false)
    private String clientName;

    @Column(name = "type")
    private String type;

    @Column(name = "added")
    private LocalDate added;

    public ClientEntity() {

    }

    @Override
    public String toString() {
        return "ClientEntity{" +
                "clientid=" + clientid +
                ", clientName='" + clientName + '\'' +
                // добавьте другие поля
                '}';
    }

    public ClientEntity(HttpServletRequest request) {
        this.setClientName(request.getParameter("name"));

        if (request.getParameter("clientType").equals("Физическое лицо")) {
            this.setType("1");
        } else {
            this.setType("2");
        }

        if (request.getParameter("added") != null && !request.getParameter("added").isEmpty()) {
            this.setAdded(LocalDate.parse(request.getParameter("added")));
        } else {
            this.setAdded(LocalDate.now());
        }

        this.setClientid(Integer.valueOf(request.getParameter("clientid")));
    }
}