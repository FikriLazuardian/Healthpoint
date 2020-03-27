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
import com.healthpoint.medic.model.KunjunganItem;

import java.util.List;

public class AdapterKunjungan extends RecyclerView.Adapter<MyHolder> implements Filterable {

    public List<KunjunganItem> kunjunganItemList;
    List<KunjunganItem> filterList1;
    Context mContext;
    CustomFilter filter;

    public AdapterKunjungan(Context context, List<KunjunganItem> kunjunganItemsList) {
        this.mContext = context;
        this.kunjunganItemList = kunjunganItemsList;
        this.filterList1 = kunjunganItemsList;
    }
    @NonNull
    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rowdaftarkunjungan, parent, false);
        MyHolder holder = new MyHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        final KunjunganItem kunjunganItem = kunjunganItemList.get(position);
    }

    @Override
    public int getItemCount() {
        return kunjunganItemList.size();
    }

    @Override
    public Filter getFilter() {
        if (filter == null) {
            filter = new CustomFilter(filterList1, this);
        }
        return filter;
    }
}
