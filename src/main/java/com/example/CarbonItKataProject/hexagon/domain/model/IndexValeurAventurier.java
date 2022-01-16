package com.example.CarbonItKataProject.hexagon.domain.model;

public enum IndexValeurAventurier {
    INDEX_PRENOM(1), INDEX_NIVEAU_HORIZONTAL_AVENTURIER(2), INDEX_NIVEAU_VERTICAL_AVENTURIER(3), INDEX_ORIENTATION(4), INDEX_TRAJET(5);

    private int value;

    IndexValeurAventurier(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
