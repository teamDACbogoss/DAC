/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ensimag.projetjava.servlet;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import fr.ensimag.projetjava.entity.Client;

/**
 *
 * @author johanna gogo-dago
 */
@Named(value = "createaccount")
@SessionScoped
public class createaccount implements Serializable {

    //public create
    
    /**
     * Creates a new instance of user
     */
    private String msg="problme";
   
    public void createAccount() {
        msg = "bye";
    }
    
    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
    
    public String createMember(String name, String firstName, String email, String mdp, String cmdp, String secretQA) {
       /* if (!mdp.equals(cmdp)) {
            msg = "Votre mot de passe et votre confirmation sont différents!";
            return "create-account";
        } else {
            //if ()
        }*/
        
        String userName = name;
        
        msg = "gfdfgh" + name;
        return "create-account";
        
        //prix = Float.toString(prix_double); 
        //return "nouvelle-position";
    }
}
