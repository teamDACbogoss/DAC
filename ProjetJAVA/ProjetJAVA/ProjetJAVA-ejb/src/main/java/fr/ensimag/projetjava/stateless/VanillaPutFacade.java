/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ensimag.projetjava.stateless;

import fr.ensimag.projetjava.entity.VanillaPut;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 *
 * @author malacarc
 */
@Stateless
public class VanillaPutFacade extends AbstractFacade<VanillaPut> implements VanillaPutFacadeLocal {
    @PersistenceContext(unitName = "ProjetJAVA_PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public VanillaPutFacade() {
        super(VanillaPut.class);
    }
    
    @Override
    public void create(VanillaPut vanillaPut) {
        super.create(vanillaPut);
    }

    @Override
    public void edit(VanillaPut vanillaPut) {
        super.edit(vanillaPut);
    }

    @Override
    public void remove(VanillaPut vanillaPut) {
        super.remove(vanillaPut);
    }

    @Override
    public VanillaPut find(Object id) {
        return super.find(id);
    }

    @Override
    public List<VanillaPut> findAll() {
        return super.findAll();
    }

    @Override
    public List<VanillaPut> findRange(int[] range) {
        return super.findRange(range);
    }

    @Override
    public int count() {
        return super.count();
    }
}
