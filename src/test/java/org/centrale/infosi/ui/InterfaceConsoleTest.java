/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package org.centrale.infosi.ui;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests d'intégration pour l'Interface Console.
 * Ces tests simulent les entrées clavier et vérifient la sortie console.
 * * @author srodr
 */
public class InterfaceConsoleTest {

    // On garde une référence vers les vrais System.in et System.out
    // pour les restaurer après les tests.
    private final InputStream standardIn = System.in;
    private final PrintStream standardOut = System.out;

    // Ce flux capturera ce que le programme affiche (System.out)
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    public InterfaceConsoleTest() {
    }

    @BeforeEach
    public void setUp() {
        // Avant chaque test, on redirige la sortie console vers notre captureur
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown() {
        // Après chaque test, on remet le clavier et la console comme avant
        // C'est très important pour ne pas casser NetBeans/Maven
        System.setIn(standardIn);
        System.setOut(standardOut);
    }

    /**
     * Test d'une partie complète (Simulation).
     * Comme le mot est aléatoire, on ne sait pas si on va gagner ou perdre.
     * On envoie donc tout l'alphabet pour forcer la fin du jeu.
     */
    @Test
    public void testMainScenarioJeu() {
        System.out.println("TEST: Simulation d'une partie complète via main()");

        // 1. Préparation des entrées simulées (Clavier virtuel)
        // On simule un joueur qui tape '!' (invalide), puis toutes les lettres de 'a' à 'z'.
        // Les \n simulent la touche "Entrée".
        String inputSimule = "!\n" + // Test entrée invalide
                             "a\nb\nc\nd\ne\nf\ng\nh\ni\nj\nk\nl\nm\n" +
                             "n\no\np\nq\nr\ns\nt\nu\nv\nw\nx\ny\nz\n";
        
        // On injecte cette chaîne dans System.in
        System.setIn(new ByteArrayInputStream(inputSimule.getBytes()));

        // 2. Lancement du jeu
        // Le jeu va lire notre chaîne comme si c'était le clavier
        InterfaceConsole.main(new String[]{});

        // 3. Récupération de tout ce que le jeu a affiché
        String sortieConsole = outputStreamCaptor.toString();

        // 4. Vérifications (Assertions)
        
        // Vérifie que le jeu a bien géré le caractère invalide '!'
        assertTrue(sortieConsole.contains("Entrée invalide") || sortieConsole.contains("Veuillez saisir une seule lettre"),
                "Le jeu aurait dû signaler une entrée invalide.");

        // Vérifie que le jeu s'est bien terminé (soit GAGNE, soit PERDU)
        boolean aGagne = sortieConsole.contains("Bravo");
        boolean aPerdu = sortieConsole.contains("Perdu");

        assertTrue(aGagne || aPerdu, 
                "Le jeu devrait être terminé (afficher Bravo ou Perdu).");
    }
}