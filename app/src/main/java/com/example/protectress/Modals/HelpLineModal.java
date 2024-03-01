package com.example.protectress.Modals;

public class HelpLineModal {

    String name,number,details;

    public HelpLineModal(String name, String number, String details) {
        this.name = name;
        this.number = number;
        this.details = details;
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

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
