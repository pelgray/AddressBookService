package com.pelgray.domain;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Entity(name = "address")
public class AddressEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @JsonIgnore
    @ManyToOne
    private ClientEntity client;

    AddressEntity() {
    }

    public AddressEntity(String name, ClientEntity client) {
        this.name = name;
        this.client = client;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ClientEntity getClient() {
        return client;
    }

    public void setClient(ClientEntity client) {
        this.client = client;
    }

    @JsonGetter("clientId")
    Integer getClientId() {
        return this.client.getId();
    }

    @JsonSetter("clientId")
    void setClientId(Integer clientId) {
        this.client = new ClientEntity(clientId);
    }

    @Override
    public String toString() {
        return "AddressEntity{" +
                "id=" + id +
                ", name='" + name +
                ", clientId=" + client.getId() + '\'' +
                '}';
    }
}
