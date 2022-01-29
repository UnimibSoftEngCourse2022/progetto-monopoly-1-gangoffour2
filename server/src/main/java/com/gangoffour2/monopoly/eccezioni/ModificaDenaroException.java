package com.gangoffour2.monopoly.eccezioni;

import lombok.Builder;
import lombok.Data;

@Data
public class ModificaDenaroException extends RuntimeException {
    int soldiDaPagare;

    public ModificaDenaroException(){
        soldiDaPagare = 0;
    }

    public ModificaDenaroException(int soldiDaPagare){
        this.soldiDaPagare = soldiDaPagare;
    }
}
