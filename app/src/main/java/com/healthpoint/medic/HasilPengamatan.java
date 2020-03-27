package com.healthpoint.medic;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.healthpoint.medic.model.CodeValuePair;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class HasilPengamatan extends AppCompatActivity {

    private Button btnLanjut;
    private Spinner spinTinggalUmum, spinInOutdoor;
    private EditText etNWadah, etTglVisit;
    private CheckBox cbBakMandi, cbBakWC, cbEmber, cbGenangan, cbSelokan, cbSumur, cbKolam, cbAdaJentik;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hasil_pengamatan);

        mContext = this;

        etNWadah = (EditText) findViewById((R.id.etNWadah));
        etTglVisit = (EditText) findViewById((R.id.etTglVisit));
        etTglVisit.setText(getTodayDate());

        btnLanjut = (Button) findViewById(R.id.btnLanjut);

        cbBakMandi = (CheckBox) findViewById(R.id.cbBakMandi);
        cbBakWC = (CheckBox) findViewById(R.id.cbBakWC);
        cbEmber = (CheckBox) findViewById(R.id.cbEmber);
        cbGenangan = (CheckBox) findViewById(R.id.cbGenangan);
        cbSelokan = (CheckBox) findViewById(R.id.cbSelokan);
        cbSumur = (CheckBox) findViewById(R.id.cbSumur);
        cbKolam = (CheckBox) findViewById(R.id.cbKolam);
        cbAdaJentik = (CheckBox) findViewById(R.id.cbAdaJentik);

        spinTinggalUmum = (Spinner) findViewById(R.id.spinTinggalUmum);
        List<CodeValuePair> listTinggalUmum = new ArrayList<>();
        listTinggalUmum.add(new CodeValuePair("R", "Tempat Tinggal"));
        listTinggalUmum.add(new CodeValuePair("U", "Tempat Umum"));
        ArrayAdapter<CodeValuePair> adapter1 = new ArrayAdapter<CodeValuePair>(mContext,
                android.R.layout.simple_spinner_item, listTinggalUmum);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinTinggalUmum.setAdapter(adapter1);

        spinInOutdoor = (Spinner) findViewById(R.id.spinInOutdoor);
        List<CodeValuePair> listInOutdoor = new ArrayList<>();
        listInOutdoor.add(new CodeValuePair("I", "Indoor"));
        listInOutdoor.add(new CodeValuePair("O", "Outdoor"));
        ArrayAdapter<CodeValuePair> adapter2 = new ArrayAdapter<CodeValuePair>(mContext,
                android.R.layout.simple_spinner_item, listInOutdoor);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinInOutdoor.setAdapter(adapter2);

        btnLanjut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            startActivity(new Intent(HasilPengamatan.this, UploadFoto.class));
            }
        });
    }

    private String getTodayDate() {
        Calendar calendar = Calendar.getInstance();;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = dateFormat.format(calendar.getTime());

        return date;
    }

    public void onCheckboxClicked(View view) {

//        switch (view.getId()) {
//
//            case R.id.cb1:
//                if (cbBakMandi.isChecked())
//                {  checked = cbBakMandi.getText().toString();}
//                Toast.makeText(getApplicationContext(), "Bak Mandi", Toast.LENGTH_LONG).show();
//                break;
//            case R.id.cb2:
//                if (cbBakWC.isChecked()){
//                    checked = cbBakWC.getText().toString();
//                }
//                Toast.makeText(getApplicationContext(), "Bak WC", Toast.LENGTH_LONG).show();
//                break;
//            case R.id.cb3:
//                if (cbEmber.isChecked()){checked = cbEmber.getText().toString();}
//                Toast.makeText(getApplicationContext(), "Ember", Toast.LENGTH_LONG).show();
//                break;
//            case R.id.cb4:
//                if (cbGenanganAir.isChecked()){checked = cbGenanganAir.getText().toString();}
//                Toast.makeText(getApplicationContext(), "Genangan Air", Toast.LENGTH_LONG).show();
//                break;
//            case R.id.cb5:
//                if (cbSelokan.isChecked()){checked = cbSelokan.getText().toString();}
//                Toast.makeText(getApplicationContext(), "Selokan", Toast.LENGTH_LONG).show();
//                break;
//            case R.id.cb6:
//                if(cbSumur.isChecked()){checked = cbSumur.getText().toString();}
//                Toast.makeText(getApplicationContext(),"Sumur",Toast.LENGTH_LONG).show();
//                break;
//            case R.id.cb7:
//                if (cbKolam.isChecked()){checked = cbKolam.getText().toString();}
//                Toast.makeText(getApplicationContext(),"Kolam",Toast.LENGTH_LONG).show();
//        }

    }
}
