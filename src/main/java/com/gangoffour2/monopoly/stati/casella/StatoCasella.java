package com.gangoffour2.monopoly.stati.casella;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.gangoffour2.monopoly.azioni.casella.AzioneCasella;
import com.gangoffour2.monopoly.azioni.casella.PassaggioVuoto;
import com.gangoffour2.monopoly.azioni.giocatore.*;

import java.io.Serializable;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = SocietaAcquistata.class, name = "SocietaAcquistata"),
        @JsonSubTypes.Type(value = SocietaIpotecata.class, name = "SocietaIpotecata"),
        @JsonSubTypes.Type(value = SocietaNonAcquistata.class, name = "SocietaNonAcquistata"),
        @JsonSubTypes.Type(value = StatoImprevisto.class, name = "StatoImprevisto"),
        @JsonSubTypes.Type(value = StatoParcheggio.class, name = "StatoParcheggio"),
        @JsonSubTypes.Type(value = StatoPrigione.class, name = "StatoPrigione"),
        @JsonSubTypes.Type(value = StatoProbabilita.class, name = "StatoProbabilita"),
        @JsonSubTypes.Type(value = StatoTassa.class, name = "StatoTassa"),
        @JsonSubTypes.Type(value = StatoVaiInPrigione.class, name = "StatoVaiInPrigione"),
        @JsonSubTypes.Type(value = StatoVia.class, name = "StatoVia"),
        @JsonSubTypes.Type(value = StazioneAcquistata.class, name = "StazioneAcquistata"),
        @JsonSubTypes.Type(value = StazioneIpotecata.class, name = "StazioneIpotecata"),
        @JsonSubTypes.Type(value = StazioneNonAcquistata.class, name = "StazioneNonAcquistata"),
        @JsonSubTypes.Type(value = TerrenoAcquistato.class, name = "TerrenoAcquistato"),
        @JsonSubTypes.Type(value = TerrenoIpotecato.class, name = "TerrenoIpotecato"),
        @JsonSubTypes.Type(value = TerrenoNonAcquistato.class, name = "TerrenoNonAcquistato")
})
public interface StatoCasella extends Serializable {

    @JsonProperty("type")
    default String getTipo(){
        return getClass().getSimpleName();
    }

    default AzioneCasella arrivo() {
        return PassaggioVuoto.builder().build();
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

    default void onAzioneGiocatore(AvviaAsta avviaAsta) {

    }


    default void onAzioneGiocatore(TerminaTurno terminaTurno) {

    }

    default void onAzioneGiocatore(TermineAsta termineAsta){

    }
}
