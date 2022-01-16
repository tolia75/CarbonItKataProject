package com.example.CarbonItKataProject.hexagon.domain.model;

public class GenericBuilder {

    public Aventurier creerNouvelAventurier(String prenom, int niveauHorizontal, int niveauVertical, String orientation, String trajets) {
        Coordonnees coordonnees = new Coordonnees.CoordonneesBuilder()
                .niveauHorizontal(niveauHorizontal)
                .niveauVertical(niveauVertical)
                .build();

        return new Aventurier.AventurierBuilder()
                .prenom(prenom)
                .coordonnees(coordonnees)
                .orientation(OrientationEnum.valueOf(orientation))
                .trajet(trajets)
                .build();
    }

    public Tresor creerNouveauTresor(int niveauHorizontal, int niveauVertical, int nombreDeTresors) {
        Coordonnees coordonnees = new Coordonnees.CoordonneesBuilder()
                .niveauHorizontal(niveauHorizontal)
                .niveauVertical(niveauVertical)
                .build();

        return new Tresor.TresorBuilder()
                .coordonnees(coordonnees)
                .nombreDeTresor(nombreDeTresors)
                .build();
    }
}
