package com.healthpoint.medic.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.healthpoint.medic.R;

public class IndividuHolder extends RecyclerView.ViewHolder {
    TextView nik,perankk,nama,tanggallahir,jenkel,agama,pendidikan,statjkn,statperkawinan,pekerjaan,nohp;
    public IndividuHolder(View view) {
        super(view);
        this.nik = view.findViewById(R.id.tv_nik);
        this.perankk = view.findViewById(R.id.tv_perankk);
        this.nama = view.findViewById(R.id.tv_nama);
        this.tanggallahir = view.findViewById(R.id.tv_tanggallahir);
        this.jenkel = view.findViewById(R.id.tv_jenkel);
        this.agama = view.findViewById(R.id.tv_agama);
        this.pendidikan = view.findViewById(R.id.tv_pendidikan);
        this.statjkn = view.findViewById(R.id.tv_statjkn);
        this.statperkawinan = view.findViewById(R.id.tv_statperkawinan);
        this.pekerjaan = view.findViewById(R.id.tv_pekerjaan);
        this.nohp = view.findViewById(R.id.tv_nohp);
    }
}
