package com.example.nicolai.arbeigobetalingsserivce;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class  MenuActivity extends AppCompatActivity {


    public BluetoothAdapter BTAdapter;

    final static int REQUEST_ENABLE_BT=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        //BTAdapter = BluetoothAdapter.getDefaultAdapter();
        //  if (BTAdapter == null) {
        // new AlertDialog.Builder(this)
        //   .setTitle("Not compatible")
        //  .setMessage("Your phone does not support Bluetooth")
        //  .setPositiveButton("Exit", new DialogInterface.OnClickListener()
        //  {public void onClick(DialogInterface dialog, int which) {
        //       System.exit(0);
        //  }
        //    }).setIcon(android.R.drawable.ic_dialog_alert)
        //     .show();
        // }
        // if (!BTAdapter.isEnabled()) {
        //  Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
        //  startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        //  }

        // if (!isConnected(MenuActivity.this)) buildDialog(MenuActivity.this).show();
        // else{
        //
        // }
    }

    public void onClickMineOpgaver(View view){
        Intent intent = new Intent(MenuActivity.this, mineOpgaverActivity.class);
        startActivity(intent);
    }

    public boolean isConnected(Context context) {

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netinfo = cm.getActiveNetworkInfo();

        if (netinfo != null && netinfo.isConnectedOrConnecting()) {
            android.net.NetworkInfo wifi = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            android.net.NetworkInfo mobile = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

            if((mobile != null && mobile.isConnectedOrConnecting()) || (wifi != null && wifi.isConnectedOrConnecting())) return true;
            else return false;
        } else
            return false;
    }

    public AlertDialog.Builder buildDialog(Context c) {

        AlertDialog.Builder builder = new AlertDialog.Builder(c);
        builder.setTitle("No Internet Connection");
        builder.setMessage("You need to have Mobile Data or wifi to access this. Press ok to Exit");

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                finish();
            }
        });

        return builder;
    }
}
