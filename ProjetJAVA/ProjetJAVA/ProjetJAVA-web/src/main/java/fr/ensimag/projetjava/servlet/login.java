/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ensimag.projetjava.servlet;

import javax.inject.Named;
import java.io.Serializable;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author josib
 */
@Named(value = "login")
@RequestScoped
public class login implements Serializable {

    private String msg;
    
    /**
     * Creates a new instance of login
     */
    public login() {
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
            return "login";
        }
        else if (email.equals("test") && pwd.equals("test")) {
            return "portfolio";
        } else {
            msg = "Incorrect Username and Password";
            return "login";
        }
    }
    
    //public void createAccount()
}
