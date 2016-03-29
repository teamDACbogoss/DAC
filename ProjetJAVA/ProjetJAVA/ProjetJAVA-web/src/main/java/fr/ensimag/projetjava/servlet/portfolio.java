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
import fr.ensimag.projetjava.stateless.StockFacadeLocal;
import fr.ensimag.projetjava.stateless.StrategyFacade;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;

/**
 *
 * @author bonkoskk
 */
@Named(value = "portfolio")
@RequestScoped
public class portfolio {

    @EJB
    private fr.ensimag.projetjava.stateless.StockFacadeLocal stockFacade;
    @EJB
    private fr.ensimag.projetjava.stateless.ClientFacadeLocal clientFacade;
    @EJB
    private fr.ensimag.projetjava.stateless.PortfolioFacadeLocal portfolioFacade;

    public portfolio() {
        msg = "";
    }
    
    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
    
    

    private List<Strategy> ListPortfolio;
    
    public void clotureStrat(String name){
        FacesContext facesContext = FacesContext.getCurrentInstance();
        sessionBean session = (sessionBean)facesContext.getApplication()
                    .createValueBinding("#{sessionBean}").getValue(facesContext);
        //ListPortfolio = session.getClient().getPortfolio().getStrategies();
        Client client = clientFacade.find(session.getClient().getEmail());
        java.util.Calendar todayCal = java.util.Calendar.getInstance();
        todayCal.set(Calendar.HOUR_OF_DAY, 0);
        todayCal.set(Calendar.MINUTE, 0);
        todayCal.set(Calendar.SECOND, 0);      

        for(Strategy s : client.getPortfolio().getStrategies()){

            if(s.getName().equals(name)){
                client.setCash(client.getCash() + s.getPrice(todayCal));
                client.getPortfolio().getStrategies().remove(s);
                //portfolioFacade.edit(client.getPortfolio());
                clientFacade.edit(client);
                return;
            }
        }
    }

    public List<Strategy> getListPortfolio() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        sessionBean session = (sessionBean)facesContext.getApplication()
                    .createValueBinding("#{sessionBean}").getValue(facesContext);
        //ListPortfolio = session.getClient().getPortfolio().getStrategies();
        Client myClient = clientFacade.find(session.getClient().getEmail());
        
        //msg = Integer.toString(myClient.getPortfolio().getStrategies().size());
        //        msg = Integer.toString(ListPortfolio.size());
        return myClient.getPortfolio().getStrategies();

        //return myClient.getPortfolio().getStrategies();
    }

    public void setListPortfolio(List<Strategy> ListPortfolio) {
        this.ListPortfolio = ListPortfolio;
    }
    
    

   
 
}
