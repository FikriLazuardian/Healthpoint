package com.healthpoint.medic.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.healthpoint.medic.R;
import com.healthpoint.medic.listeners.FragmentNavigationListener;
import com.healthpoint.medic.model.Kelurahan;
import com.healthpoint.medic.model.ResponseKelurahan;
import com.healthpoint.medic.network.APIClient;
import com.healthpoint.medic.network.APIInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class LokasiVisitFragment extends Fragment implements View.OnClickListener {

    private Context mContext;

    private Button btnNext;
    private Spinner spinKelurahan, spinKecamatan, spinKota, spinNegara;
    private EditText etAlamat, etRT, etRW, etLat, etLon;
    private APIInterface service;
    private FragmentNavigationListener mListener;

    private Bundle mBundle;

    public LokasiVisitFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
        mContext = this.getActivity();
        service = APIClient.createService(APIInterface.class);
    }

    @Override
    public void onViewCreated (View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btnNext = (Button) view.findViewById(R.id.btnLanjut);
        btnNext.setOnClickListener(this);
    }

    @Override
    public void onClick (View v) {
        mBundle.putString("alamat", etAlamat.getText().toString());
        mBundle.putString("rt", etRT.getText().toString());
        mBundle.putString("rw", etRW.getText().toString());
        String idKel = ((Kelurahan) spinKelurahan.getSelectedItem()).getIdKelurahan();
        mBundle.putString("id_kelurahan", idKel);

        mListener.FragmentOpenListener(1);
    }

    public Bundle getData() {
        return mBundle;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lokasi_visit, container, false);
        etLat = (EditText) view.findViewById((R.id.etLat));
        etLon = (EditText) view.findViewById((R.id.etLon));
        etAlamat = (EditText) view.findViewById((R.id.etAlamat));
        etRT = (EditText) view.findViewById((R.id.etRT));
        etRW = (EditText) view.findViewById((R.id.etRW));
        spinKelurahan = (Spinner) view.findViewById(R.id.spinKelurahan);
        spinKecamatan = (Spinner) view.findViewById(R.id.spinKecamatan);
        spinKota = (Spinner) view.findViewById(R.id.spinKota);
        spinNegara = (Spinner) view.findViewById(R.id.spinNegara);

        try {
            mBundle = getArguments();
            if (mBundle != null) {
                etLat.setText(mBundle.getString("lat"));
                etLon.setText(mBundle.getString("lon"));
            } else {
                etLat.setText("-");
                etLon.setText("-");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        loadSpinner();

        return view;
    }

    private void loadSpinner() {
        service.getDaftarKeluByKeca("3173030").enqueue(new Callback<ResponseKelurahan>() {
            @Override
            public void onResponse(Call<ResponseKelurahan> call, Response<ResponseKelurahan> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        List<Kelurahan> daftar = response.body().getDaftarKelurahan();

                        ArrayAdapter<Kelurahan> arrayAdapter = new ArrayAdapter<Kelurahan>(mContext, android.R.layout.simple_spinner_item, daftar);
                        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinKelurahan.setAdapter(arrayAdapter);
                    } else {
                        Log.i("onEmptyResponse", "Returned empty response");
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseKelurahan> call, Throwable t) {
                t.getStackTrace();
            }
        });

        // data kecamatan
        ArrayList<String> kec = new ArrayList<String>();
        ArrayAdapter<String> adapterKec = new ArrayAdapter<String>(mContext, android.R.layout.simple_list_item_1, kec);
        kec.add("Senen");
        spinKecamatan.setAdapter(adapterKec);

        // data kota
        ArrayList<String> kota = new ArrayList<String>();
        ArrayAdapter<String> adapterKota = new ArrayAdapter<String>(mContext, android.R.layout.simple_list_item_1, kota);
        kota.add("Jakarta Pusat");
        spinKota.setAdapter(adapterKota);

        // data Negara
        ArrayList<String> negara = new ArrayList<String>();
        ArrayAdapter<String> adapterNegara = new ArrayAdapter<String>(mContext, android.R.layout.simple_list_item_1, negara);
        negara.add("Indonesia");
        spinNegara.setAdapter(adapterNegara);
    }

    public void setFragmentNavigationListener (FragmentNavigationListener fragmentNavigationListener) {
        this.mListener = fragmentNavigationListener;
    }
}
