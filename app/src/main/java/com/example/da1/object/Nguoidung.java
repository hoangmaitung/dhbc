package com.example.da1.object;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

public class Nguoidung {
    public String name = "a";
    public int diamond;
    @SuppressLint("ApplySharedPref")
    public void saveDiamond(Context context){
        SharedPreferences preferences = context.getSharedPreferences(name, 0);
        SharedPreferences.Editor editor =preferences.edit();
        editor.putInt("Diamond", diamond);
        editor.commit();
    }

    public void getDiamond(Context context){
        SharedPreferences preferences = context.getSharedPreferences(name, 0);
        diamond = preferences.getInt("Diamond", 50);
    }
}
