/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ensimag.projetjava.servlet;

import java.io.Serializable;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author bonkoskk
 */
@Named(value = "option")
@RequestScoped
public class option {

    private String prix;

    private String msg;
    
    
    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
    /**
     * Get the value of prix
     *
     * @return the value of prix
     */
    public String getPrix() {
        return prix;
    }

    /**
     * Set the value of prix
     *
     * @param prix new value of prix
     */
    public void setPrix(String prix) {
        this.prix = prix;
    }

    /**
     * Creates a new instance of option
     */
    public option() {
    }
    
    public String pricingCall(String mat)
    {
        float prix_double;
        try  
        {  
            prix_double = Float.parseFloat(mat);
        }  
        catch(NumberFormatException e)  
        {  
            msg = "Mauvaise maturité";
            return "nouvelle-position";

        } 
        
        prix = Float.toString(prix_double); 
        return "nouvelle-position";
    }
}
