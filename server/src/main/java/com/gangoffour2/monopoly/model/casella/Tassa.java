package com.gangoffour2.monopoly.model.casella;

import com.gangoffour2.monopoly.azioni.casella.AggiungiDenaro;
import com.gangoffour2.monopoly.model.AffittiStrategy;
import com.gangoffour2.monopoly.model.Giocatore;
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
    public void arrivo(Giocatore giocatore) {
        notificaTutti(AggiungiDenaro.builder().importo(-giocatore.getStrategiaCalcoloAffitto().calcolaTassa(this)).build());
    }

    @Override
    public void randomizzaCasella(float m){
        costo = (int) Math.floor(costo*m);
    }

    public int calcolaTassa(AffittiStrategy strategia){
        return strategia.calcolaTassa(this);
    }
}
