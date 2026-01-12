/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.centrale.infosi.donnees;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Classe responsable du chargement et de la gestion du dictionnaire de mots.
 * Elle permet de lire un fichier texte et d'extraire un mot aléatoire pour le jeu.
 *
 * @author srodr
 */
public class ChargeurDictionnaire {

    /**
     * Charge le contenu du dictionnaire depuis les ressources et retourne un mot au hasard.
     * * @return Un mot aléatoire (String) provenant du dictionnaire.
     * @throws IllegalStateException Si le fichier est introuvable, vide, ou illisible.
     */
    public static String chargerMotAleatoire() {
        // Liste temporaire pour stocker les mots valides lus du fichier
        List<String> mots = new ArrayList<>();

        // Utilisation de "try-with-resources" pour fermer automatiquement le flux de lecture
        // On utilise getResourceAsStream pour lire le fichier inclus dans le classpath (dossier resources)
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        ChargeurDictionnaire.class
                                .getResourceAsStream(ConfigurationJeu.CHEMIN_DICTIONNAIRE)))) {

            String ligne;
            // Lecture du fichier ligne par ligne jusqu'à la fin
            while ((ligne = reader.readLine()) != null) {
                // Filtre : on ne garde que les lignes contenant uniquement des lettres (a-z, A-Z)
                // Cela exclut les lignes vides ou celles avec des chiffres/symboles
                if (ligne.matches("[a-zA-Z]+")) {
                    mots.add(ligne.trim()); // Ajout du mot nettoyé (sans espaces autour)
                }
            }

        } catch (Exception e) {
            // En cas d'erreur (fichier non trouvé, erreur I/O), on lance une exception claire
            throw new IllegalStateException("Impossible de charger le dictionnaire", e);
        }

        // Vérification de sécurité pour s'assurer qu'on a bien chargé des mots
        if (mots.isEmpty()) {
            throw new IllegalStateException("Le dictionnaire est vide ou aucun mot valide n'a été trouvé");
        }

        // Retourne un élément aléatoire de la liste
        return mots.get(new Random().nextInt(mots.size()));
    }

}