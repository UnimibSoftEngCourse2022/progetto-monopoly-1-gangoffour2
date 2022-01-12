package com.gangoffour2.monopoly.stati.casella;

import com.gangoffour2.monopoly.model.carta.Imprevisto;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class StatoImprevisto extends EventoCasella {

    private Imprevisto imprevisto;


}
