package com.gangoffour2.monopoly.model.casella;

import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("VaiInPrigione")
public class VaiInPrigione extends Casella {

    @Override
    public String getTipo() {
        return "VaiInPrigione";
    }
}
