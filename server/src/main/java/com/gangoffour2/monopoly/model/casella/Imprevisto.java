package com.gangoffour2.monopoly.model.casella;

import com.gangoffour2.monopoly.stati.casella.StatoImprevisto;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class Imprevisto extends Casella {

    protected Imprevisto(){
        evento = StatoImprevisto.builder().imprevisto(this).build();
    }

}

