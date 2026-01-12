package org.centrale.infosi.moteur;

/**
 * Représente l'état global d'une partie du jeu du pendu.
 *
 * <ul>
 *   <li>{@link #EN_COURS} : la partie est toujours en cours</li>
 *   <li>{@link #GAGNE} : le mot a été entièrement deviné</li>
 *   <li>{@link #PERDU} : le nombre maximal d'erreurs a été atteint</li>
 * </ul>
 *
 * Cette énumération permet de contrôler les transitions
 * et la fin de la partie dans le moteur du jeu.
 */
public enum StatutJeu {

    /** Partie en cours */
    EN_COURS,

    /** Partie gagnée */
    GAGNE,

    /** Partie perdue */
    PERDU
}
