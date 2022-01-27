package com.gangoffour2.monopoly.model.casella;

import com.gangoffour2.monopoly.stati.casella.StatoVia;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class Via extends Casella {
    @Builder.Default
    int importo = 200;

    private Via() {
        evento = StatoVia.builder().via(this).build();
        importo = 200;
    }

}
