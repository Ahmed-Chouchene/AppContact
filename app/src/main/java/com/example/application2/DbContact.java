package com.example.application2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DbContact extends SQLiteOpenHelper {

    public DbContact(@Nullable Context context) {
        super(context,"myphone_db", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
    String create_table = "create table contacts (id integer primary key, Name varchar(30) ,Phone integer)";
        System.out.println(create_table);
        db.execSQL(create_table);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String delete_query = "DROP Table contacts" ;
        db.execSQL(delete_query);
        onCreate(db);
    }
    public void addcontact(Contact contact) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Name",contact.getName());
        values.put("Phone",contact.getPhone());
        db.insert("contacts", null, values);
    }
    public ArrayList<Contact> getAllContacts(){
        ArrayList<Contact> contacts = new ArrayList<>();
        String select_query = "select * from contacts ";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(select_query, null);
        if (cursor.moveToFirst()){
            do{
                String Name = cursor.getString(cursor.getColumnIndex("Name"));
                int Phone = cursor.getInt(cursor.getColumnIndex("Phone"));
                int id_contact = cursor.getInt(cursor.getColumnIndex("id"));


                Contact contact = new Contact(id_contact, Name,Phone);
                contacts.add(contact);

            }while (cursor.moveToNext());
        }
        return contacts;
    }
//   first method to select by position contact from database
    public Contact getContactById(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String select_query = "select * from contacts where id ="+id;
        Cursor cursor = db.rawQuery(select_query, null);
        Contact contact = null;
        if (cursor.moveToFirst()) {
            int id_contact = cursor.getInt(cursor.getColumnIndex("id"));
            String Name = cursor.getString(cursor.getColumnIndex("Name"));
            int Phone = cursor.getInt(cursor.getColumnIndex("Phone"));

            contact = new Contact(id_contact, Name, Phone);


        }
        return contact;
    }

    public void updateContact(Contact contact){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Name",contact.getName());
        values.put("Phone",contact.getPhone());

        db.update("contacts", values,"id=?",new String[]{String.valueOf(contact.getId())});
    }

    public void deleteContact(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("contacts","id=?",new String[]{String.valueOf(id)});

    }
}
