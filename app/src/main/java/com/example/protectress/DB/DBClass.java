package com.example.protectress.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.protectress.Modals.ContactModal;

import java.util.ArrayList;
import java.util.List;

public class DBClass extends SQLiteOpenHelper {

    public static final int v=1;
    public static final String dbname="contact";
    public static final String table_name="record";

    public static final String NAME="name";
    public static final String NUMBER="number";

    private static final String key_id="id";

    public DBClass(@Nullable Context context) {
        super(context, dbname, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_COUNTRY_TABLE = "CREATE TABLE " + table_name + "("
                + key_id + " INTEGER PRIMARY KEY," + NAME + " TEXT,"
                + NUMBER + " TEXT" + ")";
        db.execSQL(CREATE_COUNTRY_TABLE);

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void addcontact(ContactModal contact){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues c=new ContentValues();
        c.put(NAME,contact.getName());
        c.put(NUMBER,contact.getNumber());
        db.insert(table_name,null,c);
        db.close();
    }

    // method to retrieve all the contacts in List
    public List<ContactModal> getAllContacts(){
        List<ContactModal> list=new ArrayList<>();
        String query="SELECT * FROM "+table_name;
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor c=db.rawQuery(query,null);
        if(c.moveToFirst()) {
            do {

                list.add(new ContactModal(c.getString(1),c.getString(2),c.getInt(0)));

            } while (c.moveToNext());
        }
        return list;
    }

    // get the count of data, this will allow user
    // to not add more that five contacts in database
    public int count(){
        int count=0;
        String query="SELECT COUNT(*) FROM "+table_name;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c=db.rawQuery(query,null);
        if(c.getCount()>0){
            c.moveToFirst();
            count=c.getInt(0);
        }
        c.close();
        return count;
    }

    // Deleting single country
    public void deleteContact(ContactModal contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        int i=db.delete(table_name,key_id + " = ?",
                new String[] { String.valueOf(contact.getId()) });

        db.close();
    }
}
