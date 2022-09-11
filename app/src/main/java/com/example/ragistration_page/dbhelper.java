package com.example.ragistration_page;

import android.content.ContentValues;
import android.content.Context;
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


    public boolean inputuserdata(String name, String passsword, String dob, String email, String phone, String state)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("name", name);
        contentValues.put("name", name);
        contentValues.put("name", name);
        contentValues.put("name", name);
        contentValues.put("name", name);
    }
}
