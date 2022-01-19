package com.gangoffour2.monopoly.controller;

import com.gangoffour2.monopoly.model.Configurazione;
import com.gangoffour2.monopoly.model.Partita;
import com.gangoffour2.monopoly.services.FactoryPartita;
import com.gangoffour2.monopoly.services.PartiteRespository;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
public class WebController {

    @CrossOrigin
    @PostMapping(value = "/partite")
    public String creaPartita(@RequestBody Configurazione configurazione) throws IOException {
        Partita nuovaPartita = FactoryPartita.getInstance().creaPartita(configurazione);
        PartiteRespository.getInstance().addPartita(nuovaPartita);
        nuovaPartita.inizializza();
        return nuovaPartita.getId();
    }

    @GetMapping("/partite")
    public List<Partita> getPartiteAperte(){
        return PartiteRespository.getInstance().getPartiteAperte();
    }
}
