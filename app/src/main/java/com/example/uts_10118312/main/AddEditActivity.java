package com.example.uts_10118312.main;
//NAMA  : LUTHFI RIFQI ZULFIQAR
//NIM   : 10118312
//KELAS : IF-8
//TGL   : 3-6 JUNI 2021

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.uts_10118312.R;
import com.example.uts_10118312.fragment.NoteFragment;
import com.example.uts_10118312.helper.DBHelper;


public class AddEditActivity extends AppCompatActivity {
    EditText txt_id, txt_name, txt_category, txt_isi;
    Button btn_submit, btn_cancel;
    DBHelper SQLite = new DBHelper(this);
    String id, name, isi, category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit);
        txt_id = findViewById(R.id.txt_id);
        txt_name = findViewById(R.id.txt_name);
        txt_category = findViewById(R.id.txt_category);
        txt_isi = findViewById(R.id.txt_isi);
        btn_submit = findViewById(R.id.btn_submit);
        btn_cancel = findViewById(R.id.btn_cancel);
        id = getIntent().getStringExtra(NoteFragment.TAG_ID);
        name = getIntent().getStringExtra(NoteFragment.TAG_NAME);
        isi = getIntent().getStringExtra(NoteFragment.TAG_ISI);
        category = getIntent().getStringExtra(NoteFragment.TAG_CATEGORY);
        if (id == null || id == "") {
            setTitle("Tambah Data");
        } else {
            setTitle("Edit Data");
            txt_id.setText(id);
            txt_name.setText(name);
            txt_isi.setText(isi);
            txt_category.setText(category);
        }
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (txt_id.getText().toString().equals("")) {
                        save();
                    } else {
                        edit();
                    }
                } catch (Exception e) {
                    Log.e("Submit", e.toString());
                }
            }
        });
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                blank();
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                blank();
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    // Make blank all Edit Text
    private void blank() {
        txt_name.requestFocus();
        txt_id.setText(null);
        txt_name.setText(null);
        txt_category.setText(null);
    }

    // Save data to SQLite database
    private void save() {
        if (String.valueOf(txt_name.getText()) == null || String.valueOf(txt_name.getText()).equals("") ||
                String.valueOf(txt_category.getText()) == null || String.valueOf(txt_category.getText()).equals("") ||
                String.valueOf(txt_isi.getText()) == null || String.valueOf(txt_isi.getText()).equals("")
        ) {
            Toast.makeText(getApplicationContext(),
                    "Inputan tidak boleh kosong ...", Toast.LENGTH_SHORT).show();
        } else {
            SQLite.insert(txt_name.getText().toString().trim(), txt_category.getText().toString().trim(),
                    txt_isi.getText().toString().trim());
            blank();
            finish();
        }
    }

    // Update data in SQLite database
    private void edit() {
        if (String.valueOf(txt_name.getText()) == null || String.valueOf(txt_name.getText()).equals("") ||
                String.valueOf(txt_category.getText()) == null || String.valueOf(txt_category.getText()).equals("")) {
            Toast.makeText(getApplicationContext(),
                    "Please input name or address ...", Toast.LENGTH_SHORT).show();
        } else {
            SQLite.update(Integer.parseInt(txt_id.getText().toString().trim()), txt_name.getText().toString().trim(),
                    txt_category.getText().toString().trim(), txt_isi.getText().toString().trim());
            blank();
            finish();
        }
    }
}
