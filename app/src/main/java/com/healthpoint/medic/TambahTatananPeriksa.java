package com.healthpoint.medic;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.healthpoint.medic.fragment.PengamatanVisitFragment;
import com.healthpoint.medic.fragment.UploadFotoFragment;
import com.healthpoint.medic.listeners.FragmentNavigationListener;
import com.healthpoint.medic.model.KunjunganItem;
import com.healthpoint.medic.model.PostKunjungan;
import com.healthpoint.medic.model.ResponseFoto;
import com.healthpoint.medic.network.APIClient;
import com.healthpoint.medic.network.APIInterface;

import java.util.HashMap;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TambahTatananPeriksa extends FragmentActivity implements FragmentNavigationListener {
    private PengamatanVisitFragment firstFragment;
    private UploadFotoFragment secondFragment;

    private APIInterface service;
    private Context mContext;
    private Bundle mBundle;
    AlertDialog.Builder builder;
    AlertDialog dialog;
    private byte[] arrBytePantau;
    private byte[] arrByteBerantas;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_tatanan_periksa);

        mContext = this;
        service = APIClient.createService(APIInterface.class);
        mBundle = getIntent().getExtras();

        builder = new AlertDialog.Builder(this);
        builder.setView(R.layout.progress);
        dialog = builder.create();

        if (findViewById(R.id.fragment_container) != null) {
            if (savedInstanceState != null) {
                return;
            }

            firstFragment = new PengamatanVisitFragment();
            firstFragment.setFragmentNavigationListener(this);
            firstFragment.setArguments(mBundle);

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, firstFragment).commit();
        }
    }

    @Override
    public void FragmentOpenListener(int i) {
        switch (i) {
            case 1:
                openFragmentDua();

                break;
            case 2:
                dialog.show();
                arrBytePantau = secondFragment.getArrBytePantau();
                arrByteBerantas = secondFragment.getArrByteBerantas();
                PostTatananPeriksa(secondFragment.getData());
                break;
            default:
                break;
        }
    }

    private void commitFragment (Fragment fragment) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_container, fragment);
        ft.addToBackStack(null);
        ft.commit();
    }

    private void openFragmentDua() {
        secondFragment = new UploadFotoFragment();
        secondFragment.setFragmentNavigationListener(this);
        secondFragment.setArguments(firstFragment.getData());
        commitFragment(secondFragment);
    }

    private void PostTatananPeriksa(final Bundle data) {
        Call<PostKunjungan> call = service.createTatananPeriksa(
                data.getString("tanggal_senin"),
                data.getString("n_wadah"),
                data.getString("n_wadah_jentik"),
                data.getInt("id_rumah"),
                data.getInt("id_jenis_rumah"),
                data.getInt("id_pengguna")
        );

        call.enqueue(new Callback<PostKunjungan>() {
            @Override
            public void onResponse(Call<PostKunjungan> call, Response<PostKunjungan> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(mContext, "Tambah Tatanan Periksa Berhasil", Toast.LENGTH_SHORT).show();
                    KunjunganItem data = response.body().getData();
                    String idKunjungan = data.getIdKunjungan();
                    PostDuaFoto(arrBytePantau, arrByteBerantas, idKunjungan);


                }
            }

            @Override
            public void onFailure(Call<PostKunjungan> call, Throwable t) {
                Toast.makeText(mContext, "Tambah Tatanan Periksa Gagal", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    @NonNull
    private RequestBody createPartFromString(String descriptionString) {
        return RequestBody.create(
                okhttp3.MultipartBody.FORM, descriptionString);
    }

    private void PostDuaFoto(byte[] imageBytesPantau, byte[] imageBytesBerantas, final String id_kunjungan) {
        MultipartBody.Part bodyPantau = null;
        MultipartBody.Part bodyBerantas = null;
        if (imageBytesPantau != null) {
            RequestBody requestFilePantau = RequestBody.create(MediaType.parse("image/jpeg"), imageBytesPantau);
            bodyPantau = MultipartBody.Part.createFormData("foto_pantau", "fotopantau.jpg", requestFilePantau);
        }
        if (imageBytesBerantas != null) {
            RequestBody requestFileBerantas = RequestBody.create(MediaType.parse("image/jpeg"), imageBytesBerantas);

            bodyBerantas = MultipartBody.Part.createFormData("foto_berantas", "fotoberantas.jpg", requestFileBerantas);
        }
        HashMap<String, RequestBody> map = new HashMap<>();
        map.put("id_kunjungan", createPartFromString(id_kunjungan));
        map.put("jenis_foto", createPartFromString("PB"));
        RequestBody idKunjungan =
                RequestBody.create(
                        okhttp3.MultipartBody.FORM, id_kunjungan);

        Call<ResponseFoto> call = service.uploadDuaPhoto(idKunjungan, bodyPantau, bodyBerantas);
        call.enqueue(new Callback<ResponseFoto>() {
            @Override
            public void onResponse(Call<ResponseFoto> call, Response<ResponseFoto> response) {
                if (response.isSuccessful()) {

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mBundle.putString("id_kunjungan", id_kunjungan);
                            Intent intent = new Intent(mContext, TatananPeriksa.class);
                            intent.putExtras(mBundle);
                            dialog.dismiss();
                            startActivity(intent);
                            finish();
                        }
                    },2000);

                    Toast.makeText(mContext, "Upload Foto Berhasil", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<ResponseFoto> call, Throwable t) {
                Toast.makeText(mContext, "Upload Foto Gagal", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
