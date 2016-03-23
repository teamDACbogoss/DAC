/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ensimag.projetjava.servlet;

import fr.ensimag.projetjava.entity.Client;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author josib
 */
@Named(value = "forgottenPassword")
@RequestScoped
public class forgottenPassword {
    
    @EJB
    private fr.ensimag.projetjava.stateless.ClientFacadeLocal clientFacade;

    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * Creates a new instance of forgottenPassword
     */
    public forgottenPassword() {
        msg = "";
    }
    
    public String validEmail(String email) {
        Client cl = clientFacade.find(email);
        if (cl == null) {
            msg = "Adresse e-mail inconnue";
            return "";
        } else {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            sessionBean session = (sessionBean)facesContext.getApplication()
                    .createValueBinding("#{sessionBean}").getValue(facesContext);
            String page = session.setForPwdReinit(email);
            if (page == null) {
                msg = "Adresse e-mail inconnue";
                return "";
            } else {
                return page;
            }
        }
    }
}
