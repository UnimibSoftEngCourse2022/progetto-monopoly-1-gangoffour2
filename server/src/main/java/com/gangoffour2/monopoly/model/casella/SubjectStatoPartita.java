package com.gangoffour2.monopoly.model.casella;


import com.gangoffour2.monopoly.azioni.casella.AzioneCasella;
import com.gangoffour2.monopoly.model.PartitaObserver;


public interface SubjectStatoPartita {
    void notificaTutti(AzioneCasella azione);
    void aggiungi(PartitaObserver observer);
    void rimuovi(PartitaObserver observer);
}
