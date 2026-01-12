/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package org.centrale.infosi.donnees;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe de test pour ConfigurationJeu.
 * Son but est de vérifier que les constantes globales du jeu n'ont pas été
 * modifiées par erreur.
 *
 * @author srodr
 */
public class ConfigurationJeuTest {

    public ConfigurationJeuTest() {
    }

    /**
     * Test des constantes de configuration.
     * Vérifie que les valeurs critiques (erreurs max, chemin du fichier)
     * sont bien celles attendues pour le bon fonctionnement du jeu.
     */
    @Test
    public void testConstantes() {
        System.out.println("TEST: Vérification des constantes de ConfigurationJeu");

        // 1. Vérification du nombre d'erreurs maximum
        // On s'attend à ce que la valeur soit exactement 6
        int erreursAttendues = 6;
        assertEquals(erreursAttendues, ConfigurationJeu.ERREURS_MAX,
                "Le jeu doit être configuré avec 6 erreurs maximum.");

        // 2. Vérification du chemin du dictionnaire
        // C'est critique pour que le ChargeurDictionnaire trouve le fichier
        String cheminAttendu = "/dictionnaire.txt";
        assertEquals(cheminAttendu, ConfigurationJeu.CHEMIN_DICTIONNAIRE,
                "Le chemin du dictionnaire doit pointer vers la racine des ressources (/dictionnaire.txt).");
    }
}