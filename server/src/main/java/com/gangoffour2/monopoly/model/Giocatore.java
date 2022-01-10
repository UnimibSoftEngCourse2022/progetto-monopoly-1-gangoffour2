package com.gangoffour2.monopoly.model;

import com.gangoffour2.monopoly.eccezioni.ModificaDenaroException;
import com.gangoffour2.monopoly.model.carta.Imprevisto;
import com.gangoffour2.monopoly.model.carta.Probabilita;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Giocatore implements Serializable {
    private String nick;
    private int conto;
    private ArrayList<Probabilita> probabilita;
    private ArrayList<Imprevisto> imprevisti;

    public void modificaDenaro(int importo) throws ModificaDenaroException{
        if(this.getConto() + importo < 0)
            throw new ModificaDenaroException();
        this.setConto(this.getConto() + importo);
    }
}
