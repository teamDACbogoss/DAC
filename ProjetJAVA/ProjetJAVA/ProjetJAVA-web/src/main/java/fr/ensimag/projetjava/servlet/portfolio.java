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
        ListPortfolio = session.getClient().getPortfolio().getStrategies();
        for(Strategy s : ListPortfolio){
            if(s.getName().equals(name)){
                ListPortfolio.remove(s);
            }
            return;
        }
        clientFacade.edit(session.getClient());
    }

    public List<Strategy> getListPortfolio() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        sessionBean session = (sessionBean)facesContext.getApplication()
                    .createValueBinding("#{sessionBean}").getValue(facesContext);
        ListPortfolio = session.getClient().getPortfolio().getStrategies();
        //Client myClient = clientFacade.find(session.getClient().getEmail());
        
        //msg = Integer.toString(myClient.getPortfolio().getStrategies().size());
        //        msg = Integer.toString(ListPortfolio.size());
        return ListPortfolio;

        //return myClient.getPortfolio().getStrategies();
    }

    public void setListPortfolio(List<Strategy> ListPortfolio) {
        this.ListPortfolio = ListPortfolio;
    }
    
    

   
 
}
