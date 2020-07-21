package com.example.da1;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.da1.api.Data;
import com.example.da1.api.GetData;
import com.example.da1.presenter.HomePresenter;
import com.example.da1.presenter.HomeView;

public class HomeActivity extends AppCompatActivity implements HomeView, View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        new GetData().execute();
        anhxa();
        action();
    }

    Button btnHd;
    Button btnExit;
    Button playGame;
    HomePresenter homePresenter;

    public void anhxa(){
        playGame = (Button) findViewById(R.id.play_game);
        btnHd = (Button) findViewById(R.id.btn_hd);
        btnExit = (Button) findViewById(R.id.btn_exit);
    }

    private void action() {
        playGame.setOnClickListener(this);
        btnHd.setOnClickListener(this);
        btnExit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.play_game) {
            homePresenter.playgame();
        }
        if (v.getId() == R.id.btn_hd) {
            homePresenter.hdgame();
        }
        if(v.getId() == R.id.btn_exit) {
            homePresenter.exitgame();
        }
    }

    @Override
    public void playGame() {
        if (Data.getData().arrcauhoi.size() != 0) {
            startActivity(new Intent(HomeActivity.this, PlayActivity.class));
        }
    }

    @Override
    public void hdGame() {
        startActivity(new Intent(HomeActivity.this, HdActivity.class));
    }

    @Override
    public void exitGame() {
        showConfirmDialog();
    }


    private void showConfirmDialog() {
        final Dialog dialog = new Dialog(HomeActivity.this);
        dialog.setContentView(R.layout.dialog_exit);
        Button btnNo = dialog.findViewById(R.id.btn_no);
        Button btnYes = dialog.findViewById(R.id.btn_yes);

        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        dialog.show();


    }

}