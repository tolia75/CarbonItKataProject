package com.example.CarbonItKataProject.usecases;

import com.example.CarbonItKataProject.hexagon.domain.model.Aventurier;
import com.example.CarbonItKataProject.hexagon.domain.model.Carte;
import com.example.CarbonItKataProject.hexagon.domain.model.Est;
import com.example.CarbonItKataProject.hexagon.domain.model.Nord;
import com.example.CarbonItKataProject.hexagon.domain.model.Ouest;
import com.example.CarbonItKataProject.hexagon.domain.model.Sud;
import com.example.CarbonItKataProject.hexagon.domain.model.Tresor;
import com.example.CarbonItKataProject.hexagon.domain.port.primary.Animation;
import com.example.CarbonItKataProject.hexagon.domain.port.primary.TraductionDonnees;
import com.example.CarbonItKataProject.hexagon.domain.usecases.AnimationHandler;
import com.example.CarbonItKataProject.hexagon.domain.usecases.TraductionDonneesHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AnimationTest {

    private static final String COORDONNEES_CARTE = "C - 3 - 4";
    private static final char AVENTURIER = 'A';


    private Animation animation;
    private CarteFixture carteFixture;
    private TraductionDonnees traductionDonnees;

    @BeforeEach
    void setUp() {
        traductionDonnees = new TraductionDonneesHandler();
        carteFixture = new CarteFixture();
        animation = new AnimationHandler();
    }

    @Test
    void doitFaireAvancerUnAventurierVersLeBas() {
        // Given
        traductionDonnees.lireLigne(COORDONNEES_CARTE);
        traductionDonnees.lireLigne("A - Thomas - 1 - 1 - S - A");
        Carte carte = traductionDonnees.getCarte();

        // When
        animation.animeAventuriers(carte);

        // Assert
        assertEquals(1, carte.getAventuriers().size());

        Aventurier aventurier = carte.getAventuriers().get(0);
        assertEquals(2, aventurier.getCoordonnees().getNiveauVertical());
        assertEquals(1, aventurier.getCoordonnees().getNiveauHorizontal());
        assertEquals(true, aventurier.getOrientation() instanceof Sud);
        assertEquals("", aventurier.getTrajet());

        assertEquals(Character.MIN_VALUE, carte.getZoneDeJeu()[1][1]);
        assertEquals(AVENTURIER, carte.getZoneDeJeu()[1][2]);
    }

    @Test
    void doitFaireAvancerUnAventurierVersLeHaut() {
        // Given
        traductionDonnees.lireLigne(COORDONNEES_CARTE);
        traductionDonnees.lireLigne("A - Thomas - 1 - 1 - N - A");
        Carte carte = traductionDonnees.getCarte();

        // When
        animation.animeAventuriers(carte);

        // Assert
        assertEquals(1, carte.getAventuriers().size());
        Aventurier aventurier = carte.getAventuriers().get(0);
        assertEquals(0, aventurier.getCoordonnees().getNiveauVertical());
        assertEquals(1, aventurier.getCoordonnees().getNiveauHorizontal());
        assertEquals(true, aventurier.getOrientation() instanceof Nord);
        assertEquals("", aventurier.getTrajet());

        assertEquals(Character.MIN_VALUE, carte.getZoneDeJeu()[1][1]);
        assertEquals(AVENTURIER, carte.getZoneDeJeu()[1][0]);
    }

    @Test
    void doitFaireAvancerUnAventurierVersLaDroite() {
        // Given
        traductionDonnees.lireLigne(COORDONNEES_CARTE);
        traductionDonnees.lireLigne("A - Thomas - 1 - 1 - E - A");
        Carte carte = traductionDonnees.getCarte();

        // When
        animation.animeAventuriers(carte);

        // Assert
        assertEquals(1, carte.getAventuriers().size());
        Aventurier aventurier = carte.getAventuriers().get(0);
        assertEquals(1, aventurier.getCoordonnees().getNiveauVertical());
        assertEquals(2, aventurier.getCoordonnees().getNiveauHorizontal());
        assertEquals(true, aventurier.getOrientation() instanceof Est);
        assertEquals("", aventurier.getTrajet());

        assertEquals(Character.MIN_VALUE, carte.getZoneDeJeu()[1][1]);
        assertEquals(AVENTURIER, carte.getZoneDeJeu()[2][1]);
    }

    @Test
    void doitFaireAvancerUnAventurierVersLaGauche() {
        // Given
        traductionDonnees.lireLigne(COORDONNEES_CARTE);
        traductionDonnees.lireLigne("A - Thomas - 1 - 1 - O - A");
        Carte carte = traductionDonnees.getCarte();

        // When
        animation.animeAventuriers(carte);

        // Assert
        assertEquals(1, carte.getAventuriers().size());
        Aventurier aventurier = carte.getAventuriers().get(0);
        assertEquals(1, aventurier.getCoordonnees().getNiveauVertical());
        assertEquals(0, aventurier.getCoordonnees().getNiveauHorizontal());
        assertEquals(true, aventurier.getOrientation() instanceof Ouest);
        assertEquals("", aventurier.getTrajet());

        assertEquals(Character.MIN_VALUE, carte.getZoneDeJeu()[1][1]);
        assertEquals(AVENTURIER, carte.getZoneDeJeu()[0][1]);
    }

    @Test
    void doitFaireTournerUnAventurierVersLaGaucheDepuisOuest() {
        // Given
        traductionDonnees.lireLigne(COORDONNEES_CARTE);
        traductionDonnees.lireLigne("A - Thomas - 1 - 1 - O - G");
        Carte carte = traductionDonnees.getCarte();

        // When
        animation.animeAventuriers(carte);

        // Assert
        assertEquals(1, carte.getAventuriers().size());
        Aventurier aventurier = carte.getAventuriers().get(0);
        assertEquals(1, aventurier.getCoordonnees().getNiveauVertical());
        assertEquals(1, aventurier.getCoordonnees().getNiveauHorizontal());
        assertEquals(true, aventurier.getOrientation() instanceof Sud);
        assertEquals("", aventurier.getTrajet());

        assertEquals(AVENTURIER, carte.getZoneDeJeu()[1][1]);
    }

    @Test
    void doitFaireTournerUnAventurierVersLaDroiteDepuisOuest() {
        // Given
        traductionDonnees.lireLigne(COORDONNEES_CARTE);
        traductionDonnees.lireLigne("A - Thomas - 1 - 1 - O - D");
        Carte carte = traductionDonnees.getCarte();

        // When
        animation.animeAventuriers(carte);

        // Assert
        assertEquals(1, carte.getAventuriers().size());
        Aventurier aventurier = carte.getAventuriers().get(0);
        assertEquals(1, aventurier.getCoordonnees().getNiveauVertical());
        assertEquals(1, aventurier.getCoordonnees().getNiveauHorizontal());
        assertEquals(true, aventurier.getOrientation() instanceof Nord);
        assertEquals("", aventurier.getTrajet());

        assertEquals(AVENTURIER, carte.getZoneDeJeu()[1][1]);
    }

    @Test
    void doitFaireTournerUnAventurierVersLaGaucheDepuisEst() {
        // Given
        traductionDonnees.lireLigne(COORDONNEES_CARTE);
        traductionDonnees.lireLigne("A - Thomas - 1 - 1 - E - G");
        Carte carte = traductionDonnees.getCarte();

        // When
        animation.animeAventuriers(carte);

        // Assert
        assertEquals(1, carte.getAventuriers().size());
        Aventurier aventurier = carte.getAventuriers().get(0);
        assertEquals(1, aventurier.getCoordonnees().getNiveauVertical());
        assertEquals(1, aventurier.getCoordonnees().getNiveauHorizontal());
        assertEquals(true, aventurier.getOrientation() instanceof Nord);
        assertEquals("", aventurier.getTrajet());

        assertEquals(AVENTURIER, carte.getZoneDeJeu()[1][1]);
    }

    @Test
    void doitFaireTournerUnAventurierVersLaDroiteDepuisEst() {
        // Given
        traductionDonnees.lireLigne(COORDONNEES_CARTE);
        traductionDonnees.lireLigne("A - Thomas - 1 - 1 - E - D");
        Carte carte = traductionDonnees.getCarte();

        // When
        animation.animeAventuriers(carte);

        // Assert
        assertEquals(1, carte.getAventuriers().size());
        Aventurier aventurier = carte.getAventuriers().get(0);
        assertEquals(1, aventurier.getCoordonnees().getNiveauVertical());
        assertEquals(1, aventurier.getCoordonnees().getNiveauHorizontal());
        assertEquals(true, aventurier.getOrientation() instanceof Sud);
        assertEquals("", aventurier.getTrajet());

        assertEquals(AVENTURIER, carte.getZoneDeJeu()[1][1]);
    }

    @Test
    void doitFaireTournerUnAventurierVersLaGaucheDepuisSud() {
        // Given
        traductionDonnees.lireLigne(COORDONNEES_CARTE);
        traductionDonnees.lireLigne("A - Thomas - 1 - 1 - S - G");
        Carte carte = traductionDonnees.getCarte();

        // When
        animation.animeAventuriers(carte);

        // Assert
        assertEquals(1, carte.getAventuriers().size());
        Aventurier aventurier = carte.getAventuriers().get(0);
        assertEquals(1, aventurier.getCoordonnees().getNiveauVertical());
        assertEquals(1, aventurier.getCoordonnees().getNiveauHorizontal());
        assertEquals(true, aventurier.getOrientation() instanceof Est);
        assertEquals("", aventurier.getTrajet());

        assertEquals(AVENTURIER, carte.getZoneDeJeu()[1][1]);
    }

    @Test
    void doitFaireTournerUnAventurierVersLaDroiteDepuisSud() {
        // Given
        traductionDonnees.lireLigne(COORDONNEES_CARTE);
        traductionDonnees.lireLigne("A - Thomas - 1 - 1 - S - D");
        Carte carte = traductionDonnees.getCarte();

        // When
        animation.animeAventuriers(carte);

        // Assert
        assertEquals(1, carte.getAventuriers().size());
        Aventurier aventurier = carte.getAventuriers().get(0);
        assertEquals(1, aventurier.getCoordonnees().getNiveauVertical());
        assertEquals(1, aventurier.getCoordonnees().getNiveauHorizontal());
        assertEquals(true, aventurier.getOrientation() instanceof Ouest);
        assertEquals("", aventurier.getTrajet());

        assertEquals(AVENTURIER, carte.getZoneDeJeu()[1][1]);
    }

    @Test
    void doitFaireTournerUnAventurierVersLaGaucheDepuisNord() {
        // Given
        traductionDonnees.lireLigne(COORDONNEES_CARTE);
        traductionDonnees.lireLigne("A - Thomas - 1 - 1 - N - G");
        Carte carte = traductionDonnees.getCarte();

        // When
        animation.animeAventuriers(carte);

        // Assert
        assertEquals(1, carte.getAventuriers().size());
        Aventurier aventurier = carte.getAventuriers().get(0);
        assertEquals(1, aventurier.getCoordonnees().getNiveauVertical());
        assertEquals(1, aventurier.getCoordonnees().getNiveauHorizontal());
        assertEquals(true, aventurier.getOrientation() instanceof Ouest);
        assertEquals("", aventurier.getTrajet());

        assertEquals(AVENTURIER, carte.getZoneDeJeu()[1][1]);
    }

    @Test
    void doitFaireTournerUnAventurierVersLaDroiteDepuisNord() {
        // Given
        traductionDonnees.lireLigne(COORDONNEES_CARTE);
        traductionDonnees.lireLigne("A - Thomas - 1 - 1 - N - D");
        Carte carte = traductionDonnees.getCarte();

        // When
        animation.animeAventuriers(carte);

        // Assert
        assertEquals(1, carte.getAventuriers().size());
        Aventurier aventurier = carte.getAventuriers().get(0);
        assertEquals(1, aventurier.getCoordonnees().getNiveauVertical());
        assertEquals(1, aventurier.getCoordonnees().getNiveauHorizontal());
        assertEquals(true, aventurier.getOrientation() instanceof Est);
        assertEquals("", aventurier.getTrajet());

        assertEquals(AVENTURIER, carte.getZoneDeJeu()[1][1]);
    }

    @Test
    void neDoitPasTournerNiAvancerSiPasDeTrajet() {
        // Given
        traductionDonnees.lireLigne(COORDONNEES_CARTE);
        traductionDonnees.lireLigne("A - Thomas - 1 - 1 - N - D");
        Carte carte = traductionDonnees.getCarte();

        // When
        animation.animeAventuriers(carte);
        animation.animeAventuriers(carte);

        // Assert
        assertEquals(1, carte.getAventuriers().size());
        Aventurier aventurier = carte.getAventuriers().get(0);
        assertEquals(1, aventurier.getCoordonnees().getNiveauVertical());
        assertEquals(1, aventurier.getCoordonnees().getNiveauHorizontal());
        assertEquals(true, aventurier.getOrientation() instanceof Est);
        assertEquals("", aventurier.getTrajet());

        assertEquals(AVENTURIER, carte.getZoneDeJeu()[1][1]);
    }

    @Test
    void doitResterSurPlaceSiDeplacementHorsCarteSud() {
        // Given
        traductionDonnees.lireLigne("C - 2 - 2");
        traductionDonnees.lireLigne("A - Thomas - 1 - 1 - S - A");
        Carte carte = traductionDonnees.getCarte();

        // When
        animation.animeAventuriers(carte);

        // Assert
        assertEquals(1, carte.getAventuriers().size());
        Aventurier aventurier = carte.getAventuriers().get(0);
        assertEquals(1, aventurier.getCoordonnees().getNiveauVertical());
        assertEquals(1, aventurier.getCoordonnees().getNiveauHorizontal());
        assertEquals(true, aventurier.getOrientation() instanceof Sud);
        assertEquals("", aventurier.getTrajet());

        assertEquals(AVENTURIER, carte.getZoneDeJeu()[1][1]);
    }

    @Test
    void doitResterSurPlaceSiDeplacementHorsCarteNord() {
        // Given
        traductionDonnees.lireLigne("C - 2 - 2");
        traductionDonnees.lireLigne("A - Thomas - 0 - 0 - N - A");
        Carte carte = traductionDonnees.getCarte();

        // When
        animation.animeAventuriers(carte);

        // Assert
        assertEquals(1, carte.getAventuriers().size());
        Aventurier aventurier = carte.getAventuriers().get(0);
        assertEquals(0, aventurier.getCoordonnees().getNiveauVertical());
        assertEquals(0, aventurier.getCoordonnees().getNiveauHorizontal());
        assertEquals(true, aventurier.getOrientation() instanceof Nord);
        assertEquals("", aventurier.getTrajet());

        assertEquals(AVENTURIER, carte.getZoneDeJeu()[0][0]);
    }

    @Test
    void doitResterSurPlaceSiDeplacementHorsCarteEst() {
        // Given
        traductionDonnees.lireLigne("C - 2 - 2");
        traductionDonnees.lireLigne("A - Thomas - 1 - 1 - E - A");
        Carte carte = traductionDonnees.getCarte();

        // When
        animation.animeAventuriers(carte);

        // Assert
        assertEquals(1, carte.getAventuriers().size());
        Aventurier aventurier = carte.getAventuriers().get(0);
        assertEquals(1, aventurier.getCoordonnees().getNiveauVertical());
        assertEquals(1, aventurier.getCoordonnees().getNiveauHorizontal());
        assertEquals(true, aventurier.getOrientation() instanceof Est);
        assertEquals("", aventurier.getTrajet());

        assertEquals(AVENTURIER, carte.getZoneDeJeu()[1][1]);
    }

    @Test
    void doitResterSurPlaceSiDeplacementHorsCarteOuest() {
        // Given
        traductionDonnees.lireLigne("C - 2 - 2");
        traductionDonnees.lireLigne("A - Thomas - 0 - 0 - O - A");
        Carte carte = traductionDonnees.getCarte();

        // When
        animation.animeAventuriers(carte);

        // Assert
        assertEquals(1, carte.getAventuriers().size());
        Aventurier aventurier = carte.getAventuriers().get(0);
        assertEquals(0, aventurier.getCoordonnees().getNiveauVertical());
        assertEquals(0, aventurier.getCoordonnees().getNiveauHorizontal());
        assertEquals(true, aventurier.getOrientation() instanceof Ouest);
        assertEquals("", aventurier.getTrajet());

        assertEquals(AVENTURIER, carte.getZoneDeJeu()[0][0]);
    }

    @Test
    void doitResterSurPlaceSiMontagneFaceEtTrajetDitAvanceSud() {
        // Given
        traductionDonnees.lireLigne("C - 3 - 3");
        traductionDonnees.lireLigne("A - Thomas - 1 - 1 - S - A");
        traductionDonnees.lireLigne("M - 1 - 2");

        Carte carte = traductionDonnees.getCarte();

        // When
        animation.animeAventuriers(carte);

        // Assert
        assertEquals(1, carte.getAventuriers().size());
        Aventurier aventurier = carte.getAventuriers().get(0);
        assertEquals(1, aventurier.getCoordonnees().getNiveauVertical());
        assertEquals(1, aventurier.getCoordonnees().getNiveauHorizontal());
        assertEquals(true, aventurier.getOrientation() instanceof Sud);
        assertEquals("", aventurier.getTrajet());

        assertEquals(AVENTURIER, carte.getZoneDeJeu()[1][1]);
    }

    @Test
    void doitResterSurPlaceSiMontagneFaceEtTrajetDitAvanceNord() {
        // Given
        traductionDonnees.lireLigne("C - 3 - 3");
        traductionDonnees.lireLigne("A - Thomas - 1 - 1 - N - A");
        traductionDonnees.lireLigne("M - 1 - 0");

        Carte carte = traductionDonnees.getCarte();

        // When
        animation.animeAventuriers(carte);

        // Assert
        assertEquals(1, carte.getAventuriers().size());
        Aventurier aventurier = carte.getAventuriers().get(0);
        assertEquals(1, aventurier.getCoordonnees().getNiveauVertical());
        assertEquals(1, aventurier.getCoordonnees().getNiveauHorizontal());
        assertEquals(true, aventurier.getOrientation() instanceof Nord);
        assertEquals("", aventurier.getTrajet());

        assertEquals(AVENTURIER, carte.getZoneDeJeu()[1][1]);
    }

    @Test
    void doitResterSurPlaceSiAutreAventurierFaceEtTrajetDitAvanceNord() {
        // Given
        traductionDonnees.lireLigne("C - 3 - 3");
        traductionDonnees.lireLigne("A - Thomas - 1 - 1 - N - A");
        traductionDonnees.lireLigne("A - Arthur - 1 - 0 - N - A");

        Carte carte = traductionDonnees.getCarte();

        // When
        animation.animeAventuriers(carte);

        // Assert
        assertEquals(2, carte.getAventuriers().size());
        Aventurier aventurier = carte.getAventuriers().get(0);
        assertEquals(1, aventurier.getCoordonnees().getNiveauVertical());
        assertEquals(1, aventurier.getCoordonnees().getNiveauHorizontal());
        assertEquals(true, aventurier.getOrientation() instanceof Nord);
        assertEquals("", aventurier.getTrajet());

        assertEquals(AVENTURIER, carte.getZoneDeJeu()[1][1]);
    }

    @Test
    void doitRamasserTresorSiAventurierPasseDessus() {
        // Given
        traductionDonnees.lireLigne("C - 3 - 3");
        traductionDonnees.lireLigne("A - Thomas - 1 - 1 - N - A");
        traductionDonnees.lireLigne("T - 1 - 0 - 2");

        Carte carte = traductionDonnees.getCarte();

        // When
        animation.animeAventuriers(carte);

        // Assert
        assertEquals(1, carte.getAventuriers().size());
        Aventurier aventurier = carte.getAventuriers().get(0);
        assertEquals(0, aventurier.getCoordonnees().getNiveauVertical());
        assertEquals(1, aventurier.getCoordonnees().getNiveauHorizontal());
        assertEquals(true, aventurier.getOrientation() instanceof Nord);
        assertEquals("", aventurier.getTrajet());
        assertEquals(1, aventurier.getNombreDeTresor());

        assertEquals(1, carte.getTresors().size());
        Tresor tresor = carte.getTresors().get(0);
        assertEquals(1, tresor.getNombreDeTresor());

        assertEquals(AVENTURIER, carte.getZoneDeJeu()[1][0]);
    }

    @Test
    void neDoitPasAugmenterTresorSiTresorVide() {
        // Given
        traductionDonnees.lireLigne("C - 3 - 3");
        traductionDonnees.lireLigne("A - Thomas - 1 - 1 - N - A");
        traductionDonnees.lireLigne("T - 1 - 0 - 0");

        Carte carte = traductionDonnees.getCarte();

        // When
        animation.animeAventuriers(carte);

        // Assert
        assertEquals(1, carte.getAventuriers().size());
        Aventurier aventurier = carte.getAventuriers().get(0);
        assertEquals(0, aventurier.getCoordonnees().getNiveauVertical());
        assertEquals(1, aventurier.getCoordonnees().getNiveauHorizontal());
        assertEquals(true, aventurier.getOrientation() instanceof Nord);
        assertEquals("", aventurier.getTrajet());
        assertEquals(0, aventurier.getNombreDeTresor());

        assertEquals(1, carte.getTresors().size());
        Tresor tresor = carte.getTresors().get(0);
        assertEquals(0, tresor.getNombreDeTresor());

        assertEquals(AVENTURIER, carte.getZoneDeJeu()[1][0]);
    }

    @Test
    void doitFaireAvancerAventurierAutantDeFoisQueNescessaire() {
        // Given
        traductionDonnees.lireLigne("C - 8 - 8");
        traductionDonnees.lireLigne("A - Thomas - 1 - 1 - S - AAAA");
        traductionDonnees.lireLigne("A - Aurelie - 0 - 1 - S - AGAG");

        Carte carte = traductionDonnees.getCarte();

        // When
        animation.animeAventuriers(carte);

        // Assert
        assertEquals(2, carte.getAventuriers().size());

        Aventurier thomas = carte.getAventuriers().get(0);
        assertEquals(5, thomas.getCoordonnees().getNiveauVertical());
        assertEquals(1, thomas.getCoordonnees().getNiveauHorizontal());
        assertEquals(true, thomas.getOrientation() instanceof Sud);
        assertEquals("", thomas.getTrajet());
        assertEquals(0, thomas.getNombreDeTresor());

        Aventurier aurelie = carte.getAventuriers().get(1);
        assertEquals(2, aurelie.getCoordonnees().getNiveauVertical());
        assertEquals(1, aurelie.getCoordonnees().getNiveauHorizontal());
        assertEquals(true, aurelie.getOrientation() instanceof Nord);
        assertEquals("", aurelie.getTrajet());
        assertEquals(0, aurelie.getNombreDeTresor());

        assertEquals(AVENTURIER, carte.getZoneDeJeu()[1][5]);
    }

    @Test
    void doitFaireResultatFinal() {
        // Given
        traductionDonnees.lireLigne("C - 3 - 4");
        traductionDonnees.lireLigne("M - 1 - 0");
        traductionDonnees.lireLigne("M - 2 - 1");
        traductionDonnees.lireLigne("T - 0 - 3 - 2");
        traductionDonnees.lireLigne("T - 1 - 3 - 3");
        traductionDonnees.lireLigne("A - Lara - 1 - 1 - S - AADADAGGA");
        traductionDonnees.lireLigne("A - Thomas - 2 - 3 - N - A");

        Carte carte = traductionDonnees.getCarte();

        // When
        animation.animeAventuriers(carte);

        // Assert
        assertEquals(2, carte.getAventuriers().size());

        Aventurier lara = carte.getAventuriers().get(0);
        assertEquals(3, lara.getCoordonnees().getNiveauVertical());
        assertEquals(0, lara.getCoordonnees().getNiveauHorizontal());
        assertEquals(true, lara.getOrientation() instanceof Sud);
        assertEquals("", lara.getTrajet());
        assertEquals(3, lara.getNombreDeTresor());

        Aventurier thomas = carte.getAventuriers().get(1);
        assertEquals(2, thomas.getCoordonnees().getNiveauVertical());
        assertEquals(2, thomas.getCoordonnees().getNiveauHorizontal());
        assertEquals(true, thomas.getOrientation() instanceof Nord);
        assertEquals("", thomas.getTrajet());
        assertEquals(0, thomas.getNombreDeTresor());

        assertEquals(2, carte.getTresors().size());
        Tresor tresor = carte.getTresors().get(0);
        assertEquals(0, tresor.getNombreDeTresor());

        Tresor tresor1 = carte.getTresors().get(1);
        assertEquals(2, tresor1.getNombreDeTresor());

        assertEquals(AVENTURIER, carte.getZoneDeJeu()[0][3]);
    }
}
