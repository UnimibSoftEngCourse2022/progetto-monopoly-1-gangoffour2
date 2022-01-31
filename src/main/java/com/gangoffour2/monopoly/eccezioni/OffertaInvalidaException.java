package com.gangoffour2.monopoly.eccezioni;

import com.gangoffour2.monopoly.model.giocatore.Giocatore;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class OffertaInvalidaException extends RuntimeException {
    private final Giocatore giocatore;

}
