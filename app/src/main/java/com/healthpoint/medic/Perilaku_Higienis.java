package com.healthpoint.medic;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.healthpoint.medic.database.DatabaseHelper;
import com.healthpoint.medic.model.Higienis;
import com.healthpoint.medic.network.APIInterface;

import java.util.ArrayList;
import java.util.List;

public class Perilaku_Higienis extends AppCompatActivity implements View.OnClickListener {
    private final AppCompatActivity activity = Perilaku_Higienis.this;
    Button submit3;
    RadioGroup sikatGigiGroup;
    RadioButton sikatGigi;
    Spinner bab;
    String[] lokbab = {"Lokasi biasa BAB", "Jamban", "Kolam/Sawah/Selokan", "Sungai/Danau/Laut", "Lubang Tanah", "Pantai/Tanah Lapangan/Kebun/Halaman"};
    DatabaseHelper databaseHelper;
    CheckBox cbGigi1, cbGigi2, cbGigi3, cbGigi4, cbGigi5, cbGigi6,
            cbTangan1, cbTangan2, cbTangan3, cbTangan4, cbTangan5, cbTangan6;
    ArrayAdapter<String> adapter;
    String checked,checked1;
    Higienis perilaku_higienis;
    String record = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perilaku_higienis);
        initViews();
        initListeners();
        initObjects();
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, lokbab);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bab.setAdapter(adapter);
    }

    public void initViews() {
        submit3 = (Button) findViewById(R.id.btnSubmit2);
        bab = (Spinner) findViewById(R.id.spinBab);
        cbGigi1 = (CheckBox) findViewById(R.id.cbgigi1);
        cbGigi2 = (CheckBox) findViewById(R.id.cbgigi2);
        cbGigi3 = (CheckBox) findViewById(R.id.cbgigi3);
        cbGigi4 = (CheckBox) findViewById(R.id.cbgigi4);
        cbGigi5 = (CheckBox) findViewById(R.id.cbgigi5);
        cbGigi6 = (CheckBox) findViewById(R.id.cbgigi6);
        cbTangan1 = (CheckBox) findViewById(R.id.cb1);
        cbTangan2 = (CheckBox) findViewById(R.id.cb2);
        cbTangan3 = (CheckBox) findViewById(R.id.cb3);
        cbTangan4 = (CheckBox) findViewById(R.id.cb4);
        cbTangan5 = (CheckBox) findViewById(R.id.cb5);
        cbTangan6 = (CheckBox) findViewById(R.id.cb6);
        sikatGigiGroup = (RadioGroup) findViewById(R.id.radioGigi);

    }

    public void initObjects() {
        databaseHelper = new DatabaseHelper(activity);

    }

    public void initListeners() {
        submit3.setOnClickListener(this);
        bab.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        record = "Jamban";
                        break;
                    case 1:
                        record = "Kolam/Sawah/Selokan";
                        break;
                    case 2:
                        record = "Sungai/Danau/Laut";
                        break;
                    case 3:
                        record = "Lubang Tanah";
                        break;
                    case 4:
                        record ="Pantai/Tanah Lapangan/Kebun/Halaman";
                }
                record = (String) bab.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }
    public void onCheckboxClicked(View view) {
        switch(view.getId()){
            case R.id.cbgigi1:
                if (cbGigi1.isChecked())
                {  checked = cbGigi1.getText().toString();}
                Toast.makeText(getApplicationContext(), "Saat Mandi Pagi", Toast.LENGTH_LONG).show();
                break;
            case R.id.cbgigi2:
                if (cbGigi2.isChecked()){
                    checked = cbGigi2.getText().toString();
                }
                Toast.makeText(getApplicationContext(), "Saat Mandi Sore", Toast.LENGTH_LONG).show();
                break;
            case R.id.cbgigi3:
                if (cbGigi3.isChecked()){checked = cbGigi3.getText().toString();}
                Toast.makeText(getApplicationContext(), "Sesudah Makan Pagi", Toast.LENGTH_LONG).show();
                break;
            case R.id.cbgigi4:
                if (cbGigi4.isChecked()){checked = cbGigi4.getText().toString();}
                Toast.makeText(getApplicationContext(), "Sebelum Bangun Pagi", Toast.LENGTH_LONG).show();
                break;
            case R.id.cbgigi5:
                if (cbGigi5.isChecked()){checked = cbGigi5.getText().toString();}
                Toast.makeText(getApplicationContext(), "Sebelum Tidur Malam", Toast.LENGTH_LONG).show();
                break;
            case R.id.cbgigi6:
                if(cbGigi6.isChecked()){checked = cbGigi6.getText().toString();}
                Toast.makeText(getApplicationContext(),"Sesudah Makan Siang",Toast.LENGTH_LONG).show();
                break;
            case R.id.cb1:
                if (cbTangan1.isChecked()){checked1 = cbTangan1.getText().toString();}
                Toast.makeText(getApplicationContext(),"Sebelum Menyiapkan Makanan",Toast.LENGTH_LONG).show();
            case R.id.cb2:
                if (cbTangan2.isChecked()){checked1 = cbTangan2.getText().toString();}
                Toast.makeText(getApplicationContext(),"Setiap Kali Tangan Kotor(pegang uang,binatang,berkebun)",Toast.LENGTH_LONG).show();
            case R.id.cb3:
                if (cbTangan3.isChecked()){checked1 = cbTangan3.getText().toString();}
                Toast.makeText(getApplicationContext(),"Setelah BAB",Toast.LENGTH_LONG).show();
            case R.id.cb4:
                if (cbTangan4.isChecked()){checked1 = cbTangan4.getText().toString();}
                Toast.makeText(getApplicationContext(),"Setelah Mencebok Bayi",Toast.LENGTH_LONG).show();
            case R.id.cb5:
                if (cbTangan5.isChecked()){checked1 = cbTangan5.getText().toString();}
                Toast.makeText(getApplicationContext(),"Setelah Penggunaan Pestisida",Toast.LENGTH_LONG).show();
            case R.id.cb6:
                if (cbTangan6.isChecked()){checked1 = cbTangan6.getText().toString();}
                Toast.makeText(getApplicationContext(),"Sebelum Menyusui Bayi",Toast.LENGTH_LONG).show();

        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSubmit2:
                int selectedSikatGigi = sikatGigiGroup.getCheckedRadioButtonId();
                sikatGigi = (RadioButton) findViewById(selectedSikatGigi);
                AddData();
                break;
        }
    }
    private void AddData() {
        String kpnSikatGigi = checked.getBytes().toString();
        String cuciTangan = checked1.getBytes().toString();
        // perilaku_higienis.setId_kebersihandiri();
    perilaku_higienis.setKpnsikat_gigi(kpnSikatGigi.getBytes().toString());
    perilaku_higienis.setCuci_tangan(cuciTangan.getBytes().toString());
    perilaku_higienis.setLokbab(bab.getSelectedItem().toString());
    perilaku_higienis.setSikat_gigi(sikatGigi.getText().toString());
    databaseHelper.addKebersihan_diri(perilaku_higienis);

    }
}
