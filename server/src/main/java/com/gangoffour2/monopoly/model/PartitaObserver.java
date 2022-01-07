package com.gangoffour2.monopoly.model;


import com.gangoffour2.monopoly.azioni.casella.AzioneCasella;

/**
 * Interfaccia utilizzata per ricevere dagli stati delle caselle le notifiche
 * relative alle azioni da eseguire.
 */

public interface PartitaObserver {
    void notifica(AzioneCasella azione);
}
