package com.example.emina.bihamk;

import java.util.UUID;

public class Radar {

    UUID mId;
    String mLocation;
    String mCanton;

    public Radar(){
        mId = UUID.randomUUID();
    }
    public Radar(UUID id) { mId = id;}

    public UUID getId() { return mId; }

    public String getLocation() { return mLocation; }

    public void setLocation(String location) { mLocation = location; }

    public String getCanton() { return mCanton; }

    public void setCanton(String canton) { mCanton = canton; }
}
