package com.example.lenovo.otp.Model;

/**
 * Created by lenovo on 19-12-2016.
 */

public class Contacts {

    private String name , number;

    public Contacts(String name, String number) {
        this.name = name;
        this.number = number;
    }

    public Contacts(String name)
    {
        this.name=name;
    }

    public Contacts(){};

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
