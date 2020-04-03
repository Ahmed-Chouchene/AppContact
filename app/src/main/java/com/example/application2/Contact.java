package com.example.application2;

public class Contact {

    private String name;
    private int phone;
    private int id;

    public Contact(String name, int phone) {
        this.name = name;
        this.phone = phone;
    }

    public Contact(int id, String name, int phone) {
        this.id = id;
        this.name = name;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
