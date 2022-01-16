package com.example.CarbonItKataProject.hexagon.domain.model;

import java.util.Optional;

import static com.example.CarbonItKataProject.hexagon.domain.model.Mouvement.TOURNE_A_DROITE;

public class Nord extends Orientation {

    @Override
    public Orientation tourne(String directionDuChangementDeDirection) {
        if(directionDuChangementDeDirection.equals(TOURNE_A_DROITE.getValue())) {
            return new Est();
        }
        return new Ouest();
    }

    @Override
    public Optional<Coordonnees> avance(Coordonnees coordonnees, int largeurMaxCarte, int hauteurMaxCarte) {
        Coordonnees nouvellesCoordonnes = new Coordonnees.CoordonneesBuilder()
                .niveauHorizontal(coordonnees.getNiveauHorizontal())
                .niveauVertical(coordonnees.getNiveauVertical() - 1)
                .build();

        if (sontNouvellesCoordonneesEnDehorsLaCarte(nouvellesCoordonnes, largeurMaxCarte, hauteurMaxCarte)) {
            return Optional.empty();
        } else {
            return Optional.of(nouvellesCoordonnes);
        }
    }

    public String getStringValue() {
        return "N";
    }
}
