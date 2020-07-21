package com.example.da1.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.da1.R;

import java.util.ArrayList;
import java.util.List;

public class DapanAdapter extends ArrayAdapter<String> {

    private Context context;
    private ArrayList<String> list;

    public DapanAdapter(@NonNull Context context, int resource, @NonNull List<String> objects) {
        super(context, resource, objects);
        this.context = context;
        this.list = new ArrayList<>(objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_dap_an, null);
        }

        TextView tvDapan = convertView.findViewById(R.id.tvDapan);
        tvDapan.setText(this.list.get(position));

        return convertView;
    }
}
