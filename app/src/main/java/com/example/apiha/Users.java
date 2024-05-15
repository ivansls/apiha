package com.example.apiha;

public class Users {
    public int id_user;
    public String login;
    public String password;
    public String name;
    public int client_type_id;
    public int discount_id;
    public int visit_numbers;
    public boolean exist;

    public Users(int id_user, String login, String password, String name, int client_type_id, int discount_id, int visit_numbers, boolean exist) {
        this.id_user = id_user;
        this.login = login;
        this.password = password;
        this.name = name;
        this.client_type_id = client_type_id;
        this.discount_id = discount_id;
        this.visit_numbers = visit_numbers;
        this.exist = exist;
    }


    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getClient_type_id() {
        return client_type_id;
    }

    public void setClient_type_id(int client_type_id) {
        this.client_type_id = client_type_id;
    }

    public int getDiscount_id() {
        return discount_id;
    }

    public void setDiscount_id(int discount_id) {
        this.discount_id = discount_id;
    }

    public int getVisit_numbers() {
        return visit_numbers;
    }

    public void setVisit_numbers(int visit_numbers) {
        this.visit_numbers = visit_numbers;
    }

    public boolean isExist() {
        return exist;
    }

    public void setExist(boolean exist) {
        this.exist = exist;
    }
}
