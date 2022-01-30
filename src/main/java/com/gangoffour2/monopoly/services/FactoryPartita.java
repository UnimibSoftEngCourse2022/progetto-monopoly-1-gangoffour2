package com.gangoffour2.monopoly.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gangoffour2.monopoly.model.*;
import com.gangoffour2.monopoly.model.carta.Carta;
import com.gangoffour2.monopoly.model.carta.strategy.StrategiaCarteRandom;
import com.gangoffour2.monopoly.model.casella.Casella;
import com.gangoffour2.monopoly.model.casella.strategy.StrategiaCasellaRandom;
import com.gangoffour2.monopoly.stati.partita.Lobby;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
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

    public List<Casella> creaCaselle(Configurazione.Difficolta difficolta) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        byte[] data = FileCopyUtils.copyToByteArray(new ClassPathResource("caselle.json").getInputStream());
        String json = new String(data, StandardCharsets.UTF_8);
        Casella[] arrayCaselle = mapper.readValue(json, Casella[].class);


        int idCounter = 0;
        for (Casella c : arrayCaselle) {
            c.setId(idCounter++);
        }
        ArrayList<Casella> listaCaselle = new ArrayList<>(List.of(arrayCaselle));
        Casella via = listaCaselle.get(0);
        Casella prigione = listaCaselle.get(10);
        Casella parking = listaCaselle.get(20);
        Casella vaiPrigione = listaCaselle.get(30);
        if(difficolta == Configurazione.Difficolta.HARD) {
            Collections.shuffle(listaCaselle);
            Collections.swap(listaCaselle, 0, listaCaselle.indexOf(via));
            Collections.swap(listaCaselle, 10, listaCaselle.indexOf(prigione));
            Collections.swap(listaCaselle, 20, listaCaselle.indexOf(parking));
            Collections.swap(listaCaselle, 30, listaCaselle.indexOf(vaiPrigione));
        }

        return listaCaselle;
    }

    public Carta[] creaCarte(String nomeFile) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        byte[] data = FileCopyUtils.copyToByteArray(new ClassPathResource(nomeFile).getInputStream());
        String json = new String(data, StandardCharsets.UTF_8);
        return objectMapper.readValue(json, Carta[].class);
    }

    public IMazzo creaMazzo(ITabellone tabellone, Configurazione.Difficolta difficolta) throws IOException {
        Mazzo mazzo = Mazzo.builder().build();
        if(difficolta == Configurazione.Difficolta.HARD)
            mazzo = Mazzo.builder().strategiaMazzo(StrategiaCarteRandom.builder().build()).build();
        mazzo.setImprevisti(new LinkedList<>(List.of(creaCarte("imprevisti.json"))));
        mazzo.setProbabilita(new LinkedList<>(List.of(creaCarte("probabilita.json"))));
        mazzo.getImprevisti().forEach(imprevisto -> imprevisto.setTabellone(tabellone));
        mazzo.getProbabilita().forEach(probabilita -> probabilita.setTabellone(tabellone));
        return mazzo;
    }

    public Tabellone creaTabellone(Configurazione.Difficolta difficolta) throws IOException {
        if(difficolta == Configurazione.Difficolta.HARD)
            return Tabellone.builder().caselle(creaCaselle(difficolta)).strategia(StrategiaCasellaRandom.builder().build()).build();
        return Tabellone.builder().caselle(creaCaselle(difficolta)).build();
    }

    public IPartita creaPartita(Configurazione config) throws IOException {
        Tabellone tabellone = creaTabellone(config.getDifficolta());
        IMazzo mazzo = creaMazzo(tabellone, config.getDifficolta());

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
