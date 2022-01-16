package com.example.CarbonItKataProject.hexagon.domain.port.secondary;

import com.example.CarbonItKataProject.hexagon.domain.model.Carte;

public interface EcritureDonnees {

    void creerFichierRapportDeFinDuJeu(Carte carte);
}
