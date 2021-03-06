package com.gangoffour2.monopoly.stati.casella;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gangoffour2.monopoly.azioni.casella.AggiungiDenaro;
import com.gangoffour2.monopoly.azioni.casella.AzioneCasella;
import com.gangoffour2.monopoly.model.casella.Tassa;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class StatoTassa implements StatoCasella {
    @JsonIgnore
    private Tassa tassa;

    protected StatoTassa(){

    }

    @Override
    public AzioneCasella arrivo() {
       return AggiungiDenaro.builder()
               .importo(tassa.getCosto())
               .build();
    }
}
