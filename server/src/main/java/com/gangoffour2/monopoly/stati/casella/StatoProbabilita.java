package com.gangoffour2.monopoly.stati.casella;

import com.gangoffour2.monopoly.model.casella.Probabilita;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class StatoProbabilita extends StatoCasella {

    private Probabilita probabilita;


}
