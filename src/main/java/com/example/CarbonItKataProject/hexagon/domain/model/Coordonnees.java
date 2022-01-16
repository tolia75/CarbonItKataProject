package com.example.CarbonItKataProject.hexagon.domain.model;

public class Coordonnees {

    private int niveauVertical;
    private int niveauHorizontal;

    public Coordonnees(CoordonneesBuilder coordonneesBuilder) {
        this.niveauVertical = coordonneesBuilder.niveauVertical;
        this.niveauHorizontal = coordonneesBuilder.niveauHorizontal;
    }

    public int getNiveauVertical() {
        return niveauVertical;
    }

    public int getNiveauHorizontal() {
        return niveauHorizontal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordonnees that = (Coordonnees) o;
        return niveauVertical == that.niveauVertical &&
                niveauHorizontal == that.niveauHorizontal;
    }

    public static class CoordonneesBuilder {
        private int niveauVertical;
        private int niveauHorizontal;

        public CoordonneesBuilder niveauVertical(int niveauVertical) {
            this.niveauVertical = niveauVertical;
            return this;
        }

        public CoordonneesBuilder niveauHorizontal(int niveauHorizontal) {
            this.niveauHorizontal = niveauHorizontal;
            return this;
        }

        public Coordonnees build() {
            return new Coordonnees(this);
        }
    }
}
