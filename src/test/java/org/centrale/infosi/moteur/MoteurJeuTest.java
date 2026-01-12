/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java
 */
package org.centrale.infosi.moteur;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests unitaires de la classe MoteurJeu.
 *
 * @author hayta
 */
public class MoteurJeuTest {

    private EtatJeu etat;
    private MoteurJeu moteur;

    public MoteurJeuTest() {
    }

    @BeforeAll
    public static void setUpClass() {
        // Initialisation globale si nécessaire
    }

    @AfterAll
    public static void tearDownClass() {
        // Nettoyage global si nécessaire
    }

    @BeforeEach
    public void setUp() {
        etat = new EtatJeu("reseau", 3);
        moteur = new MoteurJeu(etat);
    }

    @AfterEach
    public void tearDown() {
        moteur = null;
        etat = null;
    }

    /**
     * Test de la méthode proposerLettre avec une lettre valide présente.
     */
    @Test
    public void testProposerLettreCorrecte() {
        System.out.println("proposerLettre - lettre correcte");

        boolean result = moteur.proposerLettre('r');

        assertTrue(result);
        assertTrue(etat.getLettresProposees().contains('R'));
        assertEquals(3, etat.getErreursRestantes());
        assertEquals(StatutJeu.EN_COURS, etat.getStatut());
    }

    /**
     * Test de la méthode proposerLettre avec une lettre absente du mot.
     */
    @Test
    public void testProposerLettreIncorrecte() {
        System.out.println("proposerLettre - lettre incorrecte");

        boolean result = moteur.proposerLettre('x');

        assertFalse(result);
        assertTrue(etat.getLettresProposees().contains('X'));
        assertEquals(2, etat.getErreursRestantes());
        assertEquals(StatutJeu.EN_COURS, etat.getStatut());
    }

    /**
     * Test de la méthode proposerLettre avec une lettre déjà proposée.
     */
    @Test
    public void testProposerLettreDejaProposee() {
        System.out.println("proposerLettre - lettre déjà proposée");

        moteur.proposerLettre('r');
        boolean result = moteur.proposerLettre('r');

        assertFalse(result);
        assertEquals(1, etat.getLettresProposees().size());
        assertEquals(3, etat.getErreursRestantes());
    }

    /**
     * Test de la méthode proposerLettre avec un caractère invalide.
     */
    @Test
    public void testProposerLettreInvalide() {
        System.out.println("proposerLettre - caractère invalide");

        assertThrows(IllegalArgumentException.class, () -> {
            moteur.proposerLettre('1');
        });
    }

    /**
     * Test de la méthode getMotMasque.
     */
    @Test
    public void testGetMotMasque() {
        System.out.println("getMotMasque");

        String initial = moteur.getMotMasque();
        assertEquals("_ _ _ _ _ _", initial);

        moteur.proposerLettre('r');
        moteur.proposerLettre('e');

        String result = moteur.getMotMasque();
        assertEquals("R E _ E _ _", result);
    }

    /**
     * Test de la victoire lorsque toutes les lettres sont trouvées.
     */
    @Test
    public void testVictoire() {
        System.out.println("proposerLettre - victoire");

        moteur.proposerLettre('r');
        moteur.proposerLettre('e');
        moteur.proposerLettre('s');
        moteur.proposerLettre('a');
        moteur.proposerLettre('u');

        assertEquals(StatutJeu.GAGNE, etat.getStatut());
    }

    /**
     * Test de la méthode getEtat.
     */
    @Test
    public void testGetEtat() {
        System.out.println("getEtat");

        EtatJeu result = moteur.getEtat();
        assertNotNull(result);
        assertEquals(etat, result);
    }
}
