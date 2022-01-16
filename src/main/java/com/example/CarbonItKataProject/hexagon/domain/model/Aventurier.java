package com.example.CarbonItKataProject.hexagon.domain.model;

import java.util.List;
import java.util.stream.Collectors;

public class Aventurier extends ElementDuJeu{

    private String prenom;
    private Coordonnees coordonnees;
    private Orientation orientation;
    private String trajet;
    private int nombreDeTresors;

    public Aventurier(AventurierBuilder aventurierBuilder) {
        this.prenom = aventurierBuilder.prenom;
        this.coordonnees = aventurierBuilder.coordonnees;
        this.orientation = aventurierBuilder.orientation;
        this.trajet = aventurierBuilder.trajet;
    }

    public String getPrenom() {
        return prenom;
    }

    public Coordonnees getCoordonnees() {
        return coordonnees;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public String getTrajet() {
        return trajet;
    }

    public void avance(List<Coordonnees> coordonneesBloquantes, List<Tresor> tresors, int largeurMaxCarte, int hauteurMaxCarte) {
        this.orientation.avance(this.coordonnees, largeurMaxCarte, hauteurMaxCarte)
                .filter(nouvelleCoordonnees -> estNouvelleCoordonneesAutorisee(coordonneesBloquantes, nouvelleCoordonnees))
                .ifPresent(nouvelleCoordonnee -> this.coordonnees = nouvelleCoordonnee);

        tresors.stream()
                .filter(tresor -> estAventurierSurTresor(tresor.getCoordonnees(), this.coordonnees))
                .filter(Tresor::estTresorNonVide)
                .collect(Collectors.toList())
                .stream()
                .findFirst()
                .ifPresent(tresor -> {
                    tresor.tresorTrouve();
                    tresorTrouve();
                });


        supprimeDuTrajetLeMouvementRealise();
    }

    private void tresorTrouve() {
        this.nombreDeTresors++;
    }

    private boolean estAventurierSurTresor(Coordonnees coordonneesTresor, Coordonnees coordonneesAventurier) {
        return coordonneesTresor.equals(coordonneesAventurier);
    }

    private boolean estNouvelleCoordonneesAutorisee(List<Coordonnees> coordonneesBloquantes, Coordonnees nouvellesCoordonneesATester) {
        return coordonneesBloquantes.stream()
                .noneMatch((coordonneesMontagne -> coordonneesMontagne.equals(nouvellesCoordonneesATester)));
    }

    public void tourne() {
        String directionDuChangeementDeDirection = this.trajet.substring(0,1);
        this.orientation = this.orientation.tourne(directionDuChangeementDeDirection);
        supprimeDuTrajetLeMouvementRealise();
    }

    private void supprimeDuTrajetLeMouvementRealise() {
        this.trajet = this.trajet.substring(1);
    }

    public int getNombreDeTresor() {
        return this.nombreDeTresors;
    }

    public boolean estTrajetEnCours() {
        return !this.trajet.isEmpty();
    }

    public String toFormatSortie() {
        return ElementACreer.A + SEPARATOR + this.getPrenom() + SEPARATOR + this.getCoordonnees().getNiveauHorizontal() +
                SEPARATOR + this.getCoordonnees().getNiveauVertical() + SEPARATOR + this.orientation.getStringValue() +
                SEPARATOR + this.nombreDeTresors + System.lineSeparator();
    }

    public static class AventurierBuilder {

        private String prenom;
        private Coordonnees coordonnees;
        private Orientation orientation;
        private String trajet;

        public AventurierBuilder prenom(String prenom) {
            this.prenom = prenom;
            return this;
        }

        public AventurierBuilder trajet(String trajet) {
            this.trajet = trajet;
            return this;
        }

        public AventurierBuilder coordonnees(Coordonnees coordonnees) {
            this.coordonnees = coordonnees;
            return this;
        }

        public AventurierBuilder orientation(OrientationEnum orientationEnum) {
            switch (orientationEnum) {
                case O:
                    this.orientation = new Ouest();
                    break;
                case E:
                    this.orientation = new Est();
                    break;
                case N:
                    this.orientation = new Nord();
                    break;
                case S:
                    this.orientation = new Sud();
                    break;
            }
            return this;
        }

        public Aventurier build() {
            return new Aventurier(this);
        }
    }

}
