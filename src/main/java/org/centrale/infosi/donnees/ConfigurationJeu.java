/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.centrale.infosi.donnees;

/**
 *
 * @author srodr
 */
/**
 * Classe utilitaire contenant la configuration globale et les constantes du jeu.
 * Elle regroupe les paramètres non modifiables utilisés dans l'application.
 */
public class ConfigurationJeu {

    // Le nombre maximum d'erreurs autorisées avant de perdre la partie.
    // 'static final' indique que c'est une constante accessible globalement.
    public static final int ERREURS_MAX = 6;

    // Le chemin d'accès relatif vers le fichier contenant le dictionnaire de mots.
    public static final String CHEMIN_DICTIONNAIRE = "/dictionnaire.txt";

    /**
     * Constructeur privé.
     * Empêche l'instanciation de la classe par l'extérieur.
     * Cette classe ne doit servir que de conteneur pour les constantes statiques.
     */
    private ConfigurationJeu() {
        // Ne fait rien intentionnellement
    }

}