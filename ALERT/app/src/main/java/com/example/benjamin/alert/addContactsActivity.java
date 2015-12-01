package com.example.benjamin.alert;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.telephony.PhoneNumberUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.BatchUpdateException;

public class addContactsActivity extends AppCompatActivity {

    private String fileName = "emergencyContacts.txt";
    EditText phoneNumber;
    PhoneNumberFormattingTextWatcher phoneWatcher = new PhoneNumberFormattingTextWatcher();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contacts);
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        phoneNumber = (EditText) findViewById(R.id.phoneNumberField);
        phoneNumber.addTextChangedListener(phoneWatcher);

        Button addCnt = (Button) findViewById(R.id.addContact);
        addCnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    saveNewContact();
                    Toast.makeText(getBaseContext(), "Contact Saved", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private boolean saveNewContact() throws IOException {

        //Log.d("add contact", "cont");
        if(findViewById(R.id.nameField) == null || findViewById(R.id.nameField).toString() == "") {
            return false;
        }
        EditText name = (EditText) findViewById(R.id.nameField);

        if(findViewById(R.id.phoneNumberField) == null || phoneNumber.getText().toString() == "") {
            Toast.makeText(getBaseContext(), "Please enter a name",Toast.LENGTH_SHORT).show();
            return false;
        }
        else if (!isPhoneNumber(phoneNumber.getText().toString()))
        {
            Toast.makeText(getBaseContext(), "Phone Number is not valid",Toast.LENGTH_SHORT).show();
            return false;
        }

//        Log.d("name", name.getText().toString());
//        Log.d("phone", phoneNumber.getText().toString());
        String contact = "[" + name.getText().toString() + ":" + phoneNumber.getText().toString() + "]:";



        // Create/Open Emergency Contact File
        try {
            FileOutputStream outputStream = openFileOutput(fileName, Context.MODE_APPEND);
            Log.d("created", "c");
            outputStream.write(contact.getBytes());
            outputStream.close();
            Log.d("created", "d");
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Read file
        FileInputStream in = null;
        String s="";
        try {
            Log.d("created", "e");
            in = openFileInput(fileName);
            Log.d("created", "f");
            InputStreamReader InputRead= new InputStreamReader(in);

            char[] inputBuffer= new char[100];

            int charRead;

            while ((charRead=InputRead.read(inputBuffer))>0) {
                // char to string conversion
                String readstring=String.copyValueOf(inputBuffer,0,charRead);
                s +=readstring;
            }
            InputRead.close();
            //Toast.makeText(getBaseContext(), s,Toast.LENGTH_SHORT).show();

            // delete file
            //deleteFile(fileName);
        }
        finally {
            if (in != null) {
                in.close();
            }
        }

        return true;

    }

    private boolean isPhoneNumber(String number){
        int count = 0;
        for (char c:number.toCharArray()){
            if (c >= 48 && c <= 57) {
                count++;
            }
        }
        if (count >= 10 && count <= 11)
        {
            return true;
        }
        return false;
    }

}
