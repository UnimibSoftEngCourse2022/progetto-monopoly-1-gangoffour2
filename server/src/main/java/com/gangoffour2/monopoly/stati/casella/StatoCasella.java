package com.gangoffour2.monopoly.stati.casella;


import com.gangoffour2.monopoly.azioni.casella.AzioneCasella;
import com.gangoffour2.monopoly.azioni.casella.PassaggioVuoto;
import com.gangoffour2.monopoly.azioni.giocatore.*;

import java.io.Serializable;

public interface StatoCasella extends Serializable {

    default void arrivo() {

    }

    default AzioneCasella passaggio() {
        return PassaggioVuoto.builder().build();
    }

    default void fineGiro() {

    }

    default void onAzioneGiocatore(Ipoteca ipoteca) {

    }

    default void onAzioneGiocatore(AcquistaProprieta acquistaProprieta) {

    }

    default void onAzioneGiocatore(UpgradaTerreno upgradaTerreno) {

    }

    default void onAzioneGiocatore(DowngradaTerreno downgradaTerreno) {

    }

    default void onAzioneGiocatore(VendiProprieta vendiProprieta) {

    }

    default void onAzioneGiocatore(LanciaDadi lanciaDadi) {

    }

    default void onAzioneGiocatore(Offerta offerta) {

    }

    default void onAzioneGiocatore(Paga paga) {

    }

    default void onAzioneGiocatore(EntraInPartita entraInPartita) {

    }

    default void onAzioneGiocatore(RimuoviIpoteca rimuoviIpoteca) {

    }

    default void onAzioneGiocatore(PagaAffittoAzione pagaAffittoAzione) {

    }

    default void onAzioneGiocatore(AstaTerminata astaTerminata) {

    }

    default void onAzioneGiocatore(AvviaAsta avviaAsta) {

    }

    public void onAzioneGiocatore(AvviaAsta avviaAsta) {

    }

    public void onAzioneGiocatore(TerminaTurno terminaTurno) {

    }
}
