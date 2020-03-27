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
import com.healthpoint.medic.model.DaftarKunjunganItem;

import java.util.List;

public class AdapterDaftarKunjungan extends RecyclerView.Adapter<KunjunganHolder> implements Filterable {

    public List<DaftarKunjunganItem> daftarKunjunganItemList;
    List<DaftarKunjunganItem> filterListDaftarRumah;
    Context mContext;
    CustomFilter filter;

    public AdapterDaftarKunjungan(Context context, List<DaftarKunjunganItem> daftarKunjunganItemsList) {
        this.mContext = context;
        this.daftarKunjunganItemList = daftarKunjunganItemsList;
        this.filterListDaftarRumah = daftarKunjunganItemsList;
    }

    @NonNull
    @Override
    public KunjunganHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rowdaftarkunjungan, parent, false);
        KunjunganHolder holder = new KunjunganHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(KunjunganHolder holder, int position) {
        final DaftarKunjunganItem daftarKunjunganItem = daftarKunjunganItemList.get(position);
        holder.tglKunjungan = daftarKunjunganItem.getPeriode();
        holder.tvTanggal.setText(daftarKunjunganItem.getFormattedTanggal());
    }

    @Override
    public int getItemCount() {
        return daftarKunjunganItemList.size();
    }

    @Override
    public Filter getFilter() {
        if (filter == null) {
            filter = new CustomFilter(filterListDaftarRumah, this);
        }
//        return filter;
        return null;
    }
}