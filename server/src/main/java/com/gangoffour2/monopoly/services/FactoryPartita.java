package com.gangoffour2.monopoly.services;

import com.gangoffour2.monopoly.model.Configurazione;
import com.gangoffour2.monopoly.model.Partita;
import com.gangoffour2.monopoly.model.Tabellone;
import com.gangoffour2.monopoly.model.casella.*;
import com.gangoffour2.monopoly.stati.casella.TerrenoNonAcquistato;
import com.gangoffour2.monopoly.stati.partita.InizioTurno;
import com.gangoffour2.monopoly.stati.partita.StatoPartita;

import java.util.ArrayList;

public class FactoryPartita {

    private static FactoryPartita instance;

    public static synchronized FactoryPartita getInstance() {
        if(instance == null)
            instance = new FactoryPartita();
        return instance;
    }

    int creaPrezzoRandom(Configurazione.Difficolta difficolta) {
       return (int)Math.random() * 100;
    }

    int creaId(){
       return (int)Math.random() * 10000;
    }

    ArrayList<Integer> creaAffitti(Configurazione.Difficolta difficolta) {
        ArrayList<Integer> affitti = new ArrayList<Integer>();

        for(int i = 0; i < 4; i++){
            int affitto = creaPrezzoRandom(difficolta);
            affitti.add(affitto);
        }

        return affitti;
    }

    Societa creaSocieta(String nome, Configurazione.Difficolta difficolta) {
        return Societa.builder()
                .nome(nome)
                .costoBase(creaPrezzoRandom(difficolta))
                .ipoteca(creaPrezzoRandom(difficolta))
                .build();
    }

    Stazione creaStazione(String nome, Configurazione.Difficolta difficolta) {
        return Stazione.builder()
                .nome(nome)
                .costoBase(creaPrezzoRandom(difficolta))
                .ipoteca(creaPrezzoRandom(difficolta))
                .build();
    }

    Terreno creaTerreno(String nome, Configurazione.Difficolta difficolta, Terreno.Colore colore){
        Terreno terreno = Terreno.builder()
                .nome(nome)
                .colore(colore)
                .affitti(creaAffitti(difficolta))
                .costoCasa(creaPrezzoRandom(difficolta))
                .costoAlbergo(creaPrezzoRandom(difficolta))
                .costoBase(creaPrezzoRandom(difficolta))
                .ipoteca(creaPrezzoRandom(difficolta))
                .proprietario(null)
                .build();

        terreno.setState(TerrenoNonAcquistato.builder().build());
        terreno.getStatoCorrente().setTerreno(terreno);

        System.out.println( terreno.getSubscribers().size() );

        return terreno;
    }

    ArrayList<Casella> creaCaselle(Configurazione.Difficolta difficolta) {
        ArrayList<Casella> caselle = new ArrayList<Casella>();

        Via via = Via.builder().nome("Via").build();
        Prigione prigione = Prigione.builder().nome("Prigione").build();
        Probabilita probabilita = Probabilita.builder().nome("Probabilità").build();
        Imprevisto imprevisto = Imprevisto.builder().nome("Imprevisto").build();
        Tassa tassa = Tassa.builder().nome("Tassa").build();

        for(int i = 0; i < 3; ++i) {
            caselle.add(creaTerreno("Avenue park" + i, difficolta, Terreno.Colore.BLU));
            caselle.add(creaTerreno("Garda Lake" + i, difficolta, Terreno.Colore.MARRONE));
            caselle.add(creaTerreno("Plaza" + i, difficolta, Terreno.Colore.ROSSO));
            caselle.add(creaTerreno("Laza" + i, difficolta, Terreno.Colore.AZZURRO));
            caselle.add(creaTerreno("Aza" + i, difficolta, Terreno.Colore.GIALLO));
            caselle.add(creaTerreno("Za" + i, difficolta, Terreno.Colore.VERDE));
            caselle.add(creaTerreno("A" + i, difficolta, Terreno.Colore.VIOLA));
            caselle.add(creaTerreno("Mountain" + i, difficolta, Terreno.Colore.ARANCIONE));
        }

        for(int i = 0; i < 4; ++i) {
            caselle.add(creaSocieta("Società " + i, difficolta));
            caselle.add(creaStazione("Stazione" + i, difficolta));
        }

        caselle.add(via);
        caselle.add(prigione);
        caselle.add(imprevisto);
        caselle.add(probabilita);
        caselle.add(tassa);

        return caselle;
    }

    Tabellone creaTabellone(Configurazione.Difficolta difficolta){
        Tabellone tabellone = Tabellone.builder().caselle(creaCaselle(difficolta)).build();
        return tabellone;
    }


    public Partita creaPartita(Configurazione config){

        Configurazione.Difficolta difficolta = config.getDifficolta();

        Partita partita = Partita.builder()
                .id(creaId())
                .tabellone(creaTabellone(difficolta))
                .config(config)
                .build();

        ArrayList<Casella> caselle = partita.getTabellone().getCaselle();

        caselle.forEach(casella -> {
            System.out.println(casella.getNome());
            casella.aggiungi(partita); });

        InizioTurno inizio = new InizioTurno();

        partita.setStato(inizio);

        return partita;
    }

}
