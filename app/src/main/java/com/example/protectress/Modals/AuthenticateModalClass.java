package com.example.protectress.Modals;

public class AuthenticateModalClass {

    String email,password,fullName;
    int phn;

    public AuthenticateModalClass(String email, String password, String fullName, int phn) {
        this.email = email;
        this.password = password;
        this.fullName = fullName;
        this.phn = phn;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getPhn() {
        return phn;
    }

    public void setPhn(int phn) {
        this.phn = phn;
    }
}
