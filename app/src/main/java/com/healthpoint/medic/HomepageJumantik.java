package com.healthpoint.medic;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.LinearLayout;

import com.healthpoint.medic.model.DaftarKunjunganItem;
import com.healthpoint.medic.model.KunjunganPengguna;
import com.healthpoint.medic.model.ResponseDaftarKunjungan;
import com.healthpoint.medic.model.ResponseKunjunganPengguna;
import com.healthpoint.medic.network.APIClient;
import com.healthpoint.medic.network.APIInterface;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomepageJumantik extends AppCompatActivity  {
    private Context mContext;
    private Bundle mBundle;
    private APIInterface service;
    private DatePickerDialog datePickerDialog;

    private LinearLayout btnKtpKader, btnSuratTugas, wrapperKunjunganTerakhir;
    private TextView tvTglKunjunganTerakhir, tvNKunjungan, tvPeriodeKunjungan;
    private String lastTglKunjungan,periodeKunjungan;
    private AlertDialog dialogPopup;
    private Dialog dialogImage;
    private ImageView ivPopup;
    private FloatingActionButton floatingButton;
    private Button btnRiwayatKunjungan, btnIvClose;
    private View popupSpinner;
    private Spinner spinnerKunjunganLalu;
    private List<DaftarKunjunganItem> daftarKunjunganItems;
    AlertDialog.Builder builder;
    AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage_jumantik);

        mContext = this;
        service = APIClient.createService(APIInterface.class);
        mBundle = getIntent().getExtras();

        floatingButton = (FloatingActionButton) findViewById(R.id.floatingbtn);
        btnKtpKader = (LinearLayout) findViewById(R.id.btnKtpKader);
        btnSuratTugas = (LinearLayout) findViewById(R.id.btnSuratTugas);
        tvPeriodeKunjungan = (TextView) findViewById(R.id.tvPeriodeKunjungan);
        tvTglKunjunganTerakhir = (TextView) findViewById(R.id.tvTglKunjunganTerakhir);
        tvNKunjungan = (TextView) findViewById(R.id.tvNKunjungan);

        builder = new AlertDialog.Builder(this);
        builder.setView(R.layout.progress);
        dialog = builder.create();

        wrapperKunjunganTerakhir = (LinearLayout) findViewById(R.id.wrapperKunjunganTerakhir);
        wrapperKunjunganTerakhir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mBundle.putString("tanggal_senin", lastTglKunjungan);
                Intent intent = new Intent(mContext, HomePageKunjungan.class);
                intent.putExtras(mBundle);
                mContext.startActivity(intent);
            }
        });

        // atur popup spinner //////////////////////////////
        btnRiwayatKunjungan = (Button) findViewById(R.id.btnRiwayatKunjungan);
        popupSpinner = getLayoutInflater().inflate(R.layout.dialog_spinner, null);
        spinnerKunjunganLalu = (Spinner) popupSpinner.findViewById(R.id.spinner);

        AlertDialog.Builder mBuilder = new AlertDialog.Builder(mContext);
        mBuilder.setTitle("Kunjungan lalu");

        mBuilder.setPositiveButton("Tampilkan", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                DaftarKunjunganItem selected = (DaftarKunjunganItem) spinnerKunjunganLalu.getSelectedItem();
                mBundle.putString("tanggal_senin", selected.getTanggal_senin());
                Intent intent = new Intent(mContext, HomePageKunjungan.class);
                intent.putExtras(mBundle);
                mContext.startActivity(intent);
                finish();
            }
        });

        mBuilder.setNegativeButton("Kembali", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        mBuilder.setView(popupSpinner);
        dialogPopup = mBuilder.create();

        btnRiwayatKunjungan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialogPopup.show();
            }
        });
        // atur popup spinner //////////////////////////////

        floatingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateDialog();

            }
        });

        dialogImage = new Dialog(mContext, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        dialogImage.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogImage.setCancelable(false);
        dialogImage.setContentView(R.layout.popup_image);
        btnIvClose = (Button) dialogImage.findViewById(R.id.btnIvClose);
        ivPopup = (ImageView) dialogImage.findViewById(R.id.ivPopup);

        btnIvClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                dialogImage.dismiss();
            }
        });

        btnKtpKader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Picasso.with(getApplicationContext()).load("http://skm.duckdns.org:3331/upload/photo/ktp.jpg").into(ivPopup);
                dialogImage.show();
            }
        });
        btnSuratTugas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Picasso.with(getApplicationContext()).load("http://skm.duckdns.org:3331/upload/photo/surattugas.jpg").into(ivPopup);
                dialogImage.show();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        LoadDaftarTanggal();
        LoadKunjunganPengguna(mBundle.getInt("id_pengguna"));
    }

    private void dialContactPhone(final String phoneNumber){
        startActivity(new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel",phoneNumber,null)));
    }

    private void showDateDialog(){
        /**
         * Calendar untuk mendapatkan tanggal sekarang
         */
        Calendar newCalendar = Calendar.getInstance();

        /**
         * Initiate DatePicker dialog
         */
        datePickerDialog = new DatePickerDialog(mContext, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                /**
                 * Set Calendar untuk menampung tanggal yang dipilih
                 */
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);

                String format = "yyyy-MM-dd";
                SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.US);
                mBundle.putString("tanggal_senin", sdf.format(newDate.getTime()));
                Intent intent = new Intent(mContext, HomePageKunjungan.class);
                intent.putExtras(mBundle);
                startActivity(intent);
                finish();

                }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        /**
         * Tampilkan DatePicker dialog
         */
        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
        datePickerDialog.show();
    }

    private void LoadDaftarTanggal() {
        dialog.show();
        Call<ResponseDaftarKunjungan> call = service.getDaftarPeriode();
        call.enqueue(new Callback<ResponseDaftarKunjungan>() {
            @Override
            public void onResponse(Call<ResponseDaftarKunjungan> call, Response<ResponseDaftarKunjungan> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        daftarKunjunganItems = response.body().getDaftarPeriode();

                        // cek apakah ada daftar kunjungan
                        if (daftarKunjunganItems.size() > 0) {
                            // set info kunjungan terakhir
                            lastTglKunjungan = daftarKunjunganItems.get(0).getTanggal_senin();

                            tvTglKunjunganTerakhir.setText(daftarKunjunganItems.get(0).getFormattedTanggal());

                            // set isian spinner
                            ArrayAdapter<DaftarKunjunganItem> arrayAdapter = new ArrayAdapter<DaftarKunjunganItem>(mContext, android.R.layout.simple_spinner_item, daftarKunjunganItems);
                            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            spinnerKunjunganLalu.setAdapter(arrayAdapter);

                            // set clickable
                            wrapperKunjunganTerakhir.setClickable(true);
                            btnRiwayatKunjungan.setEnabled(true);
                        } else {
                            tvTglKunjunganTerakhir.setText("-");
                            wrapperKunjunganTerakhir.setClickable(false);
                            btnRiwayatKunjungan.setEnabled(false);
                        }
                    }
                } else {
                    Toast.makeText(mContext, "Gagal mengambil Daftar Tanggal", Toast.LENGTH_LONG).show();
                }
                dialog.dismiss();
            }

            @Override
            public void onFailure(Call<ResponseDaftarKunjungan> call, Throwable t) {
                Log.d("Error", t.getMessage());
            }
        });
    }

    private void LoadKunjunganPengguna(int idPengguna) {
        dialog.show();
        Call<ResponseKunjunganPengguna> call = service.getKunjunganPengguna(idPengguna);
        call.enqueue(new Callback<ResponseKunjunganPengguna>() {
            @Override
            public void onResponse(Call<ResponseKunjunganPengguna> call, Response<ResponseKunjunganPengguna> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        KunjunganPengguna summ = response.body().getData();
                        tvNKunjungan.setText(""+summ.getJumlahKunjungan());
                        tvPeriodeKunjungan.setText("Periode " + convertDateFormat(summ.getTanggalSenin()) + " - " + convertDateFormat(summ.getTanggalMinggu()));
                    }
                } else {
                    Toast.makeText(mContext, "Gagal mengambil Jumlah Kunjungan Pengguna", Toast.LENGTH_LONG).show();
                }
                dialog.dismiss();
            }

            @Override
            public void onFailure(Call<ResponseKunjunganPengguna> call, Throwable t) {
                Log.d("Error", t.getMessage());
            }
        });
    }

    private String convertDateFormat(String inputDateString) {
        Date date = null;
        String outputDateString = null;

        SimpleDateFormat ymdFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        SimpleDateFormat dmyFormat = new SimpleDateFormat("dd MMM yyyy", Locale.getDefault());

        try {
            date = ymdFormat.parse(inputDateString);
            outputDateString = dmyFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return outputDateString;
    }
}