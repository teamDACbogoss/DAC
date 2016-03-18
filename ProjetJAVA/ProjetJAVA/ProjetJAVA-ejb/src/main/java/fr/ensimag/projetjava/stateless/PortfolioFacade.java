/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ensimag.projetjava.stateless;

import fr.ensimag.projetjava.entity.Portfolio;
import java.util.List;
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
 
    @Override
    public void create(Portfolio portfolio) {
        super.create(portfolio);
    }

    @Override
    public void edit(Portfolio portfolio) {
        super.edit(portfolio);
    }

    @Override
    public void remove(Portfolio portfolio) {
        super.remove(portfolio);
    }

    @Override
    public Portfolio find(Object id) {
        return super.find(id);
    }

    @Override
    public List<Portfolio> findAll() {
        return super.findAll();
    }

    @Override
    public List<Portfolio> findRange(int[] range) {
        return super.findRange(range);
    }

    @Override
    public int count() {
        return super.count();
    }
}
