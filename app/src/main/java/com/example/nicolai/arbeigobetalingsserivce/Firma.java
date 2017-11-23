package com.example.nicolai.arbeigobetalingsserivce;


public class Firma {


    String opgaveID;
    String valgteOpgave;
    String valgteFirma;
    String opgaveBeskrivelse;

    public Firma(){

    }

    public Firma(String opgaveID, String valgteOpgave, String valgteFirma, String opgaveBeskrivelse) {
        this.opgaveID = opgaveID;
        this.valgteOpgave = valgteOpgave;
        this.valgteFirma = valgteFirma;
        this.opgaveBeskrivelse = opgaveBeskrivelse;
    }

    public String getOpgaveID() {
        return opgaveID;
    }

    public String getOpgaveBeskrivelse() {
        return opgaveBeskrivelse;
    }

    public String getValgteOpgave() {
        return valgteOpgave;
    }

    public String getValgteFirma() {
        return valgteFirma;
    }
}
