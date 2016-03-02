/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ensimag.projetjava;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author malacarc
 */
@Entity
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    private String email;
    private boolean isAdmin;
    private Portfolio portfolio;
    private List<Strategy> personalStrategies;
    private String name;
    private String firstName;
    private SecretQuestion secretQuestion;
    private String secretQuestionAnswer;
    private boolean isConnected;

    public User(String email, boolean isAdmin, String name, String firstName, SecretQuestion secretQuestion, String secretQuestionAnswer) {
        this.email = email;
        this.isAdmin = isAdmin;
        this.portfolio = new Portfolio();
        this.personalStrategies = new ArrayList<Strategy>();
        this.name = name;
        this.firstName = firstName;
        this.secretQuestion = secretQuestion;
        this.secretQuestionAnswer = secretQuestionAnswer;
        this.isConnected = false;
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public List<Strategy> getPersonalStrategies() {
        return personalStrategies;
    }

    public void setPersonalStrategies(List<Strategy> personalStrategies) {
        this.personalStrategies = personalStrategies;
    }
    
    public Portfolio getPortfolio() {
        return portfolio;
    }

    public void setPortfolio(Portfolio portfolio) {
        this.portfolio = portfolio;
    }
    
    public boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public SecretQuestion getSecretQuestion() {
        return secretQuestion;
    }

    public void setSecretQuestion(SecretQuestion secretQuestion) {
        this.secretQuestion = secretQuestion;
    }

    public boolean isIsConnected() {
        return isConnected;
    }

    public void setIsConnected(boolean isConnected) {
        this.isConnected = isConnected;
    }

    public String getSecretQuestionAnswer() {
        return secretQuestionAnswer;
    }

    public void setSecretQuestionAnswer(String secretQuestionAnswer) {
        this.secretQuestionAnswer = secretQuestionAnswer;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (email != null ? email.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "fr.ensimag.projetjava.User[ email=" + email + " ]";
    }
    
}
