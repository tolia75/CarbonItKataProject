package com.example.CarbonItKataProject.hexagon.domain.model;

public enum OrientationEnum {
    N("NORD"), S("SUD"), E("EST"), O("OUEST");

    private String value;

    OrientationEnum(String value) {
        this.value = value;
    }
}
