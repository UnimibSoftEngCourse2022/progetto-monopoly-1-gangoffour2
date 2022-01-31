package com.gangoffour2.monopoly.model.casella;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gangoffour2.monopoly.azioni.casella.AttesaLancioDadi;
import com.gangoffour2.monopoly.azioni.casella.VaiInAttesaPrigione;
import com.gangoffour2.monopoly.model.giocatore.Giocatore;
import com.gangoffour2.monopoly.stati.casella.StatoPrigione;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.util.HashMap;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class Prigione extends Casella {
    private int cauzione;
    private int turniInPrigione;
    @JsonIgnore
    private HashMap<Giocatore, Integer> giocatoriInPrigione = new HashMap<>();

    protected Prigione() {
        stato = StatoPrigione.builder().prigione(this).build();
        turniInPrigione = 1;
    }

    public Integer liberaGiocatore(Giocatore giocatore) {
        return giocatoriInPrigione.remove(giocatore);
    }

    public Integer imprigionaGiocatore(Giocatore giocatore) {
        return giocatoriInPrigione.put(giocatore, turniInPrigione);
    }

    @Override
    public void inizioTurno(Giocatore g) {
        if (giocatoriInPrigione.get(g) == null) {
            notificaTutti(AttesaLancioDadi.builder().build());
        } else if (giocatoriInPrigione.get(g) == 0) {
            this.liberaGiocatore(g);
            notificaTutti(AttesaLancioDadi.builder().build());
        } else {
            giocatoriInPrigione.put(g, giocatoriInPrigione.get(g) - 1);
            notificaTutti(VaiInAttesaPrigione.builder().giocatore(g).build());
        }
    }
}
