package com.example.contactmanger;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class Contact_database extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Contacts.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "contacts_list";
    private static final String COLUMN_ID = "_ID";
    private static final String COLUMN_SYMBOL = "symbol";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_NUMBER = "number";
    private Context context;

    public Contact_database(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context =context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                             "( "+ COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"+
                                 COLUMN_NAME + " TEXT, "+
                                    COLUMN_NUMBER + " TEXT, " +
                                    COLUMN_SYMBOL+ " TEXT );";
        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String query = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(query);
        onCreate(db);
    }
    public void AddData( String Name, String number,String symbol){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put(COLUMN_NAME, Name);
        value.put(COLUMN_NUMBER,number);
        value.put(COLUMN_SYMBOL,symbol);
        long result = db.insert(TABLE_NAME,null,value);
        if (result == -1){
            Toast.makeText(context, "Failed to add", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Added successfully", Toast.LENGTH_SHORT).show();
        }

    }
    Cursor readData(){
        String query = "SELECT * FROM " + TABLE_NAME + " ORDER BY " + COLUMN_NAME + " ASC ";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        try {
            if (db != null){
                cursor = db.rawQuery(query , null);
            }
        }catch (Exception e){
            Log.e("ReadData", "Error reading data from database: " + e.getMessage());
              }
        return cursor;
    }
    void DeleteContacts(String row_id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME, "_ID=?", new String[]{row_id} );
        if (result == -1){
            Toast.makeText(context, "Failed to delete", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Deleted successfully", Toast.LENGTH_SHORT).show();
        }
    }
}
