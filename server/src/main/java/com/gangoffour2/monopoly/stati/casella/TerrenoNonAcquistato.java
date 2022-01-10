package com.gangoffour2.monopoly.stati.casella;

import com.gangoffour2.monopoly.azioni.casella.RichiediAcquisto;
import com.gangoffour2.monopoly.azioni.giocatore.AcquistaProprieta;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class TerrenoNonAcquistato extends EventoTerreno{

    private TerrenoNonAcquistato(){

    }

    @Override
    public void arrivo() {
        terreno.notificaTutti(RichiediAcquisto.builder().build());
    }

    @Override
    public void passaggio(){

    }

    @Override
    public void fineGiro() {

    }

    @Override
    public void onAzioneGiocatore(AcquistaProprieta acquistaProprieta){
        terreno.setState(
                TerrenoAcquistato.builder()
                .terreno(terreno)
                .build());
    }


}
