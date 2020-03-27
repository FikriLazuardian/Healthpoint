package com.healthpoint.medic;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.widget.Toast;

import com.healthpoint.medic.fragment.LokasiVisitFragment;
import com.healthpoint.medic.fragment.PengamatanVisitFragment;
import com.healthpoint.medic.listeners.FragmentNavigationListener;
import com.healthpoint.medic.model.PostDataJumantik;
import com.healthpoint.medic.network.APIClient;
import com.healthpoint.medic.network.APIInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TambahVisitJumantik extends FragmentActivity implements FragmentNavigationListener {
    private LokasiVisitFragment firstFragment;
    private PengamatanVisitFragment secondFragment;

    private APIInterface service;
    private Context mContext;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_visit_jumantik);

        mContext = this;
        service = APIClient.createService(APIInterface.class);

        if (findViewById(R.id.fragment_container) != null) {
            if (savedInstanceState != null) {
                return;
            }

            firstFragment = new LokasiVisitFragment();
            firstFragment.setFragmentNavigationListener(this);
            firstFragment.setArguments(getIntent().getExtras());

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
                PostDataVisit(secondFragment.getData());
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
        secondFragment = new PengamatanVisitFragment();
        secondFragment.setFragmentNavigationListener(this);
        secondFragment.setArguments(firstFragment.getData());
        commitFragment(secondFragment);
    }

    public void PostDataVisit(Bundle data) {
        Call<PostDataJumantik> call = service.createJumantik(
                data.getString("lat"),
                data.getString("lon"),
                data.getString("alamat"),
                data.getString("rt"),
                data.getString("rw"),
                data.getString("id_kelurahan"),
                data.getString("rumah_umum"),
                data.getString("in_outdoor"),
                data.getString("n_wadah"),
                data.getString("ada_jentik"),
                data.getString("tgl_visit")
        );
        call.enqueue(new Callback<PostDataJumantik>() {
            @Override
            public void onResponse(Call<PostDataJumantik> call, Response<PostDataJumantik> response) {
                if (response.isSuccessful()) {
                    Log.i("debug", "onResponse:Berhasil");
                    Toast.makeText(mContext, "Tambah Visit Jumantik Berhasil", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PostDataJumantik> call, Throwable t) {
                Toast.makeText(mContext, "Tambah Visit Jumantik Gagal", Toast.LENGTH_SHORT).show();
            }
        });
    }
}