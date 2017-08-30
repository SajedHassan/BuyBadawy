package com.example.user.eshtri_first_pafge;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.id;
import static com.example.user.eshtri_first_pafge.DatabaseHandler.COLUMN_NAME_NAME;
import static java.lang.Integer.parseInt;


/**
 * Created by user on 8/30/2017.
 */

public class DataBaseHandelerForAccounts extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "contactsManager";

    // Contacts table name
    private static final String TABLE_CONTACTS2 = "accounts";

    // Contacts Table Columns names
    private static final String KEY_ID2 = "id";
    public static final String COLUMN_NAME_NAME2 = "name";
    public static final String COLUMN_NAME_NUMBER = "number";
    public static final String COLUMN_NAME_EMAIL = "email";
    public static final String COLUMN_NAME_USERNAME = "username";






    public DataBaseHandelerForAccounts(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_ENTRIES =
                "CREATE TABLE " + TABLE_CONTACTS2 + " (" +
                        KEY_ID2 + " INTEGER PRIMARY KEY," +
                        COLUMN_NAME_NAME2 + " TEXT," +
                        COLUMN_NAME_NUMBER + " TEXT," +
                        COLUMN_NAME_EMAIL + " TEXT," +
                        COLUMN_NAME_USERNAME + " TEXT" + ")";

        db.execSQL(SQL_CREATE_ENTRIES);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS2);

        // Create tables again
        onCreate(db);
    }

    // Adding new contact
    public void addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ID2, user.id);
        values.put(COLUMN_NAME_NAME2, user.name); // Contact Name
        values.put(COLUMN_NAME_NUMBER, user.number); // Contact Phone Number
        values.put(COLUMN_NAME_EMAIL, user.email);
        values.put(COLUMN_NAME_USERNAME, user.getUsername());


        // Inserting Row
        db.insert(TABLE_CONTACTS2, null, values);
        db.close(); // Closing database connection
    }

    // Getting single contact
    public User getUser(String username) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_CONTACTS2, new String[] { KEY_ID2,
                        COLUMN_NAME_NAME2,
                        COLUMN_NAME_NUMBER,
                        COLUMN_NAME_EMAIL,
                        COLUMN_NAME_USERNAME }, COLUMN_NAME_USERNAME + "=?",
                new String[] { username }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();




        User user = new User(parseInt(cursor.getString(0)),
                cursor.getString(1),cursor.getString(2), cursor.getString(3), cursor.getString(4));
        // return contact
        return user;
    }

    // Getting All Contacts
    public List<User> getAllUsers() {
        List<User> contactList = new ArrayList<User>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_CONTACTS2;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                User user = new User();
                user.id = Integer.parseInt(cursor.getString(0));
                user.name = cursor.getString(1);
                user.number = cursor.getString(2);
                user.email = cursor.getString(3);
                user.username = cursor.getString(4);

                // Adding contact to list
                contactList.add(user);
            } while (cursor.moveToNext());
        }

        // return contact list
        return contactList;
    }

//    // Getting contacts Count
//    public int getProductsCount() {
//
//    }
//    // Updating single contact
//    public int updateProduct(Product contact) {
//
//    }

    // Deleting single contact
    public void deleteUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CONTACTS2, COLUMN_NAME_NAME2 + " = ?",
                new String[] { String.valueOf(user.name) });
        db.close();
    }
}
