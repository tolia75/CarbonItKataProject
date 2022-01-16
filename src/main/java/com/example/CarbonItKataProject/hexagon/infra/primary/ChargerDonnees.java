package com.example.CarbonItKataProject.hexagon.infra.primary;

import com.example.CarbonItKataProject.hexagon.domain.model.Carte;
import com.example.CarbonItKataProject.hexagon.domain.port.primary.TraductionDonnees;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Optional;

@Component
public class ChargerDonnees {

    private static final String FICHIER_A_LIRE = "C:\\Projet\\CarbonItKataProject\\src\\main\\resources\\donnees.txt";

    private TraductionDonnees traductionDonnees;

    public ChargerDonnees(TraductionDonnees traductionDonnees) {
        this.traductionDonnees = traductionDonnees;
    }

    public Optional<Carte> chargerDonnees() {
        try {
            File fichierALire = new File(FICHIER_A_LIRE);
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fichierALire));

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                this.traductionDonnees.lireLigne(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Optional.of(this.traductionDonnees.getCarte());
    }


}
