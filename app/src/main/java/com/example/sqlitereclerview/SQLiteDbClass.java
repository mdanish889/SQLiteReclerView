package com.example.sqlitereclerview;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class SQLiteDbClass extends SQLiteOpenHelper
{

     private static final String DATABASE_NAME="Class.db";
     private static final int DATABASE_VERSION=1;

     private Context contex;
     private  String queryToCreateTable="create table classtable(Name VARCHAR(255),Address VARCHAR(255))";


    public SQLiteDbClass(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.contex=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
    try
    {

        db.execSQL(queryToCreateTable);
        Toast.makeText(contex,"Database and table created",Toast.LENGTH_SHORT).show();
    }
    catch (Exception e)
    {
        Toast.makeText(contex,"onCreate"+e.getMessage(),Toast.LENGTH_SHORT).show();
    }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {

    }
}
