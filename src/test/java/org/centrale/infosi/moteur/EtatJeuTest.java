/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java
 */
package org.centrale.infosi.moteur;

import java.util.Set;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests unitaires de la classe EtatJeu.
 *
 * @author hayta
 */
public class EtatJeuTest {

    private EtatJeu etat;

    public EtatJeuTest() {
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
    }

    @AfterEach
    public void tearDown() {
        etat = null;
    }

    /**
     * Test de la méthode getMotSecret.
     */
    @Test
    public void testGetMotSecret() {
        System.out.println("getMotSecret");
        String result = etat.getMotSecret();
        assertEquals("RESEAU", result);
    }

    /**
     * Test de la méthode getLettresProposees.
     */
    @Test
    public void testGetLettresProposees() {
        System.out.println("getLettresProposees");
        Set<Character> result = etat.getLettresProposees();
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    /**
     * Test de la méthode getErreursRestantes.
     */
    @Test
    public void testGetErreursRestantes() {
        System.out.println("getErreursRestantes");
        int result = etat.getErreursRestantes();
        assertEquals(3, result);
    }

    /**
     * Test de la méthode getStatut.
     */
    @Test
    public void testGetStatut() {
        System.out.println("getStatut");
        StatutJeu result = etat.getStatut();
        assertEquals(StatutJeu.EN_COURS, result);
    }

    /**
     * Test de la méthode decrementerErreur.
     */
    @Test
    public void testDecrementerErreur() {
        System.out.println("decrementerErreur");

        etat.decrementerErreur();
        assertEquals(2, etat.getErreursRestantes());
        assertEquals(StatutJeu.EN_COURS, etat.getStatut());

        etat.decrementerErreur();
        etat.decrementerErreur();
        assertEquals(0, etat.getErreursRestantes());
        assertEquals(StatutJeu.PERDU, etat.getStatut());
    }

    /**
     * Test de la méthode marquerGagne.
     */
    @Test
    public void testMarquerGagne() {
        System.out.println("marquerGagne");
        etat.marquerGagne();
        assertEquals(StatutJeu.GAGNE, etat.getStatut());
    }
}
