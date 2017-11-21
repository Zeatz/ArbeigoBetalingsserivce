package com.example.nicolai.arbeigobetalingsserivce;


public class Firma {


    String opgaveID;
    String valgteOpgave;
    String valgteFirma;

    public Firma(){

    }

    public Firma(String opgaveID, String valgteOpgave, String valgteFirma) {
        this.opgaveID = opgaveID;
        this.valgteOpgave = valgteOpgave;
        this.valgteFirma = valgteFirma;
    }

    public String getOpgaveID() {
        return opgaveID;
    }

    public String getValgteOpgave() {
        return valgteOpgave;
    }

    public String getValgteFirma() {
        return valgteFirma;
    }
}
