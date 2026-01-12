package org.centrale.infosi.moteur;

import java.util.HashSet;
import java.util.Set;

/**
 * Représente l'état courant d'une partie du jeu du pendu.
 *
 * <p>Cette classe stocke uniquement les données du jeu :
 * mot secret, lettres proposées, erreurs restantes et statut global.</p>
 *
 * <p>Aucune logique métier n'est implémentée ici.
 * Les modifications de l'état sont pilotées par {@link MoteurJeu}.</p>
 */
public class EtatJeu {

    /** Mot secret à deviner */
    private final String motSecret;

    /** Ensemble des lettres déjà proposées par le joueur */
    private final Set<Character> lettresProposees;

    /** Nombre d'erreurs restantes avant la défaite */
    private int erreursRestantes;

    /** Statut actuel de la partie */
    private StatutJeu statut;

    /**
     * Construit un nouvel état de jeu.
     *
     * @param motSecret mot à deviner
     * @param erreursMax nombre maximal d'erreurs autorisées
     */
    public EtatJeu(String motSecret, int erreursMax) {
        this.motSecret = motSecret.toUpperCase();
        this.lettresProposees = new HashSet<>();
        this.erreursRestantes = erreursMax;
        this.statut = StatutJeu.EN_COURS;
    }

    /**
     * Retourne le mot secret.
     *
     * @return mot secret
     */
    public String getMotSecret() {
        return motSecret;
    }

    /**
     * Retourne l'ensemble des lettres déjà proposées.
     *
     * @return lettres proposées
     */
    public Set<Character> getLettresProposees() {
        return lettresProposees;
    }

    /**
     * Retourne le nombre d'erreurs restantes.
     *
     * @return erreurs restantes
     */
    public int getErreursRestantes() {
        return erreursRestantes;
    }

    /**
     * Retourne le statut actuel du jeu.
     *
     * @return statut du jeu
     */
    public StatutJeu getStatut() {
        return statut;
    }

    /**
     * Décrémente le nombre d'erreurs restantes.
     * Si le nombre d'erreurs atteint zéro, la partie est perdue.
     */
    void decrementerErreur() {
        erreursRestantes--;
        if (erreursRestantes <= 0) {
            statut = StatutJeu.PERDU;
        }
    }

    /**
     * Marque la partie comme gagnée.
     */
    void marquerGagne() {
        statut = StatutJeu.GAGNE;
    }
}
