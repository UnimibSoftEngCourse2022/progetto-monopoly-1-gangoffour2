package com.gangoffour2.monopoly.stati.casella;

import com.gangoffour2.monopoly.azioni.giocatore.RimuoviIpoteca;
import com.gangoffour2.monopoly.model.casella.Societa;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public abstract class EventoSocieta extends EventoCasella {
    protected Societa societa;

    protected EventoSocieta() {

    }
}
