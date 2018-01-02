package com.example.nicolai.arbeigobetalingsserivce;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.math.BigDecimal;
import java.util.ArrayList;

import dk.danskebank.mobilepay.sdk.Country;
import dk.danskebank.mobilepay.sdk.MobilePay;
import dk.danskebank.mobilepay.sdk.ResultCallback;
import dk.danskebank.mobilepay.sdk.model.FailureResult;
import dk.danskebank.mobilepay.sdk.model.Payment;
import dk.danskebank.mobilepay.sdk.model.SuccessResult;

public class opgaveInfoActivity extends AppCompatActivity {

    //Firebase
    DatabaseReference mDatabase;

    //Android Layout
    Button btnTilføj;
    EditText editTextMaterialer, getEditTextKroner;
    ListView listView;
    TextView opgaveNavn;

    //Mobile pay
    int MOBILEPAY_PAYMENT_REQUEST_CODE = 1337;

    //Array
    ArrayList<String> arrayList = new ArrayList<>();
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opgave_info);

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Materialer");

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayList);

        MobilePay.getInstance().init("APPDK0000000000", Country.DENMARK);

        getEditTextKroner = (EditText) findViewById(R.id.textViewKroner);
        opgaveNavn = (TextView) findViewById(R.id.opgaveNavn);
        opgaveNavn.setTypeface(Typeface.SANS_SERIF);
        btnTilføj = (Button) findViewById(R.id.TilføjMaterialer);
        editTextMaterialer = (EditText) findViewById(R.id.editTextMaterialer);
        listView = (ListView) findViewById(R.id.listView);

        listView.setAdapter(adapter);

        btnTilføj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mDatabase.push().setValue(editTextMaterialer.getText().toString().trim());

                Toast.makeText(getApplicationContext(), "Materiale Tilføjet", Toast.LENGTH_LONG).show();
            }

        });

        mDatabase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                String string = dataSnapshot.getValue(String.class);

                arrayList.add(string);

                adapter.notifyDataSetChanged();

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



    }


    public void onClickReturnToMineOpgaver(View view){
        Intent intent = new Intent(opgaveInfoActivity.this, AktiveOpgaverActivity.class);
        startActivity(intent);
    }

    public void onClickBetal(View view){
        String no = getEditTextKroner.getText().toString();
        int kronerView = Integer.parseInt(no);

        // Checker hvis mobilePay er installeret på enheden
        boolean isMobilePayInstalled = MobilePay.getInstance().isMobilePayInstalled(getApplicationContext());

        if (isMobilePayInstalled) {
            // MobilePay er på systemet, lav et betalingsobject.
            Payment payment = new Payment();
            payment.setProductPrice(new BigDecimal(kronerView));
            payment.setOrderId("86715c57-8840-4a6f-af5f-07ee89107ece");

            // Laver betalings Intent, ved at bruge objectet lavet ovenover.
            Intent paymentIntent = MobilePay.getInstance().createPaymentIntent(payment);

            // Starter mobilepay for at gøre transaktionen færdig. Ved brug af en uniq resultcode, man selv laver.
            startActivityForResult(paymentIntent, MOBILEPAY_PAYMENT_REQUEST_CODE);
        } else {
            // MobilePay er ikke installeret. Burger SDK til at lave en intent til google play for at downloade mobilepay.
            Intent intent = MobilePay.getInstance().createDownloadMobilePayIntent(getApplicationContext());
            startActivity(intent);
        }
    }

      @Override
     public void onActivityResult(int requestCode, int resultCode, Intent data) {
       super.onActivityResult(requestCode, resultCode, data);
     if (requestCode == MOBILEPAY_PAYMENT_REQUEST_CODE) {
     //The request code matches our MobilePay Intent
       MobilePay.getInstance().handleResult(resultCode, data, new ResultCallback() {
         @Override
         public void onSuccess(SuccessResult result) {
     //The payment succeeded - you can deliver the product.
     }
    @Override
    public void onFailure(FailureResult result) {
     //The payment failed - show an appropriate error message to the user. Consult the MobilePay class documentation for possible error codes.

    }
    @Override
    public void onCancel() {
     //The payment was cancelled.
     }
    });
      }
    }
}
