/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.centrale.infosi.moteur;


import java.util.HashSet;
import java.util.Set;

public class EtatJeu {

    private final String motSecret;
    private final Set<Character> lettresProposees;
    private int erreursRestantes;
    private StatutJeu statut;

    public EtatJeu(String motSecret, int erreursMax) {
        this.motSecret = motSecret.toUpperCase();
        this.lettresProposees = new HashSet<>();
        this.erreursRestantes = erreursMax;
        this.statut = StatutJeu.EN_COURS;
    }

    public String getMotSecret() {
        return motSecret;
    }

    public Set<Character> getLettresProposees() {
        return lettresProposees;
    }

    public int getErreursRestantes() {
        return erreursRestantes;
    }

    public StatutJeu getStatut() {
        return statut;
    }

    void decrementerErreur() {
        erreursRestantes--;
        if (erreursRestantes <= 0) {
            statut = StatutJeu.PERDU;
        }
    }

    void marquerGagne() {
        statut = StatutJeu.GAGNE;
    }
}