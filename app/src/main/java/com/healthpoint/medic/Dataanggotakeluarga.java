package com.healthpoint.medic;

import android.app.DatePickerDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.healthpoint.medic.database.DatabaseHelper;
import com.healthpoint.medic.model.Agama;
import com.healthpoint.medic.model.Anggota_Keluarga;
import com.healthpoint.medic.model.Jkn;
import com.healthpoint.medic.model.Pekerjaan;
import com.healthpoint.medic.model.Pendidikan;
import com.healthpoint.medic.model.PostDataIndividu;
import com.healthpoint.medic.model.RelasiKK;
import com.healthpoint.medic.model.ResponseAgama;
import com.healthpoint.medic.model.ResponseJkn;
import com.healthpoint.medic.model.ResponseNikah;
import com.healthpoint.medic.model.ResponsePekerjaan;
import com.healthpoint.medic.model.ResponsePendidikan;
import com.healthpoint.medic.model.ResponseRelasiKK;
import com.healthpoint.medic.model.StatusNikah;
import com.healthpoint.medic.network.APIClient;
import com.healthpoint.medic.network.APIInterface;
import com.healthpoint.medic.tokenmanager.TokenManager;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Dataanggotakeluarga extends AppCompatActivity {
    private static final String TAG = "Dataanggotakeluarga";
    Context mContext;
    private final AppCompatActivity activity = Dataanggotakeluarga.this;
    Button submit;
    EditText etNama, etNIK, etNoHP, tglLahir;
    RadioGroup rgJenisKelamin;
    Spinner spinRelasiKK, spinAgama, spinPendidikan, spinJkn, spinStatNikah, spinPekerjaan;
    Anggota_Keluarga anggotakeluarga;
    DatabaseHelper databaseHelper;
//    TokenManager tokenManager;
    APIInterface service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dataanggotakeluarga);

        mContext = this;
//        tokenManager = TokenManager.getInstance(getSharedPreferences("prefs", MODE_PRIVATE));
//        service = APIClient.createServiceWithAuth(APIInterface.class, tokenManager);
        service = APIClient.createService(APIInterface.class);

        initViews();
        loadSpinner();
        initListener();
//        initObjects();
    }

    public void initViews() {
        etNIK = (EditText) findViewById(R.id.etNIK);
        spinRelasiKK = (Spinner) findViewById(R.id.spinhubKK);
        etNama = (EditText) findViewById(R.id.etNama);
        tglLahir = (EditText) findViewById(R.id.etTanggallhr);
        rgJenisKelamin = (RadioGroup) findViewById(R.id.rg_jk);
        spinAgama = (Spinner) findViewById(R.id.spinAgama);
        spinPendidikan = (Spinner) findViewById(R.id.spinPendidikan);
        spinJkn = (Spinner) findViewById(R.id.spinJKN);
        spinStatNikah = (Spinner) findViewById(R.id.spinstatKawin);
        spinPekerjaan = (Spinner) findViewById(R.id.spinPekerjaan);
        etNoHP = (EditText) findViewById(R.id.etNoHP);
        submit = (Button) findViewById(R.id.btnSimpan);
    }

    private void loadSpinner() {
        service.getAPIrelasiKK().enqueue(new Callback<ResponseRelasiKK>() {
            @Override
            public void onResponse(Call<ResponseRelasiKK> call, Response<ResponseRelasiKK> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        List<RelasiKK> daftar = response.body().getDaftarRelasiKK();

                        ArrayAdapter<RelasiKK> arrayAdapter = new ArrayAdapter<RelasiKK>(mContext, android.R.layout.simple_spinner_item, daftar);
                        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinRelasiKK.setAdapter(arrayAdapter);
                    } else {
                        Log.i("onEmptyResponse", "Returned empty response");
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseRelasiKK> call, Throwable t) {
                Log.e(TAG, "Error get relasi KK");
                t.getStackTrace();
                Toast.makeText(mContext, "Error get relasi KK", Toast.LENGTH_SHORT).show();
            }
        });

        service.getAPIAgama().enqueue(new Callback<ResponseAgama>() {
            @Override
            public void onResponse(Call<ResponseAgama> call, Response<ResponseAgama> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        List<Agama> daftar = response.body().getDaftarAgama();

                        ArrayAdapter<Agama> arrayAdapter = new ArrayAdapter<Agama>(mContext, android.R.layout.simple_spinner_item, daftar);
                        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinAgama.setAdapter(arrayAdapter);
                    } else {
                        Log.i("onEmptyResponse", "Returned empty response");
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseAgama> call, Throwable t) {
                Log.e(TAG, "Error get agama");
                t.getStackTrace();
                Toast.makeText(mContext, "Error get agama", Toast.LENGTH_SHORT).show();
            }
        });

        service.getAPIPendidikan().enqueue(new Callback<ResponsePendidikan>() {
            @Override
            public void onResponse(Call<ResponsePendidikan> call, Response<ResponsePendidikan> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        List<Pendidikan> daftar = response.body().getDaftarPendidikan();

                        ArrayAdapter<Pendidikan> arrayAdapter = new ArrayAdapter<Pendidikan>(mContext, android.R.layout.simple_spinner_item, daftar);
                        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinPendidikan.setAdapter(arrayAdapter);
                    } else {
                        Log.i("onEmptyResponse", "Returned empty response");
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponsePendidikan> call, Throwable t) {
                Log.e(TAG, "Error get pendidikan");
                t.getStackTrace();
                Toast.makeText(mContext, "Error get pendidikan", Toast.LENGTH_SHORT).show();
            }
        });

        service.getAPIJkn().enqueue(new Callback<ResponseJkn>() {
            @Override
            public void onResponse(Call<ResponseJkn> call, Response<ResponseJkn> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        List<Jkn> daftar = response.body().getDaftarJkn();

                        ArrayAdapter<Jkn> arrayAdapter = new ArrayAdapter<Jkn>(mContext, android.R.layout.simple_spinner_item, daftar);
                        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinJkn.setAdapter(arrayAdapter);
                    } else {
                        Log.i("onEmptyResponse", "Returned empty response");
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseJkn> call, Throwable t) {
                Log.e(TAG, "Error get jkn");
                t.getStackTrace();
                Toast.makeText(mContext, "Error get jkn", Toast.LENGTH_SHORT).show();
            }
        });

        service.getAPIstatusNikah().enqueue(new Callback<ResponseNikah>() {
            @Override
            public void onResponse(Call<ResponseNikah> call, Response<ResponseNikah> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        List<StatusNikah> daftar = response.body().getDaftarStatusNikah();

                        ArrayAdapter<StatusNikah> arrayAdapter = new ArrayAdapter<StatusNikah>(mContext, android.R.layout.simple_spinner_item, daftar);
                        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinStatNikah.setAdapter(arrayAdapter);
                    } else {
                        Log.i("onEmptyResponse", "Returned empty response");
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseNikah> call, Throwable t) {
                Log.e(TAG, "Error get status nikah");
                t.getStackTrace();
                Toast.makeText(mContext, "Error get status nikah", Toast.LENGTH_SHORT).show();
            }
        });

        service.getAPIPekerjaan().enqueue(new Callback<ResponsePekerjaan>() {
            @Override
            public void onResponse(Call<ResponsePekerjaan> call, Response<ResponsePekerjaan> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        List<Pekerjaan> daftar = response.body().getDaftarPekerjaan();

                        ArrayAdapter<Pekerjaan> arrayAdapter = new ArrayAdapter<Pekerjaan>(mContext, android.R.layout.simple_spinner_item, daftar);
                        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinPekerjaan.setAdapter(arrayAdapter);
                    } else {
                        Log.i("onEmptyResponse", "Returned empty response");
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponsePekerjaan> call, Throwable t) {
                Log.e(TAG, "Error get pekerjaan");
                t.getStackTrace();
                Toast.makeText(mContext, "Error get pekerjaan", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void initObjects() {
        databaseHelper = new DatabaseHelper(activity);
        anggotakeluarga = new Anggota_Keluarga();
    }

    public void initListener() {
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btnSimpan:
                    PostIndividu();
                    //                postDataToSQLite();
                    break;

                case R.id.back:
                    finish();
                    break;
            }
            }
        });

        spinRelasiKK.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                RelasiKK obj = (RelasiKK) parent.getItemAtPosition(position);
                Toast.makeText(mContext, "Set relasi " + obj.getOpsiRelasi(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinAgama.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Agama obj = (Agama) parent.getItemAtPosition(position);
                Toast.makeText(mContext, "Set agama " + obj.getOpsiAgama(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinPendidikan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Pendidikan obj = (Pendidikan) parent.getItemAtPosition(position);
                Toast.makeText(mContext, "Set pendidikan " + obj.getOpsiPendidikan(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinJkn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Jkn obj = (Jkn) parent.getItemAtPosition(position);
                Toast.makeText(mContext, "Set Jkn " + obj.getOpsiJkn(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinStatNikah.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                StatusNikah obj = (StatusNikah) parent.getItemAtPosition(position);
                Toast.makeText(mContext, "Set statusnikah " + obj.getOpsiStatusNikah(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinPekerjaan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Pekerjaan obj = (Pekerjaan) parent.getItemAtPosition(position);
                Toast.makeText(mContext, "Set pekerjaan " + obj.getOpsiPekerjaan(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        final Calendar myCalendar = Calendar.getInstance();
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                String format = "yyyy-MM-dd";
                SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.US);
                tglLahir.setText(sdf.format(myCalendar.getTime()));
            }
        };

        tglLahir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(mContext, date, myCalendar.get(Calendar.YEAR),
                        myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    private void PostIndividu() {
        String nik = etNIK.getText().toString();
        if (nik.isEmpty()) {
            etNIK.setError("masukkan NIK");
            etNIK.requestFocus();
            return;
        }
        int id = rgJenisKelamin.getCheckedRadioButtonId();
        String jk = "L";
        switch (id) {
            case R.id.rb_pr:
                jk = "P";
                break;
        }

        Call<PostDataIndividu> call = service.createIndividu("104",
                nik,
                ((RelasiKK) spinRelasiKK.getSelectedItem()).getIdRelasi(),
                etNama.getText().toString(),
                tglLahir.getText().toString(),
                jk,
                ((Agama) spinAgama.getSelectedItem()).getIdAgama(),
                ((Pendidikan) spinPendidikan.getSelectedItem()).getIdPendidikan(),
                ((Jkn) spinJkn.getSelectedItem()).getIdJkn(),
                ((StatusNikah) spinStatNikah.getSelectedItem()).getIdStatusNikah(),
                ((Pekerjaan) spinPekerjaan.getSelectedItem()).getIdPekerjaan(),
                etNoHP.getText().toString()
        );
        call.enqueue(new Callback<PostDataIndividu>() {
            @Override
            public void onResponse(Call<PostDataIndividu> call, Response<PostDataIndividu> response) {
                if (response.isSuccessful()) {
                    Log.i("debug", "onResponse:Berhasil");
                    Toast.makeText(mContext, "Tambah Individu Berhasil", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PostDataIndividu> call, Throwable t) {
                Toast.makeText(mContext, "Tambah Individu Gagal", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void addData() {
//        anggotakeluarga.setNik(etNIK.getText().toString());
//        anggotakeluarga.setNama(etNama.getText().toString());
//        anggotakeluarga.setTempat_lahir(etNoHP.getText().toString());
//        anggotakeluarga.setTanggal_lahir(tglLahir.getText().toString());
//        anggotakeluarga.setJenis_kelamin(String.valueOf(jenkelbtn.isEnabled()));
//        anggotakeluarga.setUmur((int)Long.parseLong(umur.getText().toString()));
//        anggotakeluarga.setAgama(spinAgama.getSelectedItem().toString());
//        anggotakeluarga.setStatus_kawin(spinKawin.getSelectedItem().toString());
//        anggotakeluarga.setHubungan_kk(spinHubKK.getSelectedItem().toString());
//        anggotakeluarga.setPendidikan(spinPendidikan.getSelectedItem().toString());
//        anggotakeluarga.setPekerjaan(spinPekerjaan.getSelectedItem().toString());
//        anggotakeluarga.setJkn(spinJkn.getSelectedItem().toString());
//        anggotakeluarga.setHamil(spinHamil.getSelectedItem().toString());
//        databaseHelper.addAnggota_Keluarga(anggotakeluarga);
        emptyInputEditText();
    }

    private void emptyInputEditText() {
        etNIK.setText(null);
        etNama.setText(null);
        etNoHP.setText(null);
    }
}