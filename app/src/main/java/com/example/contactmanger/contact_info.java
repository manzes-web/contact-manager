package com.example.contactmanger;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

public class contact_info extends AppCompatActivity {
    Button button1 , button2 , button3 , button4;
    TextView textView1, textView2, textView3;
    String id, name, number, symbol;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_info);
        Toolbar toolbar;
        toolbar = findViewById(R.id.toolbar3);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        button1 = findViewById(R.id.button2);
        button2 = findViewById(R.id.button4);
        button3 = findViewById(R.id.button5);
        button4 = findViewById(R.id.button6);
        textView1 = findViewById(R.id.textView5);
        textView2 = findViewById(R.id.textView6);
        textView3 = findViewById(R.id.textView4);
        getData();
        button3.setOnClickListener(View->{
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:"+ number));
            startActivity(intent);
        });
        button4.setOnClickListener(View ->{
            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("smsto:"+number));
            startActivity(intent);
        });
        button1.setOnClickListener(View ->{



        });
        button2.setOnClickListener(View->{
            ConfirmDialogueBox();
        });


    }

    void getData() {
        if (getIntent().hasExtra("id")&&getIntent().hasExtra("name")&&getIntent().hasExtra("number")&&getIntent().hasExtra("symbol")){
            id = getIntent().getStringExtra("id");
            name = getIntent().getStringExtra("name");
            number = getIntent().getStringExtra("number");
            symbol = getIntent().getStringExtra("symbol");
            textView1.setText(name);
            textView2.setText(number);
            textView3.setText(symbol);
            Log.d("ContactInfoActivity", "ID: " + id);
            Log.d("ContactInfoActivity", "Name: " + name);
            Log.d("ContactInfoActivity", "Number: " + number);
            Log.d("ContactInfoActivity", "Symbol: " + symbol);
        }else {
            Log.e("ContactInfoActivity", "Intent extras are missing!");
        }
    }

    private void ConfirmDialogueBox() {
        AlertDialog.Builder alertbox = new AlertDialog.Builder(contact_info.this);
        alertbox.setMessage("Are you sure want to delete this");

        alertbox.setPositiveButton("Yes", (dialog, which) -> {
            Contact_database db = new Contact_database(this);
            db.DeleteContacts(id);
            finish();
        });
        alertbox.setNegativeButton("No" , ((dialog, which) -> {

        }));
        AlertDialog alertDialog = alertbox.create();
        alertDialog.show();
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