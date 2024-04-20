package com.example.contactmanger;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

public class Add_Contacts extends AppCompatActivity {
    EditText editText, editText1 , editText2;
    Button addButton;
    Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contacts);
        editText1 = findViewById(R.id.editTextText);
        editText2 = findViewById(R.id.editTextText2);
        addButton = findViewById(R.id.button);
        Toolbar toolbar = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        addButton.setOnClickListener(view -> {
            String Name = editText1.getText().toString().trim();
            String Number = editText2.getText().toString().trim();
            if (!Name.isEmpty()&&!Number.isEmpty()){
                Contact_database db = new Contact_database(Add_Contacts.this);
                db.AddData(Name , Number, String.valueOf(Name.charAt(0)).toUpperCase());
                Intent intent = new Intent(Add_Contacts.this,MainActivity.class);
                startActivity(intent);
            }else if (!Name.isEmpty()){
                Toast.makeText(this, "please enter contact name", Toast.LENGTH_SHORT).show();
            } else if (!Number.isEmpty()) {
                Toast.makeText(this, "please enter user phone number", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "please fill some data", Toast.LENGTH_SHORT).show();
            }

        });

    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // Handle back button click here
        if (item.getItemId() == android.R.id.home) {
            onBackPressed(); // This will simulate a system back button press
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}