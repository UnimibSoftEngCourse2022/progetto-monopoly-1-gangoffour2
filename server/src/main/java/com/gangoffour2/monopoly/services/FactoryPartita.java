package com.gangoffour2.monopoly.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gangoffour2.monopoly.model.*;
import com.gangoffour2.monopoly.model.carta.Carta;
import com.gangoffour2.monopoly.model.casella.Casella;
import com.gangoffour2.monopoly.stati.partita.Lobby;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class FactoryPartita {

    private static FactoryPartita instance;

    public static synchronized FactoryPartita getInstance() {
        if (instance == null)
            instance = new FactoryPartita();
        return instance;
    }

    public String creaId() {
        String idPartita = UUID.randomUUID().toString();
        while (PartiteRepository.getInstance().getPartitaByid(idPartita) != null) {
            idPartita = UUID.randomUUID().toString();
        }
        return idPartita;
    }

    public List<Casella> creaCaselle() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        File jsonFile = new ClassPathResource("caselle.json").getFile();
        Casella[] arrayCaselle = mapper.readValue(jsonFile, Casella[].class);

        int idCounter = 0;
        for (Casella c : arrayCaselle) {
            c.setId(idCounter++);
        }

        return new ArrayList<>(List.of(arrayCaselle));
    }

    public Carta[] creaCarte(String nomeFile) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        File jsonFile = new ClassPathResource(nomeFile).getFile();
        return objectMapper.readValue(jsonFile, Carta[].class);
    }

    public IMazzo creaMazzo(ITabellone tabellone) throws IOException {
        Mazzo mazzo = Mazzo.builder().build();
        mazzo.setImprevisti(new LinkedList<>(List.of(creaCarte("imprevisti.json"))));
        mazzo.setProbabilita(new LinkedList<>(List.of(creaCarte("probabilita.json"))));
        mazzo.getImprevisti().forEach(imprevisto -> imprevisto.setTabellone(tabellone));
        mazzo.getProbabilita().forEach(probabilita -> probabilita.setTabellone(tabellone));
        return mazzo;
    }

    public Tabellone creaTabellone() throws IOException {
        return Tabellone.builder().caselle(creaCaselle()).build();
    }

    public IPartita creaPartita(Configurazione config) throws IOException {
        Tabellone tabellone = creaTabellone();
        IMazzo mazzo = creaMazzo(tabellone);
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
