package com.example.CarbonItKataProject.hexagon.domain.usecases;

import com.example.CarbonItKataProject.hexagon.domain.model.Carte;
import com.example.CarbonItKataProject.hexagon.domain.model.ElementACreer;
import com.example.CarbonItKataProject.hexagon.domain.model.IndexValeur;
import com.example.CarbonItKataProject.hexagon.domain.port.primary.TraductionDonnees;

import java.util.Arrays;
import java.util.List;

import static com.example.CarbonItKataProject.hexagon.domain.model.IndexValeur.INDEX_NIVEAU_VERTICAL;
import static com.example.CarbonItKataProject.hexagon.domain.model.IndexValeur.INDEX_NIVEAU_HORIZONTAL;
import static com.example.CarbonItKataProject.hexagon.domain.model.IndexValeurAventurier.INDEX_NIVEAU_VERTICAL_AVENTURIER;
import static com.example.CarbonItKataProject.hexagon.domain.model.IndexValeurAventurier.INDEX_NIVEAU_HORIZONTAL_AVENTURIER;
import static com.example.CarbonItKataProject.hexagon.domain.model.IndexValeurAventurier.INDEX_ORIENTATION;
import static com.example.CarbonItKataProject.hexagon.domain.model.IndexValeurAventurier.INDEX_PRENOM;
import static com.example.CarbonItKataProject.hexagon.domain.model.IndexValeurAventurier.INDEX_TRAJET;

public class TraductionDonneesHandler implements TraductionDonnees {

    private static final String SEPARATEUR = " - ";
    private Carte carte;

    public Carte getCarte() {
        return carte;
    }

    public void lireLigne(String ligne) {
        List<String> valeurs = getValeursFromString(ligne);

        if (estUneCarte(ligne)) {
            this.carte = creerCarte(valeurs);
        }

        if (estUneMontagne(ligne)) {
            this.carte.creerMontagne(
                    Integer.parseInt(valeurs.get(INDEX_NIVEAU_HORIZONTAL.getValue())),
                    Integer.parseInt(valeurs.get(INDEX_NIVEAU_VERTICAL.getValue())));
        }

        if (estUnTresor(ligne)) {
            this.carte.creerTresor(
                    Integer.parseInt(valeurs.get(INDEX_NIVEAU_HORIZONTAL.getValue())),
                    Integer.parseInt(valeurs.get(INDEX_NIVEAU_VERTICAL.getValue())),
                    Integer.parseInt(valeurs.get(IndexValeur.INDEX_TRESOR.getValue())));
        }

        if (estUnAventurier(ligne)) {
            this.carte.creerAventurier(
                    valeurs.get(INDEX_PRENOM.getValue()),
                    Integer.parseInt(valeurs.get(INDEX_NIVEAU_HORIZONTAL_AVENTURIER.getValue())),
                    Integer.parseInt(valeurs.get(INDEX_NIVEAU_VERTICAL_AVENTURIER.getValue())),
                    valeurs.get(INDEX_ORIENTATION.getValue()),
                    valeurs.get(INDEX_TRAJET.getValue()));
        }
    }

    private boolean estUnAventurier(String ligne) {
        return ligne.startsWith(ElementACreer.A.toString());
    }

    private boolean estUnTresor(String ligne) {
        return ligne.startsWith(ElementACreer.T.toString());
    }

    private boolean estUneMontagne(String ligne) {
        return ligne.startsWith(ElementACreer.M.toString());
    }

    private boolean estUneCarte(String ligne) {
        return ligne.startsWith(ElementACreer.C.toString());
    }

    private List<String> getValeursFromString(String ligne) {
        return Arrays.asList(ligne.split(SEPARATEUR));
    }

    private Carte creerCarte(List<String> valeurs) {
        return new Carte.CarteBuilder()
                .hauteur(Integer.parseInt(valeurs.get(INDEX_NIVEAU_VERTICAL.getValue())))
                .largeur(Integer.parseInt(valeurs.get(INDEX_NIVEAU_HORIZONTAL.getValue())))
                .build();
    }
}
