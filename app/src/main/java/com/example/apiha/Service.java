package com.example.apiha;

import java.util.ArrayList;

public class Service {
    public int id_service;
    public String name;
    public String coast;

    public Service(int id_service, String name, String coast) {
        this.id_service = id_service;
        this.name = name;
        this.coast = coast;
    }

    public int getId_service() {
        return id_service;
    }

    public void setId_service(int id_service) {
        this.id_service = id_service;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCoast() {
        return coast;
    }

    public void setCoast(String coast) {
        this.coast = coast;
    }
}
