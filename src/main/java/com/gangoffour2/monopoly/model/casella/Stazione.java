package com.gangoffour2.monopoly.model.casella;

import com.gangoffour2.monopoly.model.casella.strategy.PagamentoStrategy;
import com.gangoffour2.monopoly.stati.casella.StazioneNonAcquistata;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class Stazione extends Proprieta {

    private Stazione() {
        stato = StazioneNonAcquistata.builder().stazione(this).build();
    }

    @Override
    public int calcolaAffitto(PagamentoStrategy strategia) {
        return strategia.calcolaAffitto(this);
    }

    @Override
    public void reset(){
        setStato(StazioneNonAcquistata.builder().stazione(this).build());
    }
}
