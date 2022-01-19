package com.gangoffour2.monopoly.model.casella;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.gangoffour2.monopoly.stati.casella.StatoTassa;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@JsonTypeName("Tassa")
public class Tassa extends Casella{
    private int costo;

    protected Tassa(){
        evento = StatoTassa.builder().tassa(this).build();
    }

    @Override
    public String getTipo() {
        return "Tassa";
    }
}
