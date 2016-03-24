/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ensimag.projetjava.entity;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.OneToOne;

/**
 *
 * @author malacarc
 */
@Entity
public class Client implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(unique=true, nullable=false, length=128)
    private String email;
    
    @Column(nullable=false, length=128)
    private String mdp;
    
    @Column(nullable=false)
    private boolean isAdmin;
    @OneToOne(cascade = CascadeType.ALL)
    private Portfolio portfolio;
    //@OneToMany
    //private Set<Strategy> personalStrategies;
    private String name;
    private String firstName;
    private String secretQuestionAnswer;
    private boolean isConnected;
    private SecretQuestion secretQuestion;
    private double cash;


    public Client() {}

    public Client(String email, String mdp, boolean isAdmin, String name, String firstName, SecretQuestion secretQuestion, String secretQuestionAnswer, double cash) {
        this.email = email;
        this.mdp = mdp;
        this.isAdmin = isAdmin;
        this.portfolio = new Portfolio();
        this.name = name;
        this.firstName = firstName;
        this.secretQuestion = secretQuestion;
        this.secretQuestionAnswer = secretQuestionAnswer;
        this.isConnected = false;
        this.cash = cash;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getMdp() {
        return mdp;
    }
    
    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    /*public Set<Strategy> getPersonalStrategies() {
        return personalStrategies;
    }

    public void setPersonalStrategies(Set<Strategy> personalStrategies) {
        this.personalStrategies = personalStrategies;
    }*/
    
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
    
    public double getCash() {
        return cash;
    }

    public void setCash(double cash) {
        this.cash = cash;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (email != null ? email.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Client other = (Client) obj;
        return true;
    }

    
    @Override
    public String toString() {
        return "fr.ensimag.projetjava.User[ email=" + email + " ]";
    }
    
    /*
        //validate login
    public String validateUsernamePassword() {
        boolean valid = LoginDAO.validate(user, pwd);
        if (valid) {
            HttpSession session = SessionBean.getSession();
            session.setAttribute("username", user);
            return "admin";
        } else {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Incorrect Username and Passowrd",
                            "Please enter correct username and Password"));
            return "login";
        }
    }
 
    //logout event, invalidate session
    public String logout() {
        HttpSession session = SessionBean.getSession();
        session.invalidate();
        return "login";
    }*/
}
