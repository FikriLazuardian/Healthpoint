package com.healthpoint.medic;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.healthpoint.medic.database.DatabaseHelper;
import com.healthpoint.medic.model.Keluarga;
import com.healthpoint.medic.model.Penyakit;

public class Tbc extends AppCompatActivity implements View.OnClickListener {
    private final AppCompatActivity activity = Tbc.this;
    private Button submit;

    Spinner spinBatuk;
    String[] batuk = {"Ada batuk akhir-akhir ini?", "Ya,lebih dari 2 minggu terakhir", "Ya,kurang dari atau sama dengan 2 minggu",
            "Tidak"};
    Spinner spinTb;
    String[] tb = {"Pernah terdiagnosa TB paru oleh tenaga kesehatan", "Ya,dalam lebih dari atau sama dengan 6 bulan",
            "Ya,kurang dari 6 bulan", "Tidak"};
    Spinner spinTbPeriksa;
    String[] tbPeriksa = {"Pemeriksaaan yang digunakan untuk menegakkan diagnosis TB", "Pemeriksaan dahak",
            "Pemeriksaan foto/rontgen dada", "Lainnya", "Tidak dilakukan pemeriksaan penunjang"};
    Spinner spinTbObat;
    String[] tbObat = {"Apakah mengkonsumsi obat TB dari tenaga kesehatan", "Ya,selama", "Sedang dalam pengobatan, bulan ke- 1","Sedang dalam pengobatan, bulan ke- 2","Sedang dalam pengobatan, bulan ke- 3","Sedang dalam pengobatan, bulan ke- 4","Sedang dalam pengobatan, bulan ke- 5",
            "Sedang dalam pengobatan, bulan ke- 6","Sedang dalam pengobatan, bulan ke- 7","Sedang dalam pengobatan, bulan ke- 8","Sedang dalam pengobatan, bulan ke- 9","Sedang dalam pengobatan, bulan ke- 10","Sedang dalam pengobatan, bulan ke- 11","Sedang dalam pengobatan, bulan ke- 12", " Tidak"};
    Spinner spinSembuh;
    String[] sembuh = {"Apakah sudah pernah periksa dan dinyatakan sembuh", "Ya", "Tidak", "Masih dalam pengobatan"};
    ArrayAdapter<String> adapter;
    DatabaseHelper databaseHelper;
    Penyakit penyakit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tbc);
        initObjects();
        initViews();
        initListeners();


        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, batuk);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinBatuk.setAdapter(adapter);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, tb);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinTb.setAdapter(adapter);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, tbPeriksa);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinTbPeriksa.setAdapter(adapter);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, tbObat);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinTbObat.setAdapter(adapter);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, sembuh);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinSembuh.setAdapter(adapter);
    }

    public void initViews() {

        spinBatuk = (Spinner) findViewById(R.id.spinbatuk);
        spinTb = (Spinner) findViewById(R.id.spintb);
        spinTbPeriksa = (Spinner) findViewById(R.id.spintbperiksa);
        spinTbObat = (Spinner) findViewById(R.id.spintbobat);
        spinSembuh = (Spinner) findViewById(R.id.spintbsembuh);
        submit = (Button) findViewById(R.id.btnSubmit3);

    }

    public void initListeners() {
    submit.setOnClickListener(this);

    }
    public void initObjects(){
        databaseHelper = new DatabaseHelper(activity);
        penyakit = new Penyakit();
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSubmit3:
            AddData();
            break;
        }

    }
private void AddData() {




    }
    private void emptyInputEditText() {
   //     etNokk.setText(null);
     //   etProv.setText(null);
       // etKab.setText(null);
        //etKec.setText(null);
       // etRw.setText(null);
       // etRt.setText(null);
        //etNormh.setText(null);
        //etKodpos.setText(null);

 }
}
