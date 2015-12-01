package com.example.benjamin.alert;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.List;

public class DB_Test extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db__test);
        MySQLiteHelper db = new MySQLiteHelper(this);

        /**
         * CRUD Operations
         * */
        // add Contacts
//        db.addContact(new Contact("Aditi Dhar", "123-456-789"));
//        db.addContact(new Contact("Ashwyn", "01298998798"));
//        db.addContact(new Contact("Saheeb", "09929879828"));
        db.addContact(new Contact("Ben Le", "2085300606"));

        // get all books
        List<Contact> list = db.getAllContacts();

        for(Contact contact: list)
        {
            System.out.println(contact);
        }


        // delete one book
        db.deleteContact(list.get(0));

        list = db.getAllContacts();

        // get all books
        for(Contact contact: list)
        {
            System.out.println(contact);
        }
    }

}
