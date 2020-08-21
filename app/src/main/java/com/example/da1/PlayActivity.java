package com.example.da1;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.da1.adapter.CauhoiAdapter;
import com.example.da1.adapter.DapanAdapter;
import com.example.da1.model.Cauhoi;

import java.util.ArrayList;
import java.util.Random;

public class PlayActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        init();
        anhxa();
        setOnClick();
        hiencauhoi();
    }

    CauhoiAdapter adapter;
    Cauhoi cauhoi;
    String dapan;
    ArrayList<String> listDapan;
    private GridView gdvDapan;
    ArrayList<String> listChondapan;
    private GridView gdvChondapan;
    int index = 0;
    private ImageView imageView;
    private TextView tvDiamond;
    private ImageView imgSuggest;
    private TextView tvMan;

    private void anhxa() {
        gdvDapan = (GridView) findViewById(R.id.gdvDapan);
        gdvChondapan = (GridView) findViewById(R.id.gdvChondapan);
        imageView = (ImageView) findViewById(R.id.imageView);
        tvDiamond = (TextView) findViewById(R.id.tv_diamond);
        imgSuggest = (ImageView) findViewById(R.id.img_suggest);
        tvMan = (TextView) findViewById(R.id.tv_man);
    }

    private void init() {
        adapter = new CauhoiAdapter(this);
        listDapan = new ArrayList<>();
        listChondapan = new ArrayList<>();

    }

    private void hiencauhoi() {
        cauhoi = adapter.laycauhoi();
        dapan = cauhoi.dapan;
        sooDapan();
        hienthidapan();
        hienthiChondapan();
        Glide.with(this)
                .load(cauhoi.anh)
                .into(imageView);

        tvMan.setText(cauhoi.ten);
        tvMan.setText(cauhoi.ten);
        adapter.laythongtin();
        tvDiamond.setText(adapter.nguoidung.diamond + "");
    }

    private void hienthidapan() {
        gdvDapan.setNumColumns(listDapan.size());
        gdvDapan.setAdapter(new DapanAdapter(this, 0, listDapan));
    }

    private void hienthiChondapan() {
        gdvChondapan.setNumColumns(8);
        gdvChondapan.setAdapter(new DapanAdapter(this, 0, listChondapan));
    }

    private void sooDapan() {
        listDapan.clear();
        listChondapan.clear();
        for (int i = 0; i < dapan.length(); i++) {
            listDapan.add("");
        }
        Random r = new Random();
        for (int i = 0; i < 16; i++) {
            String a = "" + (char) (r.nextInt(26) + 65);
            listChondapan.add(a);
        }
        for (int i = 0; i < dapan.length(); i++) {
            String s = "" + dapan.charAt(i);
            listChondapan.set(i, s.toUpperCase());
        }
        for (int i = 0; i < 16; i++) {
            String s = listChondapan.get(i);
            int vt = r.nextInt(listChondapan.size());
            listChondapan.set(i, listChondapan.get(vt));
            listChondapan.set(vt, s);
        }
    }

    private void setOnClick() {
        gdvChondapan.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String s = (String) parent.getItemAtPosition(position);
                if (s.length() != 0 && index < listDapan.size()) {
                    for (int i = 0; i < listDapan.size(); i++) {
                        if (listDapan.get(i).length() == 0) {
                            index = i;
                            break;
                        }
                    }
                    listChondapan.set(position, "");
                    listDapan.set(index, s);
                    index++;
                    hienthidapan();
                    hienthiChondapan();
                    checktl();
                }
            }
        });

        gdvDapan.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String s = (String) parent.getItemAtPosition(position);
                if (s.length() != 0) {
                    index = position;
                    listDapan.set(position, "");
                    for (int i = 0; i < listChondapan.size(); i++) {
                        if (listChondapan.get(i).length() == 0) {
                            listChondapan.set(i, s);
                            break;
                        }
                    }
                    hienthidapan();
                    hienthiChondapan();
                }
            }
        });
    }

    private void checktl() {
        String s = "";
        for (String s1 : listDapan) {
            s = s + s1;
        }
        s = s.toUpperCase();
        if (s.length() == dapan.length() && !s.equals(dapan.toUpperCase())) {
            Toast.makeText(this, "Bạn đã trả lời sai !!!", Toast.LENGTH_SHORT).show();
        }
        if (s.equals(dapan.toUpperCase())) {
            adapter.laythongtin();
            adapter.nguoidung.diamond = adapter.nguoidung.diamond + 5;
            adapter.luuthongtin();
            showDialogWin();
            index = 0;
        }
    }

    private void showDialogWin() {
        final Dialog dialog = new Dialog(PlayActivity.this);
        dialog.setContentView(R.layout.dialog_win);
        Button btnNext = dialog.findViewById(R.id.btn_next);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                hiencauhoi();
            }
        });
        dialog.show();
    }

    public void dialogGoiY(View view) {
        final Dialog dialog = new Dialog(PlayActivity.this);
        dialog.setContentView(R.layout.dialog_suggest);
        Button btnYes = dialog.findViewById(R.id.btn_yes);
        ImageView idClose = dialog.findViewById(R.id.id_close);
        idClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                goiy();
            }
        });
        dialog.show();
    }

    public void goiy() {
        adapter.laythongtin();
        if (adapter.nguoidung.diamond < 5) {
            Toast.makeText(this, "Bạn không còn đủ kim cương", Toast.LENGTH_SHORT).show();
            return;
        }
        int id = -1;
        for (int i = 0; i < listDapan.size(); i++) {
            if (listDapan.get(i).length() == 0) {
                id = i;
                break;
            }
        }
        if (id == -1) {
            for (int i = 0; i < listDapan.size(); i++) {
                String s = dapan.toUpperCase().charAt(i) + "";
                if (!listDapan.get(i).toUpperCase().equals(s)) {
                    id = i;
                    break;
                }
            }
            for (int i = 0; i < listChondapan.size(); i++) {
                if (listChondapan.get(i).length() == 0) {
                    listChondapan.set(i, listDapan.get(id));
                    break;
                }
            }
        }
        String goiy = "" + dapan.charAt(id);
        goiy = goiy.toUpperCase();
        int a = 0;
        if (a == 0) {
            for (int i = 0; i < listChondapan.size(); i++) {
                if (goiy.equals(listChondapan.get(i))) {
                    listChondapan.set(i, "");
                    a++;
                    break;
                }
            }
            a--;
        }
        if (a < 0) {
            for (int i = 0; i < listDapan.size(); i++) {
                if (listDapan.get(i).toUpperCase().equals(goiy)) {
                    listDapan.set(i, "");
                    break;
                }
            }
        }
        listDapan.set(id, goiy);
        hienthiChondapan();
        hienthidapan();
        adapter.laythongtin();
        adapter.nguoidung.diamond = adapter.nguoidung.diamond - 5;
        adapter.luuthongtin();
        tvDiamond.setText(adapter.nguoidung.diamond + "");
        checktl();
    }
}