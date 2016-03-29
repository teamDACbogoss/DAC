/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ensimag.projetjava.servlet;

import fr.ensimag.projetjava.entity.Asset;
import fr.ensimag.projetjava.entity.Client;
import fr.ensimag.projetjava.entity.ParamAssetInteger;
import fr.ensimag.projetjava.entity.Portfolio;
import fr.ensimag.projetjava.entity.Stock;
import fr.ensimag.projetjava.entity.Strategy;
import fr.ensimag.projetjava.entity.VanillaCall;
import fr.ensimag.projetjava.entity.VanillaPut;
import fr.ensimag.projetjava.stateless.PortfolioFacadeLocal;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Calendar;
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
    private fr.ensimag.projetjava.stateless.AssetFacadeLocal assetFacade;
 @EJB
    private fr.ensimag.projetjava.stateless.PortfolioFacadeLocal portfolioFacade;
  @EJB
    private fr.ensimag.projetjava.stateless.ClientFacadeLocal clientFacade;
 @EJB
    private fr.ensimag.projetjava.stateless.StockFacadeLocal stockFacade;
    
    private String strategyName; 
    private double totalValue;
    private String msgerr;
    private String msgok;

    private String prix;
    private String msg_maturite;
    private String msg_strike;
    
    
    private String msg_quantite;
    private ArrayList<ParamAssetInteger> listAsset;

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
    
    public ArrayList<ParamAssetInteger> getListAsset() {
        return listAsset;
    }

    public void setListAsset(ArrayList<ParamAssetInteger> listAsset) {
        this.listAsset = listAsset;
    }
    
    public strategy(){
        strategyName = "strategie sans titre";
        listAsset = new ArrayList<ParamAssetInteger>();
        totalValue = 0.0;
        msgerr = "";
        msgok = "";
    }

    public String getMsgok() {
        return msgok;
    }

    public void setMsgok(String msgok) {
        this.msgok = msgok;
    }
    
    public String getMsgerr() {
        return msgerr;
    }

    public void setMsgerr(String msg) {
        this.msgerr = msg;
    }

    public void setStrategyName(String strategyName) {
        this.strategyName = strategyName;
    }

    public void setTotalValue(double totalValue) {
        this.totalValue = totalValue;
    }


    public String getStrategyName() {
        return strategyName;
    }

    public double getTotalValue() {
        return totalValue;
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
    
    public String getNameofUnderlying(Asset asset){
        if (asset instanceof VanillaCall) {
            return ((VanillaCall)asset).getUnderlying().getVal().getName();
        } else {
            if (asset instanceof VanillaPut) {
                return ((VanillaPut)asset).getUnderlying().getVal().getName();
            } else {
                if (asset instanceof Stock) {
                    return ((Stock)asset).getName();
                } else {
                    return "pb";
                }                   
            }
        }
    }
    
    public String getStrikeOfAsset(Asset asset){
        if (asset instanceof VanillaCall) {
            return Double.toString(((VanillaCall)asset).getStrike().getVal());
        } else {
            if (asset instanceof VanillaPut) {
                return Double.toString(((VanillaPut)asset).getStrike().getVal());
            } else {
                if (asset instanceof Stock) {
                    return "-";
                } else {
                    return "pb";
                }                   
            }
        }
    }
    
    public String getMaturityOfAsset(Asset asset){
        SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-DD");  
        if (asset instanceof VanillaCall) {
            return formatter.format(((VanillaCall)asset).getMaturity().getVal().getTime());
        } else {
            if (asset instanceof VanillaPut) {
                return formatter.format(((VanillaPut)asset).getMaturity().getVal().getTime());
            } else {
                if (asset instanceof Stock) {
                    return "-";
                } else {
                    return "pb";
                }                   
            }
        }
    }
    
    public String getPriceOfAsset(Asset asset) {
        Calendar today = Calendar.getInstance();
        today.set(Calendar.HOUR_OF_DAY, 0);
        today.set(Calendar.MINUTE, 0);
        today.set(Calendar.SECOND, 0);
        return Double.toString(asset.getPrice(today));
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
            ParamAssetInteger param =  new ParamAssetInteger(stock, quantite_int);
            listAsset.add(param);
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
                VanillaCall option = new VanillaCall("call", stock, k_double, maturite_cal);
                prix_temp = option.getPrice(today);
                ParamAssetInteger param =  new ParamAssetInteger(option, quantite_int);
                listAsset.add(param);
            } else {
                VanillaPut option = new VanillaPut("put", stock, k_double, maturite_cal);
                prix_temp = option.getPrice(today);
                 ParamAssetInteger param =  new ParamAssetInteger(option, quantite_int);
                listAsset.add(param);
            }
                prix_temp *= quantite_int;
                totalValue += prix_temp;
                prix = Double.toString(totalValue).substring(0, 5);
        }  
        return "creation-strats";
    }
    
    
    public String delete(String name) {
        Calendar today = Calendar.getInstance();
        today.set(Calendar.HOUR_OF_DAY, 0);
        today.set(Calendar.MINUTE, 0);
        today.set(Calendar.SECOND, 0);
        for(ParamAssetInteger p : listAsset) {
            if (p.getAsset().getName().equals(name)) {
                listAsset.remove(p);
                totalValue -= p.getQuantity() * p.getAsset().getPrice(today);
                prix = Double.toString(totalValue).substring(0, 5);
                break;
            }
        }
        msgerr = "";
        msgok = "";
        return "";
    }
    
    //public getAssetList
    //validate login
    public String validateStrategyCreation(String name) {
        if (name == null) {
            msgerr = "Incorrect strategy name";
            msgok = "";
            return "";
        } else {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            sessionBean session = (sessionBean)facesContext.getApplication()
                    .createValueBinding("#{sessionBean}").getValue(facesContext);
            if (name == null || name.equals("")) {
                msgerr = "The strategy must have a name";
                msgok = "";
                return "";
            }
            if (strategyFacade.find(name) != null) {
                msgerr = "There is already a strategy with the same name";
                msgok = "";
                return "";
            }
            Client cl = clientFacade.find(session.getName());
            if (cl == null) {
                msgerr = "The account is not connected";
                msgok = "";
                return "";
            }
            if (listAsset.isEmpty()) {
                msgerr = "The strategy is empty";
                msgok = "";
                return "";
            }
            for(ParamAssetInteger p : listAsset) {
                if (!(p.getAsset() instanceof Stock)) {
                    if (assetFacade.find(p.getAsset().getName()) == null) {
                        assetFacade.create(p.getAsset());
                    } else {
                        assetFacade.edit(p.getAsset());
                    }
                }
            }
            Strategy currentStrat = new Strategy(name, listAsset);
            Portfolio port = cl.getPortfolio();
            List<Strategy> listStrat = port.getStrategies();
            listStrat.add(currentStrat);
            strategyFacade.create(currentStrat);
            port.setStrategies(listStrat);
            cl.setPortfolio(port);
            clientFacade.edit(cl);
            currentStrat.setAssets(null);
            msgerr = "";
            msgok = "";
            return "";
        }
    }
}