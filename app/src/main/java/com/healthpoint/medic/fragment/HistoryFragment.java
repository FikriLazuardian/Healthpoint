package com.healthpoint.medic.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import com.healthpoint.medic.R;

import java.util.ArrayList;
import java.util.List;


public class HistoryFragment extends Fragment implements SearchView.OnQueryTextListener{

    List<String> allValues;
    ArrayAdapter<String> adapter;
    Context mContext;
    ListView listView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();

    }

    @Override
    public void onDetach(){super.onDetach();}
   @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ((AppCompatActivity) getActivity()).getSupportActionBar().show();
       View rootView = inflater.inflate(R.layout.fragment_history, container, false);
       listView = (ListView) rootView.findViewById(R.id.listv);
       TextView emptyTV = rootView.findViewById(R.id.empty);
       listView.setEmptyView(emptyTV);
       SearchView searchView = rootView.findViewById(R.id.searchv);
       searchView.setOnQueryTextListener(this);
       searchView.setQueryHint("Cari");
       getActivity().setTitle("History");
        return rootView;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        if(newText == null || newText.trim().isEmpty()){
            resetSearch();
            return false;
        }
        List<String> filteredValues = new ArrayList<>(allValues);
        for (String value : allValues){
            if(!value.toLowerCase().contains(newText.toLowerCase())){
                filteredValues.remove(value);
            }
        }
        adapter = new ArrayAdapter<>(mContext,android.R.layout.simple_list_item_1,filteredValues);
        listView.setAdapter(adapter);
        return false;

    }
    public void resetSearch(){
        adapter = new ArrayAdapter<>(mContext,android.R.layout.simple_list_item_1,allValues);
        listView.setAdapter(adapter);
    }

}
