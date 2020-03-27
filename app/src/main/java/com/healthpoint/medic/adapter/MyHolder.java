package com.healthpoint.medic.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.healthpoint.medic.R;
import com.healthpoint.medic.DashboardUtamaKeluarga;

public class MyHolder extends RecyclerView.ViewHolder   {
    TextView tvNokk,tvAlamat;
    RelativeLayout parentLayout;
    Context mContext;

    public MyHolder(View itemView) {
        super(itemView);
        this.tvNokk = itemView.findViewById(R.id.text_view);
        this.tvAlamat = itemView.findViewById(R.id.text_view1);
        this.parentLayout = itemView.findViewById(R.id.parent_layout);
        mContext = itemView.getContext();

        parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, DashboardUtamaKeluarga.class);
                String noKK = tvNokk.getText().toString();
                String alamat = tvAlamat.getText().toString();
                intent.putExtra("no_kk", tvNokk.getText().toString());
                intent.putExtra("alamat", tvAlamat.getText().toString());
                mContext.startActivity(intent);
            }
        });
    }
}
