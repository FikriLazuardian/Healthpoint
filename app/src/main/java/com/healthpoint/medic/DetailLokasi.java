package com.healthpoint.medic;

import android.util.Log;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.healthpoint.medic.adapter.AdapterDaftarRumah;
import com.healthpoint.medic.model.DaftarRumah;
import com.healthpoint.medic.model.DaftarRumahItem;
import com.healthpoint.medic.model.DataPengguna;
import com.healthpoint.medic.model.Kelurahan;
import com.healthpoint.medic.model.PostResponse;
import com.healthpoint.medic.model.ResponseDaftarRumah;
import com.healthpoint.medic.model.ResponseKelurahan;
import com.healthpoint.medic.network.APIClient;
import com.healthpoint.medic.network.APIInterface;

import java.util.List;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailLokasi extends AppCompatActivity {
    private static final String TAG = "DetailLokasi";

    private Context mContext;
    private Button btnSimpan;
    private Spinner spinKelurahan, spinKecamatan, spinKota, spinNegara;
    private TextView tvRT;
    private EditText etAlamat, etRW, etLat, etLon, etNamaPemilik, etRT;
    private APIInterface service;
    int spinKot;
    String spinKel;
    DataPengguna dataPengguna;
    Bundle mBundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_lokasi);

        mContext = this;
        service = APIClient.createService(APIInterface.class);
        mBundle = getIntent().getExtras();

        initViews();
        loadSpinner();
        initListener();
    }

    public void initViews() {
        etLat = (EditText) findViewById((R.id.etLat));
        etLon = (EditText) findViewById((R.id.etLon));
        etNamaPemilik = (EditText) findViewById((R.id.etNamaPemilik));
        etAlamat = (EditText) findViewById((R.id.etAlamat));
        tvRT = (TextView) findViewById(R.id.tvRT);
        //etRT = (EditText) findViewById(R.id.etRT);
        etRW = (EditText) findViewById((R.id.etRW));
        btnSimpan = (Button) findViewById(R.id.btnSimpan);
        spinKelurahan = (Spinner) findViewById(R.id.spinKelurahan);
        spinKecamatan = (Spinner) findViewById(R.id.spinKecamatan);
        spinKota = (Spinner) findViewById(R.id.spinKota);
        spinNegara = (Spinner) findViewById(R.id.spinNegara);



        /**
         * Kita cek apakah ada Bundle atau tidak
         */
        if (getIntent().getExtras() != null) {
            /**
             * Jika Bundle ada, ambil data dari Bundle
             */
            Bundle bundle = getIntent().getExtras();
            etLat.setText(bundle.getString("lat"));
            etLon.setText(bundle.getString("lon"));
        } else {
            /**
             * Apabila Bundle tidak ada, ambil dari Intent
             */
            etLat.setText(getIntent().getStringExtra("lat"));
            etLon.setText(getIntent().getStringExtra("lon"));
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        loadPengguna(mBundle.getInt("id_pengguna"));
    }

    private void loadSpinner() {

    spinKota.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            String kota = String.valueOf(spinKota.getSelectedItem());
            if (kota.matches("Jakarta Pusat")){
                spinKot=1;
                populateKecamatan();
            }
            if (kota.matches("Sukabumi")){
                spinKot=2;
                populateKecamatan();
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    });
        spinKecamatan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String kec = (String) adapterView.getItemAtPosition(i);
               if (kec.matches("Senen")&& spinKot==1){
                    service.getDaftarKeluByKeca("3173030").enqueue(new Callback<ResponseKelurahan>() {
                        @Override
                        public void onResponse(Call<ResponseKelurahan> call, Response<ResponseKelurahan> response) {
                            if (response.isSuccessful()) {
                                if (response.body() != null) {
                                    List<Kelurahan> daftar = response.body().getDaftarKelurahan();

                                    ArrayAdapter<Kelurahan> arrayAdapter = new ArrayAdapter<Kelurahan>(mContext, android.R.layout.simple_spinner_item, daftar);
                                    arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinKelurahan.setAdapter(arrayAdapter);
                                    spinKelurahan.setSelection(5); // langsung terpilih kel BUNGUR
                                } else {
                                    Log.i("onEmptyResponse", "Returned empty response");
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseKelurahan> call, Throwable t) {
                            Log.e(TAG, "Error get kelurahan by kecamatan");
                            t.getStackTrace();
                            Toast.makeText(mContext, "Error get kelurahan by kecamatan", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                if (kec.matches("Pabuaran") && spinKot ==2) {
                    service.getDaftarKeluByKeca("3202090").enqueue(new Callback<ResponseKelurahan>() {
                        @Override
                        public void onResponse(Call<ResponseKelurahan> call, Response<ResponseKelurahan> response) {
                            if (response.isSuccessful()) {
                                if (response.body() != null) {
                                    List<Kelurahan> daftar = response.body().getDaftarKelurahan();

                                    ArrayAdapter<Kelurahan> arrayAdapter = new ArrayAdapter<Kelurahan>(mContext, android.R.layout.simple_spinner_item, daftar);
                                    arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinKelurahan.setAdapter(arrayAdapter);
                                    spinKelurahan.setSelection(2); // langsung terpilih kel Citamiang
                                } else {
                                    Log.i("onEmptyResponse", "Returned empty response");
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseKelurahan> call, Throwable t) {
                            Log.e(TAG, "Error get kelurahan by kecamatan");
                            t.getStackTrace();
                            Toast.makeText(mContext, "Error get kelurahan by kecamatan", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                if (kec.matches("Sukabumi")&& spinKot==2){
                    service.getDaftarKeluByKeca("3202180").enqueue(new Callback<ResponseKelurahan>() {
                        @Override
                        public void onResponse(Call<ResponseKelurahan> call, Response<ResponseKelurahan> response) {
                            if (response.isSuccessful()){
                                if (response.body() !=null){
                                    List<Kelurahan> daftar = response.body().getDaftarKelurahan();
                                    ArrayAdapter<Kelurahan> arrayAdapter = new ArrayAdapter<>(mContext,android.R.layout.simple_spinner_item,daftar);
                                    arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinKelurahan.setAdapter(arrayAdapter);
                                    spinKelurahan.setSelection(2);
                                }
                                else{
                                    Log.i("onEmptyResponse","Returned empty response");
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseKelurahan> call, Throwable t) {
                            Log.e(TAG, "Error get kelurahan by kecamatan");
                            t.getStackTrace();
                            Toast.makeText(mContext, "Error get kelurahan by kecamatan", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void populateKecamatan(){
        if (spinKot==1){
            String [] js = {"Senen"};
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, js);
            spinKecamatan.setAdapter(adapter);
        }else if (spinKot==2){
            String[] sukabumi = {"Pabuaran","Sukabumi"};
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, sukabumi);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinKecamatan.setAdapter(adapter);
        }

    }


    public void initListener() {

        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<PostResponse> call = service.createRumah(
                        etLat.getText().toString(),
                        etLon.getText().toString(),
                        etNamaPemilik.getText().toString(),
                        etAlamat.getText().toString(),
                        tvRT.getText().toString(),
                        etRW.getText().toString(),
                        ((Kelurahan) spinKelurahan.getSelectedItem()).getIdKelurahan()
                );
                call.enqueue(new Callback<PostResponse>() {
                    @Override
                    public void onResponse(Call<PostResponse> call, Response<PostResponse> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(mContext, "Tambah Rumah Baru Berhasil", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }

                    @Override
                    public void onFailure(Call<PostResponse> call, Throwable t) {
                        Toast.makeText(mContext, "Tambah Rumah Baru Gagal", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
            }
        });
    }

    private void loadPengguna(int idPengguna){
        Call<DataPengguna> call = service.getPengguna(idPengguna);
        call.enqueue(new Callback<DataPengguna>() {
            @Override
            public void onResponse(Call<DataPengguna> call, Response<DataPengguna> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        dataPengguna = response.body();
                        tvRT.setText(Integer.toString(dataPengguna.getRt()));

                    }


                }else{
                    Toast.makeText(mContext, "Data Pengguna belum ada", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<DataPengguna> call, Throwable t) {

            }
        });
    }

}
