package com.gangoffour2.monopoly.model.casella;

import com.fasterxml.jackson.annotation.*;
import com.gangoffour2.monopoly.azioni.casella.AttesaLancioDadi;
import com.gangoffour2.monopoly.azioni.casella.AzioneCasella;
import com.gangoffour2.monopoly.azioni.giocatore.AzioneGiocatore;
import com.gangoffour2.monopoly.model.giocatore.Giocatore;
import com.gangoffour2.monopoly.model.PartitaObserver;
import com.gangoffour2.monopoly.stati.casella.StatoCasella;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

@Data
@SuperBuilder
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Terreno.class, name = "Terreno"),
        @JsonSubTypes.Type(value = Societa.class, name = "Societa"),
        @JsonSubTypes.Type(value = Stazione.class, name = "Stazione"),
        @JsonSubTypes.Type(value = Via.class, name = "Via"),
        @JsonSubTypes.Type(value = Imprevisto.class, name = "Imprevisto"),
        @JsonSubTypes.Type(value = Probabilita.class, name = "Probabilita"),
        @JsonSubTypes.Type(value = Prigione.class, name = "Prigione"),
        @JsonSubTypes.Type(value = Parcheggio.class, name = "Parcheggio"),
        @JsonSubTypes.Type(value = Tassa.class, name = "Tassa"),
        @JsonSubTypes.Type(value = VaiInPrigione.class, name = "VaiInPrigione"),
})
public abstract class Casella implements SubjectStatoPartita, Serializable {

    protected int id;
    protected String nome;
    @Builder.Default
    @JsonIgnore
    protected ArrayList<PartitaObserver> subscribers = new ArrayList<>();
    protected StatoCasella stato;

    protected Casella() {
        subscribers = new ArrayList<>();
    }

    @JsonProperty("type")
    public String getTipo() {
        return getClass().getSimpleName();
    }

    /**
     *
     * @param giocatore può essere utilizzato dalle caselle
     *                  che hanno bisogno di conoscere il giocatore
     */
    public void arrivo(Giocatore giocatore) {
        notificaTutti(stato.arrivo());
    }

    public void passaggio() {
        notificaTutti(stato.passaggio());
    }

    public void fineGiro() {
        stato.fineGiro();
    }

    public void randomizzaCasella(float m){ }

    /**
     * Da overrideare per i comportamenti diversi
     * @param g Usato per fare overloading tramite polimorfismo
     */
    public void inizioTurno(Giocatore g) {
        notificaTutti(AttesaLancioDadi.builder().build());
    }

    @Override
    public void notificaTutti(AzioneCasella azione) {
        subscribers.forEach(subscriber -> subscriber.onAzioneCasella(azione));
    }

    @Override
    public void aggiungi(PartitaObserver observer) {
        subscribers.add(observer);
    }

    @Override
    public void rimuovi(PartitaObserver observer) {
        subscribers.remove(observer);
    }


    public void onAzioneGiocatore(AzioneGiocatore azioneGiocatore) {
        azioneGiocatore.accept(stato);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Casella casella = (Casella) o;
        return id == casella.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
