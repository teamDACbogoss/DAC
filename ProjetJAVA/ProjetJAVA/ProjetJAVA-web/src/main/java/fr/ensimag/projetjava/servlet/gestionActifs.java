/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ensimag.projetjava.servlet;

import fr.ensimag.projetjava.entity.Stock;
import fr.ensimag.projetjava.entity.Strategy;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author josib
 */
@Named(value = "gestionActifs")
@RequestScoped
public class gestionActifs {

    @EJB
    private fr.ensimag.projetjava.stateless.StockFacadeLocal stockFacade;
    @EJB
    private fr.ensimag.projetjava.stateless.StrategyFacadeLocal strategyFacade;
    
    private String msgerr;
    private String msgok;
    private List<Strategy> listStrategy;

    public String getMsgerr() {
        return msgerr;
    }

    public void setMsgerr(String msgerr) {
        this.msgerr = msgerr;
    }

    public String getMsgok() {
        return msgok;
    }

    public void setMsgok(String msgok) {
        this.msgok = msgok;
    }

    public List<Strategy> getListStrategy() {
        listStrategy = new LinkedList<>();
        for (Strategy strat : strategyFacade.findAll()) {
            listStrategy.add(strat);
        }
        return listStrategy;
    }

    public void setListStrategy(List<Strategy> listStrategy) {
        this.listStrategy = listStrategy;
    }
    
    
    
    /**
     * Creates a new instance of gestionActifs
     */
    public gestionActifs() {
        msgerr = "";
        msgok = "";
    }
    
    public String deleteAsset(String name) {
        Stock stock = stockFacade.find(name);
        if (stock == null) {
            msgerr = "Action introuvable";
            msgok = "";
        } else {
            stockFacade.remove(stock);
            msgerr = "";
            msgok = "Action supprimée";
        }
        return "";
    }
    
    public String addAsset(String name, String code) {
        if (name == null || name.equals("")) {
            msgerr = "L'action doit avoir un nom";
            msgok = "";
            return "";
        } else if (code == null || code.equals("")) {
            msgerr = "L'action doit avoir un symbole";
            msgok = "";
            return "";
        }
        Stock checkExist = stockFacade.find(name);
        if (checkExist != null) {
            msgerr = "L'action existe déjà";
            msgok = "";
            return "";
        }
        Stock st = new Stock(name, code);
        stockFacade.create(st);
        msgerr = "";
        msgok = "Action ajoutée";
        return "gestion-actifs";
    }
    
    public String deleteStrategy(String name) {
        Strategy strat = strategyFacade.find(name);
        if (strat == null) {
            msgerr = "Stratégie introuvable";
            msgok = "";
        } else {
            strategyFacade.remove(strat);
            msgerr = "";
            msgok = "Stratégie supprimée";
        }
        return "";
    }
    
}
