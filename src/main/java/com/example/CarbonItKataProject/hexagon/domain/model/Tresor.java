package com.example.CarbonItKataProject.hexagon.domain.model;

public class Tresor extends ElementDuJeu{

    private int nombreDeTresor;
    private Coordonnees coordonnees;

    public Tresor(TresorBuilder tresorBuilder) {
        this.nombreDeTresor = tresorBuilder.nombreDeTresor;
        this.coordonnees = tresorBuilder.coordonnees;
    }

    public int getNombreDeTresor() {
        return nombreDeTresor;
    }

    public Coordonnees getCoordonnees() {
        return coordonnees;
    }

    public void tresorTrouve() {
        this.nombreDeTresor--;
    }

    public boolean estTresorNonVide() {
        return this.nombreDeTresor > 0;
    }

    public String toFormatSortie() {
        return ElementACreer.T+ SEPARATOR + this.getCoordonnees().getNiveauHorizontal()+SEPARATOR+this.getCoordonnees().getNiveauVertical()+System.lineSeparator();
    }

    public static class TresorBuilder {
        private int nombreDeTresor;
        private Coordonnees coordonnees;

        public TresorBuilder nombreDeTresor(int nombreDeTresor) {
            this.nombreDeTresor = nombreDeTresor;
            return this;
        }

        public TresorBuilder coordonnees(Coordonnees coordonnees) {
            this.coordonnees = coordonnees;
            return this;
        }

        public Tresor build() {
            return new Tresor(this);
        }
    }
}
