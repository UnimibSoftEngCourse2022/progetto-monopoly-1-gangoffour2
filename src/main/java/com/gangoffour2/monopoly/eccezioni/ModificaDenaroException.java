package com.gangoffour2.monopoly.eccezioni;

import lombok.Data;

@Data
public class ModificaDenaroException extends RuntimeException {
    private final int soldiDaPagare;

    public ModificaDenaroException(int soldiDaPagare){
        this.soldiDaPagare = soldiDaPagare;
    }
}
