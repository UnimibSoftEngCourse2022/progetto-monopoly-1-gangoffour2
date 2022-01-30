package com.gangoffour2.monopoly.controller;

import com.gangoffour2.monopoly.model.Configurazione;
import com.gangoffour2.monopoly.model.IPartita;
import com.gangoffour2.monopoly.services.FactoryPartita;
import com.gangoffour2.monopoly.services.PartiteRepository;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
public class WebController {

    @PostMapping(value = "/partite")
    public String creaPartita(@RequestBody Configurazione configurazione) throws IOException {
        IPartita nuovaPartita = FactoryPartita.getInstance().creaPartita(configurazione);
        PartiteRepository.getInstance().addPartita(nuovaPartita);
        nuovaPartita.inizializza();
        return nuovaPartita.getId();
    }

    @GetMapping("/partite")
    public List<IPartita> getPartiteAperte() {
        return PartiteRepository.getInstance().getPartiteAperte();
    }

    @GetMapping("/partite/{idPartita}")
    public IPartita getPartitaById(@PathVariable String idPartita) {
        return PartiteRepository.getInstance().getPartitaByid(idPartita);
    }
}
