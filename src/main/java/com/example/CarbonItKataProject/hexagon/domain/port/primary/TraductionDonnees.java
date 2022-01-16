package com.example.CarbonItKataProject.hexagon.domain.port.primary;

import com.example.CarbonItKataProject.hexagon.domain.model.Carte;

public interface TraductionDonnees {

    void lireLigne(String ligne);

    Carte getCarte();

}
