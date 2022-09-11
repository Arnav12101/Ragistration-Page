package com.example.ragistration_page;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class dbhelper extends SQLiteOpenHelper {

    public dbhelper(Context context) {
        super(context, "myDb.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("CREATE TABLE registration (name TEXT primary key, password TEXT, dob TEXT, email TEXT, phone TEXT, state TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("DROP TABLE if exists registration");
    }


    public boolean inputuserdata(String name, String passsword, String dob, String email, String phone, String state) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("password", passsword);
        contentValues.put("dob", dob);
        contentValues.put("email", email);
        contentValues.put("phone", phone);
        contentValues.put("state", state);


        long result = DB.insert("registration", null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }


    public boolean updateuserdata(String name, String password, String dob, String email, String phone, String state) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("password", password);
        contentValues.put("dob", dob);
        contentValues.put("email", email);
        contentValues.put("phone", phone);
        contentValues.put("state", state);
        Cursor cursor = DB.rawQuery("SELECT * FROM userdetails WHERE name = ?", new String[]{name});
        if (cursor.getCount() > 0) {
            long result = DB.update("registration", contentValues, "name=?", new String[]{name});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return true;
        }
    }

    public  boolean deleteuserdata(String name)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        Cursor cursor = DB.rawQuery("DELETE FROM registration where name = ?", new String[]{name});
        if(cursor.getCount() == -1)
        {
            return  false;
        }
        else
        {
            return  true;
        }
    }

    public Cursor getdata()
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("SELECT * FROM registration", null);
        return cursor;
    }
}
