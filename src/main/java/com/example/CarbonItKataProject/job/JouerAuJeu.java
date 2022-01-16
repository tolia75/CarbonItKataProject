package com.example.CarbonItKataProject.job;

import com.example.CarbonItKataProject.hexagon.domain.model.Carte;
import com.example.CarbonItKataProject.hexagon.domain.port.primary.Animation;
import com.example.CarbonItKataProject.hexagon.domain.port.secondary.EcritureDonnees;
import com.example.CarbonItKataProject.hexagon.infra.primary.ChargerDonnees;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class JouerAuJeu {

    private Animation animation;
    private ChargerDonnees chargerDonnees;
    private EcritureDonnees ecritureDonnees;

    public JouerAuJeu(Animation animation, ChargerDonnees chargerDonnees, EcritureDonnees ecritureDonnees) {
        this.animation = animation;
        this.chargerDonnees = chargerDonnees;
        this.ecritureDonnees = ecritureDonnees;
    }

    @Scheduled(cron = "*/10 * * * * *")
    public void jouer() {
        Carte carte = this.chargerDonnees.chargerDonnees()
                .orElseThrow(() -> new RuntimeException("Erreur de chargement de la carte"));
        this.animation.animeAventuriers(carte);
        this.ecritureDonnees.creerFichierRapportDeFinDuJeu(carte);
    }
}
