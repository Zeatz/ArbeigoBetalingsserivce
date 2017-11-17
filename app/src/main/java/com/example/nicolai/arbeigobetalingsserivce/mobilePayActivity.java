package com.example.nicolai.arbeigobetalingsserivce;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

import java.math.BigDecimal;

import dk.danskebank.mobilepay.sdk.Country;
import dk.danskebank.mobilepay.sdk.MobilePay;
import dk.danskebank.mobilepay.sdk.ResultCallback;
import dk.danskebank.mobilepay.sdk.model.FailureResult;
import dk.danskebank.mobilepay.sdk.model.Payment;
import dk.danskebank.mobilepay.sdk.model.SuccessResult;

public class mobilePayActivity extends AppCompatActivity {

    ToggleButton blueOnOff;
    BluetoothAdapter blueAdp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobile_pay);
        MobilePay.getInstance().init("APPDK0000000000", Country.DENMARK);

        blueOnOff = (ToggleButton) findViewById(R.id.toggleButton);
        blueAdp = BluetoothAdapter.getDefaultAdapter();

        blueOnOff.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked){
                    // Denne linje er for at aktivere bluetooth med pop op boks.
                    startActivityForResult(new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE), 0);
                    // Ellers kan vi bare skrive: blueAdp.enable, men så får vi ikke dialog boks med
                }
                else{
                    blueAdp.disable(); // denne vil disable bluetooth
                }
            }
        });
    }

    int MOBILEPAY_PAYMENT_REQUEST_CODE = 1337;


    public void onClickBetal(View view){

             // Check if the MobilePay app is installed on the device.
            boolean isMobilePayInstalled = MobilePay.getInstance().isMobilePayInstalled(getApplicationContext());

            if (isMobilePayInstalled) {
            // MobilePay is present on the system. Create a Payment object.
            Payment payment = new Payment();
            payment.setProductPrice(new BigDecimal(10.0));
            payment.setOrderId("86715c57-8840-4a6f-af5f-07ee89107ece");

            // Create a payment Intent using the Payment object from above.
            Intent paymentIntent = MobilePay.getInstance().createPaymentIntent(payment);

            // We now jump to MobilePay to complete the transaction. Start MobilePay and wait for the result using an unique result code of your choice.
            startActivityForResult(paymentIntent, MOBILEPAY_PAYMENT_REQUEST_CODE);
            } else {
            // MobilePay is not installed. Use the SDK to create an Intent to take the user to Google Play and download MobilePay.
            Intent intent = MobilePay.getInstance().createDownloadMobilePayIntent(getApplicationContext());
            startActivity(intent);
            }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == MOBILEPAY_PAYMENT_REQUEST_CODE) {
            // The request code matches our MobilePay Intent
            MobilePay.getInstance().handleResult(resultCode, data, new ResultCallback() {
                @Override
                public void onSuccess(SuccessResult result) {
                    // The payment succeeded - you can deliver the product.
                }
                @Override
                public void onFailure(FailureResult result) {
                    // The payment failed - show an appropriate error message to the user. Consult the MobilePay class documentation for possible error codes.
                }
                @Override
                public void onCancel() {
                    // The payment was cancelled.
                }
            });
        }
    }







}

