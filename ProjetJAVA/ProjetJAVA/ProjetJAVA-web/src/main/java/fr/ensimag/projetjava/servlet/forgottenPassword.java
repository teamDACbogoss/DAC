/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ensimag.projetjava.servlet;

import fr.ensimag.projetjava.entity.Client;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author josib
 */
@Named(value = "forgottenPassword")
@RequestScoped
public class forgottenPassword {
    
    @EJB
    private fr.ensimag.projetjava.stateless.ClientFacadeLocal clientFacade;

    private String email;
    private String msg;
    private String secretQuestion;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getSecretQuestion() {
        return secretQuestion;
    }

    public void setSecretQuestion(String secretQuestion) {
        this.secretQuestion = secretQuestion;
    }

    /**
     * Get the value of email
     *
     * @return the value of email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set the value of email
     *
     * @param email new value of email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    
    
    /**
     * Creates a new instance of forgottenPassword
     */
    public forgottenPassword() {
        email = "";
        msg = "";
        secretQuestion = "";
    }
    
    public String validEmail(String email) {
        Client cl = clientFacade.find(email);
        if (cl == null) {
            msg = "Adresse e-mail inconnue";
            return "";
        } else {
            this.email = email;
            this.secretQuestion = cl.getSecretQuestion().toString();
            return "reinitiate-password";
        }
    }
    
}
