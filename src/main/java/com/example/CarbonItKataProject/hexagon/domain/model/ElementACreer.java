package com.example.CarbonItKataProject.hexagon.domain.model;

public enum ElementACreer {
    C("CARTE"), M("MONTAGNE"), T("TRESOR"), A("AVENTURIER");

    private String value;

    ElementACreer(String value) {
        this.value = value;
    }
}
