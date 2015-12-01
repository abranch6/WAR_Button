package com.example.benjamin.alert;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Dhar on 11/30/15.
 */
public class MySQLiteHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "ContactsDB";

    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // SQL statement to create book table
        String CREATE_CONTACTS_TABLE = "CREATE TABLE contacts ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT, " +
                "phone TEXT )";

        // create books table
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older books table if existed
        db.execSQL("DROP TABLE IF EXISTS contacts");

        // create fresh books table
        this.onCreate(db);
    }
    //---------------------------------------------------------------------

    /**
     * CRUD operations (create "add", read "get", update, delete) book + get all books + delete all books
     */

    // Books table name
    private static final String TABLE_CONTACTS = "contacts";

    // Books Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_PHONE = "phone";

    private static final String[] COLUMNS = {KEY_ID, KEY_NAME, KEY_PHONE};

    public void addContact(Contact contact) {
        Log.d("addBook", contact.toString());
        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, contact.getName()); // get title
        values.put(KEY_PHONE, contact.getPhone()); // get author

        // 3. insert
        db.insert(TABLE_CONTACTS, // table
                null, //nullColumnHack
                values); // key/value -> keys = column names/ values = column values

        // 4. close
        db.close();
    }

    public Contact getContact(int id) {

        // 1. get reference to readable DB
        SQLiteDatabase db = this.getReadableDatabase();

        // 2. build query
        Cursor cursor =
                db.query(TABLE_CONTACTS, // a. table
                        COLUMNS, // b. column names
                        " id = ?", // c. selections
                        new String[]{String.valueOf(id)}, // d. selections args
                        null, // e. group by
                        null, // f. having
                        null, // g. order by
                        null); // h. limit

        // 3. if we got results get the first one
        if (cursor != null)
            cursor.moveToFirst();

        // 4. build book object
        Contact contact = new Contact();
        contact.setId(Integer.parseInt(cursor.getString(0)));
        contact.setName(cursor.getString(1));
        contact.setPhone(cursor.getString(2));

        Log.d("getContact(" + id + ")", contact.toString());

        // 5. return book
        return contact;
    }

    // Get All Books
    public List<Contact> getAllContacts() {
        List<Contact> contacts = new LinkedList<Contact>();

        // 1. build the query
        String query = "SELECT  * FROM " + TABLE_CONTACTS;

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // 3. go over each row, build book and add it to list
        Contact contact = null;
        if (cursor.moveToFirst()) {
            do {
                contact = new Contact();
                contact.setId(Integer.parseInt(cursor.getString(0)));
                contact.setName(cursor.getString(1));
                contact.setPhone(cursor.getString(2));

                // Add book to books
                contacts.add(contact);
            } while (cursor.moveToNext());
        }

        Log.d("getAllContacts()", contacts.toString());

        // return books
        return contacts;
    }

    // Updating single book
    public int updateContact(Contact contact) {

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put("name", contact.getName()); // get title
        values.put("phone", contact.getPhone()); // get author

        // 3. updating row
        int i = db.update(TABLE_CONTACTS, //table
                values, // column/value
                KEY_ID + " = ?", // selections
                new String[]{String.valueOf(contact.getId())}); //selection args

        // 4. close
        db.close();

        return i;

    }

    // Deleting single book
    public void deleteContact(Contact contact) {

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. delete
        db.delete(TABLE_CONTACTS,
                KEY_ID + " = ?",
                new String[]{String.valueOf(contact.getId())});

        // 3. close
        db.close();

        Log.d("deleteContact", contact.toString());

    }

    public void deleteAll(List<Contact> contacts)
    {
        for(Contact contact: contacts)
        {
            deleteContact(contact);
        }
    }
}

