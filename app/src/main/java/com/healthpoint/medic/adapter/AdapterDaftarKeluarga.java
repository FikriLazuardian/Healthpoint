package com.healthpoint.medic.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import com.healthpoint.medic.R;
import com.healthpoint.medic.model.CustomFilter;
import com.healthpoint.medic.model.DaftarKeluargaItem;

import java.util.List;

public class AdapterDaftarKeluarga extends RecyclerView.Adapter<MyHolder> implements Filterable {

    public List<DaftarKeluargaItem> daftarKeluargaItemList;
    List<DaftarKeluargaItem> filterList;
    Context mContext;
    CustomFilter filter;

    public AdapterDaftarKeluarga(Context context, List<DaftarKeluargaItem> daftarKeluargaItemsList){
        this.mContext = context;
        this.daftarKeluargaItemList = daftarKeluargaItemsList;
        this.filterList = daftarKeluargaItemsList;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rowdaftarkeluarga,parent,false);
        MyHolder holder = new MyHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
       final DaftarKeluargaItem daftarKeluargaItem = daftarKeluargaItemList.get(position);
       holder.tvNokk.setText(daftarKeluargaItem.getNoKK());
       holder.tvAlamat.setText(daftarKeluargaItem.getAlamat());


    }

    @Override
    public int getItemCount() {
        return daftarKeluargaItemList.size();
    }
@Override
    public Filter getFilter(){
        if(filter==null){
            filter = new CustomFilter(filterList,this);
        }
        return filter;
    }



}


