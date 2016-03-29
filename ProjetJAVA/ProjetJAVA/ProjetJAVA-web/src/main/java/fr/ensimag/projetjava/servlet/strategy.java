/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ensimag.projetjava.servlet;

import fr.ensimag.projetjava.entity.Client;
import fr.ensimag.projetjava.entity.Strategy;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
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
    
    private String strategyName; 
    private double totalValue;
    private Strategy currentStrat;
    private String msg;
    
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
    
    /*
    
    public void computePrice() {
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
    }
    */
}
