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
import static java.lang.Double.min;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;

/**
 *
 * @author bonkoskk
 */
@Named(value = "option")
@RequestScoped
public class option {

    @EJB
    private fr.ensimag.projetjava.stateless.StockFacadeLocal stockFacade;
    @EJB 
    private fr.ensimag.projetjava.stateless.StrategyFacadeLocal strategyFacade;
    @EJB 
    private fr.ensimag.projetjava.stateless.ClientFacadeLocal clientFacade;
    @EJB 
    private fr.ensimag.projetjava.stateless.VanillaCallFacadeLocal vanillaCallFacade;
    @EJB
    private fr.ensimag.projetjava.stateless.VanillaPutFacadeLocal vanillaPutFacade;
    @EJB
    private fr.ensimag.projetjava.stateless.FinancialOptionFacadeLocal financialOptionFacade;
    
    
    
    private String prix;

    private String msg_stock;
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
        listStock = list_temp;
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
    
    public String getMsg_stock() {
        return msg_stock;
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
    
    public void setMsg_stock(String msg_stock) {
        this.msg_stock = msg_stock;
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
        msg_maturite = "";
    }
    
    public String pricing(String strat, String actionName, String k, String mat, String quant)
    {
        Stock stock = stockFacade.find(actionName);
        if (stock == null) {
            msg_stock = "action inconnue princing";
            return "";
        }
        
        Calendar today = Calendar.getInstance();
        today.set(Calendar.HOUR_OF_DAY, 0);
        today.set(Calendar.MINUTE, 0);
        today.set(Calendar.SECOND, 0);
        FacesContext facesContext = FacesContext.getCurrentInstance();
        sessionBean session = (sessionBean)facesContext.getApplication()
                    .createValueBinding("#{sessionBean}").getValue(facesContext);
        //ListPortfolio = session.getClient().getPortfolio().getStrategies();
        Client client = clientFacade.find(session.getClient().getEmail());
        
        
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
                return "";

            }
            prix = Double.toString(quantite_int * stock.getPrice(today)).substring(0, Math.min(5, Double.toString(quantite_int * stock.getPrice(today)).length()));
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
                return "";

            }

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date maturite_Date;
            try {
                maturite_Date = formatter.parse(mat);
            } catch (ParseException e) {
                msg_maturite = "Mauvaise maturité \n yyyy-MM-dd";
                return "";
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
                return "";

            }

            SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");

            String formatted = format1.format(maturite_cal.getTime());
            if (strat.equals("call")){
                VanillaCall option = new VanillaCall("toto", stock, k_double, maturite_cal, today, formatted);
                prix_temp = option.getPrice(today);
            } else {
                VanillaPut option = new VanillaPut("toto", stock, k_double, maturite_cal, today, formatted);
                prix_temp = option.getPrice(today);
            }
            
                prix_temp *= quantite_int;
                prix = Double.toString(prix_temp).substring(0, Math.min(5, Double.toString(prix_temp).length()));
        }
                
        return ""; 
    }
    
    public String achat(String strat, String actionName, String k, String mat, String quant)
    {
        Stock stock = stockFacade.find(actionName);
        if (stock == null) {
            msg_stock = "action inconnue achat";
            return "";
        }
        Calendar today = Calendar.getInstance();
        today.set(Calendar.HOUR_OF_DAY, 0);
        today.set(Calendar.MINUTE, 0);
        today.set(Calendar.SECOND, 0);
        
        FacesContext facesContext = FacesContext.getCurrentInstance();
        sessionBean session = (sessionBean)facesContext.getApplication()
                    .createValueBinding("#{sessionBean}").getValue(facesContext);
        //ListPortfolio = session.getClient().getPortfolio().getStrategies();
        Client client = clientFacade.find(session.getClient().getEmail());
        
        
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
            
            List<ParamAssetInteger> stocks = new ArrayList<>();
            ParamAssetInteger param = new ParamAssetInteger(stock, quantite_int);
            stocks.add(param);
            Strategy strategy = new Strategy("Stratégie"+ Integer.toString(strategyFacade.findAll().size() + 1), stocks);
            strategyFacade.create(strategy);
            client.getPortfolio().getStrategies().add(strategy);
            client.setCash(client.getCash() - strategy.getPrice(today));
            clientFacade.edit(client);
            
            return "portfolio";

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

            SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");

            String formatted = format1.format(maturite_cal.getTime());
            Asset option;
            if (strat.equals("call")){
                option = new VanillaCall("VanillaCall" + Integer.toString(vanillaCallFacade.findAll().size() + 1) + "_" + stock.getName(), 
                                         stock, 
                                         k_double, 
                                         maturite_cal, 
                                         today,
                                         formatted);
                
                prix_temp = option.getPrice(today);
            } else {
                option = new VanillaPut("VanillaPut" + Integer.toString(vanillaPutFacade.findAll().size() + 1) + "_" + stock.getName(), 
                                        stock, 
                                        k_double, 
                                        maturite_cal, 
                                        today,
                                        formatted);
                prix_temp = option.getPrice(today);
            }
            financialOptionFacade.create((FinancialOption) option);
            prix_temp *= quantite_int;
            prix = Double.toString(prix_temp).substring(0, Math.min(5, Double.toString(prix_temp).length()));  
            List<ParamAssetInteger> stocks = new ArrayList<>();
            ParamAssetInteger param = new ParamAssetInteger(option, quantite_int);
            stocks.add(param);
            Strategy strategy = new Strategy("Stratégie"+ Integer.toString(strategyFacade.findAll().size() + 1), stocks);
            strategyFacade.create(strategy);
            client.getPortfolio().getStrategies().add(strategy);
            client.setCash(client.getCash() - strategy.getPrice(today));
            clientFacade.edit(client);
            
            return "portfolio";
        }
    }
    /*
    public String pricing_ajout(String strat, String actionName, String k, String mat, String quant)
    {
        Stock stock = stockFacade.find(actionName);
        if (stock == null) {
            msg_stock = "action inconnue ajout";
            return "";
        }

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
                return "ajout-produit";

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
                return "ajout-produit";
            }

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date maturite_Date;
            try {
                maturite_Date = formatter.parse(mat);
            } catch (ParseException e) {
                msg_maturite = "Mauvaise maturité \n yyyy-MM-dd";
                return "ajout-produit";
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
                return "ajout-produit";

            }

            SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");

            String formatted = format1.format(maturite_cal.getTime());
            if (strat.equals("call")){
                VanillaCall option = new VanillaCall("toto", 
                                                     stock, 
                                                     k_double, 
                                                     maturite_cal, 
                                                     today, 
                                                     formatted);
                prix_temp = option.getPrice(today);
            } else {
                VanillaPut option = new VanillaPut("toto", stock, k_double, maturite_cal, today, formatted);
                prix_temp = option.getPrice(today);
            }
            
                prix_temp *= quantite_int;
                prix = Double.toString(prix_temp).substring(0, Math.min(Double.toString(prix_temp).length(), 5));
        }
                
        return "ajout-produit";
    }
    
    public String achat_ajout(String strat, String actionName, String k, String mat, String quant)
    {
        Stock stock = stockFacade.find(actionName);
        if (stock == null) {
            msg_stock = "action inconnue ajout achat";
            return "";
        }
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
                return "creation-strats";
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
                return "creation-strats";
            }

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date maturite_Date;
            try {
                maturite_Date = formatter.parse(mat);
            } catch (ParseException e) {
                msg_maturite = "Mauvaise maturité \n yyyy-MM-dd";
                return "creation-strats";
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
                return "creation-strats";
            }
            
            
            SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");

            String formatted = format1.format(maturite_cal.getTime());

            if (strat.equals("call")){
                VanillaCall option = new VanillaCall("toto", stock, k_double, maturite_cal, today, formatted);
                prix_temp = option.getPrice(today);
            } else {
                VanillaPut option = new VanillaPut("toto", stock, k_double, maturite_cal, today, formatted);
                prix_temp = option.getPrice(today);
            }
            
                prix_temp *= quantite_int;
                prix = Double.toString(prix_temp).substring(0, Math.min(Double.toString(prix_temp).length(), 5));  

        }
        return "creation-strats";
    }
    */
}
