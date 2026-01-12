/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.centrale.infosi.ui;

/**
 *
 * @author srodr
 */
// 
// Nous utilisons maintenant 'org.centrale.infosi' pour correspondre à ton projet
import org.centrale.infosi.donnees.ChargeurDictionnaire;
import org.centrale.infosi.donnees.ConfigurationJeu;
import org.centrale.infosi.moteur.EtatJeu;
import org.centrale.infosi.moteur.MoteurJeu;
import org.centrale.infosi.moteur.StatutJeu;

import java.util.Scanner;

/**
 * Classe principale pour l'interface utilisateur en ligne de commande (Console).
 * Elle gère l'interaction avec le joueur, l'affichage et la boucle de jeu.
 */
public class InterfaceConsole {

    /**
     * Point d'entrée de l'application.
     * @param args Arguments de la ligne de commande (non utilisés ici).
     */
    public static void main(String[] args) {
        // Scanner pour lire les entrées clavier de l'utilisateur
        Scanner scanner = new Scanner(System.in);

        // 1. Initialisation du jeu
        // On charge un mot aléatoire depuis le dictionnaire
        String motSecret = ChargeurDictionnaire.chargerMotAleatoire();
        
        // Création de l'état initial (les données du jeu)
        EtatJeu etat = new EtatJeu(motSecret, ConfigurationJeu.ERREURS_MAX);
        
        // Création du moteur (la logique du jeu) qui manipulera l'état
        MoteurJeu moteur = new MoteurJeu(etat);

        // 2. Boucle principale du jeu
        // Tant que le statut est "EN_COURS", on continue à demander des lettres
        while (etat.getStatut() == StatutJeu.EN_COURS) {
            
            // Affichage des informations actuelles au joueur
            System.out.println("\nMot : " + moteur.getMotMasque());
            System.out.println("Erreurs restantes : " + etat.getErreursRestantes());
            System.out.println("Lettres proposées : " + etat.getLettresProposees());

            // Demande de saisie
            System.out.print("Proposez une lettre : ");
            String saisie = scanner.nextLine();

            // Validation de base : on s'assure que l'utilisateur n'entre qu'un seul caractère
            if (saisie.length() != 1) {
                System.out.println("Veuillez saisir une seule lettre.");
                continue; // Retour au début de la boucle while
            }

            // Tentative de jeu
            try {
                // On prend le premier caractère et on l'envoie au moteur
                moteur.proposerLettre(saisie.charAt(0));
            } catch (IllegalArgumentException e) {
                // Gestion des cas où le caractère n'est pas une lettre valide
                System.out.println("Entrée invalide.");
            }
        }

        // 3. Fin de partie
        // Une fois la boucle terminée (GAGNE ou PERDU), on affiche le résultat
        if (etat.getStatut() == StatutJeu.GAGNE) {
            System.out.println("\nBravo ! Le mot était : " + etat.getMotSecret());
        } else {
            System.out.println("\nPerdu ! Le mot était : " + etat.getMotSecret());
        }
    }
}