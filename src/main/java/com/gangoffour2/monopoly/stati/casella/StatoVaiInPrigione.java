package com.gangoffour2.monopoly.stati.casella;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gangoffour2.monopoly.azioni.casella.ArrestaGiocatore;
import com.gangoffour2.monopoly.azioni.casella.AzioneCasella;
import com.gangoffour2.monopoly.model.casella.VaiInPrigione;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class StatoVaiInPrigione implements StatoCasella {
    @JsonIgnore
    VaiInPrigione vaiInPrigione;

    @Override
    public AzioneCasella arrivo() {
        return ArrestaGiocatore.builder().build();
    }
}
