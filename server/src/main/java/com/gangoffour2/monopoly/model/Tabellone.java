package com.gangoffour2.monopoly.model;

import java.util.ArrayList;

import com.gangoffour2.monopoly.model.carta.Imprevisto;
import com.gangoffour2.monopoly.model.carta.Probabilita;
import com.gangoffour2.monopoly.model.casella.Casella;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Tabellone{
    private ArrayList<Casella> caselle;
    private ArrayList<Probabilita> probabilita;
    private ArrayList<Imprevisto> imprevisti;

}
