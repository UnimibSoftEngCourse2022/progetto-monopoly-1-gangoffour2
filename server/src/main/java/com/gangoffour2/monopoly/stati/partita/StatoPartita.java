package com.gangoffour2.monopoly.stati.partita;

import com.gangoffour2.monopoly.azioni.casella.*;
import com.gangoffour2.monopoly.azioni.giocatore.*;
import com.gangoffour2.monopoly.model.Partita;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;


@Data
@SuperBuilder
public abstract class StatoPartita implements Serializable {
    Partita partita;

    protected StatoPartita(){
    }


    /**
     * I metodi onAzioneCasella vengono chiamati a partire dagli
     * stati delle caselle per capire che azione è necessaria nella partita, se necessaria.
     */

    public void onAzioneCasella(ArrestaGiocatore arrestaGiocatore){

    }


    public void onAzioneCasella(PagaAffitto pagaAffitto){

    }

    public void onAzioneCasella(PescaImprevisto pescaImprevisto){

    }


    public void onAzioneCasella(RichiediAcquisto richiediAcquisto){

    }

    public void onAzioneCasella(AttesaLancioDadi attesaLancioDadi) throws InterruptedException {

    }

    public void onAzioneCasella(PassaggioVuoto passaggioVuoto){
        partita.turnoStandard();
    }

    /**
     * I metodi esegui vengono chiamati dalla partita sulla casella: ad esempio, prima di attendere il lancio dei dadi,
     * sarà necessario un cambio di stato attraverso onAzioneCasella; poi, il nuovo stato si potrà effettivamente
     * mettere in attesa del lancio dei dadi.
     */
    public void esegui(AzioneCasella azione){

    }
    public void esegui(ArrestaGiocatore arrestaGiocatore){

    }

    public void esegui(PagaAffitto pagaAffitto){

    }

    public void esegui(PescaImprevisto pescaImprevisto){

    }

    public void esegui(RichiediAcquisto attesaAcquisto){

    }


    public void esegui(AttesaLancioDadi attesaLancioDadi){

    }

    /**
     * Di default, gli eventi arrivati dall'esterno non sbloccano la partita.
     * Ogni stato ridefinirà il suo comportamento in base al tipo dell'azione.
     */
    public boolean onAzioneGiocatore(AcquistaProprieta acquistaProprieta) throws InterruptedException {
        return false;
    }

    public boolean onAzioneGiocatore(VendiProprieta vendiProprieta) throws InterruptedException {
        return false;
    }

    public  boolean onAzioneGiocatore(Ipoteca ipoteca) throws InterruptedException {
        return false;
    }

    public boolean onAzioneGiocatore(UpgradaTerreno upgradaTerreno) throws InterruptedException {
        return false;
    }
    public boolean onAzioneGiocatore(DowngradaTerreno downgradaTerreno) throws InterruptedException {
        return false;
    }

    public boolean onAzioneGiocatore(LanciaDadi lanciaDadi) throws InterruptedException {
        return false;
    }

    public void onAzioneCasella(Offerta offerta) throws InterruptedException {
    }

    public void onAzioneCasella(AttesaPrigione attesaPrigione) throws InterruptedException {
    }

    public boolean onAzioneGiocatore(Paga paga) throws InterruptedException {
        return false;
    }

    public boolean onAzioneGiocatore(EntraInPartita entraInPartita) throws InterruptedException {
        return false;
    }

    public boolean onAzioneGiocatore(RimuoviIpoteca rimuoviIpoteca) {
        return false;
    }

    /**
     * Metodo che deve essere eseguito se viene rilevato un timeout nell'attesa dell'input
     */
    public void onTimeout() {
    }

    public boolean onAzioneGiocatore(Offerta offerta) {
        return false;
    }


    public void onAzioneCasella(ModificaDenaro modificaDenaro) {

    }
}
