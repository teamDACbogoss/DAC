/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ensimag.projetjava.servlet;

import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;
import fr.ensimag.projetjava.entity.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.EJB;

/**
 *
 * @author bonkoskk
 */
@Named(value = "portfolio")
@RequestScoped
public class portfolio {

    @EJB
    private fr.ensimag.projetjava.stateless.StockFacadeLocal stockFacade;
    
    private String prix;

    private String msg_maturite;
    private String msg_strike;
    private String msg_quantite;
    private List<String> actions;

    private ArrayList<Stock> listStock;

   
   public ArrayList<Stock> getListStock() {
       ArrayList<Stock> list_temp = new ArrayList<>();
       for(Stock st : stockFacade.findAll()) {
           list_temp.add(st);
        }
        return list_temp;
   }

   public void setListStock(ArrayList<Stock> listStock) {
      this.listStock = listStock;
   }
    
    public List<String> getActions() {
        actions = new LinkedList<>();
        for(Stock st : stockFacade.findAll()) {
            actions.add(st.getName());
        }
        return actions;
    }

    public void setActions(List<String> actions) {
        this.actions = actions;
    }

    
    
    public String getMsg_maturite() {
        return msg_maturite;
    }
    
    public String getMsg_strike() {
        return msg_strike;
    }
    
    public String getMsg_quantite() {
        return msg_quantite;
    }

    public void setMsg_maturite(String msg_maturite) {
        this.msg_maturite = msg_maturite;
    }
    
    public void setMsg_strike(String msg_strike) {
        this.msg_strike = msg_strike;
    }
    
    public void setMsg_quantite(String msg_quantite) {
        this.msg_quantite = msg_quantite;
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
    public portfolio() {
        msg_maturite = "";
    }
    
    public String pricing(String strat, String actionName, String k, String mat, String quant)
    {
        Stock stock = stockFacade.find(actionName);
        Calendar today = Calendar.getInstance();
        today.set(Calendar.HOUR_OF_DAY, 0);
        today.set(Calendar.MINUTE, 0);
        today.set(Calendar.SECOND, 0);
        
        
        if (strat.equals("action"))
        {
            int quantite_int; 
            try  
            {  
                quantite_int = Integer.parseInt(quant);
            }  
            catch(NumberFormatException e)  
            {  
                msg_quantite = "Mauvaise quantité";
                return "nouvelle-position";

            }
            prix = Double.toString(quantite_int * stock.getPrice(today)).substring(0, 5);
        } else {
            double prix_temp;

            double k_double; 
            try  
            {  
                k_double = Double.parseDouble(k);
            }  
            catch(NumberFormatException e)  
            {  
                msg_strike = "Mauvais strike";
                return "nouvelle-position";

            }

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date maturite_Date;
            try {
                maturite_Date = formatter.parse(mat);
            } catch (ParseException e) {
                msg_maturite = "Mauvaise maturité \n yyyy-MM-dd";
                return "nouvelle-position";
            }

            Calendar maturite_cal = Calendar.getInstance();
            maturite_cal.setTime(maturite_Date);

            int quantite_int; 
            try  
            {  
                quantite_int = Integer.parseInt(quant);
            }  
            catch(NumberFormatException e)  
            {  
                msg_quantite = "Mauvaise quantité";
                return "nouvelle-position";

            }

            if (strat.equals("call")){
                VanillaCall option = new VanillaCall("toto", stock, k_double, maturite_cal);
                prix_temp = option.getPrice(today);
            } else {
                VanillaPut option = new VanillaPut("toto", stock, k_double, maturite_cal);
                prix_temp = option.getPrice(today);
            }
            
                prix_temp *= quantite_int;
                prix = Double.toString(prix_temp).substring(0, 5);
        }
                
        return "nouvelle-position"; 
    }
    
    public String achat(String strat, String actionName, String k, String mat, String quant)
    {
        Stock stock = stockFacade.find(actionName);
        Calendar today = Calendar.getInstance();
        today.set(Calendar.HOUR_OF_DAY, 0);
        today.set(Calendar.MINUTE, 0);
        today.set(Calendar.SECOND, 0);
        
        
        if (strat.equals("action"))
        {
            int quantite_int; 
            try  
            {  
                quantite_int = Integer.parseInt(quant);
            }  
            catch(NumberFormatException e)  
            {  
                msg_quantite = "Mauvaise quantité";
                return "cotations";
            }
        } else {
            double prix_temp;

            double k_double; 
            try  
            {  
                k_double = Double.parseDouble(k);
            }  
            catch(NumberFormatException e)  
            {  
                msg_strike = "Mauvais strike";
                return "cotations";
            }

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date maturite_Date;
            try {
                maturite_Date = formatter.parse(mat);
            } catch (ParseException e) {
                msg_maturite = "Mauvaise maturité \n yyyy-MM-dd";
                return "cotations";
            }

            Calendar maturite_cal = Calendar.getInstance();
            maturite_cal.setTime(maturite_Date);

            int quantite_int; 
            try  
            {  
                quantite_int = Integer.parseInt(quant);
            }  
            catch(NumberFormatException e)  
            {  
                msg_quantite = "Mauvaise quantité";
                return "cotations";
            }

            if (strat.equals("call")){
                VanillaCall option = new VanillaCall("toto", stock, k_double, maturite_cal);
                prix_temp = option.getPrice(today);
            } else {
                VanillaPut option = new VanillaPut("toto", stock, k_double, maturite_cal);
                prix_temp = option.getPrice(today);
            }
            
                prix_temp *= quantite_int;
                prix = Double.toString(prix_temp).substring(0, 5);
        }
        return "cotations";
    }
}
