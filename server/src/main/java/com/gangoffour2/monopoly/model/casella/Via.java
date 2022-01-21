package com.gangoffour2.monopoly.model.casella;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.gangoffour2.monopoly.stati.casella.StatoVia;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@JsonTypeName("Via")
public class Via extends Casella{
    @Builder.Default
    int importo = 200;
    @Override
    public String getTipo() {
        return "Via";
    }

    private Via(){
        evento = StatoVia.builder().via(this).build();
        importo = 200;
    }

}
