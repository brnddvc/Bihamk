package com.example.emina.bihamk.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.HashMap;

import static android.os.Build.ID;
import static com.example.emina.bihamk.database.RadarDbSchema.RadarTable.Cols.CANTON;
import static com.example.emina.bihamk.database.RadarDbSchema.RadarTable.Cols.LOCATION;
import static com.example.emina.bihamk.database.RadarDbSchema.RadarTable.Cols.UUID;

public class RadarBaseHelper extends SQLiteOpenHelper {


    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "radarBase.db";

    public RadarBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " + RadarDbSchema.RadarTable.NAME + "(" +
                RadarDbSchema.RadarTable.Cols.UUID + ", " +
                LOCATION + ", " +
                CANTON +
                ")"
        );

        getWritableDatabase();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public ArrayList<String> dajPodatke(String s){
        ArrayList<String> podaci = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        String[] rezultati = new String[] {LOCATION,CANTON};
        Cursor cursor = null;
        cursor = db.query(RadarDbSchema.RadarTable.NAME,rezultati,null,null,null,null, null);
        if(cursor.getCount()>0){
            while (cursor.moveToNext()){
                    if(cursor.getString(cursor.getColumnIndex(CANTON)).equals(s))
                        podaci.add(cursor.getString(cursor.getColumnIndex(LOCATION)));
            }
        }
        cursor.close();
        db.close();
        return podaci;
    }
}