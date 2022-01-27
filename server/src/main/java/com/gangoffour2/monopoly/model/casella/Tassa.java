package com.gangoffour2.monopoly.model.casella;

import com.gangoffour2.monopoly.stati.casella.StatoTassa;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class Tassa extends Casella {
    private int costo;

    protected Tassa() {
        evento = StatoTassa.builder().tassa(this).build();
    }
}
