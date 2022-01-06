package com.gangoffour2.monopoly.model.casella;

import com.gangoffour2.monopoly.model.Giocatore;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public abstract class Casella {
    private String nome;
    public abstract void arrivo(Giocatore g);
    public abstract void passaggio(Giocatore g);
    public abstract void fineGiro();
}
