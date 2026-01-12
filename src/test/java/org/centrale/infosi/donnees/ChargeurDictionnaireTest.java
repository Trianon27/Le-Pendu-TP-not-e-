/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package org.centrale.infosi.donnees;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe de test pour ChargeurDictionnaire.
 * @author srodr
 */
public class ChargeurDictionnaireTest {
    
    public ChargeurDictionnaireTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test basique : Vérifie qu'on peut charger un mot.
     */
    @Test
    public void testChargerMotAleatoire() {
        System.out.println("TEST: chargerMotAleatoire (Simple)");
        String result = ChargeurDictionnaire.chargerMotAleatoire();
        
        assertNotNull(result, "Le mot ne doit pas être null");
        assertFalse(result.isEmpty(), "Le mot ne doit pas être vide");
        assertTrue(result.matches("[a-zA-Z]+"), "Le mot doit contenir seulement des lettres");
    }

    /**
     * Test de robustesse : Exécute la méthode plusieurs fois (ex: 50 fois).
     * Cela permet de vérifier la stabilité de la lecture du fichier 
     * et du générateur de nombres aléatoires.
     */
    @Test
    public void testRobustesseAleatoire() {
        System.out.println("TEST: chargerMotAleatoire (Robustesse / Boucle)");
        
        for (int i = 0; i < 50; i++) {
            String result = ChargeurDictionnaire.chargerMotAleatoire();
            
            // On revérifie à chaque itération
            assertNotNull(result);
            assertFalse(result.isEmpty());
            // Si une seule itération échoue, le test entier échouera
        }
    }
    
}