/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ensimag.projetjava.entity;

/**
 *
 * @author malacarc
 */
public enum SecretQuestion {
    q1("Quel est le nom de votre première école ?"),
    q2("Quel est le nom de votre premier animal domestique ?"),
    q3("Quel est le nom de l'hopital dans lequel vous êtes né ?"),
    q4("Quel est le nom de votre premier enfant ?"),
    q5("Quel est le nom de votre meilleur ami d'enfance ?"); 
    
    private final String question;

    /**
     * @param text
     */
    private SecretQuestion(final String question) {
        this.question = question;
    }

    /* (non-Javadoc)
     * @see java.lang.Enum#toString()
     */
    @Override
    public String toString() {
        return question;
    }
}
