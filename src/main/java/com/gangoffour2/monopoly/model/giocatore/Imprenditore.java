package com.gangoffour2.monopoly.model.giocatore;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gangoffour2.monopoly.model.casella.strategyCaselle.PagamentoStrategiaImprenditore;
import com.gangoffour2.monopoly.model.casella.strategyCaselle.PagamentoStrategy;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class Imprenditore extends Giocatore {
    @JsonIgnore
    @Builder.Default
    private transient PagamentoStrategy strategiaCalcoloAffitto = new PagamentoStrategiaImprenditore();
}
