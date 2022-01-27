package com.gangoffour2.monopoly.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gangoffour2.monopoly.model.*;
import com.gangoffour2.monopoly.model.casella.*;
import com.gangoffour2.monopoly.stati.partita.Lobby;
import org.springframework.core.io.ClassPathResource;

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

    public String creaId(){
        String idPartita = UUID.randomUUID().toString();
        while(PartiteRepository.getInstance().getPartitaByid(idPartita) != null){
            idPartita = UUID.randomUUID().toString();
        }
        return idPartita;
    }

    public List<Casella> creaCaselle() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        File jsonFile = new ClassPathResource("caselle.json").getFile();
        Casella[] arrayCaselle = mapper.readValue(jsonFile, Casella[].class);

        int idCounter = 0;
        for(Casella c: arrayCaselle) {
            c.setId(idCounter++);
        }

        return new ArrayList<>(List.of(arrayCaselle));
    }

    public Tabellone creaTabellone() throws IOException {
        return Tabellone.builder().caselle(creaCaselle()).build();
    }

    public IPartita creaPartita(Configurazione config) throws IOException {
        Tabellone tabellone = creaTabellone();
        Mazzo mazzo = Mazzo.builder().build();
        IPartita partita = Partita.builder()
                .id(creaId())
                .tabellone(tabellone)
                .mazzo(mazzo)
                .config(config)
                .build();
        tabellone.setPartita(partita);

        List<Casella> caselle = tabellone.getCaselle();

        caselle.forEach(casella -> casella.aggiungi((PartitaObserver) partita));

        partita.setStato(Lobby.builder().build());

        return partita;
    }

}
