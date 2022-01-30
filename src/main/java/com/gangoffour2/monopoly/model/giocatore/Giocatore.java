package com.gangoffour2.monopoly.model.giocatore;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.gangoffour2.monopoly.eccezioni.ModificaDenaroException;
import com.gangoffour2.monopoly.model.casella.*;
import com.gangoffour2.monopoly.model.casella.strategy.PagamentoStrategiaGiocatore;
import com.gangoffour2.monopoly.model.casella.strategy.PagamentoStrategy;
import com.gangoffour2.monopoly.model.IPartita;
import com.gangoffour2.monopoly.model.carta.Carta;
import com.gangoffour2.monopoly.model.carta.CartaEsciGratisPrigione;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Giocatore.class, name = "Giocatore"),
        @JsonSubTypes.Type(value = Imprenditore.class, name = "Imprenditore"),
})
public class Giocatore implements Serializable {
    @JsonIgnore
    private IPartita partita;
    @JsonIgnore
    private String idSessione;
    private String nick;
    private int conto;

    @JsonIgnore
    @Builder.Default
    private transient PagamentoStrategy strategiaCalcoloAffitto = new PagamentoStrategiaGiocatore();

    @Builder.Default
    private Queue<Carta> esciGratis = new LinkedList<>();
    private Casella casellaCorrente;

    @Builder.Default
    private ArrayList<Proprieta> proprietaPossedute = new ArrayList<>();

    @JsonProperty("type")
    public String getTipo() {
        return getClass().getSimpleName();
    }

    public void aggiungiDenaro(int importo) throws ModificaDenaroException {
        if (this.getConto() + importo < 0)
            throw new ModificaDenaroException(-importo);
        this.setConto(this.getConto() + importo);
    }

    public Carta popCartaEsciDiPrigione(){
        return esciGratis.remove();
    }

    public void paga(Giocatore destinatario, int importo) throws ModificaDenaroException {
        this.aggiungiDenaro(-importo);
        destinatario.conto += importo;
    }

    public void acquistaProprieta(Proprieta proprieta) throws ModificaDenaroException{
        acquistaProprieta(proprieta, null, proprieta.getCostoBase());
    }

    public void acquistaProprieta(Proprieta proprieta, Giocatore venditore, int importo){
        if (venditore != null){
            venditore.aggiungiDenaro(importo);
        }
        aggiudica(proprieta, importo);
    }

    public void abbandona() {
        lasciaProprieta();
    }

    private void lasciaProprieta() {
        for (Proprieta p : proprietaPossedute) {
            p.setProprietario(null);
            p.reset();
        }
        proprietaPossedute.clear();
    }

    public void aggiungiEsciGratis(CartaEsciGratisPrigione c) {
        this.getEsciGratis().add(c);
    }

    public boolean haCartaEsciGratis() {
        return this.getEsciGratis().size() > 0;
    }

    public void aggiudica(Proprieta proprieta, int importo) throws ModificaDenaroException {
        aggiungiDenaro(-importo);
        proprietaPossedute.add(proprieta);
        proprieta.setProprietario(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Giocatore giocatore = (Giocatore) o;
        return nick.equals(giocatore.nick);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nick);
    }
}
