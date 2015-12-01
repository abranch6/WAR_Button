package com.example.benjamin.alert;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class AddContactsLocation extends AppCompatActivity {

    protected Button addNewContactBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contacts_location);

        // Add New Contact Button Action
        addNewContactBtn = (Button) findViewById(R.id.button2);
        Toolbar tlBar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(tlBar);

        addNewContactBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                addNewContact();

            }
        });

    }

    private void addNewContact(){
        Log.d("newContact", "AddNewContact selected");
        Intent intent = new Intent(this, AddNewContactsForm.class);
        startActivity(intent);
    }
}
