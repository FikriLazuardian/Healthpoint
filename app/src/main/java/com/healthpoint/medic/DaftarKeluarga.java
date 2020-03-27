package com.healthpoint.medic;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


import com.healthpoint.medic.adapter.AdapterDaftarKeluarga;
import com.healthpoint.medic.model.DaftarKeluargaItem;
import com.healthpoint.medic.model.ResponseDaftarKeluarga;
import com.healthpoint.medic.network.APIClient;
import com.healthpoint.medic.network.APIInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DaftarKeluarga extends AppCompatActivity {
    AdapterDaftarKeluarga adapterDaftarKeluarga;
    RecyclerView recyclerView;
    APIInterface service;
    Context mContext;
    FloatingActionButton floatingBtn;
    private List<DaftarKeluargaItem> daftarKeluargaItemList = new ArrayList<>();
    private RecyclerView.LayoutManager layoutManager;
    private String idPengguna = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_keluarga);
        service = APIClient.createService(APIInterface.class);
        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        floatingBtn = (FloatingActionButton) findViewById(R.id.fab);
        loadJSON();
        //Menggunakan Layout Manager, Dan Membuat List Secara Vertical
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(layoutManager);
        adapterDaftarKeluarga = new AdapterDaftarKeluarga(this, daftarKeluargaItemList);
        recyclerView.setAdapter(adapterDaftarKeluarga);
        floatingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DaftarKeluarga.this, Datakeluarga.class));

            }
        });

        idPengguna = getIntent().getStringExtra("id_pengguna");
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.bar, menu);
        final MenuItem searchItem = menu.findItem(R.id.search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                //Filter As Yo Type
                adapterDaftarKeluarga.getFilter().filter(query);
                return false;
            }
        });
        return true;
    }

    private void loadJSON() {
        Call<ResponseDaftarKeluarga> call = service.getDaftarKeluarga();
        call.enqueue(new Callback<ResponseDaftarKeluarga>() {
            @Override
            public void onResponse(Call<ResponseDaftarKeluarga> call, Response<ResponseDaftarKeluarga> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        final List<DaftarKeluargaItem> daftarKeluargaItems = response.body().getDaftarKeluarga();
                        recyclerView.setAdapter(new AdapterDaftarKeluarga(mContext, daftarKeluargaItems));
                        adapterDaftarKeluarga.notifyDataSetChanged();
                    }
                } else {


                }
            }


            @Override
            public void onFailure(Call<ResponseDaftarKeluarga> call, Throwable t) {
                Log.d("Error", t.getMessage());
            }
        });
    }

}
