/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ensimag.projetjava.stateless;

import fr.ensimag.projetjava.Portfolio;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author malacarc
 */
@Stateless
public class PortfolioFacade extends AbstractFacade<Portfolio> implements PortfolioFacadeLocal {
    @PersistenceContext(unitName = "ProjetJAVA_PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PortfolioFacade() {
        super(Portfolio.class);
    }
    
}
