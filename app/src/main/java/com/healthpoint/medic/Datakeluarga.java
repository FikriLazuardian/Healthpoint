package com.healthpoint.medic;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.healthpoint.medic.model.PostDataKeluarga;
import com.healthpoint.medic.network.APIClient;
import com.healthpoint.medic.network.APIInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Datakeluarga extends AppCompatActivity implements LocationListener, View.OnClickListener {
    private static final String TAG = "Datakeluarga";
    Context mContext;
    //    private final AppCompatActivity activity = mContext;

    Button submit, btnLokasi;
    EditText etNokk, etLat, etLon, etNamaKK, etAlamat, etIdKelurahan, etNanggota;
    APIInterface service;
    LocationManager mlocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datakeluarga);

        mContext = this;
        service = APIClient.createService(APIInterface.class);

        initViews();
        initListener();
//        initObjects();
    }

    public void initViews() {
        etNokk = (EditText) findViewById(R.id.etNokk);
        btnLokasi = (Button) findViewById(R.id.lokasiku);
        etLat = (EditText) findViewById(R.id.etLat);
        etLon = (EditText) findViewById(R.id.etLon);
        etNamaKK = (EditText) findViewById(R.id.etNamaKK);
        etAlamat = (EditText) findViewById(R.id.etAlamat);
        etIdKelurahan = (EditText) findViewById(R.id.idKel);
        etNanggota = (EditText) findViewById(R.id.etNanggota);
        submit = (Button) findViewById(R.id.btnSubmit1);
    }

    public void initListener() {
        submit.setOnClickListener(this);
        btnLokasi.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSubmit1:
                PostKeluarga();
                break;
            case R.id.lokasiku:
                getLocation();
                break;
        }
    }

    private void PostKeluarga() {
        String noKK = etNokk.getText().toString();
        String lat = etLat.getText().toString();
        String lon = etLon.getText().toString();
        String namaKK = etNamaKK.getText().toString();
        String idKelurahan = etIdKelurahan.getText().toString();
        String alamat = etAlamat.getText().toString();
        String nAnggota = etNanggota.getText().toString();

        if (noKK.isEmpty()) {
            etNokk.setError("masukkan No KK");
            etNokk.requestFocus();
            return;
        }
        if (idKelurahan.isEmpty()) {
            etIdKelurahan.setError("masukkan Id Kelurahan");
            etIdKelurahan.requestFocus();
            return;
        }

        Call<PostDataKeluarga> call = service.createKeluarga(noKK, lat, lon, namaKK, alamat, idKelurahan, nAnggota);
        call.enqueue(new Callback<PostDataKeluarga>() {
            @Override
            public void onResponse(Call<PostDataKeluarga> call, Response<PostDataKeluarga> response) {
                if (response.isSuccessful()) {
                    Log.i("debug", "onResponse:Berhasil");
                    Toast.makeText(mContext, "Tambah Keluarga Berhasil", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PostDataKeluarga> call, Throwable t) {
                Toast.makeText(mContext, "Tambah Keluarga Gagal", Toast.LENGTH_SHORT).show();
            }
        });

    }

    void getLocation() {
        try {
            mlocation = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            mlocation.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 5000, 5, this);
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        etLat.setText(String.valueOf(location.getLatitude()));
        etLon.setText(String.valueOf(location.getLongitude()));
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        Toast.makeText(mContext, "Please Enable GPS and Internet", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
