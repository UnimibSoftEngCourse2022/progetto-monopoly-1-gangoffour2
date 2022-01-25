package com.gangoffour2.monopoly.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gangoffour2.monopoly.model.Configurazione;
import com.gangoffour2.monopoly.model.Partita;
import com.gangoffour2.monopoly.model.Tabellone;
import com.gangoffour2.monopoly.model.casella.*;
import com.gangoffour2.monopoly.stati.partita.Lobby;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class FactoryPartita {

    private static FactoryPartita instance;

    public static synchronized FactoryPartita getInstance() {
        if(instance == null)
            instance = new FactoryPartita();
        return instance;
    }

    String creaId(){
        String idPartita = UUID.randomUUID().toString();
        while(PartiteRepository.getInstance().getPartitaByid(idPartita) != null){
            idPartita = UUID.randomUUID().toString();
        }
        return idPartita;
    }

    ArrayList<Casella> creaCaselle() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        File jsonFile = new File(getClass().getClassLoader().getResource("caselle.json").getFile());
        Casella[] arrayCaselle = mapper.readValue(jsonFile, Casella[].class);

        return new ArrayList<>(List.of(arrayCaselle));
    }

    Tabellone creaTabellone() throws IOException {
        return Tabellone.builder().caselle(creaCaselle()).build();
    }


    public Partita creaPartita(Configurazione config) throws IOException {

        Partita partita = Partita.builder()
                .id(creaId())
                .tabellone(creaTabellone())
                .config(config)
                .build();

        partita.getTabellone().setPartita(partita);

        ArrayList<Casella> caselle = partita.getTabellone().getCaselle();

        caselle.forEach(casella -> casella.aggiungi(partita));

        partita.setStato(Lobby.builder().build());

        return partita;
    }

}
