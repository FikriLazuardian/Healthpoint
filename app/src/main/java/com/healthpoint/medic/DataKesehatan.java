package com.healthpoint.medic;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

public class DataKesehatan extends AppCompatActivity {
    private RelativeLayout menupenyakit;
    private RelativeLayout menuperilakuhigienis;
    private RelativeLayout menupemakaiantembakau;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datakesehatan);
        menupenyakit = (RelativeLayout) findViewById(R.id.menupenyakit);
        menuperilakuhigienis = (RelativeLayout) findViewById(R.id.menuperilakuhigienis);
        menupemakaiantembakau = (RelativeLayout) findViewById(R.id.menupemakaiantembakau);
        menupenyakit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent masuk = new Intent(DataKesehatan.this,Tbc.class);
                startActivity(masuk);
                finish();
            }
        });
        menupemakaiantembakau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent masuk = new Intent(DataKesehatan.this,Pemakaian_Tembakau.class);
                startActivity(masuk);
                finish();
            }
        });
        menuperilakuhigienis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent masuk = new Intent(DataKesehatan.this,Perilaku_Higienis.class);
                startActivity(masuk);
                finish();
            }
        });
    }
}
