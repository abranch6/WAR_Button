package com.example.benjamin.alert;

/**
 * Created by Benjamin on 11/15/2015.
 */
public class Contact {

    private String name, PhoneNumber;

    public Contact(String name, String PhoneNumber){
        this.name = name;
        this.PhoneNumber = PhoneNumber;
    }

    public String getName(){
        return name;
    }

    public String getPhoneNumber(){
        return PhoneNumber;
    }

}
