package com.gangoffour2.monopoly.stati.partita;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.gangoffour2.monopoly.azioni.casella.*;
import com.gangoffour2.monopoly.azioni.giocatore.*;
import com.gangoffour2.monopoly.azioni.giocatore.PagaAffittoAzione;
import com.gangoffour2.monopoly.model.Partita;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;


@Data
@SuperBuilder
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "type")
public abstract class StatoPartita implements Serializable {
    @JsonIgnore
    Partita partita;

    @JsonProperty("type")
    public String getTipo(){
        return getClass().getSimpleName();
    }

    protected StatoPartita(){ }

    /**
     * Metodo che deve essere eseguito se viene rilevato un timeout nell'attesa dell'input
     */


    public void onTimeout() { }
    /**
     * I metodi onAzioneCasella vengono chiamati a partire dagli
     * stati delle caselle per capire che azione è necessaria nella partita, se necessaria.
     */

    public void onAzioneCasella(ArrestaGiocatore arrestaGiocatore){
    }

    public void onAzioneCasella(PagaAffittoAzione pagaAffittoAzione){
        throw new UnsupportedOperationException();
    }

    public void onAzioneCasella(PescaImprevisto pescaImprevisto){
    }

    public void onAzioneCasella(RichiediAcquisto richiediAcquisto){
    }

    public void onAzioneCasella(AttesaLancioDadi attesaLancioDadi) {
    }

    public void onAzioneCasella(PassaggioVuoto passaggioVuoto){
        partita.turnoStandard();
    }

    public void onAzioneCasella(PagaAffitto pagaAffitto){
    }

    public void onAzioneCasella(AttesaPrigione attesaPrigione) {
    }

    public void onAzioneCasella(ModificaDenaro modificaDenaro) {
    }

    public void onAzioneCasella(PescaProbabilita pescaProbabilita) {
    }

    /**
     * I metodi esegui vengono chiamati dalla partita sulla casella: ad esempio, prima di attendere il lancio dei dadi,
     * sarà necessario un cambio di stato attraverso onAzioneCasella; poi, il nuovo stato si potrà effettivamente
     * mettere in attesa del lancio dei dadi.
     */
    public void esegui(AzioneCasella azione){ }

    public void esegui(ArrestaGiocatore arrestaGiocatore){ }

    public void esegui(PagaAffitto pagaAffitto){ }

    public void esegui(PescaImprevisto pescaImprevisto){ }

    public void esegui(RichiediAcquisto attesaAcquisto){ }

    public void esegui(AttesaLancioDadi attesaLancioDadi){ }

    public void esegui(AstaTerminata astaTerminata){ }


    /**
     * Di default, gli eventi arrivati dall'esterno non sbloccano la partita.
     * Ogni stato ridefinirà il suo comportamento in base al tipo dell'azione.
     */
    public boolean onAzioneGiocatore(AcquistaProprieta acquistaProprieta) {
        return false;
    }

    public boolean onAzioneGiocatore(VendiProprieta vendiProprieta) {
        return false;
    }

    public  boolean onAzioneGiocatore(Ipoteca ipoteca) {
        return false;
    }

    public boolean onAzioneGiocatore(UpgradaTerreno upgradaTerreno) {
        return false;
    }

    public boolean onAzioneGiocatore(DowngradaTerreno downgradaTerreno) {
        return false;
    }

    public boolean onAzioneGiocatore(LanciaDadi lanciaDadi) {
        return false;
    }

    public boolean onAzioneGiocatore(Paga paga) {
        return false;
    }

    public boolean onAzioneGiocatore(EntraInPartita entraInPartita) {
        return false;
    }

    public boolean onAzioneGiocatore(RimuoviIpoteca rimuoviIpoteca) {
        return false;
    }

    public boolean onAzioneGiocatore(Offerta offerta) {
        return false;
    }

    public boolean onAzioneGiocatore(PagaAffittoAzione pagaAffittoAzione) {
        return false;
    }

    public boolean onAzioneGiocatore(AstaTerminata astaTerminata) { return false;}
}
