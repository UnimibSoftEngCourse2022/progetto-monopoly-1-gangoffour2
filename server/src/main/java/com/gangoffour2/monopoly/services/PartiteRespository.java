package com.gangoffour2.monopoly.services;

import com.gangoffour2.monopoly.model.Partita;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PartiteRespository {
    private static PartiteRespository instance;
    private final HashMap<String, Partita> partite;

    private PartiteRespository(){
        partite = new HashMap<>();
    }

    public static synchronized PartiteRespository getInstance(){
        if(instance == null){
            instance = new PartiteRespository();
        }

        return instance;
    }

    public Partita getPartitaByid(String id){
        return partite.get(id);
    }

    public void addPartita(Partita partita) {
        partite.put(partita.getId(), partita);
    }


    public List<Partita> getPartiteAperte(){
        return new ArrayList<>(partite.values());
    }
}
