package com.example.CarbonItKataProject.hexagon.domain.usecases;

import com.example.CarbonItKataProject.hexagon.domain.model.Aventurier;
import com.example.CarbonItKataProject.hexagon.domain.model.Carte;
import com.example.CarbonItKataProject.hexagon.domain.port.primary.Animation;

public class AnimationHandler implements Animation {

    @Override
    public void animeAventuriers(Carte carte) {
        while (carte.getAventuriers().stream().anyMatch(Aventurier::estTrajetEnCours)) {
            carte.animeAventuriers();
        }
    }
}
