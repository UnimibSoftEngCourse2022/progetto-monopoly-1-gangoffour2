package com.gangoffour2.monopoly.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gangoffour2.monopoly.eccezioni.ModificaDenaroException;
import com.gangoffour2.monopoly.model.carta.Carta;
import com.gangoffour2.monopoly.model.carta.CartaEsciGratisPrigione;
import com.gangoffour2.monopoly.model.casella.Casella;
import com.gangoffour2.monopoly.model.casella.Proprieta;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Giocatore implements Serializable {
    @JsonIgnore
    private Partita partita;
    @JsonIgnore
    private String idSessione;
    private String nick;
    private int conto;
    private ArrayList<CartaEsciGratisPrigione> esciGratis;
    private Casella casellaCorrente;

    @Builder.Default
    private ArrayList<Proprieta> proprietaPossedute = new ArrayList<>();

    public void modificaDenaro(int importo) throws ModificaDenaroException{
        if(this.getConto() + importo < 0)
            throw new ModificaDenaroException();
        this.setConto(this.getConto() + importo);
    }


    public void paga(Giocatore destinatario, int importo){
        conto -= importo;
        destinatario.conto += importo;
    }

    public void acquistaProprieta(Proprieta proprieta){
        proprietaPossedute.add(proprieta);
        proprieta.setProprietario(this);
    }


    public void abbandona(){
        lasciaProprieta();
    }

    public void lasciaProprieta(){
        for (Proprieta p : proprietaPossedute){
            p.setProprietario(null);
        }
        proprietaPossedute.clear();
    }

    public void aggiungiEsciGratis(CartaEsciGratisPrigione c){
        this.getEsciGratis().add(c);
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
