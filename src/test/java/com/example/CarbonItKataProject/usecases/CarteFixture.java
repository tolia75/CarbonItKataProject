package com.example.CarbonItKataProject.usecases;

import com.example.CarbonItKataProject.hexagon.domain.model.Aventurier;
import com.example.CarbonItKataProject.hexagon.domain.model.Carte;
import com.example.CarbonItKataProject.hexagon.domain.model.Coordonnees;
import com.example.CarbonItKataProject.hexagon.domain.model.OrientationEnum;

public class CarteFixture {

    public Carte getCarte() {
        return new Carte.CarteBuilder()
                .largeur(5)
                .hauteur(5)
                .build();
    }

    public Aventurier getAventurierDescendDuneCase() {
        Coordonnees coordonnees = new Coordonnees.CoordonneesBuilder()
                .niveauHorizontal(1)
                .niveauVertical(1)
                .build();

        return new Aventurier.AventurierBuilder()
                .coordonnees(coordonnees)
                .orientation(OrientationEnum.S)
                .trajet("A")
                .build();
    }
}
