package com.example.application2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddContact extends AppCompatActivity {

    Button btnConfirm;
    EditText Name,Phone;
    DbContact db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        btnConfirm = findViewById(R.id.btnConfirm);
        Name = findViewById(R.id.Name);
        Phone = findViewById(R.id.Phone);
        db = new DbContact(this);

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Name1 = Name.getText().toString();
                int Phone1 = Integer.parseInt(Phone.getText().toString());
                Contact contact = new Contact(Name1, Phone1);
                System.out.println(contact.getName());
                System.out.println(contact.getPhone());
                db.addcontact(contact);
                Toast.makeText(AddContact.this, "Contact added", Toast.LENGTH_SHORT).show();
            }
        });

    }
}


