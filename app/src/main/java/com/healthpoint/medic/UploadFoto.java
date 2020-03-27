package com.healthpoint.medic;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

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
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UploadFoto extends AppCompatActivity {

    private Button takeFotoPantau, takeFotoBerantas, btnSimpanFoto;
    private ImageView ivPantau, ivBerantas;
    private int GALLERY = 1, CAMERA = 2, currentRequestCode;
    private APIInterface service;
    private Context mContext;
    private Bitmap mFotoPantau;
    private InputStream mIS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_foto);
        service = APIClient.createService(APIInterface.class);
        mContext = this;
        currentRequestCode = 0;

        requestMultiplePermissions();

        takeFotoPantau = (Button) findViewById(R.id.btnImagePantau);
        takeFotoBerantas = (Button) findViewById(R.id.btnImagePemberantasan);
        btnSimpanFoto = (Button) findViewById(R.id.btnSimpanFoto);
        ivPantau = (ImageView) findViewById(R.id.imgPantau);
        ivBerantas = (ImageView) findViewById(R.id.imgPemberantasan);

        takeFotoPantau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPictureDialog();
            }
        });

        takeFotoBerantas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPictureDialog();
            }
        });

        btnSimpanFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//            if (currentRequestCode == GALLERY) {
//                try {
//                    uploadImage(getBytes(mIS));
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            } else if (currentRequestCode == CAMERA) {
//                uploadImage(getBytes(mFotoPantau));
//            }
            Toast.makeText(UploadFoto.this, "Upload foto berhasil", Toast.LENGTH_SHORT).show();
            finish();
            }
        });
    }

    private void showPictureDialog() {
        AlertDialog.Builder pictureDialog = new AlertDialog.Builder(this);
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
                        takePhotoFromCamera();
                        break;
                    case 1:
                        choosePhotoFromGallery();
                        break;
                }
            }
        });
        pictureDialog.show();
    }

    public void choosePhotoFromGallery() {
        //Create an Intent with action as ACTION_PICK
        Intent intent = new Intent(Intent.ACTION_PICK);
        // Sets the type as image/*. This ensures only components of type image are selected
        intent.setType("image/jpeg");
        //We pass an extra array with the accepted mime types. This will ensure only components with these MIME types as targeted.
        String[] mimeTypes = {"image/jpeg", "image/png"};
        intent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes);
        // Launching the Intent
        startActivityForResult(intent, GALLERY);
    }

    private void takePhotoFromCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, CAMERA);
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == this.RESULT_CANCELED) {
            return;
        }
        currentRequestCode = requestCode;
        if (requestCode == GALLERY) {
            if (data != null) {
                Uri contentUri = data.getData();
                try {
                    mIS = getContentResolver().openInputStream(data.getData());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                ivPantau.setImageURI(contentUri);
            }
        } else if (requestCode == CAMERA) {
            mFotoPantau = (Bitmap) data.getExtras().get("data");
            ivPantau.setImageBitmap(mFotoPantau);
        }
    }

    private void requestMultiplePermissions() {
        Dexter.withActivity(this)
                .withPermissions(
                        Manifest.permission.CAMERA,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        // check if all permissions are granted
                        if (report.areAllPermissionsGranted()) {
                            Toast.makeText(getApplicationContext(), "All permissions are granted by user!", Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(getApplicationContext(), "Some Error! ", Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(mContext, "Upload Foto Berhasil", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseFoto> call, Throwable t) {
                Toast.makeText(mContext, "Upload Foto Gagal", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
