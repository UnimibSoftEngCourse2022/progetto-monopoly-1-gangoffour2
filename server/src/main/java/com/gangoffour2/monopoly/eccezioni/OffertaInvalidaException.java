package com.gangoffour2.monopoly.eccezioni;

import com.gangoffour2.monopoly.model.Giocatore;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class OffertaInvalidaException extends RuntimeException{
    private Giocatore giocatore;

}
