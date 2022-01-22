package com.gangoffour2.monopoly.model.casella;

import com.gangoffour2.monopoly.stati.casella.StatoProbabilita;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class Probabilita extends Casella{

    protected Probabilita(){
        evento = StatoProbabilita.builder().probabilita(this).build();
    }

}
