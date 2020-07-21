package com.example.da1.adapter;

import com.example.da1.PlayActivity;
import com.example.da1.api.Data;
import com.example.da1.model.Cauhoi;
import com.example.da1.object.Nguoidung;

import java.util.ArrayList;

public class CauhoiAdapter {

    PlayActivity activity;
    ArrayList<Cauhoi> list = new ArrayList<>();
    int causo = -1;
    public Nguoidung nguoidung;

    public CauhoiAdapter(PlayActivity activity) {
        this.activity = activity;
        nguoidung = new Nguoidung();
        Taodata();
    }

    private void Taodata() {
        list = Data.getData().arrcauhoi;
    }

    public Cauhoi laycauhoi() {
        causo++;
        if (causo >= list.size()) {
            causo = list.size() - 1;
        }
        return list.get(causo);
    }

    public void laythongtin(){
        nguoidung.getDiamond(activity);

    }

    public void luuthongtin(){
        nguoidung.saveDiamond(activity);

    }
}
