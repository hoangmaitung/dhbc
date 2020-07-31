package com.example.da1.api;

import com.example.da1.model.Cauhoi;

import java.util.ArrayList;

public class Data {
    private static Data data;
    static {
        data = new Data();
    }
    public static Data getData(){
        return data;
    }
    public ArrayList<Cauhoi> arrcauhoi = new ArrayList<>();
}
