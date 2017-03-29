package com.depakto.classes.construktor;

import com.depakto.tsmobile.BuildConfig;

public class Student {
    public boolean away = false;
    public String awayMessage = BuildConfig.FLAVOR;
    public int channel = 0;
    public boolean commander = false;
    public String country = "PL";
    public int id = 0;
    public int idDB = 0;
    public int index = -1;
    public boolean input = false;
    public boolean mutedByMe = false;
    public String name = "Nick";
    public boolean output = false;
    public boolean talking = false;
    public String uID = BuildConfig.FLAVOR;

    public int getId() {
        return this.id;
    }
}
