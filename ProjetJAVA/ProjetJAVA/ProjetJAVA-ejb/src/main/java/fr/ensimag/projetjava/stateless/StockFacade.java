/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ensimag.projetjava.stateless;

import fr.ensimag.projetjava.entity.Stock;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author yeungd
 */
@Stateless
public class StockFacade extends AbstractFacade<Stock> implements StockFacadeLocal {
    @PersistenceContext(unitName = "ProjetJAVA_PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public StockFacade() {
        super(Stock.class);
    }
    
}
