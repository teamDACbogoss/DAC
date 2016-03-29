/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ensimag.projetjava.servlet;

import fr.ensimag.projetjava.entity.Asset;
import fr.ensimag.projetjava.entity.Client;
import fr.ensimag.projetjava.entity.Stock;
import fr.ensimag.projetjava.entity.Strategy;
import fr.ensimag.projetjava.entity.VanillaCall;
import fr.ensimag.projetjava.entity.VanillaPut;
import fr.ensimag.projetjava.stateless.PortfolioFacadeLocal;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;


/**
 *
 * @author randont
 */
@Named(value = "strategy")
@SessionScoped
public class strategy implements Serializable {
 @EJB
    private fr.ensimag.projetjava.stateless.StrategyFacadeLocal strategyFacade;
 @EJB
    private fr.ensimag.projetjava.stateless.PortfolioFacadeLocal portfolioFacade;
 @EJB
    private fr.ensimag.projetjava.stateless.StockFacadeLocal stockFacade;
    
    private String strategyName; 
    private double totalValue;
    private Strategy currentStrat;
    private String msg;
    
    private String prix;
    private String msg_maturite;
    private String msg_strike;
    
    
    private String msg_quantite;
    private List<String> actions;
    private ArrayList<Asset> listAsset;

    public PortfolioFacadeLocal getPortfolioFacade() {
        return portfolioFacade;
    }

    public void setPortfolioFacade(PortfolioFacadeLocal portfolioFacade) {
        this.portfolioFacade = portfolioFacade;
    }

    public String getPrix() {
        return prix;
    }

    public void setPrix(String prix) {
        this.prix = prix;
    }

    public String getMsg_maturite() {
        return msg_maturite;
    }

    public void setMsg_maturite(String msg_maturite) {
        this.msg_maturite = msg_maturite;
    }

    public String getMsg_strike() {
        return msg_strike;
    }

    public void setMsg_strike(String msg_strike) {
        this.msg_strike = msg_strike;
    }

    public String getMsg_quantite() {
        return msg_quantite;
    }

    public void setMsg_quantite(String msg_quantite) {
        this.msg_quantite = msg_quantite;
    }

    public List<String> getActions() {
        return actions;
    }

    public void setActions(List<String> actions) {
        this.actions = actions;
    }

  

    public ArrayList<Asset> getListAsset() {
        return listAsset;
    }

    public void setListAsset(ArrayList<Asset> listAsset) {
        this.listAsset = listAsset;
    }
    
    public strategy(){
        strategyName = "strategie sans titre";
        totalValue = 0.0;
        currentStrat = null; //new Strategy();
        msg = "";
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setStrategyName(String strategyName) {
        this.strategyName = strategyName;
    }

    public void setTotalValue(double totalValue) {
        this.totalValue = totalValue;
    }

    public void setCurrentStrat(Strategy currentStrat) {
        this.currentStrat = currentStrat;
    }

    public String getStrategyName() {
        return strategyName;
    }

    public double getTotalValue() {
        return totalValue;
    }

    public Strategy getCurrentStrat() {
        return currentStrat;
    }
    
    public String getNameofAsset(Asset asset){
        if (asset instanceof VanillaCall) {
            return "call";
        } else {
            if (asset instanceof VanillaPut) {
                return "put";
            } else {
                if (asset instanceof Stock) {
                    return "action";
                } else {
                    return "pb";
                }                   
            }
        }
    }
    
    /*public double computePrice(Asset asset) {
        if (asset instanceof Stock) {
            
        }
    }*/
    
    public String ajouter(String strat, String actionName, String k, String mat, String quant) {
        
        Stock stock = stockFacade.find(actionName);
        Calendar today = Calendar.getInstance();
        today.set(Calendar.HOUR_OF_DAY, 0);
        today.set(Calendar.MINUTE, 0);
        today.set(Calendar.SECOND, 0);
        String prix_asset;
        
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
            prix_asset = Double.toString(quantite_int * stock.getPrice(today)).substring(0, 5);
            listAsset.add(stock);
            totalValue += quantite_int * stock.getPrice(today);
            prix = Double.toString(totalValue).substring(0, 5);
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

            if (strat.equals("call")){
                VanillaCall option = new VanillaCall("toto", stock, k_double, maturite_cal);
                prix_temp = option.getPrice(today);
                listAsset.add(option);
            } else {
                VanillaPut option = new VanillaPut("toto", stock, k_double, maturite_cal);
                prix_temp = option.getPrice(today);
                listAsset.add(option);
            }
                prix_temp *= quantite_int;
                totalValue += prix_temp;
                prix = Double.toString(totalValue).substring(0, 5);
                
                
        }
        
        return "creation-strats";
    }
    /*public void computePrice() {
        currentStrat.getPrice(timestamp.getTime());
    }
    
    //public getAssetList
    //validate login
    public String validateStrategyCreation(String name) {
        if (name == null) {
            msg = "Incorrect strategy name";
            return "";
        } else {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            sessionBean session = (sessionBean)facesContext.getApplication()
                    .createValueBinding("#{sessionBean}").getValue(facesContext);
            String page = session.login(email, pwd);
            if (page == null) {
                msg = "Incorrect Username and Password";
                return "";
            } else {
                return page;
            }
        }
    }*/
}
