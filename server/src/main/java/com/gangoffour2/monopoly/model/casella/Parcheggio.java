package com.gangoffour2.monopoly.model.casella;

import com.gangoffour2.monopoly.stati.casella.StatoParcheggio;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class Parcheggio extends Casella {

    protected Parcheggio() {
        stato = StatoParcheggio.builder().parcheggio(this).build();
    }

}
