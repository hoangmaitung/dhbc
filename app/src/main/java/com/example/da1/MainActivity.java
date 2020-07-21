package com.example.da1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextPaint;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView = (TextView) findViewById(R.id.tv_game);
        textView.setText("ĐUỔI HÌNH BẮT CHỮ".toUpperCase());

        TextPaint paint = textView.getPaint();
        float width = paint.measureText("ĐUỔI HÌNH BẮT CHỮ");

        Shader textShader = new LinearGradient(0, 0, width, textView.getTextSize(),
                new int[]{
                        Color.parseColor("#ffff45"),
                        Color.parseColor("#fdf636"),
                        Color.parseColor("#ffec32"),
                        Color.parseColor("#ffec32"),
                        Color.parseColor("#3effe4"),
                        Color.parseColor("#fbff27"),
                        Color.parseColor("#ff4927"),
                        Color.parseColor("#ffc61a"),
                        Color.parseColor("#ffc7b4"),
                        Color.parseColor("#fffe1a"),
                        Color.parseColor("#fcff11"),
                        Color.parseColor("#ff7719"),
                        Color.parseColor("#46ffff"),
                        Color.parseColor("#f7ff1f"),
                        Color.parseColor("#f88c1d"),
                        Color.parseColor("#fffe3f"),
                        Color.parseColor("#fc750c"),
                }, null, Shader.TileMode.CLAMP);
        textView.getPaint().setShader(textShader);

        new CountDownTimer(3000, 3000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {

                startActivity(new Intent(MainActivity.this, HomeActivity.class));
                finish();
            }
        }.start();
    }
}