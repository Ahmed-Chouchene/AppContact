package com.example.application2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView contactList;
    Button btnAdd;
    DbContact db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         contactList = findViewById(R.id.contactList);
         btnAdd = findViewById(R.id.btnAdd);
         db = new DbContact(this);

         btnAdd.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {

                 Intent intent = new Intent(MainActivity.this,AddContact.class);
                 startActivity(intent);

             }
         });

        contactList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Contact selected_contact = (Contact) parent.getItemAtPosition(position);
                Intent intent = new Intent(MainActivity.this, UpdateContact.class);
//                intent.putExtra("id",Integer.valueOf(selected_contact.getId()));
                intent.putExtra("id",selected_contact.getId());
                startActivity(intent);

//                System.out.println(Integer.valueOf(selected_contact.getId()));
            }});
    }

    @Override
    protected void onResume() {
        super.onResume();
        ArrayList<Contact> contacts = db.getAllContacts();
        ContactAdapter contactAdapter = new ContactAdapter(this, R.layout.item_contact,contacts);
        contactList.setAdapter(contactAdapter);
    }

}
