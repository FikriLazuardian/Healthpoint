package com.healthpoint.medic;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.healthpoint.medic.adapter.AdapterDaftarRumah;
import com.healthpoint.medic.model.DaftarRt;
import com.healthpoint.medic.model.DaftarRumah;
import com.healthpoint.medic.model.DaftarRumahItem;
import com.healthpoint.medic.model.DaftarRumahKoor;
import com.healthpoint.medic.model.DataKader;
import com.healthpoint.medic.model.DataKoor;
import com.healthpoint.medic.model.DataPengguna;
import com.healthpoint.medic.model.ListJenisRw;
import com.healthpoint.medic.model.RekapPerjeni;
import com.healthpoint.medic.model.RekapPerjenisrumah;
import com.healthpoint.medic.model.ResponseKelurahan;
import com.healthpoint.medic.model.ResponseLapKader;
import com.healthpoint.medic.model.ResponseLapKoor;
import com.healthpoint.medic.model.ResponseRingkasanKunjungan;
import com.healthpoint.medic.model.ResponseDaftarRumah;
import com.healthpoint.medic.model.RingkasanKunjungan;
import com.healthpoint.medic.network.APIClient;
import com.healthpoint.medic.network.APIInterface;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.VerticalPositionMark;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomePageKunjungan extends AppCompatActivity {
    private AdapterDaftarRumah adapterDaftarRumah;
    private RecyclerView recyclerView;
    private FloatingActionButton floatingBtn;
    private Button btnSort, btnFilter, btnReset,btnLaporan;
    private Context mContext;
    private RecyclerView.LayoutManager layoutManager;
    private APIInterface service;
    private TextView tvTanggal, tvRumahDiperiksa, tvNWadah, tvNJentik, tvNoJentik, tvPersentasi;
    private Bundle mBundle;
    private String tglKunjungan;
    int status;
    private BottomSheetDialog bottomSheetDialogSort, bottomSheetDialogFilter;
    private LinearLayout ascendingPemilik, descendingPemilik, ascendingAlamat, descendingAlamat, adaKunjungan, tidakAdaKunjungan;
    private static final String TAG = "GeneratePDF";
    final private int REQUEST_CODE_ASK_PERMISSIONS = 111;
    private File myFile;
    List<DaftarRumahItem> mylist;
    List<DaftarRumah> listRmhLaporan;
    List<ListJenisRw>listRmhKoor;
    List<DaftarRt> listLaporanKoor;
    List<RekapPerjenisrumah> listDataPerjenisrumah;
    RingkasanKunjungan ringkasanKunjungan;
    AlertDialog.Builder builder;
    AlertDialog dialog;
    DataPengguna dataPengguna;
    DataKader datakader;
    DataKoor dataKoor;
    Integer rt;



    Font font,font1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page_kunjungan);

        mContext = this;
        service = APIClient.createService(APIInterface.class);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerRumah);
        tvTanggal = (TextView) findViewById(R.id.tvTanggalKunjungan);
        tvRumahDiperiksa = (TextView) findViewById(R.id.tvRumahDiperiksa);
        tvNWadah = (TextView) findViewById(R.id.tvNWadah);
        tvNJentik = (TextView) findViewById(R.id.tvNJentik);
        tvNoJentik = (TextView) findViewById(R.id.tvNoJentik);
        tvPersentasi = (TextView) findViewById(R.id.tvPersentasi);

        btnSort = (Button) findViewById(R.id.btnSort);
        btnFilter = (Button) findViewById(R.id.btnFilter);
        btnLaporan = (Button) findViewById(R.id.btnlaporan);
        btnReset = (Button) findViewById(R.id.btnReset);
        mBundle = getIntent().getExtras();
        tglKunjungan = mBundle.getString("tanggal_senin");
        tvTanggal.setText(convertDateFormat(tglKunjungan));

        builder = new AlertDialog.Builder(this);
        builder.setView(R.layout.progress);
        dialog = builder.create();


        List<DaftarRumahItem> daftarRumahKosong = new ArrayList<>();
        adapterDaftarRumah = new AdapterDaftarRumah(this, daftarRumahKosong, mBundle);
        adapterDaftarRumah.notifyDataSetChanged();
        floatingBtn = (FloatingActionButton) findViewById(R.id.floatingbtn);
        floatingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent maps = new Intent(mContext,MapsActivity.class);
                maps.putExtras(mBundle);
                startActivity(maps);
            }
        });

        btnSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetDialogSort.show();
            }
        });
        btnFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetDialogFilter.show();
            }
        });
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapterDaftarRumah.reset();
            }
        });
        btnLaporan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();
                try {
                    createPdfWrapper();
                    if (status==3) {
                        //createPdfKoor();
                        createPdfKader();
                    }else if (status==7) {
                        createPdfKoor();
                    //    createPdfKader();
                    }else{
                        Toast.makeText(mContext,"Data Belum Ada",Toast.LENGTH_LONG).show();
                    }
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            dialog.dismiss();
                        }
                    },5000);

                }catch (DocumentException e){
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
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

    @Override
    protected void onResume() {
        super.onResume();

        LoadRingkasan(tglKunjungan, mBundle.getInt("id_pengguna"));
        LoadDaftarRumahKunjungan(tglKunjungan, mBundle.getInt("id_pengguna"));
        LoadProfile(mBundle.getInt("id_pengguna"));
        LoadLaporanKader(tglKunjungan,mBundle.getInt("id_pengguna"));
        LoadLaporanKoor(tglKunjungan,mBundle.getInt("id_pengguna"));
        // Menggunakan Layout Manager, Dan Membuat List Secara Vertical
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(layoutManager);
        List<DaftarRumahItem> daftarKosong = new ArrayList<>();
        adapterDaftarRumah = new AdapterDaftarRumah(this, daftarKosong, mBundle);
        recyclerView.setAdapter(adapterDaftarRumah);
    }

    private void LoadRingkasan(String tglKunjungan, int idPengguna) {
       dialog.show();
        Call<ResponseRingkasanKunjungan> call = service.getRingkasanKunjungan(tglKunjungan, idPengguna);
        call.enqueue(new Callback<ResponseRingkasanKunjungan>() {
            @Override
            public void onResponse(Call<ResponseRingkasanKunjungan> call, Response<ResponseRingkasanKunjungan> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        String msg = response.body().getMessage();
                        if (!msg.equals("Tidak ada data kunjungan")) {
                            ringkasanKunjungan = (RingkasanKunjungan) response.body().getData();
                            tvRumahDiperiksa.setText(ringkasanKunjungan.getRumahDiperiksa());
                            tvNWadah.setText(ringkasanKunjungan.getJumlahWadah());
                            tvNJentik.setText(ringkasanKunjungan.getJumlahJentik());
                            tvNoJentik.setText(ringkasanKunjungan.getJumlahNoJentik());
                            tvPersentasi.setText(ringkasanKunjungan.getPersenBebasJentik() + " %");
                        }
                    }
                } else {
                    Toast.makeText(mContext, "Ringkasan Kunjungan belum ada", Toast.LENGTH_LONG).show();
                }
                dialog.dismiss();
            }

            @Override
            public void onFailure(Call<ResponseRingkasanKunjungan> call, Throwable t) {

            }
        });
    }

    private void LoadDaftarRumahKunjungan(String tglKunjungan, int idPengguna) {
        dialog.show();
        Call<ResponseDaftarRumah> call = service.getDaftarRumahKunjungan(tglKunjungan, idPengguna);
        call.enqueue(new Callback<ResponseDaftarRumah>() {
            @Override
            public void onResponse(Call<ResponseDaftarRumah> call, Response<ResponseDaftarRumah> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        mylist = response.body().getDaftarRumah();
                        adapterDaftarRumah = new AdapterDaftarRumah(mContext, response.body().getDaftarRumah(), mBundle);
                        recyclerView.setAdapter(adapterDaftarRumah);
                    }
                } else {
                    Toast.makeText(mContext, "Gagal mengambil Daftar Rumah", Toast.LENGTH_LONG).show();
                }
                dialog.dismiss();
            }

            @Override
            public void onFailure(Call<ResponseDaftarRumah> call, Throwable t) {
//                Log.d("Error", t.getMessage());
            }
        });
        createBottomSheetDialogSort();
        createBottomSheetDialogFilter();

    }

    private void LoadLaporanKader(String tglKunjungan,int idPengguna){
        Call<ResponseLapKader> call = service.getKunjunganLapKader(tglKunjungan,idPengguna);
        call.enqueue(new Callback<ResponseLapKader>() {
            @Override
            public void onResponse(Call<ResponseLapKader> call, Response<ResponseLapKader> response) {
                if (response.isSuccessful()){
                    if (response.body() != null){
                        String msg = response.body().getMessage();
                        if (!msg.equals("Tidak ada data")){
                            datakader = response.body().getDataKader();
                            listRmhLaporan = response.body().getDataKader().getDaftarRumah();
                            listDataPerjenisrumah = response.body().getDataKader().getRekapPerjenisrumah();
                        }

                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseLapKader> call, Throwable t) {

            }
        });
    }

    private void LoadLaporanKoor(String tglKunjungan,int idPengguna){
        Call<ResponseLapKoor> call = service.getKunjunganLapKoor(tglKunjungan,idPengguna);
        call.enqueue(new Callback<ResponseLapKoor>() {
            @Override
            public void onResponse(Call<ResponseLapKoor> call, Response<ResponseLapKoor> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null){
                        String msg = response.body().getMessage();
                        if (!msg.equals("Tidak ada data")){
                           dataKoor = response.body().getDataKoor();
                           listLaporanKoor = response.body().getDataKoor().getDaftarRt();
                           listRmhKoor= response.body().getDataKoor().getListJenisRw();
                           if (status==7){
                               tvRumahDiperiksa.setText(String.valueOf(dataKoor.getRumahDiperiksa()));
                               tvNWadah.setText(String.valueOf(dataKoor.getJumlahWadah()));
                               tvNJentik.setText(String.valueOf(dataKoor.getJumlahJentik()));
                               tvNoJentik.setText(String.valueOf(dataKoor.getNTanpaJentik()));
                               tvPersentasi.setText(dataKoor.getPersentaseBebasJentik()+ " %");
                           }

                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseLapKoor> call, Throwable t) {

            }
        });
    }

    private void LoadProfile(int idPengguna){
        Call<DataPengguna> call = service.getPengguna(idPengguna);
        call.enqueue(new Callback<DataPengguna>() {
            @Override
            public void onResponse(Call<DataPengguna> call, Response<DataPengguna> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        dataPengguna = response.body();
                        status = dataPengguna.getStatus();

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

    private void createBottomSheetDialogSort() {
        if (bottomSheetDialogSort == null) {
            View view = LayoutInflater.from(this).inflate(R.layout.bottom_sheet, null);
            ascendingPemilik = view.findViewById(R.id.ascendingPemilik);
            descendingPemilik = view.findViewById(R.id.descendingPemilik);
            ascendingAlamat = view.findViewById(R.id.ascendingAlamat);
            descendingAlamat = view.findViewById(R.id.descendingAlamat);

            ascendingPemilik.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    adapterDaftarRumah.sortPemilikAsc();
                    bottomSheetDialogSort.dismiss();
                }
            });
            descendingPemilik.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    adapterDaftarRumah.sortPemilikDesc();
                    bottomSheetDialogSort.dismiss();
                }
            });
            ascendingAlamat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    adapterDaftarRumah.sortAlamatAsc();
                    bottomSheetDialogSort.dismiss();
                }
            });
            descendingAlamat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    adapterDaftarRumah.sortAlamatDesc();
                    bottomSheetDialogSort.dismiss();
                }
            });

            bottomSheetDialogSort = new BottomSheetDialog(this);
            bottomSheetDialogSort.setContentView(view);
        }
    }
    private void createBottomSheetDialogFilter(){
        if (bottomSheetDialogFilter == null) {
            View view = LayoutInflater.from(this).inflate(R.layout.bottom_sheet_filter, null);
            adaKunjungan = view.findViewById(R.id.adaKunjungan);
            tidakAdaKunjungan = view.findViewById(R.id.tdkAdaKunjungan);
            //rt03 = view.findViewById(R.id.rt03);
            //rt05 = view.findViewById(R.id.rt05);

            adaKunjungan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                adapterDaftarRumah.filterAdaKunjungan();
                bottomSheetDialogFilter.dismiss();
              }
            });
             tidakAdaKunjungan.setOnClickListener(new View.OnClickListener() {
                @Override
               public void onClick(View view) {
                adapterDaftarRumah.filterTidakAdaKunjungan();
                bottomSheetDialogFilter.dismiss();
             }
             });
            //rt03.setOnClickListener(new View.OnClickListener() {
             //   @Override
               // public void onClick(View view) {
            //        adapterDaftarRumah.filterRt03();
                 //   bottomSheetDialogFilter.dismiss();
             //   }
           // });
           // rt05.setOnClickListener(new View.OnClickListener() {
             //   @Override
             //   public void onClick(View view) {
             //       adapterDaftarRumah.filterRt05();
             //       bottomSheetDialogFilter.dismiss();
            //    }
           // });
            bottomSheetDialogFilter = new BottomSheetDialog(this);
            bottomSheetDialogFilter.setContentView(view);
        }
    }
    private void createPdfWrapper() throws IOException,DocumentException{
        int hasWriteStoragePermission = ActivityCompat.checkSelfPermission(mContext, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (hasWriteStoragePermission != PackageManager.PERMISSION_GRANTED) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (!shouldShowRequestPermissionRationale(Manifest.permission.WRITE_CONTACTS)) {
                    showMessageOKCancel("You need to allow access to Storage",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                        requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                                REQUEST_CODE_ASK_PERMISSIONS);
                                    }
                                }
                            });
                    return;
                }
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        REQUEST_CODE_ASK_PERMISSIONS);
            }
            return;
        }
    }

    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener){
        new AlertDialog.Builder(mContext)
                .setMessage(message)
                .setPositiveButton("OK",okListener)
                .setNegativeButton("Cancel",null)
                .create()
                .show();
    }
    public void createPdfKader()throws IOException, DocumentException {

        String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Documents";
        File dir = new File(path);
        if (!dir.exists()){
            dir.mkdir();
            Log.i(TAG,"Pdf Directory created");
        }

        String username = mBundle.getString("username");
        String pdfname = "Laporan_"+ username+"_"+ convertDateFormat(tglKunjungan) +".pdf";
        myFile = new File(dir,pdfname);
        OutputStream output = new FileOutputStream(myFile);

        Document document = new Document(PageSize.A4.rotate());
        float[] pointColumnWidth = {1,4,4,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2};
        PdfPTable table = new PdfPTable(pointColumnWidth);

        table.getDefaultCell().setFixedHeight(30);
        table.setTotalWidth(PageSize.A4.getWidth());
        table.setWidthPercentage(100);



        font = new Font(Font.FontFamily.TIMES_ROMAN,12,Font.BOLD,BaseColor.BLACK);
        font1 = new Font(Font.FontFamily.TIMES_ROMAN,11, Font.NORMAL, BaseColor.BLACK);
        Chunk chunk = new Chunk("LAPORAN KEGIATAN PEMANTAUAN SARANG NYAMUK DI 7 (TUJUH) TATANAN",font);
        Paragraph preface = new Paragraph(chunk);
        preface.setAlignment(Paragraph.ALIGN_CENTER);

        Chunk chunk1 = new Chunk("TINGKAT KELURAHAN" +"KECAMATAN",font);
        Paragraph paragraph2 = new Paragraph(chunk1);
        paragraph2.setAlignment(Paragraph.ALIGN_CENTER);

        Chunk chunk2 = new Chunk("TAHUN 2019",font);
        Paragraph paragraph3 = new Paragraph(chunk2);
        paragraph3.setAlignment(Paragraph.ALIGN_CENTER);

        addEmptyLine(paragraph3,1);

        Chunk chunk3 = new Chunk("TANGGAL : " + convertDateFormat(tglKunjungan),font);
        Paragraph paragpraph4 = new Paragraph(chunk3);
        paragpraph4.setAlignment(Paragraph.ALIGN_LEFT);

        addEmptyLine(paragpraph4,1);

      // List<List<String>> dataset = getData();

        //create table
        table.addCell(createCell("No",1,3,PdfPCell.BOX));
        table.addCell(createCell("NAMA PEMILIK BANGUNAN",1,3,PdfPCell.BOX));
        table.addCell(createCell("ALAMAT RUMAH",1,3,PdfPCell.BOX));
        table.addCell(createCell("Tgl Kunjungan",1,3,PdfPCell.BOX));
        table.addCell(createCell("TATANAN DIPERIKSA",14,1,PdfPCell.BOX));
        table.addCell(createCell("RUMAH TANGGA",2,1,PdfPCell.BOX));
        table.addCell(createCell("PERKANTORAN",2,1,PdfPCell.BOX));
        table.addCell(createCell("SARANA PENDIDIKAN",2,1,PdfPCell.BOX));
        table.addCell(createCell("TTU",2,1,PdfPCell.BOX));
        table.addCell(createCell("TPM",2,1,PdfPCell.BOX));
        table.addCell(createCell("FAS.OLAHRAGA",2,1,PdfPCell.BOX));
        table.addCell(createCell("FAS.KESEHATAN",2,1,PdfPCell.BOX));
        table.addCell(createCell("DIPERIKSA",1,1,PdfPCell.BOX));
        table.addCell(createCell("JENTIK(+)",1,1,PdfPCell.BOX));
        table.addCell(createCell("DIPERIKSA",1,1,PdfPCell.BOX));
        table.addCell(createCell("JENTIK(+)",1,1,PdfPCell.BOX));
        table.addCell(createCell("DIPERIKSA",1,1,PdfPCell.BOX));
        table.addCell(createCell("JENTIK(+)",1,1,PdfPCell.BOX));
        table.addCell(createCell("DIPERIKSA",1,1,PdfPCell.BOX));
        table.addCell(createCell("JENTIK(+)",1,1,PdfPCell.BOX));
        table.addCell(createCell("DIPERIKSA",1,1,PdfPCell.BOX));
        table.addCell(createCell("JENTIK(+)",1,1,PdfPCell.BOX));
        table.addCell(createCell("DIPERIKSA",1,1,PdfPCell.BOX));
        table.addCell(createCell("JENTIK(+)",1,1,PdfPCell.BOX));
        table.addCell(createCell("DIPERIKSA",1,1,PdfPCell.BOX));
        table.addCell(createCell("JENTIK(+)",1,1,PdfPCell.BOX));
        for (int i = 0; i < listRmhLaporan.size(); i++) {
            DaftarRumah obj = listRmhLaporan.get(i);
            String nama = obj.getNamaPemilik();
            String alamatRumah = obj.getAlamat();
            int idJenisRumah = obj.getIdJenisRumah();
            int n_Wadah = obj.getNWadah();
            int n_WadahJentik = obj.getNWadahJentik();
            int no = i + 1;
                table.addCell(String.valueOf(no));
                table.addCell(String.valueOf(nama));
                table.addCell(String.valueOf(alamatRumah));
                table.addCell(String.valueOf(convertDateFormat(tglKunjungan)));
                if (idJenisRumah==1) {
                    table.addCell(String.valueOf(n_Wadah));
                    table.addCell(String.valueOf(n_WadahJentik));
                    table.addCell(" ");
                    table.addCell(" ");
                    table.addCell(" ");
                    table.addCell(" ");
                    table.addCell(" ");
                    table.addCell(" ");
                    table.addCell(" ");
                    table.addCell(" ");
                    table.addCell(" ");
                    table.addCell(" ");
                    table.addCell(" ");
                    table.addCell(" ");
                }
                else if (idJenisRumah==2){
                    table.addCell(" ");
                    table.addCell(" ");
                    table.addCell(String.valueOf(n_Wadah));
                    table.addCell(String.valueOf(n_WadahJentik));
                    table.addCell(" ");
                    table.addCell(" ");
                    table.addCell(" ");
                    table.addCell(" ");
                    table.addCell(" ");
                    table.addCell(" ");
                    table.addCell(" ");
                    table.addCell(" ");
                    table.addCell(" ");
                    table.addCell(" ");
                }
                else if (idJenisRumah==3){
                    table.addCell(" ");
                    table.addCell(" ");
                    table.addCell(" ");
                    table.addCell(" ");
                    table.addCell(String.valueOf(n_Wadah));
                    table.addCell(String.valueOf(n_WadahJentik));
                    table.addCell(" ");
                    table.addCell(" ");
                    table.addCell(" ");
                    table.addCell(" ");
                    table.addCell(" ");
                    table.addCell(" ");
                    table.addCell(" ");
                    table.addCell(" ");
                }
                else if (idJenisRumah==4){
                    table.addCell(" ");
                    table.addCell(" ");
                    table.addCell(" ");
                    table.addCell(" ");
                    table.addCell(" ");
                    table.addCell(" ");
                    table.addCell(String.valueOf(n_Wadah));
                    table.addCell(String.valueOf(n_WadahJentik));
                    table.addCell(" ");
                    table.addCell(" ");
                    table.addCell(" ");
                    table.addCell(" ");
                    table.addCell(" ");
                    table.addCell(" ");
                }
                else if (idJenisRumah==5){
                    table.addCell(" ");
                    table.addCell(" ");
                    table.addCell(" ");
                    table.addCell(" ");
                    table.addCell(" ");
                    table.addCell(" ");
                    table.addCell(" ");
                    table.addCell(" ");
                    table.addCell(String.valueOf(n_Wadah));
                    table.addCell(String.valueOf(n_WadahJentik));
                    table.addCell(" ");
                    table.addCell(" ");
                    table.addCell(" ");
                    table.addCell(" ");
                }
                else if (idJenisRumah==6){
                    table.addCell(" ");
                    table.addCell(" ");
                    table.addCell(" ");
                    table.addCell(" ");
                    table.addCell(" ");
                    table.addCell(" ");
                    table.addCell(" ");
                    table.addCell(" ");
                    table.addCell(" ");
                    table.addCell(" ");
                    table.addCell(String.valueOf(n_Wadah));
                    table.addCell(String.valueOf(n_WadahJentik));
                    table.addCell(" ");
                    table.addCell(" ");
                }
                else if (idJenisRumah==7){
                    table.addCell(" ");
                    table.addCell(" ");
                    table.addCell(" ");
                    table.addCell(" ");
                    table.addCell(" ");
                    table.addCell(" ");
                    table.addCell(" ");
                    table.addCell(" ");
                    table.addCell(" ");
                    table.addCell(" ");
                    table.addCell(" ");
                    table.addCell(" ");
                    table.addCell(String.valueOf(n_Wadah));
                    table.addCell(String.valueOf(n_WadahJentik));
                }
                else {
                    table.addCell(" ");
                    table.addCell(" ");
                    table.addCell(" ");
                    table.addCell(" ");
                    table.addCell(" ");
                    table.addCell(" ");
                    table.addCell(" ");
                    table.addCell(" ");
                    table.addCell(" ");
                    table.addCell(" ");
                    table.addCell(" ");
                    table.addCell(" ");
                    table.addCell(" ");
                    table.addCell(" ");
                }

                }



                Integer totalDiperiksa = datakader.getJumlahWadah();
                Integer totalAdaJentik = datakader.getJumlahJentik();
                Integer totalTdkJentik = datakader.getNTanpaJentik();
                Integer persentaseBebasJentik = datakader.getPersentaseBebasJentik();
                table.addCell(createCell("Jumlah", 4, 1, PdfPCell.BOX));
                for (int i=0;i<listDataPerjenisrumah.size();i++) {
                    RekapPerjenisrumah rekap = listDataPerjenisrumah.get(i);
                    int rekapWadah = rekap.getNWadah();
                    int rekapWadahJentik = rekap.getNWadahJentik();
                    int idJenisRumah = rekap.getIdJenisRumah();
                    if (idJenisRumah == 1) {
                        table.addCell(String.valueOf(rekapWadah));
                        table.addCell(String.valueOf(rekapWadahJentik));
                    } else if (idJenisRumah == 2) {
                        table.addCell(String.valueOf(rekapWadah));
                        table.addCell(String.valueOf(rekapWadahJentik));

                    } else if (idJenisRumah == 3) {
                        table.addCell(String.valueOf(rekapWadah));
                        table.addCell(String.valueOf(rekapWadahJentik));
                    } else if (idJenisRumah == 4) {
                        table.addCell(String.valueOf(rekapWadah));
                        table.addCell(String.valueOf(rekapWadahJentik));
                    } else if (idJenisRumah == 5) {
                        table.addCell(String.valueOf(rekapWadah));
                        table.addCell(String.valueOf(rekapWadahJentik));
                    }
                    else if (idJenisRumah == 6) {
                        table.addCell(String.valueOf(rekapWadah));
                        table.addCell(String.valueOf(rekapWadahJentik));
                    }
                    else if (idJenisRumah == 7) {
                        table.addCell(String.valueOf(rekapWadah));
                        table.addCell(String.valueOf(rekapWadahJentik));
                    }
                }

        Chunk chunk7 = new Chunk("ABJ(%):" + persentaseBebasJentik,font);
        //Paragraph paragraph8 = new Paragraph(chunk7);
        //paragraph8.setAlignment(Paragraph.ALIGN_BASELINE);

        Chunk chunk4 = new Chunk("Jumlah Total Diperiksa:" + totalDiperiksa,font);
        Paragraph paragraph5 = new Paragraph(chunk4);
        paragraph5.add(new Chunk(" "));
        paragraph5.add(new Chunk(" "));
        paragraph5.add(new Chunk(" "));
        paragraph5.add(new Chunk(" "));
        paragraph5.add(new Chunk(" "));
        paragraph5.add(chunk7);
        paragraph5.setAlignment(Paragraph.ALIGN_LEFT);

        Chunk chunk6 = new Chunk("Jumlah Total Jentik(-):" + totalTdkJentik,font);
        //Paragraph paragpraph7 = new Paragraph(chunk6);
        //paragpraph7.setAlignment(Paragraph.ALIGN_LEFT);
        Chunk chunk5 = new Chunk("Jumlah Total Jentik(+):" + totalAdaJentik,font);
        Paragraph paragraph6 = new Paragraph(chunk5);
        paragraph6.add(new Chunk(" "));
        paragraph6.add(new Chunk(" "));
        paragraph6.add(new Chunk(" "));
        paragraph6.add(new Chunk(" "));
        paragraph6.add(new Chunk(" "));
        paragraph6.add(chunk6);
        paragraph6.setAlignment(Paragraph.ALIGN_LEFT);

        Chunk glue = new Chunk(new VerticalPositionMark());
        Paragraph paragraph9 = new Paragraph("Mengetahui:",font);
        paragraph9.add(new Chunk(glue));
        paragraph9.add("Jakarta, " + convertDateFormat(tglKunjungan));

        Chunk glue2 = new Chunk(new VerticalPositionMark());
        Paragraph paragraph10 = new Paragraph("Ketua RT",font);
        paragraph10.add(new Chunk(glue2));
        paragraph10.add("Surveyor");

        Chunk chunk8 = new Chunk("Kelurahan Bungur",font);
        Paragraph paragraph11 = new Paragraph(chunk8);
        paragraph11.setAlignment(Paragraph.ALIGN_LEFT);

       String user = mBundle.getString("username");
        Chunk chunk9 = new Chunk(user,font);
        Paragraph paragraph12 = new Paragraph(chunk9);
        paragraph12.setAlignment(Paragraph.ALIGN_RIGHT);

        PdfWriter.getInstance(document,output);
        document.open();

        document.add(preface);
        document.add(paragraph2);
        document.add(paragraph3);
        document.add(paragpraph4);
        document.add(table);
        document.add(paragraph5);
        document.add(paragraph6);
        document.add(paragraph9);
        document.add(paragraph10);
        document.add(paragraph11);
        document.add(paragraph12);
        document.close();
        Toast.makeText(this,"Pdf Disimpan di folder Documents",Toast.LENGTH_SHORT).show();
        viewPdf();

    }
    private static void addEmptyLine(Paragraph paragraph,int number){
        for (int i = 0; i<number;i++){
            paragraph.add(new Paragraph(" "));
        }
    }

    public void createPdfKoor()throws IOException, DocumentException {

        String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Documents";
        File dir = new File(path);
        if (!dir.exists()) {
            dir.mkdir();
            Log.i(TAG, "Pdf Directory created");
        }

        String username = mBundle.getString("username");
        String pdfname = "Laporan_" + username + "_" + convertDateFormat(tglKunjungan) + ".pdf";
        myFile = new File(dir, pdfname);
        OutputStream output = new FileOutputStream(myFile);

        Document document = new Document(PageSize.A4.rotate());
        float[] pointColumnWidth = {1,4,2,2,2,2,2,2,2,2,2,2,2,2,2,2};
        PdfPTable table = new PdfPTable(pointColumnWidth);

        table.getDefaultCell().setFixedHeight(30);
        table.setTotalWidth(PageSize.A4.getWidth());
        table.setWidthPercentage(100);


        font = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD, BaseColor.BLACK);
        font1 = new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL, BaseColor.BLACK);
        Chunk chunk = new Chunk("LAPORAN KEGIATAN PEMANTAUAN SARANG NYAMUK DI 7 (TUJUH) TATANAN", font);
        Paragraph preface = new Paragraph(chunk);
        preface.setAlignment(Paragraph.ALIGN_CENTER);

        Chunk chunk1 = new Chunk("TINGKAT KELURAHAN"+ "KECAMATAN", font);
        Paragraph paragraph2 = new Paragraph(chunk1);
        paragraph2.setAlignment(Paragraph.ALIGN_CENTER);

        Chunk chunk2 = new Chunk("TAHUN 2019", font);
        Paragraph paragraph3 = new Paragraph(chunk2);
        paragraph3.setAlignment(Paragraph.ALIGN_CENTER);

        addEmptyLine(paragraph3, 1);

        Chunk chunk3 = new Chunk("Periode : " + convertDateFormat(dataKoor.getTglMinggu()) + " - " +convertDateFormat(dataKoor.getTglSenin()), font);
        Paragraph paragpraph4 = new Paragraph(chunk3);
        paragpraph4.setAlignment(Paragraph.ALIGN_LEFT);

        Chunk chunk8 = new Chunk("RW : " +  " 8 " , font);
        Paragraph paragpraph12 = new Paragraph(chunk8);
        paragpraph12.setAlignment(Paragraph.ALIGN_LEFT);
        // List<List<String>> dataset = getData();
        //create table
        table.addCell(createCell("No", 1, 3, PdfPCell.BOX));
        table.addCell(createCell("RT", 1, 3, PdfPCell.BOX));
        table.addCell(createCell("TATANAN DIPERIKSA", 14, 1, PdfPCell.BOX));
        table.addCell(createCell("RUMAH TANGGA", 2, 1, PdfPCell.BOX));
        table.addCell(createCell("PERKANTORAN", 2, 1, PdfPCell.BOX));
        table.addCell(createCell("SARANA PENDIDIKAN", 2, 1, PdfPCell.BOX));
        table.addCell(createCell("TTU", 2, 1, PdfPCell.BOX));
        table.addCell(createCell("TPM", 2, 1, PdfPCell.BOX));
        table.addCell(createCell("FAS.OLAHRAGA", 2, 1, PdfPCell.BOX));
        table.addCell(createCell("FAS.KESEHATAN", 2, 1, PdfPCell.BOX));
        table.addCell(createCell("DIPERIKSA", 1, 1, PdfPCell.BOX));
        table.addCell(createCell("JENTIK(+)", 1, 1, PdfPCell.BOX));
        table.addCell(createCell("DIPERIKSA", 1, 1, PdfPCell.BOX));
        table.addCell(createCell("JENTIK(+)", 1, 1, PdfPCell.BOX));
        table.addCell(createCell("DIPERIKSA", 1, 1, PdfPCell.BOX));
        table.addCell(createCell("JENTIK(+)", 1, 1, PdfPCell.BOX));
        table.addCell(createCell("DIPERIKSA", 1, 1, PdfPCell.BOX));
        table.addCell(createCell("JENTIK(+)", 1, 1, PdfPCell.BOX));
        table.addCell(createCell("DIPERIKSA", 1, 1, PdfPCell.BOX));
        table.addCell(createCell("JENTIK(+)", 1, 1, PdfPCell.BOX));
        table.addCell(createCell("DIPERIKSA", 1, 1, PdfPCell.BOX));
        table.addCell(createCell("JENTIK(+)", 1, 1, PdfPCell.BOX));
        table.addCell(createCell("DIPERIKSA", 1, 1, PdfPCell.BOX));
        table.addCell(createCell("JENTIK(+)", 1, 1, PdfPCell.BOX));
        for (int i=0;i<listLaporanKoor.size();i++) {
            int no = i + 1;
            DaftarRt obj = listLaporanKoor.get(i);
            int rt = obj.getRt();
            String n_Wadah = obj.getJumlahWadah();
            String n_WadahJentik = obj.getJumlahJentik();
            List<DaftarRumahKoor> jenisRumah = obj.getDaftarRumah();
            DaftarRumahKoor obx = jenisRumah.get(i);
            int idJenisRumah = obx.getIdJenisRumah();
            table.addCell(String.valueOf(no));
            table.addCell(String.valueOf(rt));
            if (idJenisRumah == 1) {
                table.addCell(String.valueOf(n_Wadah));
                table.addCell(String.valueOf(n_WadahJentik));
                table.addCell(" ");
                table.addCell(" ");
                table.addCell(" ");
                table.addCell(" ");
                table.addCell(" ");
                table.addCell(" ");
                table.addCell(" ");
                table.addCell(" ");
                table.addCell(" ");
                table.addCell(" ");
                table.addCell(" ");
                table.addCell(" ");
            } else if (idJenisRumah == 2) {
                table.addCell(" ");
                table.addCell(" ");
                table.addCell(String.valueOf(n_Wadah));
                table.addCell(String.valueOf(n_WadahJentik));
                table.addCell(" ");
                table.addCell(" ");
                table.addCell(" ");
                table.addCell(" ");
                table.addCell(" ");
                table.addCell(" ");
                table.addCell(" ");
                table.addCell(" ");
                table.addCell(" ");
                table.addCell(" ");
            } else if (idJenisRumah == 3) {
                table.addCell(" ");
                table.addCell(" ");
                table.addCell(" ");
                table.addCell(" ");
                table.addCell(String.valueOf(n_Wadah));
                table.addCell(String.valueOf(n_WadahJentik));
                table.addCell(" ");
                table.addCell(" ");
                table.addCell(" ");
                table.addCell(" ");
                table.addCell(" ");
                table.addCell(" ");
                table.addCell(" ");
                table.addCell(" ");
            } else if (idJenisRumah == 4) {
                table.addCell(" ");
                table.addCell(" ");
                table.addCell(" ");
                table.addCell(" ");
                table.addCell(" ");
                table.addCell(" ");
                table.addCell(String.valueOf(n_Wadah));
                table.addCell(String.valueOf(n_WadahJentik));
                table.addCell(" ");
                table.addCell(" ");
                table.addCell(" ");
                table.addCell(" ");
                table.addCell(" ");
                table.addCell(" ");
            } else if (idJenisRumah == 5) {
                table.addCell(" ");
                table.addCell(" ");
                table.addCell(" ");
                table.addCell(" ");
                table.addCell(" ");
                table.addCell(" ");
                table.addCell(" ");
                table.addCell(" ");
                table.addCell(String.valueOf(n_Wadah));
                table.addCell(String.valueOf(n_WadahJentik));
                table.addCell(" ");
                table.addCell(" ");
                table.addCell(" ");
                table.addCell(" ");
            } else if (idJenisRumah == 6) {
                table.addCell(" ");
                table.addCell(" ");
                table.addCell(" ");
                table.addCell(" ");
                table.addCell(" ");
                table.addCell(" ");
                table.addCell(" ");
                table.addCell(" ");
                table.addCell(" ");
                table.addCell(" ");
                table.addCell(String.valueOf(n_Wadah));
                table.addCell(String.valueOf(n_WadahJentik));
                table.addCell(" ");
                table.addCell(" ");
            } else if (idJenisRumah == 7) {
                table.addCell(" ");
                table.addCell(" ");
                table.addCell(" ");
                table.addCell(" ");
                table.addCell(" ");
                table.addCell(" ");
                table.addCell(" ");
                table.addCell(" ");
                table.addCell(" ");
                table.addCell(" ");
                table.addCell(" ");
                table.addCell(" ");
                table.addCell(String.valueOf(n_Wadah));
                table.addCell(String.valueOf(n_WadahJentik));
            } else {
                table.addCell(" ");
                table.addCell(" ");
                table.addCell(" ");
                table.addCell(" ");
                table.addCell(" ");
                table.addCell(" ");
                table.addCell(" ");
                table.addCell(" ");
                table.addCell(" ");
                table.addCell(" ");
                table.addCell(" ");
                table.addCell(" ");
                table.addCell(" ");
                table.addCell(" ");
            }
        }
            table.addCell(createCell("Jumlah", 2, 1, PdfPCell.BOX));

            for (int k = 0; k < listRmhKoor.size(); k++) {
                ListJenisRw rekap = listRmhKoor.get(k);
                int rekapWadah = rekap.getNWadah();
                int rekapWadahJentik = rekap.getNWadahJentik();
                int idJenisRumahRekap = rekap.getIdJenisRumah();
                if (idJenisRumahRekap == 1) {
                    table.addCell(String.valueOf(rekapWadah));
                    table.addCell(String.valueOf(rekapWadahJentik));
                } else if (idJenisRumahRekap == 2) {
                    table.addCell(String.valueOf(rekapWadah));
                    table.addCell(String.valueOf(rekapWadahJentik));
                } else if (idJenisRumahRekap == 3) {
                    table.addCell(String.valueOf(rekapWadah));
                    table.addCell(String.valueOf(rekapWadahJentik));
                } else if (idJenisRumahRekap == 4) {
                    table.addCell(String.valueOf(rekapWadah));
                    table.addCell(String.valueOf(rekapWadahJentik));
                } else if (idJenisRumahRekap == 5) {
                    table.addCell(String.valueOf(rekapWadah));
                    table.addCell(String.valueOf(rekapWadahJentik));
                } else if (idJenisRumahRekap == 6) {
                    table.addCell(String.valueOf(rekapWadah));
                    table.addCell(String.valueOf(rekapWadahJentik));
                } else if (idJenisRumahRekap == 7) {
                    table.addCell(String.valueOf(rekapWadah));
                    table.addCell(String.valueOf(rekapWadahJentik));
                }
            }

        Integer totalDiperiksa = dataKoor.getJumlahWadah();
        Integer totalAdaJentik = dataKoor.getJumlahJentik();
        Integer totalTdkJentik = dataKoor.getNTanpaJentik();
        Integer persentaseBebasJentik = dataKoor.getPersentaseBebasJentik();

            Chunk chunk7 = new Chunk("ABJ(%):" + persentaseBebasJentik, font);
            //Paragraph paragraph8 = new Paragraph(chunk7);
            //paragraph8.setAlignment(Paragraph.ALIGN_BASELINE);

            Chunk chunk4 = new Chunk("Jumlah Total Diperiksa:" + totalDiperiksa, font);
            Paragraph paragraph5 = new Paragraph(chunk4);
            paragraph5.add(new Chunk(" "));
            paragraph5.add(new Chunk(" "));
            paragraph5.add(new Chunk(" "));
            paragraph5.add(new Chunk(" "));
            paragraph5.add(new Chunk(" "));
            paragraph5.add(chunk7);
            paragraph5.setAlignment(Paragraph.ALIGN_LEFT);

            Chunk chunk6 = new Chunk("Jumlah Total Jentik(-):" + totalTdkJentik, font);
            //Paragraph paragpraph7 = new Paragraph(chunk6);
            //paragpraph7.setAlignment(Paragraph.ALIGN_LEFT);
            Chunk chunk5 = new Chunk("Jumlah Total Jentik(+):" + totalAdaJentik, font);
            Paragraph paragraph6 = new Paragraph(chunk5);
            paragraph6.add(new Chunk(" "));
            paragraph6.add(new Chunk(" "));
            paragraph6.add(new Chunk(" "));
            paragraph6.add(new Chunk(" "));
            paragraph6.add(new Chunk(" "));
            paragraph6.add(chunk6);
            paragraph6.setAlignment(Paragraph.ALIGN_LEFT);

            Chunk glue = new Chunk("Jakarta, " + convertDateFormat(tglKunjungan), font);
            Paragraph paragraph9 = new Paragraph(glue);
            paragraph9.setAlignment(Paragraph.ALIGN_RIGHT);

            Chunk glue2 = new Chunk("Koordinator Jumantik RW 8 ",font);
            Paragraph paragraph10 = new Paragraph(glue2);
            paragraph10.setAlignment(Paragraph.ALIGN_RIGHT);

            String user = mBundle.getString("username");
            Chunk chunk9 = new Chunk(user, font);
            Paragraph paragraph11 = new Paragraph(chunk9);
            paragraph11.setAlignment(Paragraph.ALIGN_RIGHT);


            PdfWriter.getInstance(document, output);
            document.open();

            document.add(preface);
            document.add(paragraph2);
            document.add(paragraph3);
            document.add(paragpraph4);
            document.add(paragpraph12);
            document.add(table);
            document.add(paragraph5);
            document.add(paragraph6);
            document.add(paragraph9);
            document.add(paragraph10);
            document.add(paragraph11);



            document.newPage();
            //table 2
            float[] pointColumnWidth2 = {1,4,4,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2};
            PdfPTable table2 = new PdfPTable(pointColumnWidth2);

            table2.getDefaultCell().setFixedHeight(30);
            table2.setTotalWidth(PageSize.A4.getWidth());
            table2.setWidthPercentage(100);

        Chunk chunk21 = new Chunk("LAPORAN KEGIATAN PEMANTAUAN SARANG NYAMUK DI 7 (TUJUH) TATANAN",font);
        Paragraph preface2 = new Paragraph(chunk21);
        preface2.setAlignment(Paragraph.ALIGN_CENTER);

        Chunk chunk22 = new Chunk("TINGKAT KELURAHAN" + "KECAMATAN",font);
        Paragraph paragraph22 = new Paragraph(chunk22);
        paragraph22.setAlignment(Paragraph.ALIGN_CENTER);

        Chunk chunk23 = new Chunk("TAHUN 2019",font);
        Paragraph paragraph23 = new Paragraph(chunk23);
        paragraph23.setAlignment(Paragraph.ALIGN_CENTER);

        addEmptyLine(paragraph23,1);

        Chunk chunk24 = new Chunk("PERIODE : " + convertDateFormat(dataKoor.getTglMinggu())+" - "+convertDateFormat(dataKoor.getTglSenin()),font);
        Paragraph paragpraph24 = new Paragraph(chunk24);
        paragpraph24.setAlignment(Paragraph.ALIGN_LEFT);

        Chunk chunk121 = new Chunk("RW : " +  " 8 " , font);
        Paragraph paragpraph31 = new Paragraph(chunk121);
        paragpraph31.setAlignment(Paragraph.ALIGN_LEFT);
        // List<List<String>> dataset = getData();

        // List<List<String>> dataset = getData();

        //create table
        table2.addCell(createCell("No",1,3,PdfPCell.BOX));
        table2.addCell(createCell("NAMA PEMILIK BANGUNAN",1,3,PdfPCell.BOX));
        table2.addCell(createCell("ALAMAT RUMAH",1,3,PdfPCell.BOX));
        table2.addCell(createCell("Tgl Kunjungan",1,3,PdfPCell.BOX));
        table2.addCell(createCell("TATANAN DIPERIKSA",14,1,PdfPCell.BOX));
        table2.addCell(createCell("RUMAH TANGGA",2,1,PdfPCell.BOX));
        table2.addCell(createCell("PERKANTORAN",2,1,PdfPCell.BOX));
        table2.addCell(createCell("SARANA PENDIDIKAN",2,1,PdfPCell.BOX));
        table2.addCell(createCell("TTU",2,1,PdfPCell.BOX));
        table2.addCell(createCell("TPM",2,1,PdfPCell.BOX));
        table2.addCell(createCell("FAS.OLAHRAGA",2,1,PdfPCell.BOX));
        table2.addCell(createCell("FAS.KESEHATAN",2,1,PdfPCell.BOX));
        table2.addCell(createCell("DIPERIKSA",1,1,PdfPCell.BOX));
        table2.addCell(createCell("JENTIK(+)",1,1,PdfPCell.BOX));
        table2.addCell(createCell("DIPERIKSA",1,1,PdfPCell.BOX));
        table2.addCell(createCell("JENTIK(+)",1,1,PdfPCell.BOX));
        table2.addCell(createCell("DIPERIKSA",1,1,PdfPCell.BOX));
        table2.addCell(createCell("JENTIK(+)",1,1,PdfPCell.BOX));
        table2.addCell(createCell("DIPERIKSA",1,1,PdfPCell.BOX));
        table2.addCell(createCell("JENTIK(+)",1,1,PdfPCell.BOX));
        table2.addCell(createCell("DIPERIKSA",1,1,PdfPCell.BOX));
        table2.addCell(createCell("JENTIK(+)",1,1,PdfPCell.BOX));
        table2.addCell(createCell("DIPERIKSA",1,1,PdfPCell.BOX));
        table2.addCell(createCell("JENTIK(+)",1,1,PdfPCell.BOX));
        table2.addCell(createCell("DIPERIKSA",1,1,PdfPCell.BOX));
        table2.addCell(createCell("JENTIK(+)",1,1,PdfPCell.BOX));
        for (int i=0;i<listLaporanKoor.size();i++) {
            DaftarRt obj2 = listLaporanKoor.get(i);
            Integer rt = obj2.getRt();
            if (rt == 1) {
                List<DaftarRumahKoor> obx = obj2.getDaftarRumah();
                for (int k = 0; k < obx.size(); k++) {
                    DaftarRumahKoor obx2 = obx.get(k);
                    int no = k+1;
                    String nama = obx2.getNamaPemilik();
                    String alamatRumah = obx2.getAlamat();
                    int idJenisRumah = obx2.getIdJenisRumah();
                    int n_Wadah = obx2.getNWadah();
                    int n_WadahJentik = obx2.getNWadahJentik();
                    table2.addCell(String.valueOf(no));
                    table2.addCell(String.valueOf(nama));
                    table2.addCell(String.valueOf(alamatRumah));
                    table2.addCell(String.valueOf(convertDateFormat(tglKunjungan)));
                    if (idJenisRumah == 1) {
                        table2.addCell(String.valueOf(n_Wadah));
                        table2.addCell(String.valueOf(n_WadahJentik));
                        table2.addCell(" ");
                        table2.addCell(" ");
                        table2.addCell(" ");
                        table2.addCell(" ");
                        table2.addCell(" ");
                        table2.addCell(" ");
                        table2.addCell(" ");
                        table2.addCell(" ");
                        table2.addCell(" ");
                        table2.addCell(" ");
                        table2.addCell(" ");
                        table2.addCell(" ");
                    } else if (idJenisRumah == 2) {
                        table2.addCell(" ");
                        table2.addCell(" ");
                        table2.addCell(String.valueOf(n_Wadah));
                        table2.addCell(String.valueOf(n_WadahJentik));
                        table2.addCell(" ");
                        table2.addCell(" ");
                        table2.addCell(" ");
                        table2.addCell(" ");
                        table2.addCell(" ");
                        table2.addCell(" ");
                        table2.addCell(" ");
                        table2.addCell(" ");
                        table2.addCell(" ");
                        table2.addCell(" ");
                    } else if (idJenisRumah == 3) {
                        table2.addCell(" ");
                        table2.addCell(" ");
                        table2.addCell(" ");
                        table2.addCell(" ");
                        table2.addCell(String.valueOf(n_Wadah));
                        table2.addCell(String.valueOf(n_WadahJentik));
                        table2.addCell(" ");
                        table2.addCell(" ");
                        table2.addCell(" ");
                        table2.addCell(" ");
                        table2.addCell(" ");
                        table2.addCell(" ");
                        table2.addCell(" ");
                        table2.addCell(" ");
                    } else if (idJenisRumah == 4) {
                        table2.addCell(" ");
                        table2.addCell(" ");
                        table2.addCell(" ");
                        table2.addCell(" ");
                        table2.addCell(" ");
                        table2.addCell(" ");
                        table2.addCell(String.valueOf(n_Wadah));
                        table2.addCell(String.valueOf(n_WadahJentik));
                        table2.addCell(" ");
                        table2.addCell(" ");
                        table2.addCell(" ");
                        table2.addCell(" ");
                        table2.addCell(" ");
                        table2.addCell(" ");
                    } else if (idJenisRumah == 5) {
                        table2.addCell(" ");
                        table2.addCell(" ");
                        table2.addCell(" ");
                        table2.addCell(" ");
                        table2.addCell(" ");
                        table2.addCell(" ");
                        table2.addCell(" ");
                        table2.addCell(" ");
                        table2.addCell(String.valueOf(n_Wadah));
                        table2.addCell(String.valueOf(n_WadahJentik));
                        table2.addCell(" ");
                        table2.addCell(" ");
                        table2.addCell(" ");
                        table2.addCell(" ");
                    } else if (idJenisRumah == 6) {
                        table2.addCell(" ");
                        table2.addCell(" ");
                        table2.addCell(" ");
                        table2.addCell(" ");
                        table2.addCell(" ");
                        table2.addCell(" ");
                        table2.addCell(" ");
                        table2.addCell(" ");
                        table2.addCell(" ");
                        table2.addCell(" ");
                        table2.addCell(String.valueOf(n_Wadah));
                        table2.addCell(String.valueOf(n_WadahJentik));
                        table2.addCell(" ");
                        table2.addCell(" ");
                    } else if (idJenisRumah == 7) {
                        table2.addCell(" ");
                        table2.addCell(" ");
                        table2.addCell(" ");
                        table2.addCell(" ");
                        table2.addCell(" ");
                        table2.addCell(" ");
                        table2.addCell(" ");
                        table2.addCell(" ");
                        table2.addCell(" ");
                        table2.addCell(" ");
                        table2.addCell(" ");
                        table2.addCell(" ");
                        table2.addCell(String.valueOf(n_Wadah));
                        table2.addCell(String.valueOf(n_WadahJentik));
                    } else {
                        table2.addCell(" ");
                        table2.addCell(" ");
                        table2.addCell(" ");
                        table2.addCell(" ");
                        table2.addCell(" ");
                        table2.addCell(" ");
                        table2.addCell(" ");
                        table2.addCell(" ");
                        table2.addCell(" ");
                        table2.addCell(" ");
                        table2.addCell(" ");
                        table2.addCell(" ");
                        table2.addCell(" ");
                        table2.addCell(" ");


                    }

                }
            }
        }

        table2.addCell(createCell("Jumlah", 4, 1, PdfPCell.BOX));
        for (int i=0;i<listLaporanKoor.size();i++) {
            DaftarRt obj2 = listLaporanKoor.get(i);
             rt = obj2.getRt();
            if (rt == 1) {
                List<RekapPerjeni> obx = obj2.getRekapPerjenis();
                for (int j = 0; j < obx.size(); j++) {
                    RekapPerjeni rekap = obx.get(j);
                    int rekapWadah = rekap.getNWadah();
                    int rekapWadahJentik = rekap.getNWadahJentik();
                    int idJenisRumah = rekap.getIdJenisRumah();
                    if (idJenisRumah == 1) {
                        table2.addCell(String.valueOf(rekapWadah));
                        table2.addCell(String.valueOf(rekapWadahJentik));
                    } else if (idJenisRumah == 2) {
                        table2.addCell(String.valueOf(rekapWadah));
                        table2.addCell(String.valueOf(rekapWadahJentik));

                    } else if (idJenisRumah == 3) {
                        table2.addCell(String.valueOf(rekapWadah));
                        table2.addCell(String.valueOf(rekapWadahJentik));
                    } else if (idJenisRumah == 4) {
                        table2.addCell(String.valueOf(rekapWadah));
                        table2.addCell(String.valueOf(rekapWadahJentik));
                    } else if (idJenisRumah == 5) {
                        table2.addCell(String.valueOf(rekapWadah));
                        table2.addCell(String.valueOf(rekapWadahJentik));
                    } else if (idJenisRumah == 6) {
                        table2.addCell(String.valueOf(rekapWadah));
                        table2.addCell(String.valueOf(rekapWadahJentik));
                    } else if (idJenisRumah == 7) {
                        table2.addCell(String.valueOf(rekapWadah));
                        table2.addCell(String.valueOf(rekapWadahJentik));
                    }
                }
            }
        }
        for (int i=0;i<listLaporanKoor.size();i++) {
            DaftarRt obj2 = listLaporanKoor.get(i);
             rt = obj2.getRt();
            if (rt == 1) {
                String totalDiperiksa2 = obj2.getJumlahWadah();
                String totalAdaJentik2 = obj2.getJumlahJentik();
                Integer totalTdkJentik2 = obj2.getNTanpaJentik();
                Integer persentaseBebasJentik2 = obj2.getPersentaseBebasJentik();
                Chunk chunk25 = new Chunk("ABJ(%):" + persentaseBebasJentik2, font);
                //Paragraph paragraph8 = new Paragraph(chunk7);
                //paragraph8.setAlignment(Paragraph.ALIGN_BASELINE);

                Chunk chunk26 = new Chunk("Jumlah Total Diperiksa:" + totalDiperiksa2, font);
                Paragraph paragraph25 = new Paragraph(chunk26);
                paragraph25.add(new Chunk(" "));
                paragraph25.add(new Chunk(" "));
                paragraph25.add(new Chunk(" "));
                paragraph25.add(new Chunk(" "));
                paragraph25.add(new Chunk(" "));
                paragraph25.add(chunk25);
                paragraph25.setAlignment(Paragraph.ALIGN_LEFT);

                Chunk chunk27 = new Chunk("Jumlah Total Jentik(-):" + totalTdkJentik2, font);
                //Paragraph paragpraph7 = new Paragraph(chunk6);
                //paragpraph7.setAlignment(Paragraph.ALIGN_LEFT);
                Chunk chunk28 = new Chunk("Jumlah Total Jentik(+):" + totalAdaJentik2, font);
                Paragraph paragraph26 = new Paragraph(chunk28);
                paragraph26.add(new Chunk(" "));
                paragraph26.add(new Chunk(" "));
                paragraph26.add(new Chunk(" "));
                paragraph26.add(new Chunk(" "));
                paragraph26.add(new Chunk(" "));
                paragraph26.add(chunk27);
                paragraph26.setAlignment(Paragraph.ALIGN_LEFT);

                Chunk glue21 = new Chunk(new VerticalPositionMark());
                Paragraph paragraph27 = new Paragraph("Mengetahui:", font);
                paragraph27.add(new Chunk(glue21));
                paragraph27.add("Jakarta, " + convertDateFormat(tglKunjungan));

                Chunk glue22 = new Chunk(new VerticalPositionMark());
                Paragraph paragraph28 = new Paragraph("Ketua RT"+" "+ rt, font);
                paragraph28.add(new Chunk(glue22));
                paragraph28.add("Surveyor");

                Chunk chunk29 = new Chunk("Kelurahan Bungur", font);
                Paragraph paragraph29 = new Paragraph(chunk29);
                paragraph29.setAlignment(Paragraph.ALIGN_LEFT);

                String user2 = mBundle.getString("username");
                Chunk chunk20 = new Chunk(user2, font);
                Paragraph paragraph20 = new Paragraph(chunk20);
                paragraph20.setAlignment(Paragraph.ALIGN_RIGHT);


                document.add(preface2);
                document.add(paragraph22);
                document.add(paragraph23);
                document.add(paragpraph24);
                document.add(paragpraph31);
                document.add(table2);
                document.add(paragraph25);
                document.add(paragraph26);
                document.add(paragraph27);
                document.add(paragraph28);
                document.add(paragraph29);
                document.add(paragraph20);

            }
        }

                document.newPage();

        //table 3
        float[] pointColumnWidth3 = {1,4,4,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2};
        PdfPTable table3 = new PdfPTable(pointColumnWidth3);

        table3.getDefaultCell().setFixedHeight(30);
        table3.setTotalWidth(PageSize.A4.getWidth());
        table3.setWidthPercentage(100);

        Chunk chunk31 = new Chunk("LAPORAN KEGIATAN PEMANTAUAN SARANG NYAMUK DI 7 (TUJUH) TATANAN",font);
        Paragraph preface3 = new Paragraph(chunk31);
        preface3.setAlignment(Paragraph.ALIGN_CENTER);

        Chunk chunk32 = new Chunk("TINGKAT KELURAHAN"+"KECAMATAN",font);
        Paragraph paragraph32 = new Paragraph(chunk32);
        paragraph32.setAlignment(Paragraph.ALIGN_CENTER);

        Chunk chunk33 = new Chunk("TAHUN 2019",font);
        Paragraph paragraph33 = new Paragraph(chunk33);
        paragraph33.setAlignment(Paragraph.ALIGN_CENTER);

        addEmptyLine(paragraph33,1);

        Chunk chunk34 = new Chunk("PERIODE : " + convertDateFormat(dataKoor.getTglMinggu())+" - "+convertDateFormat(dataKoor.getTglSenin()),font);
        Paragraph paragpraph34 = new Paragraph(chunk34);
        paragpraph34.setAlignment(Paragraph.ALIGN_LEFT);

        Chunk chunk344 = new Chunk("RW : " +" 8 ",font);
        Paragraph paragpraph64 = new Paragraph(chunk344);
        paragpraph64.setAlignment(Paragraph.ALIGN_LEFT);


        // List<List<String>> dataset = getData();

        //create table
        table3.addCell(createCell("No",1,3,PdfPCell.BOX));
        table3.addCell(createCell("NAMA PEMILIK BANGUNAN",1,3,PdfPCell.BOX));
        table3.addCell(createCell("ALAMAT RUMAH",1,3,PdfPCell.BOX));
        table3.addCell(createCell("Tgl Kunjungan",1,3,PdfPCell.BOX));
        table3.addCell(createCell("TATANAN DIPERIKSA",14,1,PdfPCell.BOX));
        table3.addCell(createCell("RUMAH TANGGA",2,1,PdfPCell.BOX));
        table3.addCell(createCell("PERKANTORAN",2,1,PdfPCell.BOX));
        table3.addCell(createCell("SARANA PENDIDIKAN",2,1,PdfPCell.BOX));
        table3.addCell(createCell("TTU",2,1,PdfPCell.BOX));
        table3.addCell(createCell("TPM",2,1,PdfPCell.BOX));
        table3.addCell(createCell("FAS.OLAHRAGA",2,1,PdfPCell.BOX));
        table3.addCell(createCell("FAS.KESEHATAN",2,1,PdfPCell.BOX));
        table3.addCell(createCell("DIPERIKSA",1,1,PdfPCell.BOX));
        table3.addCell(createCell("JENTIK(+)",1,1,PdfPCell.BOX));
        table3.addCell(createCell("DIPERIKSA",1,1,PdfPCell.BOX));
        table3.addCell(createCell("JENTIK(+)",1,1,PdfPCell.BOX));
        table3.addCell(createCell("DIPERIKSA",1,1,PdfPCell.BOX));
        table3.addCell(createCell("JENTIK(+)",1,1,PdfPCell.BOX));
        table3.addCell(createCell("DIPERIKSA",1,1,PdfPCell.BOX));
        table3.addCell(createCell("JENTIK(+)",1,1,PdfPCell.BOX));
        table3.addCell(createCell("DIPERIKSA",1,1,PdfPCell.BOX));
        table3.addCell(createCell("JENTIK(+)",1,1,PdfPCell.BOX));
        table3.addCell(createCell("DIPERIKSA",1,1,PdfPCell.BOX));
        table3.addCell(createCell("JENTIK(+)",1,1,PdfPCell.BOX));
        table3.addCell(createCell("DIPERIKSA",1,1,PdfPCell.BOX));
        table3.addCell(createCell("JENTIK(+)",1,1,PdfPCell.BOX));
        for (int i=0;i<listLaporanKoor.size();i++) {
            DaftarRt obj2 = listLaporanKoor.get(i);
            Integer rt = obj2.getRt();
            if (rt == 2) {
                List<DaftarRumahKoor> obx = obj2.getDaftarRumah();
                for (int k = 0; k < obx.size(); k++) {
                    DaftarRumahKoor obx2 = obx.get(k);
                    int no = k+1;
                    String nama = obx2.getNamaPemilik();
                    String alamatRumah = obx2.getAlamat();
                    int idJenisRumah = obx2.getIdJenisRumah();
                    int n_Wadah = obx2.getNWadah();
                    int n_WadahJentik = obx2.getNWadahJentik();
                    table3.addCell(String.valueOf(no));
                    table3.addCell(String.valueOf(nama));
                    table3.addCell(String.valueOf(alamatRumah));
                    table3.addCell(String.valueOf(convertDateFormat(tglKunjungan)));
                    if (idJenisRumah == 1) {
                        table3.addCell(String.valueOf(n_Wadah));
                        table3.addCell(String.valueOf(n_WadahJentik));
                        table3.addCell(" ");
                        table3.addCell(" ");
                        table3.addCell(" ");
                        table3.addCell(" ");
                        table3.addCell(" ");
                        table3.addCell(" ");
                        table3.addCell(" ");
                        table3.addCell(" ");
                        table3.addCell(" ");
                        table3.addCell(" ");
                        table3.addCell(" ");
                        table3.addCell(" ");
                    } else if (idJenisRumah == 2) {
                        table3.addCell(" ");
                        table3.addCell(" ");
                        table3.addCell(String.valueOf(n_Wadah));
                        table3.addCell(String.valueOf(n_WadahJentik));
                        table3.addCell(" ");
                        table3.addCell(" ");
                        table3.addCell(" ");
                        table3.addCell(" ");
                        table3.addCell(" ");
                        table3.addCell(" ");
                        table3.addCell(" ");
                        table3.addCell(" ");
                        table3.addCell(" ");
                        table3.addCell(" ");
                    } else if (idJenisRumah == 3) {
                        table3.addCell(" ");
                        table3.addCell(" ");
                        table3.addCell(" ");
                        table3.addCell(" ");
                        table3.addCell(String.valueOf(n_Wadah));
                        table3.addCell(String.valueOf(n_WadahJentik));
                        table3.addCell(" ");
                        table3.addCell(" ");
                        table3.addCell(" ");
                        table3.addCell(" ");
                        table3.addCell(" ");
                        table3.addCell(" ");
                        table3.addCell(" ");
                        table3.addCell(" ");
                    } else if (idJenisRumah == 4) {
                        table3.addCell(" ");
                        table3.addCell(" ");
                        table3.addCell(" ");
                        table3.addCell(" ");
                        table3.addCell(" ");
                        table3.addCell(" ");
                        table3.addCell(String.valueOf(n_Wadah));
                        table3.addCell(String.valueOf(n_WadahJentik));
                        table3.addCell(" ");
                        table3.addCell(" ");
                        table3.addCell(" ");
                        table3.addCell(" ");
                        table3.addCell(" ");
                        table3.addCell(" ");
                    } else if (idJenisRumah == 5) {
                        table3.addCell(" ");
                        table3.addCell(" ");
                        table3.addCell(" ");
                        table3.addCell(" ");
                        table3.addCell(" ");
                        table3.addCell(" ");
                        table3.addCell(" ");
                        table3.addCell(" ");
                        table3.addCell(String.valueOf(n_Wadah));
                        table3.addCell(String.valueOf(n_WadahJentik));
                        table3.addCell(" ");
                        table3.addCell(" ");
                        table3.addCell(" ");
                        table3.addCell(" ");
                    } else if (idJenisRumah == 6) {
                        table3.addCell(" ");
                        table3.addCell(" ");
                        table3.addCell(" ");
                        table3.addCell(" ");
                        table3.addCell(" ");
                        table3.addCell(" ");
                        table3.addCell(" ");
                        table3.addCell(" ");
                        table3.addCell(" ");
                        table3.addCell(" ");
                        table3.addCell(String.valueOf(n_Wadah));
                        table3.addCell(String.valueOf(n_WadahJentik));
                        table3.addCell(" ");
                        table3.addCell(" ");
                    } else if (idJenisRumah == 7) {
                        table3.addCell(" ");
                        table3.addCell(" ");
                        table3.addCell(" ");
                        table3.addCell(" ");
                        table3.addCell(" ");
                        table3.addCell(" ");
                        table3.addCell(" ");
                        table3.addCell(" ");
                        table3.addCell(" ");
                        table3.addCell(" ");
                        table3.addCell(" ");
                        table3.addCell(" ");
                        table3.addCell(String.valueOf(n_Wadah));
                        table3.addCell(String.valueOf(n_WadahJentik));
                    } else {
                        table3.addCell(" ");
                        table3.addCell(" ");
                        table3.addCell(" ");
                        table3.addCell(" ");
                        table3.addCell(" ");
                        table3.addCell(" ");
                        table3.addCell(" ");
                        table3.addCell(" ");
                        table3.addCell(" ");
                        table3.addCell(" ");
                        table3.addCell(" ");
                        table3.addCell(" ");
                        table3.addCell(" ");
                        table3.addCell(" ");


                    }

                }
            }
        }

        table3.addCell(createCell("Jumlah", 4, 1, PdfPCell.BOX));
        for (int i=0;i<listLaporanKoor.size();i++) {
            DaftarRt obj2 = listLaporanKoor.get(i);
            Integer rt = obj2.getRt();
            if (rt == 2) {
                List<RekapPerjeni> obx = obj2.getRekapPerjenis();
                for (int j = 0; j < obx.size(); j++) {
                    RekapPerjeni rekap = obx.get(j);
                    int rekapWadah = rekap.getNWadah();
                    int rekapWadahJentik = rekap.getNWadahJentik();
                    int idJenisRumah = rekap.getIdJenisRumah();
                    if (idJenisRumah == 1) {
                        table3.addCell(String.valueOf(rekapWadah));
                        table3.addCell(String.valueOf(rekapWadahJentik));
                    } else if (idJenisRumah == 2) {
                        table3.addCell(String.valueOf(rekapWadah));
                        table3.addCell(String.valueOf(rekapWadahJentik));

                    } else if (idJenisRumah == 3) {
                        table3.addCell(String.valueOf(rekapWadah));
                        table3.addCell(String.valueOf(rekapWadahJentik));
                    } else if (idJenisRumah == 4) {
                        table3.addCell(String.valueOf(rekapWadah));
                        table3.addCell(String.valueOf(rekapWadahJentik));
                    } else if (idJenisRumah == 5) {
                        table3.addCell(String.valueOf(rekapWadah));
                        table3.addCell(String.valueOf(rekapWadahJentik));
                    } else if (idJenisRumah == 6) {
                        table3.addCell(String.valueOf(rekapWadah));
                        table3.addCell(String.valueOf(rekapWadahJentik));
                    } else if (idJenisRumah == 7) {
                        table3.addCell(String.valueOf(rekapWadah));
                        table3.addCell(String.valueOf(rekapWadahJentik));
                    }
                }
            }
        }
        for (int i=0;i<listLaporanKoor.size();i++) {
            DaftarRt obj2 = listLaporanKoor.get(i);
            rt = obj2.getRt();
            if (rt == 2) {
                String totalDiperiksa2 = obj2.getJumlahWadah();
                String totalAdaJentik2 = obj2.getJumlahJentik();
                Integer totalTdkJentik2 = obj2.getNTanpaJentik();
                Integer persentaseBebasJentik2 = obj2.getPersentaseBebasJentik();
                Chunk chunk35 = new Chunk("ABJ(%):" + persentaseBebasJentik2, font);
                //Paragraph paragraph8 = new Paragraph(chunk7);
                //paragraph8.setAlignment(Paragraph.ALIGN_BASELINE);

                Chunk chunk36 = new Chunk("Jumlah Total Diperiksa:" + totalDiperiksa2, font);
                Paragraph paragraph35 = new Paragraph(chunk36);
                paragraph35.add(new Chunk(" "));
                paragraph35.add(new Chunk(" "));
                paragraph35.add(new Chunk(" "));
                paragraph35.add(new Chunk(" "));
                paragraph35.add(new Chunk(" "));
                paragraph35.add(chunk35);
                paragraph35.setAlignment(Paragraph.ALIGN_LEFT);

                Chunk chunk37 = new Chunk("Jumlah Total Jentik(-):" + totalTdkJentik2, font);
                //Paragraph paragpraph7 = new Paragraph(chunk6);
                //paragpraph7.setAlignment(Paragraph.ALIGN_LEFT);
                Chunk chunk38 = new Chunk("Jumlah Total Jentik(+):" + totalAdaJentik2, font);
                Paragraph paragraph36 = new Paragraph(chunk38);
                paragraph36.add(new Chunk(" "));
                paragraph36.add(new Chunk(" "));
                paragraph36.add(new Chunk(" "));
                paragraph36.add(new Chunk(" "));
                paragraph36.add(new Chunk(" "));
                paragraph36.add(chunk37);
                paragraph36.setAlignment(Paragraph.ALIGN_LEFT);

                Chunk glue31 = new Chunk(new VerticalPositionMark());
                Paragraph paragraph37 = new Paragraph("Mengetahui:", font);
                paragraph37.add(new Chunk(glue31));
                paragraph37.add("Jakarta, " + convertDateFormat(tglKunjungan));

                Chunk glue32 = new Chunk(new VerticalPositionMark());
                Paragraph paragraph38 = new Paragraph("Ketua RT" + " "+rt, font);
                paragraph38.add(new Chunk(glue32));
                paragraph38.add("Surveyor");

                Chunk chunk39 = new Chunk("Kelurahan Bungur", font);
                Paragraph paragraph39 = new Paragraph(chunk39);
                paragraph39.setAlignment(Paragraph.ALIGN_LEFT);

                String user3 = mBundle.getString("username");
                Chunk chunk30 = new Chunk(user3, font);
                Paragraph paragraph30 = new Paragraph(chunk30);
                paragraph30.setAlignment(Paragraph.ALIGN_RIGHT);


                document.add(preface3);
                document.add(paragraph32);
                document.add(paragraph33);
                document.add(paragpraph34);
                document.add(paragpraph64);
                document.add(table3);
                document.add(paragraph35);
                document.add(paragraph36);
                document.add(paragraph37);
                document.add(paragraph38);
                document.add(paragraph39);
                document.add(paragraph30);

            }
        }
        document.newPage();

        //table 4
        float[] pointColumnWidth4 = {1,4,4,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2};
        PdfPTable table4 = new PdfPTable(pointColumnWidth4);

        table4.getDefaultCell().setFixedHeight(30);
        table4.setTotalWidth(PageSize.A4.getWidth());
        table4.setWidthPercentage(100);

        Chunk chunk41 = new Chunk("LAPORAN KEGIATAN PEMANTAUAN SARANG NYAMUK DI 7 (TUJUH) TATANAN",font);
        Paragraph preface4 = new Paragraph(chunk41);
        preface4.setAlignment(Paragraph.ALIGN_CENTER);

        Chunk chunk42 = new Chunk("TINGKAT KELURAHAN" + "KECAMATAN",font);
        Paragraph paragraph42 = new Paragraph(chunk42);
        paragraph42.setAlignment(Paragraph.ALIGN_CENTER);

        Chunk chunk43 = new Chunk("TAHUN 2019",font);
        Paragraph paragraph43 = new Paragraph(chunk43);
        paragraph43.setAlignment(Paragraph.ALIGN_CENTER);

        addEmptyLine(paragraph43,1);

        Chunk chunk44 = new Chunk("PERIODE : " + convertDateFormat(dataKoor.getTglMinggu())+" - "+convertDateFormat(dataKoor.getTglSenin()),font);
        Paragraph paragpraph44 = new Paragraph(chunk44);
        paragpraph44.setAlignment(Paragraph.ALIGN_LEFT);

        Chunk chunk54 = new Chunk("RW : " + " 8 ",font);
        Paragraph paragpraph74 = new Paragraph(chunk54);
        paragpraph74.setAlignment(Paragraph.ALIGN_LEFT);


        // List<List<String>> dataset = getData();

        //create table
        table4.addCell(createCell("No",1,3,PdfPCell.BOX));
        table4.addCell(createCell("NAMA PEMILIK BANGUNAN",1,3,PdfPCell.BOX));
        table4.addCell(createCell("ALAMAT RUMAH",1,3,PdfPCell.BOX));
        table4.addCell(createCell("Tgl Kunjungan",1,3,PdfPCell.BOX));
        table4.addCell(createCell("TATANAN DIPERIKSA",14,1,PdfPCell.BOX));
        table4.addCell(createCell("RUMAH TANGGA",2,1,PdfPCell.BOX));
        table4.addCell(createCell("PERKANTORAN",2,1,PdfPCell.BOX));
        table4.addCell(createCell("SARANA PENDIDIKAN",2,1,PdfPCell.BOX));
        table4.addCell(createCell("TTU",2,1,PdfPCell.BOX));
        table4.addCell(createCell("TPM",2,1,PdfPCell.BOX));
        table4.addCell(createCell("FAS.OLAHRAGA",2,1,PdfPCell.BOX));
        table4.addCell(createCell("FAS.KESEHATAN",2,1,PdfPCell.BOX));
        table4.addCell(createCell("DIPERIKSA",1,1,PdfPCell.BOX));
        table4.addCell(createCell("JENTIK(+)",1,1,PdfPCell.BOX));
        table4.addCell(createCell("DIPERIKSA",1,1,PdfPCell.BOX));
        table4.addCell(createCell("JENTIK(+)",1,1,PdfPCell.BOX));
        table4.addCell(createCell("DIPERIKSA",1,1,PdfPCell.BOX));
        table4.addCell(createCell("JENTIK(+)",1,1,PdfPCell.BOX));
        table4.addCell(createCell("DIPERIKSA",1,1,PdfPCell.BOX));
        table4.addCell(createCell("JENTIK(+)",1,1,PdfPCell.BOX));
        table4.addCell(createCell("DIPERIKSA",1,1,PdfPCell.BOX));
        table4.addCell(createCell("JENTIK(+)",1,1,PdfPCell.BOX));
        table4.addCell(createCell("DIPERIKSA",1,1,PdfPCell.BOX));
        table4.addCell(createCell("JENTIK(+)",1,1,PdfPCell.BOX));
        table4.addCell(createCell("DIPERIKSA",1,1,PdfPCell.BOX));
        table4.addCell(createCell("JENTIK(+)",1,1,PdfPCell.BOX));
        for (int i=0;i<listLaporanKoor.size();i++) {
            DaftarRt obj2 = listLaporanKoor.get(i);
            rt = obj2.getRt();
            if (rt == 3) {
                List<DaftarRumahKoor> obx = obj2.getDaftarRumah();
                for (int k = 0; k < obx.size(); k++) {
                    DaftarRumahKoor obx2 = obx.get(k);
                    int no = k+1;
                    String nama = obx2.getNamaPemilik();
                    String alamatRumah = obx2.getAlamat();
                    int idJenisRumah = obx2.getIdJenisRumah();
                    int n_Wadah = obx2.getNWadah();
                    int n_WadahJentik = obx2.getNWadahJentik();
                    table4.addCell(String.valueOf(no));
                    table4.addCell(String.valueOf(nama));
                    table4.addCell(String.valueOf(alamatRumah));
                    table4.addCell(String.valueOf(convertDateFormat(tglKunjungan)));
                    if (idJenisRumah == 1) {
                        table4.addCell(String.valueOf(n_Wadah));
                        table4.addCell(String.valueOf(n_WadahJentik));
                        table4.addCell(" ");
                        table4.addCell(" ");
                        table4.addCell(" ");
                        table4.addCell(" ");
                        table4.addCell(" ");
                        table4.addCell(" ");
                        table4.addCell(" ");
                        table4.addCell(" ");
                        table4.addCell(" ");
                        table4.addCell(" ");
                        table4.addCell(" ");
                        table4.addCell(" ");
                    } else if (idJenisRumah == 2) {
                        table4.addCell(" ");
                        table4.addCell(" ");
                        table4.addCell(String.valueOf(n_Wadah));
                        table4.addCell(String.valueOf(n_WadahJentik));
                        table4.addCell(" ");
                        table4.addCell(" ");
                        table4.addCell(" ");
                        table4.addCell(" ");
                        table4.addCell(" ");
                        table4.addCell(" ");
                        table4.addCell(" ");
                        table4.addCell(" ");
                        table4.addCell(" ");
                        table4.addCell(" ");
                    } else if (idJenisRumah == 3) {
                        table4.addCell(" ");
                        table4.addCell(" ");
                        table4.addCell(" ");
                        table4.addCell(" ");
                        table4.addCell(String.valueOf(n_Wadah));
                        table4.addCell(String.valueOf(n_WadahJentik));
                        table4.addCell(" ");
                        table4.addCell(" ");
                        table4.addCell(" ");
                        table4.addCell(" ");
                        table4.addCell(" ");
                        table4.addCell(" ");
                        table4.addCell(" ");
                        table4.addCell(" ");
                    } else if (idJenisRumah == 4) {
                        table4.addCell(" ");
                        table4.addCell(" ");
                        table4.addCell(" ");
                        table4.addCell(" ");
                        table4.addCell(" ");
                        table4.addCell(" ");
                        table4.addCell(String.valueOf(n_Wadah));
                        table4.addCell(String.valueOf(n_WadahJentik));
                        table4.addCell(" ");
                        table4.addCell(" ");
                        table4.addCell(" ");
                        table4.addCell(" ");
                        table4.addCell(" ");
                        table4.addCell(" ");
                    } else if (idJenisRumah == 5) {
                        table4.addCell(" ");
                        table4.addCell(" ");
                        table4.addCell(" ");
                        table4.addCell(" ");
                        table4.addCell(" ");
                        table4.addCell(" ");
                        table4.addCell(" ");
                        table4.addCell(" ");
                        table4.addCell(String.valueOf(n_Wadah));
                        table4.addCell(String.valueOf(n_WadahJentik));
                        table4.addCell(" ");
                        table4.addCell(" ");
                        table4.addCell(" ");
                        table4.addCell(" ");
                    } else if (idJenisRumah == 6) {
                        table4.addCell(" ");
                        table4.addCell(" ");
                        table4.addCell(" ");
                        table4.addCell(" ");
                        table4.addCell(" ");
                        table4.addCell(" ");
                        table4.addCell(" ");
                        table4.addCell(" ");
                        table4.addCell(" ");
                        table4.addCell(" ");
                        table4.addCell(String.valueOf(n_Wadah));
                        table4.addCell(String.valueOf(n_WadahJentik));
                        table4.addCell(" ");
                        table4.addCell(" ");
                    } else if (idJenisRumah == 7) {
                        table4.addCell(" ");
                        table4.addCell(" ");
                        table4.addCell(" ");
                        table4.addCell(" ");
                        table4.addCell(" ");
                        table4.addCell(" ");
                        table4.addCell(" ");
                        table4.addCell(" ");
                        table4.addCell(" ");
                        table4.addCell(" ");
                        table4.addCell(" ");
                        table4.addCell(" ");
                        table4.addCell(String.valueOf(n_Wadah));
                        table4.addCell(String.valueOf(n_WadahJentik));
                    } else {
                        table4.addCell(" ");
                        table4.addCell(" ");
                        table4.addCell(" ");
                        table4.addCell(" ");
                        table4.addCell(" ");
                        table4.addCell(" ");
                        table4.addCell(" ");
                        table4.addCell(" ");
                        table4.addCell(" ");
                        table4.addCell(" ");
                        table4.addCell(" ");
                        table4.addCell(" ");
                        table4.addCell(" ");
                        table4.addCell(" ");

                    }

                }
            }
        }

        table4.addCell(createCell("Jumlah", 4, 1, PdfPCell.BOX));
        for (int i=0;i<listLaporanKoor.size();i++) {
            DaftarRt obj2 = listLaporanKoor.get(i);
            rt = obj2.getRt();
            if (rt == 3) {
                List<RekapPerjeni> obx = obj2.getRekapPerjenis();
                for (int j = 0; j < obx.size(); j++) {
                    RekapPerjeni rekap = obx.get(j);
                    int rekapWadah = rekap.getNWadah();
                    int rekapWadahJentik = rekap.getNWadahJentik();
                    int idJenisRumah = rekap.getIdJenisRumah();
                    if (idJenisRumah == 1) {
                        table4.addCell(String.valueOf(rekapWadah));
                        table4.addCell(String.valueOf(rekapWadahJentik));
                    } else if (idJenisRumah == 2) {
                        table4.addCell(String.valueOf(rekapWadah));
                        table4.addCell(String.valueOf(rekapWadahJentik));
                    } else if (idJenisRumah == 3) {
                        table4.addCell(String.valueOf(rekapWadah));
                        table4.addCell(String.valueOf(rekapWadahJentik));
                    } else if (idJenisRumah == 4) {
                        table4.addCell(String.valueOf(rekapWadah));
                        table4.addCell(String.valueOf(rekapWadahJentik));
                    } else if (idJenisRumah == 5) {
                        table4.addCell(String.valueOf(rekapWadah));
                        table4.addCell(String.valueOf(rekapWadahJentik));
                    } else if (idJenisRumah == 6) {
                        table4.addCell(String.valueOf(rekapWadah));
                        table4.addCell(String.valueOf(rekapWadahJentik));
                    } else if (idJenisRumah == 7) {
                        table4.addCell(String.valueOf(rekapWadah));
                        table4.addCell(String.valueOf(rekapWadahJentik));
                    }
                }
            }
        }
        for (int i=0;i<listLaporanKoor.size();i++) {
            DaftarRt obj2 = listLaporanKoor.get(i);
            rt = obj2.getRt();
            if (rt == 3) {
                String totalDiperiksa2 = obj2.getJumlahWadah();
                String totalAdaJentik2 = obj2.getJumlahJentik();
                Integer totalTdkJentik2 = obj2.getNTanpaJentik();
                Integer persentaseBebasJentik2 = obj2.getPersentaseBebasJentik();
                Chunk chunk45 = new Chunk("ABJ(%):" + persentaseBebasJentik2, font);
                //Paragraph paragraph8 = new Paragraph(chunk7);
                //paragraph8.setAlignment(Paragraph.ALIGN_BASELINE);

                Chunk chunk46 = new Chunk("Jumlah Total Diperiksa:" + totalDiperiksa2, font);
                Paragraph paragraph45 = new Paragraph(chunk46);
                paragraph45.add(new Chunk(" "));
                paragraph45.add(new Chunk(" "));
                paragraph45.add(new Chunk(" "));
                paragraph45.add(new Chunk(" "));
                paragraph45.add(new Chunk(" "));
                paragraph45.add(chunk45);
                paragraph45.setAlignment(Paragraph.ALIGN_LEFT);

                Chunk chunk47 = new Chunk("Jumlah Total Jentik(-):" + totalTdkJentik2, font);
                //Paragraph paragpraph7 = new Paragraph(chunk6);
                //paragpraph7.setAlignment(Paragraph.ALIGN_LEFT);
                Chunk chunk48 = new Chunk("Jumlah Total Jentik(+):" + totalAdaJentik2, font);
                Paragraph paragraph46 = new Paragraph(chunk48);
                paragraph46.add(new Chunk(" "));
                paragraph46.add(new Chunk(" "));
                paragraph46.add(new Chunk(" "));
                paragraph46.add(new Chunk(" "));
                paragraph46.add(new Chunk(" "));
                paragraph46.add(chunk47);
                paragraph46.setAlignment(Paragraph.ALIGN_LEFT);

                Chunk glue41 = new Chunk(new VerticalPositionMark());
                Paragraph paragraph47 = new Paragraph("Mengetahui:", font);
                paragraph47.add(new Chunk(glue41));
                paragraph47.add("Jakarta, " + convertDateFormat(tglKunjungan));

                Chunk glue42 = new Chunk(new VerticalPositionMark());
                Paragraph paragraph48 = new Paragraph("Ketua RT" +" "+ rt, font);
                paragraph48.add(new Chunk(glue42));
                paragraph48.add("Surveyor");

                Chunk chunk49 = new Chunk("Kelurahan Bungur", font);
                Paragraph paragraph49 = new Paragraph(chunk49);
                paragraph49.setAlignment(Paragraph.ALIGN_LEFT);

                String user4 = mBundle.getString("username");
                Chunk chunk40 = new Chunk(user4, font);
                Paragraph paragraph40 = new Paragraph(chunk40);
                paragraph40.setAlignment(Paragraph.ALIGN_RIGHT);


                document.add(preface4);
                document.add(paragraph42);
                document.add(paragraph43);
                document.add(paragpraph44);
                document.add(paragpraph74);
                document.add(table4);
                document.add(paragraph45);
                document.add(paragraph46);
                document.add(paragraph47);
                document.add(paragraph48);
                document.add(paragraph49);
                document.add(paragraph40);

            }
        }
        document.close();

            Toast.makeText(this, "Pdf Disimpan di folder Documents", Toast.LENGTH_SHORT).show();
            viewPdf();
        }



    private void viewPdf(){
        String username = mBundle.getString("username");
        String pdfname = "Laporan_"+ username+"_"+ convertDateFormat(tglKunjungan) +".pdf";
        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/Documents/"+pdfname);
         Intent target = new Intent(Intent.ACTION_VIEW);
         target.setDataAndType(FileProvider.getUriForFile(HomePageKunjungan.this, "com.healthpoint.medic",file),"application/pdf");
         target.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
         target.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

         Intent intent = Intent.createChooser(target,"Open File");
         try{
             startActivity(intent);

         }catch (ActivityNotFoundException e){

         }


    }
    public PdfPCell createCell(String content, int colspan, int rowspan, int border) {
        PdfPCell cell = new PdfPCell(new Phrase(content, font1));
        cell.setColspan(colspan);
        cell.setRowspan(rowspan);
        cell.setBorder(border);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        return cell;
    }


}
