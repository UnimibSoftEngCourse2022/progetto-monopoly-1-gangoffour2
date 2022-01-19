package com.gangoffour2.monopoly.model.casella;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.gangoffour2.monopoly.stati.casella.StatoImprevisto;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@JsonTypeName("Imprevisto")
public class Imprevisto extends Casella {

    protected Imprevisto(){
        evento = StatoImprevisto.builder().imprevisto(this).build();
    }

    @Override
    public String getTipo() {
        return "Imprevisto";
    }
}
