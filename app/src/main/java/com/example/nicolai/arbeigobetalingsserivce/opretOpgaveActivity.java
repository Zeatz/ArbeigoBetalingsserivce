package com.example.nicolai.arbeigobetalingsserivce;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class opretOpgaveActivity extends AppCompatActivity {


    Button buttonBestil;
    Spinner spinnerOpgave, spinnerFirma;
    EditText beskrivelse;

    DatabaseReference databaseArbeigo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opret_opgave);

        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        databaseArbeigo = FirebaseDatabase.getInstance().getReference("opgave");


        buttonBestil = (Button) findViewById(R.id.buttonTilføjFirma);
        spinnerOpgave = (Spinner) findViewById(R.id.spinnerOpgaver);
        spinnerFirma = (Spinner) findViewById(R.id.spinnerFirma);
        beskrivelse = (EditText) findViewById(R.id.beskrivelse);

        buttonBestil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            tilføjOpgave();
            }
        });
    }

    public void onClickReturnToMineOpgaver(View view){
        Intent intent = new Intent(opretOpgaveActivity.this, mineOpgaverActivity.class);
        startActivity(intent);
    }

    private void tilføjOpgave(){
        String opgaveBeskrivelse = beskrivelse.getText().toString().trim();
        String opgaver = spinnerOpgave.getSelectedItem().toString();
        String firma = spinnerFirma.getSelectedItem().toString();
        String id = databaseArbeigo.push().getKey();

        Firma opgave1 = new Firma(id, opgaver,firma, opgaveBeskrivelse);

        databaseArbeigo.child(id).setValue(opgave1);

        Toast.makeText(this, "Opgave tilføjet", Toast.LENGTH_LONG).show();
    }

}
