package com.example.benjamin.alert;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import static android.R.layout.simple_list_item_1;

public class Contacts extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        MySQLiteHelper db = new MySQLiteHelper(this);

        List<Contact> contacts = db.getAllContacts();

        String[] contactsArray = new String[contacts.size()];

        int i = 0;
        for(Contact contact: contacts)
        {
            contactsArray[i++] = contact.getName() + " - " + contact.getPhone();
        }

        ArrayAdapter<String> contactsArrayAdapter = new ArrayAdapter<String>(this, simple_list_item_1, contactsArray);

        ListView contactLists = (ListView)findViewById(R.id.listView1);

        contactLists.setAdapter(contactsArrayAdapter);

    }

    public void add(View view)
    {
        boolean flag = true;

        if(flag) {
            Intent intent = new Intent(this, AddNewContactsForm.class);
            startActivity(intent);
            return;
        }

    }
}
