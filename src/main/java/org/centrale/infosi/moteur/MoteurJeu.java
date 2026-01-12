/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.centrale.infosi.moteur;
/**
 *
 * @author hayta
 */
public class MoteurJeu {
    
    private final EtatJeu etat;

    public MoteurJeu(EtatJeu etat) {
        this.etat = etat;
    }

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

    private boolean motCompletementDevine() {
        for (char c : etat.getMotSecret().toCharArray()) {
            if (!etat.getLettresProposees().contains(c)) {
                return false;
            }
        }
        return true;
    }

    public EtatJeu getEtat() {
        return etat;
    }
}
