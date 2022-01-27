package com.gangoffour2.monopoly.stati.partita;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.gangoffour2.monopoly.azioni.casella.*;
import com.gangoffour2.monopoly.azioni.giocatore.*;
import com.gangoffour2.monopoly.model.IPartita;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Data
@SuperBuilder
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "type")
public abstract class StatoPartita implements Serializable {
    @JsonIgnore
    IPartita partita;

    protected StatoPartita() {
    }

    @JsonProperty("type")
    public String getTipo() {
        return getClass().getSimpleName();
    }

    /**
     * Metodo che deve essere eseguito se viene rilevato un timeout nell'attesa dell'input
     */


    public void onTimeout() {
    }

    /**
     * I metodi onAzioneCasella vengono chiamati a partire dagli
     * stati delle caselle per capire che azione è necessaria nella partita, se necessaria.
     */

    public void onAzioneCasella(ArrestaGiocatore arrestaGiocatore) {
    }

    public void onAzioneCasella(PagaAffittoAzione pagaAffittoAzione) {
        throw new UnsupportedOperationException();
    }

    public void onAzioneCasella(PescaImprevisto pescaImprevisto) {
    }

    public void onAzioneCasella(RichiediAcquisto richiediAcquisto) {
    }

    public void onAzioneCasella(AttesaLancioDadi attesaLancioDadi) {
    }

    /**
     * @param passaggioVuoto Usato per fare overloading tramite polimorfismo
     */
    public void onAzioneCasella(PassaggioVuoto passaggioVuoto) {
        partita.turnoStandard();
    }

    public void onAzioneCasella(PagaAffitto pagaAffitto) {
    }

    public void onAzioneCasella(AttesaPrigione attesaPrigione) {
    }

    public void onAzioneCasella(AggiungiDenaro modificaDenaro) {
    }

    public void onAzioneCasella(PescaProbabilita pescaProbabilita) {
    }

    public void onAzioneCasella(VaiInAttesaPrigione checkPrigione) {
    }

    public void onAzioneCasella(AvviaAsta avviaAsta) {
    }

    /**
     * I metodi esegui vengono chiamati dalla partita sulla casella: ad esempio, prima di attendere il lancio dei dadi,
     * sarà necessario un cambio di stato attraverso onAzioneCasella; poi, il nuovo stato si potrà effettivamente
     * mettere in attesa del lancio dei dadi.
     */

    public void esegui(){

    }


    /**
     * Di default, gli eventi arrivati dall'esterno non sbloccano la partita.
     * Ogni stato ridefinirà il suo comportamento in base al tipo dell'azione.
     */
    public void onAzioneGiocatore(AcquistaProprieta acquistaProprieta) {

    }

    public void onAzioneGiocatore(VendiProprieta vendiProprieta) {

    }

    public void onAzioneGiocatore(Ipoteca ipoteca) {

    }

    public void onAzioneGiocatore(UpgradaTerreno upgradaTerreno) {

    }

    public void onAzioneGiocatore(DowngradaTerreno downgradaTerreno) {

    }

    public void onAzioneGiocatore(LanciaDadi lanciaDadi) {

    }

    public void onAzioneGiocatore(Paga paga) {

    }

    public void onAzioneGiocatore(EntraInPartita entraInPartita) {

    }

    public void onAzioneGiocatore(RimuoviIpoteca rimuoviIpoteca) {

    }

    public void onAzioneGiocatore(Offerta offerta) {

    }

    public void onAzioneGiocatore(PagaAffittoAzione pagaAffittoAzione) {

    }

    public void onAzioneGiocatore(AstaTerminata astaTerminata) {

    }


    public void onAzioneGiocatore(AvviaAsta avviaAsta) {

    }

    public void onAzioneGiocatore(TerminaTurno terminaTurno) {

    }
}
