package com.example.application2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.jar.Attributes;

public class UpdateContact extends AppCompatActivity {

    DbContact db ;
    EditText editName,editPhone;
    Button btnUpdate;
    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_contact);

        id = getIntent().getIntExtra("id",0 );
        db = new DbContact(this);
        Contact contact = db.getContactById(id);

        editName = findViewById(R.id.editName);
        editPhone = findViewById(R.id.editPhone);

        btnUpdate = findViewById(R.id.btnUpdate);

        editName.setText(contact.getName());
        editPhone.setText(contact.getPhone()+"");

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editName.getText().toString();
                int phone = Integer.parseInt(editPhone.getText().toString());

                Contact newContact = new Contact(id, name, phone);

                db.updateContact(newContact);
                Toast.makeText(UpdateContact.this,"contact updated",Toast.LENGTH_LONG).show();
            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.delete_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){

            case R.id.item_delete:

                showAlert();


                break;

        }
        return super.onOptionsItemSelected(item);
    }

    private void showAlert() {

        AlertDialog.Builder alertbuilder = new AlertDialog.Builder(this);
        alertbuilder.setTitle("confirmation")
                .setMessage("are you sure ?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //delete contact

                        db.deleteContact(id);
                        finish();

                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        AlertDialog dialog = alertbuilder.create();
        dialog.show();

    }
}

