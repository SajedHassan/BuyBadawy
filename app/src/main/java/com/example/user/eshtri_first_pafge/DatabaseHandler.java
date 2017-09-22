//package com.example.user.eshtri_first_pafge;
//
//import android.content.ContentValues;
//import android.content.Context;
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteOpenHelper;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * Created by user on 8/30/2017.
// */
//
//public class DatabaseHandler extends SQLiteOpenHelper {
//
//    // All Static variables
//    // Database Version
//    private static final int DATABASE_VERSION = 1;
//
//    // Database Name
//    private static final String DATABASE_NAME = "contactsManager";
//
//    // Contacts table name
//    private static final String TABLE_CONTACTS = "products";
//
//    // Contacts Table Columns names
//    private static final String KEY_ID = "id";
//    public static final String COLUMN_NAME_NAME = "name";
//    public static final String COLUMN_NAME_PRICE = "price";
//    public static final String COLUMN_NAME_PICTURE= "picture";
//    public static final String COLUMN_NAME_ADDRESS= "address";
//    public static final String COLUMN_NAME_DESCRIPTION = "description";
//    public static final String COLUMN_NAME_DETAILS = "details";
//    public static final String COLUMN_NAME_CATEGORY = "category";
//
//
//
//
//
//    public DatabaseHandler(Context context) {
//        super(context, DATABASE_NAME, null, DATABASE_VERSION);
//    }
//
//    // Creating Tables
//    @Override
//    public void onCreate(SQLiteDatabase db) {
//        final String SQL_CREATE_ENTRIES =
//                "CREATE TABLE " + TABLE_CONTACTS + " (" +
//                        KEY_ID + " INTEGER PRIMARY KEY," +
//                        COLUMN_NAME_NAME + " TEXT," +
//                        COLUMN_NAME_PRICE + " INTEGER," +
//                        COLUMN_NAME_PICTURE + " INTEGER," +
//                        COLUMN_NAME_ADDRESS + " TEXT," +
//                        COLUMN_NAME_DESCRIPTION + " TEXT," +
//                        COLUMN_NAME_DETAILS + " TEXT," +
//                        COLUMN_NAME_CATEGORY + " INTEGER" + ")";
//
//        db.execSQL(SQL_CREATE_ENTRIES);
//    }
//
//    // Upgrading database
//    @Override
//    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        // Drop older table if existed
//        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
//
//        // Create tables again
//        onCreate(db);
//    }
//
//    // Adding new contact
//    public void addProduct(Product pro) {
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        ContentValues values = new ContentValues();
//        values.put(COLUMN_NAME_NAME, pro.productN); // Contact Name
//        values.put(COLUMN_NAME_PRICE, pro.price); // Contact Phone Number
//        values.put(COLUMN_NAME_PICTURE, pro.picture);
//        values.put(COLUMN_NAME_ADDRESS, pro.address);
//        values.put(COLUMN_NAME_DESCRIPTION, pro.description);
//        values.put(COLUMN_NAME_DETAILS, pro.details);
//        values.put(COLUMN_NAME_CATEGORY, pro.category);
//
//        // Inserting Row
//        db.insert(TABLE_CONTACTS, null, values);
//        db.close(); // Closing database connection
//    }
//
//    // Getting single contact
//    public Product getProduct(int id) {
//        SQLiteDatabase db = this.getReadableDatabase();
//
//        Cursor cursor = db.query(TABLE_CONTACTS, new String[] { KEY_ID,
//                        COLUMN_NAME_NAME,
//                        COLUMN_NAME_PRICE,
//                        COLUMN_NAME_PICTURE,
//                        COLUMN_NAME_ADDRESS,
//                        COLUMN_NAME_DESCRIPTION,
//                        COLUMN_NAME_DETAILS,
//                        COLUMN_NAME_CATEGORY }, KEY_ID + "=?",
//                new String[] { String.valueOf(id) }, null, null, null, null);
//        if (cursor != null)
//            cursor.moveToFirst();
//
//
//
//
//        Product pro = new Product(cursor.getString(0),
//                Integer.parseInt(cursor.getString(1)), Integer.parseInt(cursor.getString(2)), cursor.getString(3), cursor.getString(4), cursor.getString(5), Integer.parseInt(cursor.getString(6)));
//        // return contact
//        return pro;
//    }
//
//    // Getting All Contacts
//    public List<Product> getAllProducts() {
//        List<Product> contactList = new ArrayList<Product>();
//        // Select All Query
//        String selectQuery = "SELECT  * FROM " + TABLE_CONTACTS;
//
//        SQLiteDatabase db = this.getWritableDatabase();
//        Cursor cursor = db.rawQuery(selectQuery, null);
//
//        // looping through all rows and adding to list
//        if (cursor.moveToFirst()) {
//            do {
//                Product pro = new Product();
//                pro.productN = cursor.getString(0);
//                pro.price = Integer.parseInt(cursor.getString(1));
//                pro.picture = Integer.parseInt(cursor.getString(2));
//                pro.address = cursor.getString(3);
//                pro.description = cursor.getString(4);
//                pro.details = cursor.getString(5);
//                pro.category = Integer.parseInt(cursor.getString(6));
//
//                // Adding contact to list
//                contactList.add(pro);
//            } while (cursor.moveToNext());
//        }
//
//        // return contact list
//        return contactList;
//    }
//
////    // Getting contacts Count
////    public int getProductsCount() {
////
////    }
////    // Updating single contact
////    public int updateProduct(Product contact) {
////
////    }
//
//    // Deleting single contact
//    public void deleteProduct(Product pro) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        db.delete(TABLE_CONTACTS, COLUMN_NAME_NAME + " = ?",
//                new String[] { String.valueOf(pro.productN) });
//        db.close();
//    }
//
//}
