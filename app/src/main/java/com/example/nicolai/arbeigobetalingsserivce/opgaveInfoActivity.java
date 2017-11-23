package com.example.nicolai.arbeigobetalingsserivce;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.math.BigDecimal;

import dk.danskebank.mobilepay.sdk.Country;
import dk.danskebank.mobilepay.sdk.MobilePay;
import dk.danskebank.mobilepay.sdk.model.Payment;

public class opgaveInfoActivity extends AppCompatActivity {

    TextView opgaveNavn;
    int MOBILEPAY_PAYMENT_REQUEST_CODE = 1337;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opgave_info);

        MobilePay.getInstance().init("APPDK0000000000", Country.DENMARK);

        opgaveNavn = (TextView) findViewById(R.id.opgaveNavn);
        opgaveNavn.setTypeface(Typeface.SANS_SERIF);

    }


    public void onClickReturnToMineOpgaver(View view){
        Intent intent = new Intent(opgaveInfoActivity.this, mineOpgaverActivity.class);
        startActivity(intent);
    }

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

    //  @Override
    // public void onActivityResult(int requestCode, int resultCode, Intent data) {
    //   super.onActivityResult(requestCode, resultCode, data);
    // if (requestCode == MOBILEPAY_PAYMENT_REQUEST_CODE) {
    // The request code matches our MobilePay Intent
    //   MobilePay.getInstance().handleResult(resultCode, data, new ResultCallback() {
    //     @Override
    //   public void onSuccess(SuccessResult result) {
    // The payment succeeded - you can deliver the product.
    // }
    //@Override
    //public void onFailure(FailureResult result) {
    // The payment failed - show an appropriate error message to the user. Consult the MobilePay class documentation for possible error codes.
    //}
    //@Override
    //public void onCancel() {
    // The payment was cancelled.
    // }
    //});
    //  }
    //}
}
