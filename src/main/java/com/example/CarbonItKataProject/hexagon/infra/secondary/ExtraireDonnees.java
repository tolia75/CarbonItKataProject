package com.example.CarbonItKataProject.hexagon.infra.secondary;

import com.example.CarbonItKataProject.hexagon.domain.model.Aventurier;
import com.example.CarbonItKataProject.hexagon.domain.model.Carte;
import com.example.CarbonItKataProject.hexagon.domain.model.Montagne;
import com.example.CarbonItKataProject.hexagon.domain.model.Tresor;
import com.example.CarbonItKataProject.hexagon.domain.port.secondary.EcritureDonnees;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

@Component
public class ExtraireDonnees implements EcritureDonnees {

    private static final String PATH_TO_CREATE = "C:\\Projet\\CarbonItKataProject\\src\\main\\resources\\resultat.txt";

    @Override
    public void creerFichierRapportDeFinDuJeu(Carte carte) {
        char[][] zoneDeJeu = carte.getZoneDeJeu();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(carte.toFormatSortie());

        List<Montagne> montagnes = carte.getMontagnes();
        montagnes.forEach(montagne -> stringBuilder.append(montagne.toFormatSortie()));

        List<Tresor> tresors = carte.getTresors();
        tresors.forEach(tresor -> {
            if (tresor.estTresorNonVide()) {
                stringBuilder.append(tresor.toFormatSortie());
            }
        });

        List<Aventurier> aventuriers = carte.getAventuriers();
        aventuriers.forEach(aventurier -> stringBuilder.append(aventurier.toFormatSortie()));

        try {
            ecrireDonnees(stringBuilder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void ecrireDonnees(String ligne) throws IOException {
        File file = new File(PATH_TO_CREATE);
        Writer writer = new FileWriter(file);
        BufferedWriter bufferedWriter = new BufferedWriter(writer);
        bufferedWriter.write(ligne);
        bufferedWriter.newLine();
        bufferedWriter.close();
    }
}
