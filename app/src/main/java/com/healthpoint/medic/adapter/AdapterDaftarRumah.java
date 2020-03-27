package com.healthpoint.medic.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.healthpoint.medic.R;
import com.healthpoint.medic.model.DaftarRumahItem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AdapterDaftarRumah extends RecyclerView.Adapter<RumahHolder> {

    private ArrayList<DaftarRumahItem> allDaftarRumah;
    private ArrayList<DaftarRumahItem> shownList;
    private Context mContext;
    private Bundle mBundle;

    public AdapterDaftarRumah(Context context, List<DaftarRumahItem> daftarRumahItems, Bundle bundle) {
        this.mContext = context;
        allDaftarRumah = new ArrayList<>();
        allDaftarRumah.addAll(daftarRumahItems);
        shownList = (ArrayList<DaftarRumahItem>) allDaftarRumah.clone();
        this.mBundle = bundle;
    }

    @NonNull
    @Override
    public RumahHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rowdaftarrumah, parent, false);
        RumahHolder holder = new RumahHolder(view, mBundle);
        return holder;
    }

    @Override
    public void onBindViewHolder(RumahHolder holder, int position) {
        final DaftarRumahItem daftarRumahItem = shownList.get(position);
        holder.tvPemilik.setText("Rumah " + daftarRumahItem.getNamaPemilik());
        holder.tvAlamat.setText(daftarRumahItem.getAlamat());
        holder.tvRT.setText("RT " + daftarRumahItem.getRt());
        holder.idRumah = daftarRumahItem.getIDrumah();
        holder.idKunjungan = daftarRumahItem.getIdKunjungan();
        if (holder.idKunjungan > 0) {
            holder.ivRecordFound.setVisibility(View.VISIBLE);
        } else {
            holder.ivRecordFound.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return shownList.size();
    }

    public void reset(){
        shownList = (ArrayList<DaftarRumahItem>) allDaftarRumah.clone();
        notifyDataSetChanged();
    }

    public void filterAdaKunjungan() {
        shownList = new ArrayList<>();
        for(DaftarRumahItem item : allDaftarRumah) {
            if (item.getIdKunjungan() > 0) {
                shownList.add(item);
            }
        }
        notifyDataSetChanged();
    }

    public void filterTidakAdaKunjungan() {
        shownList = new ArrayList<>();
        for(DaftarRumahItem item : allDaftarRumah) {
            if (item.getIdKunjungan() == 0) {
                shownList.add(item);
            }
        }
        notifyDataSetChanged();
    }

    public void sortPemilikAsc() {
        shownList = (ArrayList<DaftarRumahItem>) allDaftarRumah.clone();
        Collections.sort(shownList, DaftarRumahItem.BY_NAME_PEMILIK_ASCENDING);
        notifyDataSetChanged();
    }

    public void sortPemilikDesc() {
        shownList = (ArrayList<DaftarRumahItem>) allDaftarRumah.clone();
        Collections.sort(shownList, DaftarRumahItem.BY_NAME_PEMILIK_DESCENDING);
        notifyDataSetChanged();
    }

    public void sortAlamatAsc() {
        shownList = (ArrayList<DaftarRumahItem>) allDaftarRumah.clone();
        Collections.sort(shownList, DaftarRumahItem.BY_ALAMAT_ASCENDING);
        notifyDataSetChanged();
    }

    public void sortAlamatDesc() {
        shownList = (ArrayList<DaftarRumahItem>) allDaftarRumah.clone();
        Collections.sort(shownList, DaftarRumahItem.BY_ALAMAT_DESCENDING);
        notifyDataSetChanged();
    }
}