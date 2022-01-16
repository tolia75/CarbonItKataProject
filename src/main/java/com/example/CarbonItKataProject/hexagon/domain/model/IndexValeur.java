package com.example.CarbonItKataProject.hexagon.domain.model;

public enum IndexValeur {
    INDEX_NIVEAU_HORIZONTAL(1), INDEX_NIVEAU_VERTICAL(2), INDEX_TRESOR(3);

    private int value;

    IndexValeur(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
