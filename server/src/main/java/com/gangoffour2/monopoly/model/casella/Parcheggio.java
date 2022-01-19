package com.gangoffour2.monopoly.model.casella;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.gangoffour2.monopoly.stati.casella.StatoParcheggio;
import lombok.Data;

@Data
@JsonTypeName("Parcheggio")
public class Parcheggio extends Casella {

    protected Parcheggio(){
        evento = StatoParcheggio.builder().parcheggio(this).build();
    }
    @Override
    public String getTipo() {
        return "Parcheggio";
    }
}
