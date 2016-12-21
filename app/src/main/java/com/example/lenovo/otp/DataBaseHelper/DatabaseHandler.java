package com.example.lenovo.otp.DataBaseHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.lenovo.otp.Model.CustomSms;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 21-12-2016.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    // All Static variables
    // Database Version

    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "smsManager";

    // Contacts table name
    private static final String TABLE_CONTACTS = "sms";

    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_OTP="otp";
    private static final String KEY_DATE="date";


    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }




    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_OTP + "TEXT," + KEY_DATE + "TEXT" +")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);

        // Create tables again
        onCreate(db);
    }

   public void addContact(CustomSms customSms) {
        SQLiteDatabase db = this.getWritableDatabase();
         db.beginTransaction();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, customSms.getAddress()); // Contact Name
        values.put(KEY_OTP, customSms.getOtp());
        values.put(KEY_DATE,customSms.getDate());

        // Inserting Row
        db.insert(TABLE_CONTACTS, null, values);
        db.close(); // Closing database connection
    }

    CustomSms getTexts(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_CONTACTS, new String[] { KEY_ID,
                        KEY_NAME,KEY_OTP,KEY_DATE}, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        CustomSms sms = new CustomSms(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2),cursor.getString(3));
        // return contact
        return sms;
    }


    public List<CustomSms> getAllContacts() {
        List<CustomSms> smsList = new ArrayList<CustomSms>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_CONTACTS;

        SQLiteDatabase db = this.getWritableDatabase();
        db=this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                CustomSms contact = new CustomSms();
                contact.setId(Integer.parseInt(cursor.getString(0)));
                contact.setAddress(cursor.getString(1));
                contact.setOtp(cursor.getString(2));
                contact.setDate(cursor.getString(3));
                // Adding contact to list
                smsList.add(contact);
            } while (cursor.moveToNext());
        }

        // return contact list
        return smsList;
    }

    public int updateContact(CustomSms customSms) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, customSms.getAddress());
        values.put(KEY_OTP,customSms.getOtp());
        values.put(KEY_DATE,customSms.getDate());


        // updating row
        return db.update(TABLE_CONTACTS, values, KEY_ID + " = ?",
                new String[] { String.valueOf(customSms.getId()) });
    }

    // Deleting single contact
    public void deleteContact(CustomSms customSms) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CONTACTS, KEY_ID + " = ?",
                new String[] { String.valueOf(customSms.getId()) });
        db.close();
    }





    // Getting contacts Count
    public int getContactsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_CONTACTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }

}
