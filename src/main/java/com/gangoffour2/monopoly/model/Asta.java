package com.gangoffour2.monopoly.model;

import com.gangoffour2.monopoly.eccezioni.OffertaInvalidaException;
import com.gangoffour2.monopoly.model.casella.Proprieta;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
public class Asta implements Serializable {
    private int offertaAttuale;
    private Giocatore miglioreOfferente;
    private Proprieta prop;

    public void offri(Giocatore g, int offerta) throws OffertaInvalidaException {
        if (offerta <= this.getOffertaAttuale() || g.getConto() < offerta)
            throw new OffertaInvalidaException(g);
        else {
            this.setMiglioreOfferente(g);
            this.setOffertaAttuale(offerta);
        }
    }
}
