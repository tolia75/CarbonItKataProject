package com.example.CarbonItKataProject.hexagon.domain.model;

import java.util.Optional;

public abstract class Orientation {

    protected static final int COORDONNEE_MINIMALE = 0;

    public abstract Orientation tourne(String directionDuChangementDeDirection);

    public abstract Optional<Coordonnees> avance(Coordonnees coordonnees, int largeurMaxCarte, int hauteurMaxCarte);

    public  abstract String getStringValue();

    protected boolean sontNouvellesCoordonneesEnDehorsLaCarte(Coordonnees nouvellesCoordonnees, int largeurMaxCarte, int hauteurMaxCarte) {
        return estNouvelleLargeurEnDehorsDeLaCarte(nouvellesCoordonnees, largeurMaxCarte) || estNouvelleHauteurEnDehorsDeLaCarte(nouvellesCoordonnees, hauteurMaxCarte);
    }

    private boolean estNouvelleLargeurEnDehorsDeLaCarte(Coordonnees nouvellesCoordonnees, int largeurMaxCarte) {
        return nouvellesCoordonnees.getNiveauHorizontal() > largeurMaxCarte || nouvellesCoordonnees.getNiveauHorizontal() < COORDONNEE_MINIMALE;
    }

    private boolean estNouvelleHauteurEnDehorsDeLaCarte(Coordonnees nouvellesCoordonnees, int hauteurMaxCarte) {
        return nouvellesCoordonnees.getNiveauVertical() > hauteurMaxCarte || nouvellesCoordonnees.getNiveauVertical() < COORDONNEE_MINIMALE;
    }
}
