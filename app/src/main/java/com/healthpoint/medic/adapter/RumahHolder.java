package com.healthpoint.medic.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.RelativeLayout;

import com.healthpoint.medic.R;
import com.healthpoint.medic.TambahTatananPeriksa;
import com.healthpoint.medic.TatananPeriksa;

public class RumahHolder extends RecyclerView.ViewHolder {
    TextView tvPemilik, tvAlamat, tvRT;
    RelativeLayout parentLayout;
    Context mContext;
    ImageView ivRecordFound;

    private Bundle mBundle;
    int idRumah;
    int idKunjungan;

    public RumahHolder(View view, Bundle bundle) {
        super(view);
        this.parentLayout = itemView.findViewById(R.id.parent_layout);
        this.tvPemilik = view.findViewById(R.id.tvPemilik);
        this.tvAlamat = view.findViewById(R.id.tvAlamat);
        this.tvRT = view.findViewById(R.id.tvRT);
        this.ivRecordFound = view.findViewById(R.id.ivRecordFound);
        this.mBundle = bundle;
        mContext = itemView.getContext();

        parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBundle.putInt("id_rumah", idRumah);
                mBundle.putString("nama_pemilik", tvPemilik.getText().toString());
                mBundle.putString("alamat", tvAlamat.getText().toString());
                mBundle.putString("rt", tvRT.getText().toString());
                if (idKunjungan > 0) {
                    mBundle.putString("id_kunjungan", ""+idKunjungan);
                    Intent intent = new Intent(mContext, TatananPeriksa.class);
                    intent.putExtras(mBundle);
                    mContext.startActivity(intent);
                } else {
                    Intent intent = new Intent(mContext, TambahTatananPeriksa.class);
                    intent.putExtras(mBundle);
                    mContext.startActivity(intent);
                }
            }
        });
    }
}
