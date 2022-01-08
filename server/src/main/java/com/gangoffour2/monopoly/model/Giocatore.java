package com.gangoffour2.monopoly.model;

import com.gangoffour2.monopoly.eccezioni.ModificaDenaroException;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Giocatore  {
    private String nick;
    private int conto;

    public void modificaDenaro(int importo) throws ModificaDenaroException{
        if(this.getConto() + importo < 0)
            throw new ModificaDenaroException();
        this.setConto(this.getConto() + importo);
    }
}
