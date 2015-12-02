package com.example.benjamin.alert;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ToggleButton;

import java.util.List;

import static android.R.layout.simple_list_item_1;

public class Contacts extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        final MySQLiteHelper db = new MySQLiteHelper(this);

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

        contactLists.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position,
                                    long arg3) {
                String value = (String) adapter.getItemAtPosition(position);
                Log.d("listItem", value);

                if (value != null) {
                    String name;
                    String phone;

                    String[] split = value.split(" - ");
                    try {
                        name = split[0];
                        phone = split[1];

                        ToggleButton delete = (ToggleButton) findViewById(R.id.addcontacts);
                        if (delete.isChecked()) {

                            int ID = getID(name, phone);
                            Log.d("ID", Integer.toString(ID));
                            if (ID != -1) {
                                Contact delContact = new Contact(split[0], split[1]);
                                delContact.setId(ID);
                                Log.d("del", Integer.toString(ID));
                                db.deleteContact(delContact);

                                Intent intent = getIntent();
                                finish();
                                startActivity(intent);

                            }

                        }
                    } catch (Exception e) {
                        Log.d("Null","No name/phone");
                    }
//                    Log.d("split", split[0]);
//                    Log.d("split", split[1]);


                }

                // assuming string and if you want to get the value on click of list item
                // do what you intend to do on click of listview row
            }
        });
    }


    private int getID(String name, String phone){

        MySQLiteHelper db = new MySQLiteHelper(this);
        List<Contact> contactList = db.getAllContacts();
        Log.d("name", name);
        Log.d("phone", phone);
        for(Contact cnt:contactList)
        {
            Log.d("contName", cnt.getName());
            Log.d("contPhone", cnt.getPhone());
            if (name.equals(cnt.getName()) && phone.equals(cnt.getPhone()))
            {
                return cnt.getId();
            }
        }

        return -1;
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event){
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("contactBack", "BACK");
            startActivity(intent);
        }
        return true;
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

    public void edit(View view){
        Log.d("Edit", "edit pressed");
    }


}
