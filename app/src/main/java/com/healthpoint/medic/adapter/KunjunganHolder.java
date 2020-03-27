package com.healthpoint.medic.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.RelativeLayout;

import com.healthpoint.medic.HomePageKunjungan;
import com.healthpoint.medic.R;
import com.healthpoint.medic.TatananPeriksa;

public class KunjunganHolder extends RecyclerView.ViewHolder {
    TextView tvTanggal;
    RelativeLayout parentLayout;
    Context mContext;
    String tglKunjungan;

    public KunjunganHolder(View view) {
        super(view);
        this.parentLayout = itemView.findViewById(R.id.parent_layout);
        this.tvTanggal = view.findViewById(R.id.tvTanggal);
        mContext = itemView.getContext();

        parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("tgl_kunjungan", tglKunjungan);
                Intent intent = new Intent(mContext, HomePageKunjungan.class);
                intent.putExtras(bundle);
                mContext.startActivity(intent);
            }
        });
    }
}
