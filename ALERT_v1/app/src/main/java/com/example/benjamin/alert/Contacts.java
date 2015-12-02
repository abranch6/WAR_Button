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

        contactLists.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position,
                                    long arg3) {
                String value = (String) adapter.getItemAtPosition(position);
                Log.d("listItem", value);

                ToggleButton delete = (ToggleButton) findViewById(R.id.addcontacts);
                if (delete.isChecked()){
                    Log.d("delete", "del");
                }
                // assuming string and if you want to get the value on click of list item
                // do what you intend to do on click of listview row
            }
        });
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
