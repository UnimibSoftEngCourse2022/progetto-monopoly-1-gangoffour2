package com.gangoffour2.monopoly.model.casella;

import com.gangoffour2.monopoly.stati.casella.StatoParcheggio;
import lombok.Data;

@Data
public class Parcheggio extends Casella {

    protected Parcheggio() {
        evento = StatoParcheggio.builder().parcheggio(this).build();
    }

}
