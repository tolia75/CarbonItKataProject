package com.example.CarbonItKataProject.hexagon.domain.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.CarbonItKataProject.hexagon.domain.model.Mouvement.AVANCE;

public class Carte extends ElementDuJeu{

    private static final char MONTAGNE = 'M';
    private static final char TRESOR = 'T';
    private static final char AVENTURIER = 'A';
    private static final String SEPARATOR = " - ";
    private int hauteur;
    private int largeur;
    private List<Tresor> tresors = new ArrayList();
    private List<Aventurier> aventuriers = new ArrayList();
    private List<Montagne> montagnes = new ArrayList();
    private GenericBuilder genericBuilder = new GenericBuilder();

    private char[][] zoneDeJeu;

    public Carte(CarteBuilder carteBuilder) {
        this.hauteur = carteBuilder.hauteur;
        this.largeur = carteBuilder.largeur;

        zoneDeJeu = new char[largeur][hauteur];
    }

    public int getHauteur() {
        return hauteur;
    }

    public int getLargeur() {
        return largeur;
    }

    public char[][] getZoneDeJeu() {
        return zoneDeJeu;
    }

    public List<Montagne> getMontagnes() {
        return montagnes;
    }

    public void creerMontagne(int largeur, int hauteur) {
        zoneDeJeu[largeur][hauteur] = MONTAGNE;
        Coordonnees coordonnees = new Coordonnees.CoordonneesBuilder()
                .niveauHorizontal(largeur)
                .niveauVertical(hauteur)
                .build();

        Montagne montagne = new Montagne.MontagneBuilder()
                .coorodonnees(coordonnees)
                .build();

        this.montagnes.add(montagne);

    }

    public void creerAventurier(String prenom, int largeur, int hauteur, String orientation, String trajets) {
        zoneDeJeu[largeur][hauteur] = AVENTURIER;
        Aventurier aventurier = genericBuilder.creerNouvelAventurier(prenom, largeur, hauteur, orientation, trajets);
        this.aventuriers.add(aventurier);
    }

    public void creerTresor(int largeur, int hauteur, int nombreDeTresors) {
        zoneDeJeu[largeur][hauteur] = TRESOR;
        Tresor tresor = genericBuilder.creerNouveauTresor(largeur, hauteur, nombreDeTresors);
        this.tresors.add(tresor);
    }

    public List<Tresor> getTresors() {
        return this.tresors;
    }

    public List<Aventurier> getAventuriers() {
        return this.aventuriers;
    }

    public void animeAventuriers() {
        this.getAventuriers().forEach(aventurier -> {
            if (aventurier.getTrajet().isEmpty()) {
                return;
            }

            if (aventurier.getTrajet().startsWith(AVANCE.getValue())) {
                avanceAventurier(aventurier);
            } else {
                aventurier.tourne();
            }
        });
    }

    private void avanceAventurier(Aventurier aventurier) {
        List<Coordonnees> coordonneesBloquantes = getCoordonneesBloquantes();
        this.zoneDeJeu[aventurier.getCoordonnees().getNiveauHorizontal()][aventurier.getCoordonnees().getNiveauVertical()] = Character.MIN_VALUE;
        aventurier.avance(coordonneesBloquantes, this.tresors, this.largeur - 1, this.hauteur - 1);
        this.zoneDeJeu[aventurier.getCoordonnees().getNiveauHorizontal()][aventurier.getCoordonnees().getNiveauVertical()] = AVENTURIER;
    }

    private List<Coordonnees> getCoordonneesBloquantes() {
        List<Coordonnees> coordonneesBloquantes = new ArrayList<>();
        List<Coordonnees> coordonneesAventuriers = this.aventuriers.stream()
                .map(Aventurier::getCoordonnees)
                .collect(Collectors.toList());
        List<Coordonnees> coordonneesMontagnes = this.montagnes.stream()
                .map(Montagne::getCoordonnees)
                .collect(Collectors.toList());

        coordonneesBloquantes.addAll(coordonneesMontagnes);
        coordonneesBloquantes.addAll(coordonneesAventuriers);
        return coordonneesBloquantes;
    }

    public String toFormatSortie() {
        return ElementACreer.C + SEPARATOR + this.getLargeur() + SEPARATOR + this.getHauteur() + System.lineSeparator();
    }

    public static class CarteBuilder {

        private int hauteur;
        private int largeur;

        public CarteBuilder hauteur(int hauteur) {
            this.hauteur = hauteur;
            return this;
        }

        public CarteBuilder largeur(int largeur) {
            this.largeur = largeur;
            return this;
        }

        public Carte build() {
            return new Carte(this);
        }
    }
}
