package com.example.uts_10118312.adapter;
//NAMA  : LUTHFI RIFQI ZULFIQAR
//NIM   : 10118312
//KELAS : IF-8
//TGL   : 3-6 JUNI 2021

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.uts_10118312.R;
import com.example.uts_10118312.main.Models.Data;

import java.util.List;

public class Adapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<Data> items;

    public Adapter(Activity activity, List<Data> items) {
        this.activity = activity;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int location) {
        return items.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.list_row, null);
        TextView id = (TextView) convertView.findViewById(R.id.id);
        TextView name = (TextView) convertView.findViewById(R.id.name);
        TextView date = (TextView) convertView.findViewById(R.id.date);
        TextView category = (TextView) convertView.findViewById(R.id.category);
        Data data = items.get(position);
        id.setText(data.getId());
        name.setText(data.getJudul());
        date.setText(data.getDate());
        category.setText(data.getKategori());
        return convertView;
    }
}