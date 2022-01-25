package com.gangoffour2.monopoly.services;

import com.gangoffour2.monopoly.model.Giocatore;
import com.gangoffour2.monopoly.model.Partita;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PartiteRepository {
    private static PartiteRepository instance;
    private final HashMap<String, Partita> partite;
    private final HashMap<String, Giocatore> giocatori;

    private PartiteRepository(){
        partite = new HashMap<>();
        giocatori = new HashMap<>();
    }

    public static synchronized PartiteRepository getInstance(){
        if(instance == null){
            instance = new PartiteRepository();
        }

        return instance;
    }

    public synchronized void rimuoviGiocatoreById(String idSessione){
        giocatori.remove(idSessione);
    }

    public synchronized Giocatore getGiocatoreByIdSessione(String idSessione){
        return giocatori.get(idSessione);
    }

    public synchronized void registraGiocatore(String idSessione, Giocatore giocatore){
        giocatori.put(idSessione, giocatore);
    }

    public synchronized Partita getPartitaByid(String id){
        return partite.get(id);
    }

    public synchronized void addPartita(Partita partita) {
        partite.put(partita.getId(), partita);
    }


    public synchronized List<Partita> getPartiteAperte(){
        return new ArrayList<>(partite.values());
    }
}
