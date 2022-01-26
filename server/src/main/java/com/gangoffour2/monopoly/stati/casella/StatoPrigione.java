package com.gangoffour2.monopoly.stati.casella;

import com.gangoffour2.monopoly.azioni.giocatore.Paga;
import com.gangoffour2.monopoly.eccezioni.ModificaDenaroException;
import com.gangoffour2.monopoly.model.casella.Prigione;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class StatoPrigione extends EventoCasella {
    private Prigione prigione;

    @Override
    public void onAzioneGiocatore(Paga paga) throws ModificaDenaroException{
        if(paga.getGiocatore().getConto() < prigione.getCauzione()){
            throw new ModificaDenaroException();
        }
        paga.getGiocatore().modificaDenaro(-prigione.getCauzione());
    }
}
