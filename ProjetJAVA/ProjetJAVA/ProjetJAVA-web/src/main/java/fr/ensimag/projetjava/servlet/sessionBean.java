/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ensimag.projetjava.servlet;

import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author josib
 */
@Named(value = "sessionBean")
@SessionScoped
public class sessionBean implements Serializable {

    private String name;
    private boolean isAdmin;
    private boolean isLogged;

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
    }
    
    public String login(String name, boolean isAdmin) {
        this.name = name;
        this.isAdmin = isAdmin;
        this.isLogged = true;
        if (isAdmin) {
            return "admin";
        } else {
            return "portfolio";
        }
    }
    
    public void checkLogged() throws IOException {
        if (!isLogged) {
            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
            ec.redirect(ec.getRequestContextPath() + "/login.xhtml");
        }
    }
    
    public void checkAdminLogged() throws IOException {
        if (!isLogged) {
            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
            ec.redirect(ec.getRequestContextPath() + "/login.xhtml");
        } else if (!isAdmin) {
            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
            ec.redirect(ec.getRequestContextPath() + "/portfolio.xhtml");
        }
    }
    
    public String logout() {
        this.isLogged = false;
        this.isAdmin = false;
        this.name = "";
        return "login";
    }
    
}
