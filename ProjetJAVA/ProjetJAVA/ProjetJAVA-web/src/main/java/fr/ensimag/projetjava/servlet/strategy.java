/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ensimag.projetjava.servlet;

import fr.ensimag.projetjava.entity.Client;
import fr.ensimag.projetjava.entity.Portfolio;
import fr.ensimag.projetjava.entity.Strategy;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Calendar;
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
    private fr.ensimag.projetjava.stateless.ClientFacadeLocal clientFacade;
    
    private String strategyName; 
    private double totalValue;
    private Strategy currentStrat;
    private String msgerr;
    private String msgok;

    public strategy(){
        strategyName = "strategie sans titre";
        totalValue = 0.0;
        currentStrat = null; //new Strategy();
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
    
    public void computePrice() {
        Calendar today = Calendar.getInstance();
        today.set(Calendar.HOUR_OF_DAY, 0);
        today.set(Calendar.MINUTE, 0);
        today.set(Calendar.SECOND, 0);
        currentStrat.getPrice(today);
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
            if (currentStrat.getAssets().isEmpty()) {
                msgerr = "The strategy is empty";
                msgok = "";
                return "";
            }
            Portfolio port = cl.getPortfolio();
            List<Strategy> listStrat = port.getStrategies();
            listStrat.add(currentStrat);
            port.setStrategies(listStrat);
            cl.setPortfolio(port);
            currentStrat.setAssets(null);
            msgerr = "";
            msgok = "";
            return "";
        }
    }
    

    
}
