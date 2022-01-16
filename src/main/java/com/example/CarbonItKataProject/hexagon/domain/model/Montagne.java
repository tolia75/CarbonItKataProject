package com.example.CarbonItKataProject.hexagon.domain.model;

public class Montagne extends ElementDuJeu {

    private Coordonnees coordonnees;

    public Montagne(MontagneBuilder montagneBuilder) {
        this.coordonnees = montagneBuilder.coordonnees;
    }

    public Coordonnees getCoordonnees() {
        return coordonnees;
    }

    @Override
    public String toFormatSortie() {
        return ElementACreer.M + SEPARATOR + this.getCoordonnees().getNiveauHorizontal() + SEPARATOR +
                this.getCoordonnees().getNiveauVertical() + System.lineSeparator();
    }

    public static class MontagneBuilder {
        private Coordonnees coordonnees;

        public MontagneBuilder coorodonnees(Coordonnees coordonnees) {
            this.coordonnees = coordonnees;
            return this;
        }

        public Montagne build() {
            return new Montagne(this);
        }
    }
}
