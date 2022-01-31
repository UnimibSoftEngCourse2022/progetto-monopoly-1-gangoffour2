package com.gangoffour2.monopoly.controller;

import lombok.Data;

@Data
public class GiocatoreEntraRequestBody {
    private String nickname;
    private Boolean isImprenditore;
}
