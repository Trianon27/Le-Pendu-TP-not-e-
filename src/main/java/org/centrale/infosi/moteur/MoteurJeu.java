package org.centrale.infosi.moteur;

/**
 * Moteur du jeu du pendu.
 *
 * <p>Cette classe contient toute la logique métier du jeu :
 * validation des lettres, mise à jour de l'état,
 * détection des conditions de victoire et de défaite.</p>
 *
 * <p>Elle ne gère ni les entrées utilisateur ni l'affichage.</p>
 */
public class MoteurJeu {

    /** État courant du jeu */
    private final EtatJeu etat;

    /**
     * Construit un moteur de jeu à partir d'un état initial.
     *
     * @param etat état du jeu
     */
    public MoteurJeu(EtatJeu etat) {
        this.etat = etat;
    }

    /**
     * Propose une lettre pour la partie en cours.
     *
     * <ul>
     *   <li>La lettre est convertie en majuscule</li>
     *   <li>Une lettre déjà proposée n'entraîne aucune pénalité</li>
     *   <li>Une lettre absente du mot décrémente le nombre d'erreurs</li>
     * </ul>
     *
     * @param lettre lettre proposée par le joueur
     * @return {@code true} si la lettre est présente dans le mot,
     *         {@code false} sinon
     * @throws IllegalArgumentException si le caractère n'est pas une lettre
     */
    public boolean proposerLettre(char lettre) {
        lettre = Character.toUpperCase(lettre);

        if (!Character.isLetter(lettre)) {
            throw new IllegalArgumentException("Caractère invalide");
        }

        if (etat.getLettresProposees().contains(lettre)
                || etat.getStatut() != StatutJeu.EN_COURS) {
            return false;
        }

        etat.getLettresProposees().add(lettre);

        if (!etat.getMotSecret().contains(String.valueOf(lettre))) {
            etat.decrementerErreur();
            return false;
        }

        if (motCompletementDevine()) {
            etat.marquerGagne();
        }

        return true;
    }

    /**
     * Retourne la représentation masquée du mot secret.
     *
     * <p>Les lettres non encore trouvées sont remplacées par '_'.</p>
     *
     * @return mot masqué
     */
    public String getMotMasque() {
        StringBuilder sb = new StringBuilder();

        for (char c : etat.getMotSecret().toCharArray()) {
            if (etat.getLettresProposees().contains(c)) {
                sb.append(c);
            } else {
                sb.append('_');
            }
            sb.append(' ');
        }
        return sb.toString().trim();
    }

    /**
     * Vérifie si toutes les lettres du mot ont été devinées.
     *
     * @return {@code true} si le mot est entièrement deviné,
     *         {@code false} sinon
     */
    private boolean motCompletementDevine() {
        for (char c : etat.getMotSecret().toCharArray()) {
            if (!etat.getLettresProposees().contains(c)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Retourne l'état courant du jeu.
     *
     * @return état du jeu
     */
    public EtatJeu getEtat() {
        return etat;
    }
}
