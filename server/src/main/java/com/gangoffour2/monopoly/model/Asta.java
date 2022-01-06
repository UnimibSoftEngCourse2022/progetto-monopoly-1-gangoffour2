package com.gangoffour2.monopoly.model;

import com.gangoffour2.monopoly.model.casella.Proprietà;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Asta {
    private int offertaAttuale;
    private Giocatore miglioreOfferente;
    private Proprietà prop;
}
