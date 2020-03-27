package com.healthpoint.medic;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;



//Class Adapter ini Digunakan Untuk Mengatur Bagaimana Data akan Ditampilkan
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    public ItemData[] itemsdata;

   public RecyclerViewAdapter(ItemData[] itemsdata){
        this.itemsdata = itemsdata;

    }
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,int viewType) {

        View itemLayoutView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_design, null);

        ViewHolder viewHolder = new ViewHolder(itemLayoutView);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {

        // - get data from your itemsData at this position
        // - replace the contents of the view with that itemsData

        viewHolder.namamenu.setText(itemsdata[position].getNamamenu());
        viewHolder.gambarmenu.setImageResource(itemsdata[position].getImagemenu());
    }

   public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView namamenu;
        public ImageView gambarmenu;
        private RelativeLayout ItemList;
        private Context context;

        ViewHolder(View itemView) {
            super(itemView);

            //Untuk Menghubungkan dan Mendapakan Context dari MainActivity
            context = itemView.getContext();
            //Menginisialisasi View-View untuk kita gunakan pada RecyclerView
            namamenu = itemView.findViewById(R.id.txtmenuset);
            gambarmenu = itemView.findViewById(R.id.imgprofile);
            ItemList = itemView.findViewById(R.id.itemlist);
            ItemList.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent();
                    switch (getAdapterPosition()){
                        case 0 :
                            intent = new Intent(context, UbahProfile.class);
                            break;
                        case 1 :
                            intent = new Intent(context, TentangAplikasi.class);
                            break;


                    }
                    context.startActivity(intent);
                }
            });
        }
    }



    public int getItemCount() {
        //Menghitung Ukuran/Jumlah Data Yang Akan Ditampilkan Pada RecyclerView
        return itemsdata.length;
    }

}