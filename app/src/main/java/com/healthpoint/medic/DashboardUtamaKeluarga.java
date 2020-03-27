package com.healthpoint.medic;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.healthpoint.medic.adapter.AdapterDaftarKeluarga;
import com.healthpoint.medic.adapter.AdapterDashboardKeluarga;
import com.healthpoint.medic.model.DaftarIndividuItem;
import com.healthpoint.medic.model.DaftarKeluargaItem;
import com.healthpoint.medic.model.ResponseDetilKeluarga;
import com.healthpoint.medic.network.APIClient;
import com.healthpoint.medic.network.APIInterface;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardUtamaKeluarga extends AppCompatActivity implements View.OnClickListener  {
    AdapterDashboardKeluarga adapterDashboardKeluarga;
    private List<DaftarIndividuItem> daftarIndividuItemList = new ArrayList<>();
    private RelativeLayout menu1;
    private RelativeLayout menu2;
    private RelativeLayout menu3;
    RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    TextView txtNamaKepalaKeluarga, txtNokk, txtAlamat, txtJmlhAnggota, txtPeriodePengambilan;
    APIInterface service;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_utama_keluarga);

        service = APIClient.createService(APIInterface.class);

        initViews();
        initListener();

        LoadDetilKeluarga(getIntent().getStringExtra("no_kk"));
    }

    public void initViews() {
        txtNamaKepalaKeluarga = (TextView) findViewById(R.id.txtNamaKepalaKeluarga);
        txtNokk = (TextView) findViewById(R.id.txtNokk);
        txtAlamat = (TextView) findViewById(R.id.txtAlamat);
        txtJmlhAnggota = (TextView) findViewById(R.id.txtJmlhAnggota);
        txtPeriodePengambilan = (TextView) findViewById(R.id.txtPeriodePengambilan);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerdashboard);

                //Menggunakan Layout Manager, Dan Membuat List Secara Vertical
                layoutManager = new LinearLayoutManager(this);

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(layoutManager);
        adapterDashboardKeluarga = new AdapterDashboardKeluarga(this,daftarIndividuItemList);
        recyclerView.setAdapter(adapterDashboardKeluarga);
        menu1 = (RelativeLayout) findViewById(R.id.menubtntambahanggota);
        menu2 = (RelativeLayout) findViewById(R.id.menubtnpenyakit);
        menu3 = (RelativeLayout) findViewById(R.id.menubtnkesehatan);

//        etNokk = (EditText) findViewById(R.id.etNokk);
//        btnLokasi = (Button) findViewById(R.id.lokasiku);
//        etLat = (EditText) findViewById(R.id.etLat);
//        etLon = (EditText) findViewById(R.id.etLon);
//        etNamaKK = (EditText) findViewById(R.id.etNamaKK);
//        etAlamat = (EditText) findViewById(R.id.etAlamat);
//        etIdKelurahan = (EditText) findViewById(R.id.idKel);
//        etNanggota = (EditText) findViewById(R.id.etNanggota);
//        submit = (Button) findViewById(R.id.btnSubmit1);
    }

    public void initListener() {
        menu1.setOnClickListener(this);
        menu2.setOnClickListener(this);
        menu3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.menubtntambahanggota:
                startActivity(new Intent(DashboardUtamaKeluarga.this, Dataanggotakeluarga.class));
                break;
            case R.id.menubtnpenyakit:
                startActivity(new Intent(DashboardUtamaKeluarga.this, Tbc.class));
                break;
            case R.id.menubtnkesehatan:
                startActivity(new Intent(DashboardUtamaKeluarga.this, Perilaku_Higienis.class));
                break;
        }
    }

    private void LoadDetilKeluarga(String no_kk) {
        Call<ResponseDetilKeluarga> call = service.getDetilKeluarga(no_kk);
        call.enqueue(new Callback<ResponseDetilKeluarga>() {
            @Override
            public void onResponse(Call<ResponseDetilKeluarga> call, Response<ResponseDetilKeluarga> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        final DaftarKeluargaItem daftarKeluargaItem = response.body().getDetilKeluarga();
                        txtNokk.setText("No. KK : " + daftarKeluargaItem.getNoKK());
                        txtNamaKepalaKeluarga.setText("Nama Kepala Keluarga : " + daftarKeluargaItem.getNamaKK());
                        txtAlamat.setText("Alamat : " + daftarKeluargaItem.getAlamat());
                        txtJmlhAnggota.setText("Jumlah Anggota : " + daftarKeluargaItem.getNAnggota());
                        txtPeriodePengambilan.setText("Periode Pengambilan Data : " + daftarKeluargaItem.getTglCatat());
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseDetilKeluarga> call, Throwable t) {
                Log.d("Error", t.getMessage());
            }
        });
    }

}
