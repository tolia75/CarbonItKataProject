package com.example.CarbonItKataProject.hexagon.domain.model;

public enum Mouvement {
    AVANCE("A"), TOURNE_A_DROITE("D"), TOURNE_A_GAUCHE("G");

    private String value;

    Mouvement(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
