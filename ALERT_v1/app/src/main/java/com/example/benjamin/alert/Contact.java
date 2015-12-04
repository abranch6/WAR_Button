package com.example.benjamin.alert;

/**
 * Class to represent a contact
 * Created by Dhar on 11/30/15.
 */
public class Contact {

    private int id;
    private String name;
    private String phone;

    public Contact(){}

    public Contact(String name, String phone) {
        super();
        this.name = name;
        this.phone = phone;
    }

    //getters & setters

    @Override
    public String toString() {
        return "Contact [id=" + id + ", name=" + name + ",phone=" + phone
                + "]";
    }

    public int getId()
    {
        return this.id;
    }

    public String getName()
    {
        return this.name;
    }

    public String getPhone()
    {
        return this.phone;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public void setName(String name)
    {
        this.name  = name;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }
}