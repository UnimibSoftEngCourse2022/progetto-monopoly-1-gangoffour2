package com.gangoffour2.monopoly.azioni.casella;

import com.gangoffour2.monopoly.model.Giocatore;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class PagaAffitto implements AzioneCasella{
    protected Giocatore proprietario;

    private PagaAffitto(){
    }
}
