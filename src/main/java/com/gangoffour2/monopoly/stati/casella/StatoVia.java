package com.gangoffour2.monopoly.stati.casella;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gangoffour2.monopoly.azioni.casella.AzioneCasella;
import com.gangoffour2.monopoly.azioni.casella.AggiungiDenaro;
import com.gangoffour2.monopoly.model.casella.Via;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class StatoVia implements StatoCasella {
    @JsonIgnore
    private Via via;

    protected StatoVia(){

    }

    @Override
    public AzioneCasella passaggio() {
        return AggiungiDenaro.builder()
                .importo(via.getImporto())
                .build();
    }

    @Override
    public AzioneCasella arrivo(){
        return passaggio();
    }
}
