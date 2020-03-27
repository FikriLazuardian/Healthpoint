package com.healthpoint.medic.model;

import android.widget.Filter;

import com.healthpoint.medic.adapter.AdapterDaftarKeluarga;
import com.healthpoint.medic.adapter.AdapterDaftarKunjungan;
import com.healthpoint.medic.adapter.AdapterKunjungan;

import java.util.ArrayList;
import java.util.List;

public class CustomFilter extends Filter {
    AdapterDaftarKeluarga adapterDaftarKeluarga;
    AdapterDaftarKunjungan adapterDaftarKunjungan;
    AdapterKunjungan adapterKunjungan;
    List<DaftarKeluargaItem> filterList;
    List<DaftarKunjunganItem> filterListDaftarTanggal;
    List<KunjunganItem> filterList1;
    public CustomFilter(List<DaftarKeluargaItem>filterList, AdapterDaftarKeluarga adapterDaftarKeluarga){
        this.adapterDaftarKeluarga = adapterDaftarKeluarga;
        this.filterList = filterList;
    }
    public CustomFilter(List<DaftarKunjunganItem> filterListDaftarTanggal, AdapterDaftarKunjungan adapterDaftarKunjungan){
        this.adapterDaftarKunjungan = adapterDaftarKunjungan;
        this.filterListDaftarTanggal = filterListDaftarTanggal;
    }

    public CustomFilter(List<KunjunganItem> filterList1, AdapterKunjungan adapterKunjungan) {
    }

    @Override
    protected FilterResults performFiltering(CharSequence text){
        FilterResults results = new FilterResults();
        //CHECK  VALIDITY
        if(text != null && text.length() > 0)
        {
            //CHANGE TO UPPER
            text=text.toString().toUpperCase();
            //STORE OUR FILTERED PLAYERS
            List<DaftarKeluargaItem> filteredPlayers=new ArrayList<>();
            for (int i=0;i<filterList.size();i++)
            {
                //CHECK
                if(filterList.get(i).getNoKK().toUpperCase().contains(text))
                {
                    //ADD PLAYER TO FILTERED PLAYERS
                    filteredPlayers.add(filterList.get(i));
                }
            }
            results.count=filteredPlayers.size();
            results.values=filteredPlayers;
        }else
        {
            results.count=filterList.size();
            results.values=filterList;
        }
        return results;
    }
    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {
        adapterDaftarKunjungan.daftarKunjunganItemList = (List<DaftarKunjunganItem>) results.values;
        //REFRESH
        adapterDaftarKunjungan.notifyDataSetChanged();
    }
}
