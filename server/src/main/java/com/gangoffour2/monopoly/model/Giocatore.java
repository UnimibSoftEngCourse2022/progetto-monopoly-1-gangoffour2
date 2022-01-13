package com.gangoffour2.monopoly.model;

import com.gangoffour2.monopoly.eccezioni.ModificaDenaroException;
import com.gangoffour2.monopoly.model.carta.Imprevisto;
import com.gangoffour2.monopoly.model.carta.Probabilita;
import com.gangoffour2.monopoly.model.casella.Casella;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Giocatore implements Serializable {
    private String nick;
    private int conto;
    private ArrayList<Probabilita> probabilita;
    private ArrayList<Imprevisto> imprevisti;
    private Casella casellaCorrente;

    public void modificaDenaro(int importo) throws ModificaDenaroException{
        if(this.getConto() + importo < 0)
            throw new ModificaDenaroException();
        this.setConto(this.getConto() + importo);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Giocatore giocatore = (Giocatore) o;
        return nick.equals(giocatore.nick);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nick);
    }
}
