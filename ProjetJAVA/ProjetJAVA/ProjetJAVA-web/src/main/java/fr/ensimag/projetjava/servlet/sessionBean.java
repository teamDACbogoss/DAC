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
import java.util.Calendar;
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
    private String solde;
    private String totalValue;
    private int profile_pic;
    private Client client;

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public int getProfile_pic() {
        if (client != null && isLogged) {
            this.profile_pic = client.getProfilePic();
        }
        return profile_pic;
    }

    public void setProfile_pic(int profile_pic) {
        this.profile_pic = profile_pic;
    }

    public String getSolde() {
        if (client != null && isLogged) {
            Client clientFromDB = clientFacade.find(client.getEmail());
            if (clientFromDB == null) {
                return "Error";
            }
            solde = Double.toString(clientFromDB.getCash());
        }
        return solde;
    }

    public void setSolde(String solde) {
        Client clientFromDB = clientFacade.find(client.getEmail());
        clientFromDB.setCash(Double.parseDouble(solde));
        clientFacade.edit(clientFromDB);
        this.solde = solde;
    }

    public String getTotalValue() {
        double temp = 0.;
        java.util.Calendar todayCal = java.util.Calendar.getInstance();
        todayCal.set(Calendar.HOUR_OF_DAY, 0);
        todayCal.set(Calendar.MINUTE, 0);
        todayCal.set(Calendar.SECOND, 0);      
        if (client != null && isLogged) {
            Client clientFromDB = clientFacade.find(client.getEmail());
            temp += clientFromDB.getPortfolio().getValue(todayCal);
            totalValue = Double.toString(clientFromDB.getCash() + temp);
        }
        return totalValue;
    }

    public void setTotalValue(String totalValue) {
        this.totalValue = totalValue;
    }

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
        this.solde = "";
        this.totalValue = "";
        this.profile_pic = 0;
        this.client = null;
    }
    
    public String login(String name, String pwd) {
        this.client = clientFacade.find(name);
        if (client == null || !client.getMdp().equals(pwd)) {
            return null;
        } else {
            this.name = name;
            this.isAdmin = client.getIsAdmin();
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
