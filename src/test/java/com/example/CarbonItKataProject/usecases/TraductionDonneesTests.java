package com.example.CarbonItKataProject.usecases;

import com.example.CarbonItKataProject.hexagon.domain.model.Aventurier;
import com.example.CarbonItKataProject.hexagon.domain.model.Carte;
import com.example.CarbonItKataProject.hexagon.domain.model.Nord;
import com.example.CarbonItKataProject.hexagon.domain.model.Sud;
import com.example.CarbonItKataProject.hexagon.domain.model.Tresor;
import com.example.CarbonItKataProject.hexagon.domain.port.primary.TraductionDonnees;
import com.example.CarbonItKataProject.hexagon.domain.usecases.TraductionDonneesHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class TraductionDonneesTests {

    private static final String COORDONNEES_CARTE = "C - 3 - 4";
    private static final char MONTAGNE = 'M';
    private static final char TRESOR = 'T';
    private static final char AVENTURIER = 'A';
    private TraductionDonnees traductionDonnees;

    @BeforeEach
    void setUp() {
        traductionDonnees = new TraductionDonneesHandler();
    }

    @Test
    void neDoitPasGererCommentaire() {
        // When
        traductionDonnees.lireLigne("# Commentaire à ne pas prendre en compte");

        // assert
        assertNull(traductionDonnees.getCarte());
    }

    @Test
    void doitCreerCarteDeLaTailleIndiquee() {
        // When
        traductionDonnees.lireLigne(COORDONNEES_CARTE);

        // Assert
        assertNotNull(traductionDonnees.getCarte());
        Carte carte = traductionDonnees.getCarte();
        assertEquals(3, carte.getLargeur());
        assertEquals(4, carte.getHauteur());
    }

    @Test
    void doitCreerMontagneALendroitIndique() {
        // Given
        traductionDonnees.lireLigne(COORDONNEES_CARTE);

        // When
        traductionDonnees.lireLigne("M - 1 - 0");
        traductionDonnees.lireLigne("M - 2 - 1");

        // Assert
        assertNotNull(traductionDonnees.getCarte());
        Carte carte = traductionDonnees.getCarte();
        assertEquals(MONTAGNE, carte.getZoneDeJeu()[1][0]);
        assertEquals(MONTAGNE, carte.getZoneDeJeu()[2][1]);
    }

    @Test
    void doitCreerTresorsALendroitIndiqueAvecLeBonNombreDeTresor() {
        // Given
        traductionDonnees.lireLigne(COORDONNEES_CARTE);

        // When
        traductionDonnees.lireLigne("T - 1 - 3 - 2");
        traductionDonnees.lireLigne("T - 2 - 1 - 1");

        // Assert
        assertNotNull(traductionDonnees.getCarte());
        Carte carte = traductionDonnees.getCarte();
        assertEquals(TRESOR, carte.getZoneDeJeu()[1][3]);
        assertEquals(TRESOR, carte.getZoneDeJeu()[2][1]);
        assertEquals(2, carte.getTresors().size());

        Tresor tresor1 = carte.getTresors().get(0);
        assertEquals(2, tresor1.getNombreDeTresor());
        assertEquals(1, tresor1.getCoordonnees().getNiveauHorizontal());
        assertEquals(3, tresor1.getCoordonnees().getNiveauVertical());


        Tresor tresor2 = carte.getTresors().get(1);
        assertEquals(1, tresor2.getNombreDeTresor());
        assertEquals(2, tresor2.getCoordonnees().getNiveauHorizontal());
        assertEquals(1, tresor2.getCoordonnees().getNiveauVertical());
    }

    @Test
    void doitCreerAventurierALendroitIndiqueAVecLesBonParametres() {
        // Given
        traductionDonnees.lireLigne(COORDONNEES_CARTE);

        // When
        traductionDonnees.lireLigne("A - Lara - 0 - 3 - S - AADAGA");
        traductionDonnees.lireLigne("A - Thomas - 1 - 2 - N - GADAADA");

        // Assert
        assertNotNull(traductionDonnees.getCarte());
        Carte carte = traductionDonnees.getCarte();
        assertEquals(AVENTURIER, carte.getZoneDeJeu()[0][3]);
        assertEquals(AVENTURIER, carte.getZoneDeJeu()[1][2]);
        assertEquals(2, carte.getAventuriers().size());

        Aventurier aventurier1 = carte.getAventuriers().get(0);
        assertEquals("Lara", aventurier1.getPrenom());
        assertEquals(0, aventurier1.getCoordonnees().getNiveauHorizontal());
        assertEquals(3, aventurier1.getCoordonnees().getNiveauVertical());
        assertEquals(true, aventurier1.getOrientation() instanceof Sud);
        assertEquals("AADAGA", aventurier1.getTrajet());

        Aventurier aventurier2 = carte.getAventuriers().get(1);
        assertEquals("Thomas", aventurier2.getPrenom());
        assertEquals(true, aventurier2.getOrientation() instanceof Nord);
        assertEquals("GADAADA", aventurier2.getTrajet());
        assertEquals(1, aventurier2.getCoordonnees().getNiveauHorizontal());
        assertEquals(2, aventurier2.getCoordonnees().getNiveauVertical());
    }
}
