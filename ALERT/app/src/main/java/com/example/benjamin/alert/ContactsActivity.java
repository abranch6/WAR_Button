package com.example.benjamin.alert;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class ContactsActivity extends AppCompatActivity {

    private ArrayList<ArrayList<String>> contactInfo;
    private ArrayList<String> contactArrayListNames;
    private ArrayList<String> contactArrayListNumbers;
    private ArrayList<Contact> contactObjs = new ArrayList<Contact>();
    private String fileName = "emergencyContacts.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);

        // Contact List View
        ListView cListView = (ListView) findViewById(R.id.ContactListView);
        try {
            contactArrayListNames = readContactFileNames();
            contactArrayListNumbers = readContactFileNumbers();
            for (int i = 0; i < contactArrayListNames.size(); i++){
                contactObjs.add(new Contact(contactArrayListNames.get(i), contactArrayListNumbers.get(i)));
            }
        } catch (IOException e) {
            contactArrayListNames = null;
            contactArrayListNumbers = null;
            e.printStackTrace();
        }

        // Array Adapter
        if (contactArrayListNames != null && contactArrayListNumbers != null) {
//            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_2, contactArrayListNames );
//            cListView.setAdapter(arrayAdapter);
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_2, android.R.id.text1, contactObjs) {
                public View getView(int position, View convertView, ViewGroup parent) {
                    View view = super.getView(position, convertView, parent);
                    TextView text1 = (TextView) view.findViewById(android.R.id.text1);
                    TextView text2 = (TextView) view.findViewById(android.R.id.text2);

                    text1.setText(contactObjs.get(position).getName());
                    text2.setText(contactObjs.get(position).getPhoneNumber());
                    return view;
                }
            };
            cListView.setAdapter(arrayAdapter);
            //arrayAdapter.notifyDataSetChanged();
        }




        FloatingActionButton addContactBtn = (FloatingActionButton) findViewById(R.id.addNewContact);

        addContactBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addContact();
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void addContact() {
        Intent intent = new Intent(this, addContactsActivity.class);
        startActivity(intent);
    }

    public ArrayList<String> readContactFileNames() throws IOException {

        ArrayList<String> contactListNames = new ArrayList<String>();

        // Read file
        FileInputStream in = null;
        String s = "";
        try {
            in = openFileInput(fileName);
            InputStreamReader InputRead = new InputStreamReader(in);

            char[] inputBuffer = new char[100];

            int charRead;

            while ((charRead = InputRead.read(inputBuffer)) > 0) {
                // char to string conversion
                String readstring = String.copyValueOf(inputBuffer, 0, charRead);
                s += readstring;
            }
            InputRead.close();
//            Toast.makeText(getBaseContext(), s, Toast.LENGTH_SHORT).show();

            // delete file
            //deleteFile(fileName);
        } finally {
            if (in != null) {
                in.close();
            }
        }


        if (s == "") {
        } else {
            s = s.substring(0, s.length() - 1);

            String[] contactArray = s.split(":");
//            for (String contact : contactArray) {
//                Log.d("contacts", contact);
//            }

            //Extract Names
            for (int i = 0; i < contactArray.length; i += 2) {
                try {
                    if (i < contactArray.length) {
                        contactListNames.add(contactArray[i].substring(1, contactArray[i].length()));
                    }
                } finally {
                }
            }
        }
        Log.d("Contact Names", contactListNames.toString());
        return contactListNames;
    }

    public ArrayList<String> readContactFileNumbers() throws IOException {

        ArrayList<String> contactListNumbers = new ArrayList<String>();

        // Read file
        FileInputStream in = null;
        String s="";
        try {
            in = openFileInput(fileName);
            InputStreamReader InputRead= new InputStreamReader(in);

            char[] inputBuffer= new char[100];

            int charRead;

            while ((charRead=InputRead.read(inputBuffer))>0) {
                // char to string conversion
                String readstring=String.copyValueOf(inputBuffer,0,charRead);
                s +=readstring;
            }
            InputRead.close();
            Toast.makeText(getBaseContext(), s, Toast.LENGTH_SHORT).show();

            // delete file
            //deleteFile(fileName);
        }
        finally {
            if (in != null) {
                in.close();
            }
        }


        if (s == ""){
        } else {
            s = s.substring(0, s.length()-1);

            String[] contactArray = s.split(":");
//            for (String contact:contactArray)
//            {
//                Log.d("contacts", contact);
//            }

            // Extract Numbers
            for (int i = 0; i < contactArray.length; i+=2)
            {
                try {
                    if (i+1 < contactArray.length){
                        contactListNumbers.add(contactArray[i+1].substring(0,contactArray[i+1].length()-1));
                    }
                } finally {}
            }
        }

        //Format PhoneNumbers
        List<String> numbers = new ArrayList<String>();

        for (String nums:contactListNumbers) {
            StringBuilder pNumber = new StringBuilder();
            for (char c:nums.toCharArray()){
                if (c >= 48 && c <= 57) {
                    pNumber.append(c);
                }
            }
            numbers.add(pNumber.toString());

        }
        return contactListNumbers;

    }

    @Override
    public void onResume()
    {
        super.onResume();
        Log.d("resume","resuemd again");
        try {
            contactArrayListNames = readContactFileNames();
            contactArrayListNumbers = readContactFileNumbers();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (contactArrayListNames != null && contactArrayListNumbers != null) {
            contactObjs = new ArrayList<Contact>();
            for (int i = 0; i < contactArrayListNames.size(); i++){
                contactObjs.add(new Contact(contactArrayListNames.get(i), contactArrayListNumbers.get(i)));
            }
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_2, android.R.id.text1, contactObjs) {
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView text1 = (TextView) view.findViewById(android.R.id.text1);
                TextView text2 = (TextView) view.findViewById(android.R.id.text2);

                text1.setText(contactObjs.get(position).getName());
                text2.setText(contactObjs.get(position).getPhoneNumber());
                return view;
            }
        };
        // Contact List View
        ListView cListView = (ListView) findViewById(R.id.ContactListView);
        cListView.setAdapter(arrayAdapter);

    }
}
