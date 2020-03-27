package com.healthpoint.medic.fragment;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.healthpoint.medic.R;
import com.healthpoint.medic.listeners.FragmentNavigationListener;
import com.healthpoint.medic.model.ResponseFoto;
import com.healthpoint.medic.network.APIClient;
import com.healthpoint.medic.network.APIInterface;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.DexterError;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.PermissionRequestErrorListener;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class UploadFotoFragment extends Fragment implements View.OnClickListener {

    private Button takeFotoPantau, takeFotoBerantas, btnSimpan;
    private ImageView ivPantau, ivBerantas;
    private int GALLERY_P = 1, CAMERA_P = 2, GALLERY_B = 3, CAMERA_B = 4, currentRequestCode;
    private Bitmap bmpFotoPantau, bmpFotoBerantas;
    private InputStream mIS;
    private byte[] arrBytePantau;
    private byte[] arrByteBerantas;

    private APIInterface service;
    private FragmentNavigationListener mListener;
    private Bundle mBundle;

    public UploadFotoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated (View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onClick (View v) {
        mListener.FragmentOpenListener(2);
    }


    public Bundle getData() {
        return mBundle;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_upload_foto, container, false);

        service = APIClient.createService(APIInterface.class);
        currentRequestCode = 0;

        mBundle = getArguments();
        //mBundle.putByteArray("foto_pantau", null);
        //mBundle.putByteArray("foto_berantas", null);
        bmpFotoPantau = null;
        bmpFotoBerantas = null;

        requestMultiplePermissions();

        takeFotoPantau = (Button) view.findViewById(R.id.btnImagePantau);
        takeFotoBerantas = (Button) view.findViewById(R.id.btnImageBerantas);
        btnSimpan = (Button) view.findViewById(R.id.btnSimpan);
        ivPantau = (ImageView) view.findViewById(R.id.imgPantau);
        ivBerantas = (ImageView) view.findViewById(R.id.imgBerantas);

        takeFotoPantau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPictureDialog('P');
            }
        });

        takeFotoBerantas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPictureDialog('B');
            }
        });

        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bmpFotoPantau != null){
                    //mBundle.putByteArray("foto_pantau", getBytes(bmpFotoPantau));
                    arrBytePantau = getBytes(bmpFotoPantau);}
                if (bmpFotoBerantas != null) {
                    //  mBundle.putByteArray("foto_berantas", getBytes(bmpFotoBerantas));
                    arrByteBerantas = getBytes(bmpFotoBerantas);}
                mListener.FragmentOpenListener(2);
            }
        });

        return view;
    }
    public byte[] getArrBytePantau(){
        return arrBytePantau;
    }
    public byte[] getArrByteBerantas(){
        return arrByteBerantas;
    }

    public void setFragmentNavigationListener (FragmentNavigationListener fragmentNavigationListener) {
        this.mListener = fragmentNavigationListener;
    }

    private void showPictureDialog(final char jenisFoto) {
        AlertDialog.Builder pictureDialog = new AlertDialog.Builder(getActivity());
        pictureDialog.setTitle("Silahkan Pilih");
        String[] pictureDialogItems = {
                "Pilih Foto dari Camera",
                "Pilih Foto dari Gallery"
        };
        pictureDialog.setItems(pictureDialogItems, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        if (jenisFoto == 'P') // ambil foto Pantau dari Camera
                            takePhotoFromCamera(2);
                        else if (jenisFoto == 'B') // ambil foto Berantas dari Camera
                            takePhotoFromCamera(4);
                        break;
                    case 1:
                        if (jenisFoto == 'P') // ambil foto Pantau dari Camera
                            choosePhotoFromGallery(1);
                        else if (jenisFoto == 'B') // ambil foto Berantas dari Camera
                            choosePhotoFromGallery(3);
                        break;
                }
            }
        });
        pictureDialog.show();
    }

    public void choosePhotoFromGallery(int idJenisFoto) {
        //Create an Intent with action as ACTION_PICK
        Intent intent = new Intent(Intent.ACTION_PICK);
        // Sets the type as image/*. This ensures only components of type image are selected
        intent.setType("image/jpeg");
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        // Launching the Intent
        startActivityForResult(intent, idJenisFoto);
    }

    private void takePhotoFromCamera(int idJenisFoto) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, idJenisFoto);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 0) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED
                    && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                takeFotoPantau.setEnabled(true);
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_CANCELED) {
            return;
        }
        currentRequestCode = requestCode;
        if (requestCode == GALLERY_P || requestCode == GALLERY_B) {
            if (data != null) {
                Uri contentUri = data.getData();
                try {

                    if (requestCode == GALLERY_P) {
                        bmpFotoPantau = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), contentUri);
                    } else if (requestCode == GALLERY_B) {
                        bmpFotoBerantas = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), contentUri);
                    }

//                    mIS = getContentResolver().openInputStream(data.getData());
                } catch (IOException e) {
                    e.printStackTrace();
                }

                if (requestCode == GALLERY_P)
                    ivPantau.setImageURI(contentUri);
                else if (requestCode == GALLERY_B)
                    ivBerantas.setImageURI(contentUri);
            }
        } else if (requestCode == CAMERA_P || requestCode == CAMERA_B) {
            if (requestCode == CAMERA_P) {
                bmpFotoPantau = (Bitmap) data.getExtras().get("data");
                ivPantau.setImageBitmap(bmpFotoPantau);
            } else if (requestCode == CAMERA_B) {
                bmpFotoBerantas = (Bitmap) data.getExtras().get("data");
                ivBerantas.setImageBitmap(bmpFotoBerantas);
            }
        }
    }

    private void requestMultiplePermissions() {
        Dexter.withActivity(getActivity())
                .withPermissions(
                        Manifest.permission.CAMERA,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        // check if all permissions are granted
                        if (report.areAllPermissionsGranted()) {
                            Toast.makeText(getActivity(), "All permissions are granted by user!", Toast.LENGTH_SHORT).show();
                        }

                        // check for permanent denial of any permission
                        if (report.isAnyPermissionPermanentlyDenied()) {
                            // show alert dialog navigating to Settings
                            //openSettingsDialog();
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).
                withErrorListener(new PermissionRequestErrorListener() {
                    @Override
                    public void onError(DexterError error) {
                        Toast.makeText(getActivity(), "Some Error! ", Toast.LENGTH_SHORT).show();
                    }
                })
                .onSameThread()
                .check();
    }

    private byte[] getBytes(InputStream is) throws IOException {
        ByteArrayOutputStream byteBuff = new ByteArrayOutputStream();

        int buffSize = 1024;
        byte[] buff = new byte[buffSize];

        int len = 0;
        while ((len = is.read(buff)) != -1) {
            byteBuff.write(buff, 0, len);
        }

        return byteBuff.toByteArray();
    }

    private byte[] getBytes(Bitmap bmp) {
        ByteArrayOutputStream byteBuff = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, byteBuff);

        return byteBuff.toByteArray();
    }

    private void uploadImage(byte[] imageBytes) {
        RequestBody requestFile = RequestBody.create(MediaType.parse("image/jpeg"), imageBytes);

        MultipartBody.Part body = MultipartBody.Part.createFormData("path_foto", "image.jpg", requestFile);
        RequestBody description =
                RequestBody.create(
                        okhttp3.MultipartBody.FORM, "1");

        Call<ResponseFoto> call = service.uploadPhoto(description, body);
        call.enqueue(new Callback<ResponseFoto>() {
            @Override
            public void onResponse(Call<ResponseFoto> call, Response<ResponseFoto> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(getActivity(), "Upload Foto Berhasil", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseFoto> call, Throwable t) {
                Toast.makeText(getActivity(), "Upload Foto Gagal", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

