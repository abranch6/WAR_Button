package com.example.benjamin.alert;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

/**
 * Add contacts form
 */
public class AddNewContactsForm extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_contacts_form);


    }

    /**
     * Adds a new contact to the contact database
     * @param view Button
     */
    public void addContact(View view) {
        // Do something in response to button

        EditText name = (EditText) findViewById(R.id.editText);
        String sname = "";
        sname = name.getText().toString();

        EditText phone = (EditText) findViewById(R.id.editText2);
        String sphone = "";
        sphone = phone.getText().toString();

        MySQLiteHelper db = new MySQLiteHelper(this);

        db.addContact(new Contact(sname, sphone));

        boolean flag = true;

        finish();
        if(flag) {
            Intent intent = new Intent(this, Contacts.class);
            startActivity(intent);
            return;
        }

    }
}
