package com.example.CarbonItKataProject.hexagon.domain.model;

import java.util.Optional;

import static com.example.CarbonItKataProject.hexagon.domain.model.Mouvement.TOURNE_A_DROITE;

public class Est extends Orientation {

    @Override
    public Orientation tourne(String directionDuChangementDeDirection) {
        if (directionDuChangementDeDirection.equals(TOURNE_A_DROITE.getValue())) {
            return new Sud();
        }
        return new Nord();
    }

    @Override
    public Optional<Coordonnees> avance(Coordonnees coordonnees, int largeurMaxCarte, int hauteurMaxCarte) {
        Coordonnees nouvellesCoordonnes = new Coordonnees.CoordonneesBuilder()
                .niveauHorizontal(coordonnees.getNiveauHorizontal() + 1)
                .niveauVertical(coordonnees.getNiveauVertical())
                .build();


        if (sontNouvellesCoordonneesEnDehorsLaCarte(nouvellesCoordonnes, largeurMaxCarte, hauteurMaxCarte)) {
            return Optional.empty();
        } else {
            return Optional.of(nouvellesCoordonnes);
        }
    }

    public String getStringValue() {
        return "E";
    }
}
