package com.gangoffour2.monopoly.stati.casella;

import com.gangoffour2.monopoly.model.casella.Parcheggio;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class StatoParcheggio implements StatoCasella {

    private Parcheggio parcheggio;


}
