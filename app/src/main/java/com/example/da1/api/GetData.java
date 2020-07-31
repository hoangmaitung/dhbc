package com.example.da1.api;

import android.os.AsyncTask;

import com.example.da1.model.Cauhoi;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class GetData extends AsyncTask<Void, Void, Void> {
    String data;
    @Override
    protected Void doInBackground(Void... voids) {
        //chay
        OkHttpClient client = new OkHttpClient();
        //khai bao dia chi request
        Request request = new Request.Builder()
                .url("https://5f0f11d3faef3500160b8f45.mockapi.io/laycauhoi")
                .build();
        //khai bao nhan du lieu
        Response response = null;
        //tra du lieu
        try {
            //goi den request
            response = client.newCall(request).execute();
            //lay kq tra ra
            ResponseBody responseBody = response.body();
            //gan kq vao data
            data = responseBody.string();
        } catch (IOException e) {
            data = null;
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        //neu co data
        if (data != null) {
            try {
                //xoa du lieu arrcauhoi
                Data.getData().arrcauhoi.clear();
                //chuyen ve dang json
                JSONArray array = new JSONArray(data);

                //lay tung json
                for (int i = 0; i < array.length(); i++) {
                    JSONObject object = array.getJSONObject(i);
                    //gan du lieu
                    Cauhoi cauhoi = new Cauhoi();
                    cauhoi.ten = object.getString("ten");
                    cauhoi.dapan = object.getString("dapan");
                    cauhoi.anh = object.getString("anh");
                    //them vao list
                    Data.getData().arrcauhoi.add(cauhoi);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {

        }
    }
}
