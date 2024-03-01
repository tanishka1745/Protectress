package com.example.protectress.Modals;

public class ContactModal {

    String name,number;
    int id;

    public ContactModal(String name, String number,int id) {
        this.name = name;
        this.number = number;
        this.id=id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
