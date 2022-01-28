package com.gangoffour2.monopoly.model.carta;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.gangoffour2.monopoly.model.Giocatore;
import com.gangoffour2.monopoly.model.ITabellone;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Data
@SuperBuilder
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = CartaEsciGratisPrigione.class, name = "CartaEsciGratisPrigione"),
        @JsonSubTypes.Type(value = CartaModificaDenaro.class, name = "CartaModificaDenaro"),
        @JsonSubTypes.Type(value = CartaMuoviPosizioneACasella.class, name = "CartaMuoviPosizioneACasella"),
        @JsonSubTypes.Type(value = CartaMuoviPosizioneIntero.class, name = "CartaMuoviPosizioneIntero"),
})
public abstract class Carta implements Serializable {
    protected String testo;

    protected Carta(){}

    @JsonProperty("type")
    public String getTipo(){
        return getClass().getSimpleName();
    }


    @JsonIgnore
    protected ITabellone tabellone;

    public abstract boolean effetto(Giocatore g);

    public void randomizzaCarta(float m){ }
}
