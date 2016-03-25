/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ensimag.projetjava.stateless;

import fr.ensimag.projetjava.entity.Asset;
import fr.ensimag.projetjava.entity.Strategy;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author randont
 */
@Stateless
public class StrategyFacade extends AbstractFacade<Strategy> implements StrategyFacadeLocal { 
    @PersistenceContext(unitName = "ProjetJAVA_PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public StrategyFacade() {
        super(Strategy.class);
    }
}
