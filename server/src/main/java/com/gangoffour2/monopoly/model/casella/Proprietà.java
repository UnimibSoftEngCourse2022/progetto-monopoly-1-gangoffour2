package com.gangoffour2.monopoly.model.casella;

import com.gangoffour2.monopoly.model.Asta;
import com.gangoffour2.monopoly.model.Giocatore;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public abstract class Proprietà extends Casella {
    private int costoBase;
    private int ipoteca;
    private Giocatore proprietario;

    public Proprietà(String nome) {
        super(nome);
    }

    public void rimuoviIpoteca(){

    }
}
