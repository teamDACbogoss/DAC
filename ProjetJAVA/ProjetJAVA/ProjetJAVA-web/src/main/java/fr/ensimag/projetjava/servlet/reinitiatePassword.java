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
@Named(value = "reinitiatePassword")
@RequestScoped
public class reinitiatePassword {

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
    public reinitiatePassword() {
        msg = "";
    }
    
    public String reinitiate(String pwd) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
            sessionBean session = (sessionBean)facesContext.getApplication()
                    .createValueBinding("#{sessionBean}").getValue(facesContext);
        String name = session.getName();
        Client cl = clientFacade.find(name);
        if (cl == null) {
            return "login";
        } else if (pwd == null || pwd.length() < 6) {
            msg = "Mot de passe trop court";
            return "";
        } else {
            cl.setMdp(pwd);
            clientFacade.edit(cl);
            msg = "Password reinitialized";
            return "";
        }
    }
    
}
