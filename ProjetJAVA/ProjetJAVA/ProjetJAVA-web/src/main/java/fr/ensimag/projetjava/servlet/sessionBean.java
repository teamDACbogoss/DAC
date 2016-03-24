/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ensimag.projetjava.servlet;

import fr.ensimag.projetjava.entity.Client;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author josib
 */
@Named(value = "sessionBean")
@SessionScoped
public class sessionBean implements Serializable {

    @EJB
    private fr.ensimag.projetjava.stateless.ClientFacadeLocal clientFacade;
    
    private String name;
    private boolean isAdmin;
    private boolean isLogged;
    private String secretQuestion;
    private String secretAnswer;

    /**
     * Get the value of secretAnswer
     *
     * @return the value of secretAnswer
     */
    public String getSecretAnswer() {
        return secretAnswer;
    }

    /**
     * Set the value of secretAnswer
     *
     * @param secretAnswer new value of secretAnswer
     */
    public void setSecretAnswer(String secretAnswer) {
        this.secretAnswer = secretAnswer;
    }


    /**
     * Get the value of secretQuestion
     *
     * @return the value of secretQuestion
     */
    public String getSecretQuestion() {
        return secretQuestion;
    }

    /**
     * Set the value of secretQuestion
     *
     * @param secretQuestion new value of secretQuestion
     */
    public void setSecretQuestion(String secretQuestion) {
        this.secretQuestion = secretQuestion;
    }


    /**
     * Get the value of isLogged
     *
     * @return the value of isLogged
     */
    public boolean isIsLogged() {
        return isLogged;
    }

    /**
     * Set the value of isLogged
     *
     * @param isLogged new value of isLogged
     */
    public void setIsLogged(boolean isLogged) {
        this.isLogged = isLogged;
    }


    /**
     * Get the value of isAdmin
     *
     * @return the value of isAdmin
     */
    public boolean getIsAdmin() {
        return isAdmin;
    }

    /**
     * Set the value of isAdmin
     *
     * @param isAdmin new value of isAdmin
     */
    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }


    /**
     * Get the value of name
     *
     * @return the value of name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the value of name
     *
     * @param name new value of name
     */
    public void setName(String name) {
        this.name = name;
    }

    
    /**
     * Creates a new instance of sessionBean
     */
    public sessionBean() {
        this.isLogged = false;
        this.isAdmin = false;
        this.name = "";
        this.secretQuestion = "";
    }
    
    public String login(String name, String pwd) {
        Client cl = clientFacade.find(name);
        if (cl == null || !cl.getMdp().equals(pwd)) {
            return null;
        } else {
            this.name = name;
            this.isAdmin = cl.getIsAdmin();
            this.isLogged = true;
            if (isAdmin) {
                return "gestion-comptes";
            } else {
                return "portfolio";
            }
        }
    }
    
    public void checkLogged() throws IOException {
        if (!isLogged) {
            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
            ec.redirect(ec.getRequestContextPath() + "/login.xhtml");
        }
    }
    
    public void checkAdminLogged() throws IOException {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        if (!isLogged) {
            ec.redirect(ec.getRequestContextPath() + "/login.xhtml");
        } else if (!isAdmin) {
            ec.redirect(ec.getRequestContextPath() + "/portfolio.xhtml");
        }
    }
    
    public String logout() {
        this.isLogged = false;
        this.isAdmin = false;
        this.name = "";
        return "login";
    }
    
    public String setForPwdReinit(String name) {
        Client cl = clientFacade.find(name);
        if (cl == null) {
            return null;
        } else {
            this.isLogged = false;
            this.isAdmin = false;
            this.name = name;
            this.secretQuestion = cl.getSecretQuestion().toString();
            this.secretAnswer = cl.getSecretQuestionAnswer();
            return "answer-question";
        }
    }
   
    
}
