package com.gangoffour2.monopoly.eccezioni;

import lombok.Data;

@Data
public class ModificaDenaroException extends RuntimeException {
    int soldiDaPagare;

    public ModificaDenaroException(int soldiDaPagare){
        this.soldiDaPagare = soldiDaPagare;
    }
}
