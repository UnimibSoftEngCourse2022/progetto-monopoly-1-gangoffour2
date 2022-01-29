package com.gangoffour2.monopoly.model.casella;

import com.gangoffour2.monopoly.stati.casella.StatoProbabilita;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class Probabilita extends Casella {

    protected Probabilita() {
        stato = StatoProbabilita.builder().probabilita(this).build();
    }

}
