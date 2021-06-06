package com.example.uts_10118312.fragment;
//NAMA  : LUTHFI RIFQI ZULFIQAR
//NIM   : 10118312
//KELAS : IF-8
//TGL   : 3-6 JUNI 2021

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.uts_10118312.R;
import com.example.uts_10118312.adapter.Adapter;
import com.example.uts_10118312.helper.DBHelper;
import com.example.uts_10118312.main.AddEditActivity;
import com.example.uts_10118312.main.Models.Data;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class NoteFragment extends Fragment {
    ListView listView;
    AlertDialog.Builder dialog;
    List<Data> itemList = new ArrayList<>();
    Adapter adapter;
    DBHelper SQLite = new DBHelper(getActivity());
    public static final String TAG_ID = "id";
    public static final String TAG_NAME = "name";
    public static final String TAG_DATE = "date";
    public static final String TAG_CATEGORY = "category";
    public static final String TAG_ISI = "isi";

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_note, container, false);

        FloatingActionButton fab = view.findViewById(R.id.floatPlus);
        listView = view.findViewById(R.id.list);
        SQLite = new DBHelper(getActivity());
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AddEditActivity.class);
                startActivity(intent);
            }
        });
        adapter = new Adapter(getActivity(), itemList);
        listView.setAdapter(adapter);
        // long press listview to show edit and delete
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // TODO Auto-generated method stub
                final String idx = itemList.get(position).getId();
                final String name = itemList.get(position).getJudul();
                final String date = itemList.get(position).getDate();
                final String category = itemList.get(position).getKategori();
                final String isi = itemList.get(position).getIsi();
                final CharSequence[] dialogitem = {"Edit", "Delete"};
                dialog = new AlertDialog.Builder(getActivity());
                dialog.setTitle("Isi Catatan");
                dialog.setMessage(isi);
                dialog.setPositiveButton("Edit", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent intent = new Intent(getActivity(), AddEditActivity.class);
                        intent.putExtra(TAG_ID, idx);
                        intent.putExtra(TAG_NAME, name);
                        intent.putExtra(TAG_DATE, date);
                        intent.putExtra(TAG_CATEGORY, category);
                        intent.putExtra(TAG_ISI, isi);
                        startActivity(intent);
                    }
                });
                dialog.setNegativeButton("Hapus", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        SQLite.delete(Integer.parseInt(idx));
                        itemList.clear();
                        getAllData();
                    }
                });
                dialog.show();
            }
        });
        getAllData();
        return view;
    }

    private void getAllData() {
        ArrayList<HashMap<String, String>> row = SQLite.getAllData();
        for (int i = 0; i < row.size(); i++) {
            String id = row.get(i).get(TAG_ID);
            String title = row.get(i).get(TAG_NAME);
            String date = row.get(i).get(TAG_DATE);
            String category = row.get(i).get(TAG_CATEGORY);
            String isi = row.get(i).get(TAG_ISI);
            Data data = new Data();
            data.setId(id);
            data.setJudul(title);
            data.setKategori(category);
            data.setIsi(isi);
            data.setDate(date);
            itemList.add(data);
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onResume() {
        super.onResume();
        itemList.clear();
        getAllData();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return item.getItemId() == R.id.floatPlus;
    }

}
