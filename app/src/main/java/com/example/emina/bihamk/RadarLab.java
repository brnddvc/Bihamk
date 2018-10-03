package com.example.emina.bihamk;

import android.content.Context;
import android.database.Cursor;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.example.emina.bihamk.database.RadarBaseHelper;
import com.example.emina.bihamk.database.RadarCursorWrapper;
import com.example.emina.bihamk.database.RadarDbSchema;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.example.emina.bihamk.database.RadarDbSchema.RadarTable.Cols.CANTON;
import static com.example.emina.bihamk.database.RadarDbSchema.RadarTable.Cols.LOCATION;


public class RadarLab {

    private static RadarLab sRadarLab;
    private Context mContext;
    private SQLiteDatabase mDatabase;

    public static RadarLab get(Context context) {
        if (sRadarLab == null) {
            sRadarLab = new RadarLab(context);
        }
        return sRadarLab;
    }

    private RadarLab(Context context) {
        mContext = context.getApplicationContext();
        mDatabase = new RadarBaseHelper(mContext)
                .getWritableDatabase();

    }

    public void addRadar(Radar r) {
        ContentValues values = getContentValues(r);
        mDatabase.insert(RadarDbSchema.RadarTable.NAME, null, values);
    }

    private static ContentValues getContentValues(Radar radar) {
        ContentValues values = new ContentValues();
        values.put(RadarDbSchema.RadarTable.Cols.UUID, radar.getId().toString());
        values.put(LOCATION, radar.getLocation());
        values.put(CANTON, radar.getCanton());
        return values;
    }

    public List<Radar> getRadars() {
        List<Radar> radars = new ArrayList<>();
        RadarCursorWrapper cursor = queryRadars(null, null);
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                radars.add(cursor.getRadar());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        return radars;
    }

    public Radar getCrime(UUID id) {
        RadarCursorWrapper cursor = queryRadars(
                RadarDbSchema.RadarTable.Cols.UUID + " = ?",
                new String[]{id.toString()}
        );
        try {
            if (cursor.getCount() == 0) {
                return null;
            }
            cursor.moveToFirst();
            return cursor.getRadar();
        } finally {
            cursor.close();
        }
    }

    public void updateRadar(Radar radar) {
        String uuidString = radar.getId().toString();
        ContentValues values = getContentValues(radar);
        mDatabase.update(RadarDbSchema.RadarTable.NAME, values,
                RadarDbSchema.RadarTable.Cols.UUID + " = ?",
                new String[]{uuidString});
    }

    private RadarCursorWrapper queryRadars(String whereClause, String[] whereArgs) {
        Cursor cursor = mDatabase.query(
                RadarDbSchema.RadarTable.NAME,
                null, // Columns - null selects all columns
                whereClause,
                whereArgs,
                null, // groupBy
                null, // having
                null  // orderBy
        );
        return new RadarCursorWrapper(cursor);
    }

}
