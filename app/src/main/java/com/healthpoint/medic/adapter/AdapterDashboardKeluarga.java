package com.healthpoint.medic.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.healthpoint.medic.R;
import com.healthpoint.medic.model.DaftarIndividuItem;

import java.util.List;

public class AdapterDashboardKeluarga extends RecyclerView.Adapter<IndividuHolder>{
    public List<DaftarIndividuItem> daftarIndividuItemList;
    Context mContext;

    public AdapterDashboardKeluarga(Context context, List<DaftarIndividuItem> daftarIndividuItemList){
        this.mContext = context;
        this.daftarIndividuItemList = daftarIndividuItemList;

    }
    @NonNull
    @Override
    public IndividuHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rowdashboardkeluarga,parent,false);
        IndividuHolder holder = new IndividuHolder(view);
        return holder;
    }
    @Override
    public void onBindViewHolder(IndividuHolder holder, int position) {
        final DaftarIndividuItem daftarKeluargaItem = daftarIndividuItemList.get(position);



    }

    @Override
    public int getItemCount() {
        return daftarIndividuItemList.size();
    }
}
