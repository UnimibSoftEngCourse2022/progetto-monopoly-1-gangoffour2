package com.gangoffour2.monopoly.model.casella;

import com.gangoffour2.monopoly.azioni.casella.AggiungiDenaro;
import com.gangoffour2.monopoly.stati.casella.StatoTassa;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class Tassa extends Casella {
    private int costo;

    protected Tassa() {
        stato = StatoTassa.builder().tassa(this).build();
    }

    @Override
    public void arrivo() {
        notificaTutti(AggiungiDenaro.builder().importo(-costo).build());
    }
}
