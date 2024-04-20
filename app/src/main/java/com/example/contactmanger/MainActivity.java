package com.example.contactmanger;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button addButton;
    TextView textView,  textView1, textView2;
    Rows_Adapter rows_adapter;
    RecyclerView recyclerView;
    Contact_database db;
    private final ArrayList<String> Name = new ArrayList<>();
    private final ArrayList<String> Number = new ArrayList<>();
    private final ArrayList<String> Symbol = new ArrayList<>();
    private final ArrayList<Integer> id = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        FloatingActionButton addButton = findViewById(R.id.floatingActionButton2);
        addButton.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, Add_Contacts.class);
            startActivity(intent);
        });
        db = new Contact_database(MainActivity.this);
        displayContacts();
        rows_adapter = new Rows_Adapter(MainActivity.this, this,id ,Name, Number ,Symbol);
        recyclerView.setAdapter(rows_adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1){
            recreate();
        }
    }

    private void displayContacts() {
        Cursor cursor =db.readData();

        if (cursor.getCount() == 0){
            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
        }else {
            while (cursor.moveToNext()){
                id.add(Integer.valueOf(String.valueOf(cursor.getInt(0))));
                Name.add(cursor.getString(1));
                Number.add(cursor.getString(2));
                Symbol.add(cursor.getString(3));
            }
         }
    }
}