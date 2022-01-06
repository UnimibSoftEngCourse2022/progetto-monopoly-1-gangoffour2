package com.gangoffour2.monopoly.model;

import lombok.Builder;
import lombok.AllArgsConstructor;

@Builder
@AllArgsConstructor
public abstract class Casella {
    private String nome;
    public abstract void arrivo(Giocatore g);
    public abstract void passaggio(Giocatore g);
    public abstract void fineGiro();
}
