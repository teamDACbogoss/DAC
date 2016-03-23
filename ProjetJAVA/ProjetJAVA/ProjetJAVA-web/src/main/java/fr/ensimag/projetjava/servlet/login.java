/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ensimag.projetjava.servlet;

import fr.ensimag.projetjava.entity.Client;
import fr.ensimag.projetjava.stateless.ClientFacade;
import javax.inject.Named;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author josib
 */
@Named(value = "login")
@RequestScoped
public class login implements Serializable {
    
    @EJB
    private fr.ensimag.projetjava.stateless.ClientFacadeLocal clientFacade;
    

    private String msg;
    
    /**
     * Creates a new instance of login
     */
    public login() {
        this.msg = "";
    }
    
    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
 
    //validate login
    public String validateUsernamePassword(String email, String pwd) {
        if (email == null || pwd == null) {
            msg = "Incorrect Username and Password";
            return "";
        } else {
            Client cl = clientFacade.find(email);
            FacesContext facesContext = FacesContext.getCurrentInstance();
            sessionBean session = (sessionBean)facesContext.getApplication()
                    .createValueBinding("#{sessionBean}").getValue(facesContext);
            if (cl == null || !cl.getMdp().equals(pwd)) {
                msg = "Incorrect Username and Password";
                return "";
            } else {
                return session.login(email, cl.getIsAdmin());
            }
        }
    }
}
