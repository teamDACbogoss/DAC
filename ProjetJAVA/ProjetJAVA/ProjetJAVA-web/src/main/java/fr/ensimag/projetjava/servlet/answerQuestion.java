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
@Named(value = "answerQuestion")
@RequestScoped
public class answerQuestion {

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
    public answerQuestion() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        sessionBean session = (sessionBean)facesContext.getApplication()
                .createValueBinding("#{sessionBean}").getValue(facesContext);
        email = session.getName();
        secretQuestion = session.getSecretQuestion();
        msg = "";
    }
    
    public String validAnswer(String answer) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
            sessionBean session = (sessionBean)facesContext.getApplication()
                    .createValueBinding("#{sessionBean}").getValue(facesContext);
        Client cl = clientFacade.find(email);
        if (cl == null || this.secretQuestion == null) {
            return "login";
        } else if (answer == null || !session.getSecretAnswer().equals(answer)) {
            msg = "Mauvaise réponse a la question secrète ";
            //msg = msg + "." + session.getSecretAnswer() + "!=" + answer + ".";
            return "";
        } else {
            return "reinitiate-password";
        }
    }
    
}
