package com.example.emina.bihamk.database;

import android.database.Cursor;
        import android.database.CursorWrapper;

import com.example.emina.bihamk.Radar;

import java.util.UUID;

public class RadarCursorWrapper extends CursorWrapper {

    public RadarCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Radar getRadar() {
        String uuidString = getString(getColumnIndex(RadarDbSchema.RadarTable.Cols.UUID));
        String location = getString(getColumnIndex(RadarDbSchema.RadarTable.Cols.LOCATION));
        String canton = getString(getColumnIndex(RadarDbSchema.RadarTable.Cols.CANTON));

        Radar r = new Radar(UUID.fromString(uuidString));
        r.setLocation(location);
        r.setCanton(canton);

        return r;
    }
}
