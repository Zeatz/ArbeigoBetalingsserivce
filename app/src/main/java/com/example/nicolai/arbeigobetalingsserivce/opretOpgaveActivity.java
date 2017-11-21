package com.example.nicolai.arbeigobetalingsserivce;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

public class opretOpgaveActivity extends AppCompatActivity {


    Button buttonBestil;
    Spinner spinnerOpgave, spinnerFirma;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opret_opgave);

        buttonBestil = (Button) findViewById(R.id.buttonTilføjFirma);
        spinnerOpgave = (Spinner) findViewById(R.id.spinnerOpgaver);
        spinnerFirma = (Spinner) findViewById(R.id.spinnerFirma);

        buttonBestil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    private void tilføjOpgave(){
        String
    }

}
