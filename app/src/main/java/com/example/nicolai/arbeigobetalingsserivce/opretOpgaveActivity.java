package com.example.nicolai.arbeigobetalingsserivce;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class opretOpgaveActivity extends AppCompatActivity {


    Button buttonBestil;
    Spinner spinnerOpgave, spinnerFirma;

    DatabaseReference databaseArbeigo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opret_opgave);

        databaseArbeigo = FirebaseDatabase.getInstance().getReference("opgave");

        buttonBestil = (Button) findViewById(R.id.buttonTilføjFirma);
        spinnerOpgave = (Spinner) findViewById(R.id.spinnerOpgaver);
        spinnerFirma = (Spinner) findViewById(R.id.spinnerFirma);

        buttonBestil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            tilføjOpgave();
            }
        });
    }

    private void tilføjOpgave(){
        String opgaver = spinnerOpgave.getSelectedItem().toString();
        String firma = spinnerFirma.getSelectedItem().toString();
        String id = databaseArbeigo.push().getKey();

        Firma opgave1 = new Firma(id, opgaver,firma);

        databaseArbeigo.child(id).setValue(opgave1);

        Toast.makeText(this, "Opgave tilføjet", Toast.LENGTH_LONG).show();
    }

}
