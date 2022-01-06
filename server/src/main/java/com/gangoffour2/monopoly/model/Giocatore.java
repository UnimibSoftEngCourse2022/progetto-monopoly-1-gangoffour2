package com.gangoffour2.monopoly.model;

import lombok.Builder;
import lombok.AllArgsConstructor;

@Builder
@AllArgsConstructor
public class Giocatore {
    private String nick;
    private int conto;

}
