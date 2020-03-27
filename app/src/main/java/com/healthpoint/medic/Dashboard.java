package com.healthpoint.medic;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.healthpoint.medic.network.APIClient;
import com.healthpoint.medic.network.APIInterface;
import com.healthpoint.medic.tokenmanager.TokenManager;

public class Dashboard extends AppCompatActivity {
    private Context mContext;
    private Bundle mBundle;
    private TokenManager tokenManager;
    private APIInterface service;

    private RelativeLayout menu1, menu2, menuJumantik, menuKaderSuper;
    private TextView tvUsername;
    private int pengguna;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

       super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_dashboard);

       mContext = this;
       tokenManager = TokenManager.getInstance(getSharedPreferences("prefs", MODE_PRIVATE));
       service = APIClient.createService(APIInterface.class);
       mBundle = getIntent().getExtras();

       pengguna = mBundle.getInt("id_pengguna");
       if (tokenManager.getToken() == null) {
           startActivity(new Intent(Dashboard.this, Login.class));
           finish();
       }

        // Find the toolbar view inside the activity layout
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        TextView toolbarText = (TextView) findViewById(R.id.toolbar_title);
        toolbarText.setText(getTitle());
        // Sets the Toolbar to act as the ActionBar for this Activity window.
        // Make sure the toolbar exists in the activity and is not null

        setSupportActionBar(toolbar);
        if (toolbar != null){
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        menu1 = (RelativeLayout) findViewById(R.id.menu1);
        menu2 = (RelativeLayout) findViewById(R.id.menu2);
        menuJumantik = (RelativeLayout) findViewById(R.id.menuJumantik);
        menuKaderSuper = (RelativeLayout) findViewById(R.id.menuKaderSuper);
        menu1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent angklrg = new Intent(mContext, DaftarKeluarga.class);
                startActivity(angklrg);

            }
        });
        menu2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent klrg = new Intent(mContext, TambahVisitJumantik.class);
                startActivity(klrg);

            }
        });
        menuJumantik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent jumantik = new Intent(mContext, HomepageJumantik.class);
                jumantik.putExtras(mBundle);
                startActivity(jumantik);

            }
        });
        menuKaderSuper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent jemantik = new Intent(mContext, UploadFoto.class);
                startActivity(jemantik);

            }
        });

        tvUsername = (TextView) findViewById(R.id.tvUsername);
        tvUsername.setText(mBundle.getString("username"));
   }

    // Menu icons are inflated just as they were with actionbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_utama, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.profile:
                Intent profileIntent = new Intent(this,UbahProfile.class);
                profileIntent.putExtras(mBundle);
                startActivity(profileIntent);
                finish();
                return true;

        }
        return false;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setMessage("Apakah kamu ingin keluar?");
        builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //if user pressed "ya", then he is allowed to exit from application
                finish();
                System.exit(0);
            }
        });
        builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //if user select "Tidak", just cancel this dialog and continue with app
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }
}
