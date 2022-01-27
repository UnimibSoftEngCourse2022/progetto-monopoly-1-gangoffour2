package com.gangoffour2.monopoly.model;

import java.io.Serializable;
import java.util.List;
import java.util.function.Predicate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gangoffour2.monopoly.model.casella.Casella;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Tabellone implements ITabellone, Serializable {
    @JsonIgnore
    private Partita partita;
    private List<Casella> caselle;


    @Override
    public Casella getCasella(int posizione){
        return caselle.get(posizione);
    }

    @Override
    public void muoviGiocatore(Giocatore giocatore, int quantita){
        Casella corrente = giocatore.getCasellaCorrente();
        giocatore.setCasellaCorrente(caselle.get((caselle.indexOf(corrente) + quantita) % caselle.size()));
    }

    @Override
    public void muoviGiocatoreIntero(Giocatore giocatore, int quantita){
        if(quantita != 0){
            Turno turno = partita.getTurnoCorrente();
            muoviGiocatore(giocatore, quantita);
            if(quantita > 0) {
                turno.setCasellaDaVisitare(quantita);
                partita.turnoStandard();
            }
            else
                giocatore.getCasellaCorrente().arrivo();
            }
    }

    /**
     * Funzione che sposta un giocatore sul tabellone sulla base della prima occorrenza trovata (tipo, nome, ecc...).
     * Bisogna passare una lambda che prende come argomento la casella trovata e ritorna vero o falso.
     * @param giocatore Il giocatore da spostare
     * @param predicato La funzione da applicare per capire quando si è arrivati alla casella giusta
     */

    @Override
    public void muoviAProssimaCasella(Giocatore giocatore, Predicate<Casella> predicato){
        Casella corrente = giocatore.getCasellaCorrente();
        int i = caselle.indexOf(corrente);
        Casella prossimaCasella;
        do{
            prossimaCasella = caselle.get((i + 1) % caselle.size());
            ++i;
        }while (!predicato.test(prossimaCasella));
        muoviGiocatoreIntero(giocatore, i);
    }

    @Override
    public void applicaEffetto(Giocatore giocatore, int offset){
        if(offset == 0){
            giocatore.getCasellaCorrente().arrivo();
        }
        else {
            int posizioneCorrente = caselle.indexOf(giocatore.getCasellaCorrente());
            caselle.get((posizioneCorrente - offset + caselle.size()) % caselle.size()).passaggio();
        }
    }


}
