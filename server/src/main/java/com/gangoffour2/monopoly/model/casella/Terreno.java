package com.gangoffour2.monopoly.model.casella;

import com.gangoffour2.monopoly.azioni.giocatore.AcquistaProprieta;
import com.gangoffour2.monopoly.azioni.giocatore.Ipoteca;
import com.gangoffour2.monopoly.stati.casella.EventoTerreno;
import lombok.Data;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;



@Data
@SuperBuilder
public class Terreno extends Proprieta {
    private ArrayList<Integer> affitti;
    private Colore colore;
    private int costoCasa;
    private int costoAlbergo;
    @Getter
    private EventoTerreno statoCorrente;

    public enum Colore{
        ROSSO,
        BLU,
        AZZURRO,
        GIALLO,
        ARANCIONE,
        MARRONE,
        VIOLA,
        VERDE
    }

    protected Terreno(){
    }

    @Override
    public void arrivo() {
        statoCorrente.arrivo();
    }

    @Override
    public void passaggio() {
        statoCorrente.passaggio();
    }

    @Override
    public void fineGiro() {
        statoCorrente.fineGiro();
    }

    @Override
    public void onAzioneGiocatore(AcquistaProprieta acquistaProprieta){
        statoCorrente.onAzioneGiocatore(acquistaProprieta);
    }


    @Override
    public void onAzioneGiocatore(Ipoteca ipoteca){
        statoCorrente.onAzioneGiocatore(ipoteca);
    }

    public void setState(EventoTerreno nuovoStato){
        statoCorrente = nuovoStato;
    }
}
