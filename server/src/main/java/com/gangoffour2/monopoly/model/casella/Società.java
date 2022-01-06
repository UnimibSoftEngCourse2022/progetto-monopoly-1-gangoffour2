package com.gangoffour2.monopoly.model.casella;

import com.gangoffour2.monopoly.model.Giocatore;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Società extends Proprietà{
    //manca img
    public Società(String nome) {
        super(nome);
    }

    @Override
    public void arrivo(Giocatore g) {
    }

    @Override
    public void passaggio(Giocatore g) {
    }

    @Override
    public void fineGiro() {
    }
}
