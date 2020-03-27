package com.healthpoint.medic.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.healthpoint.medic.R;
import com.healthpoint.medic.listeners.FragmentNavigationListener;
import com.healthpoint.medic.model.JenisRumah;
import com.healthpoint.medic.model.ResponseJenisRumah;
import com.healthpoint.medic.network.APIClient;
import com.healthpoint.medic.network.APIInterface;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class PengamatanVisitFragment extends Fragment implements View.OnClickListener {

    private Button btnLanjut;
    private Spinner spinJenisRumah;
    private EditText etNdiperiksa, etNjentik, etTglVisit;
    private FragmentNavigationListener mListener;
    private Bundle mBundle;
    String textWadahDiperiksa,textAdaJentik;

    private Context mContext;
    private APIInterface service;

    public PengamatanVisitFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        service = APIClient.createService(APIInterface.class);

        mContext = this.getActivity();
        mBundle = getArguments();
    }

    @Override
    public void onViewCreated (View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onClick (View v) {
        int nDiperiksa = Integer.parseInt(etNdiperiksa.getText().toString());
        int nJentik = Integer.parseInt(etNjentik.getText().toString());
        if(nJentik > nDiperiksa)
            Toast.makeText(mContext, "Jumlah jentik harus kurang dari jumlah wadah yang diperiksa!", Toast.LENGTH_SHORT).show();
        else {
            int idJenisRumah = ((JenisRumah) spinJenisRumah.getSelectedItem()).getIdJenisRumah();
            mBundle.putString("n_wadah", etNdiperiksa.getText().toString());
            mBundle.putString("n_wadah_jentik", etNjentik.getText().toString());
            mBundle.putInt("id_jenis_rumah", idJenisRumah);

            mListener.FragmentOpenListener(1);
        }
    }

    public Bundle getData() {
        return mBundle;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_pengamatan_visit, container, false);

        etNdiperiksa = (EditText) view.findViewById((R.id.etNdiperiksa));
        if (etNdiperiksa.getText().toString().length() == 0){
            etNdiperiksa.setError("Wadah Diperiksa Harus Diisi");
        }
        etNjentik = (EditText) view.findViewById((R.id.etNjentik));
        if (etNjentik.getText().toString().length() == 0){
            etNjentik.setError("Jentik Harus Diisi");
        }
        etTglVisit = (EditText) view.findViewById((R.id.etTglVisit));
//            etTglVisit.setText(getTodayDate());
        etTglVisit.setText(mBundle.getString("tanggal_senin"));
        spinJenisRumah = (Spinner) view.findViewById(R.id.spinJenisRumah);

        btnLanjut = (Button) view.findViewById(R.id.btnLanjut);
        btnLanjut.setOnClickListener(this);

        loadSpinner();

        etNjentik.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {}

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                int nDiperiksa = Integer.parseInt(etNdiperiksa.getText().toString());
                if (s.length() != 0) {
                    int nJentik = Integer.parseInt(s.toString());
                    if (nJentik > nDiperiksa)
                        Toast.makeText(mContext, "Jumlah jentik harus kurang dari jumlah wadah yang diperiksa!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

    private void loadSpinner() {
        service.getDaftarJenisRumah().enqueue(new Callback<ResponseJenisRumah>() {
            @Override
            public void onResponse(Call<ResponseJenisRumah> call, Response<ResponseJenisRumah> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        List<JenisRumah> daftar = response.body().getDaftarJenisRumah();

                        ArrayAdapter<JenisRumah> arrayAdapter = new ArrayAdapter<JenisRumah>(mContext, android.R.layout.simple_spinner_item, daftar);
                        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinJenisRumah.setAdapter(arrayAdapter);
                    } else {
                        Log.i("onEmptyResponse", "Returned empty response");
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseJenisRumah> call, Throwable t) {
                t.getStackTrace();
            }
        });
    }

    public void setFragmentNavigationListener (FragmentNavigationListener fragmentNavigationListener) {
        this.mListener = fragmentNavigationListener;
    }

    private String getTodayDate() {
        Calendar calendar = Calendar.getInstance();;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = dateFormat.format(calendar.getTime());

        return date;
    }
    }

