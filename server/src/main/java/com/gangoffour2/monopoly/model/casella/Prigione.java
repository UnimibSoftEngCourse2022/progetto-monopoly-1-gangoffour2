package com.gangoffour2.monopoly.model.casella;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.gangoffour2.monopoly.stati.casella.StatoPrigione;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@JsonTypeName("Prigione")
public class Prigione extends Casella{

    protected Prigione(){
        evento = StatoPrigione.builder().prigione(this).build();
    }

    @Override
    public String getTipo() {
        return "Prigione";
    }
}
