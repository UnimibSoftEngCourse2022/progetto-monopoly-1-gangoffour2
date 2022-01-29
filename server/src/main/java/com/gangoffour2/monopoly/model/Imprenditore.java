package com.gangoffour2.monopoly.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private transient AffittiStrategy strategiaCalcoloAffitto = new AffittiStrategiaImprenditore();
}
