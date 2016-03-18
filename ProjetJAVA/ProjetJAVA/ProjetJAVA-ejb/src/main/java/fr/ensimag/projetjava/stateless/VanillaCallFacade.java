/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ensimag.projetjava.stateless;

import fr.ensimag.projetjava.entity.VanillaCall;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 *
 * @author malacarc
 */
@Stateless
public class VanillaCallFacade extends AbstractFacade<VanillaCall> implements VanillaCallFacadeLocal {
    @PersistenceContext(unitName = "ProjetJAVA_PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public VanillaCallFacade() {
        super(VanillaCall.class);
    }
    
    @Override
    public void create(VanillaCall vanillaCall) {
        super.create(vanillaCall);
    }

    @Override
    public void edit(VanillaCall vanillaCall) {
        super.edit(vanillaCall);
    }

    @Override
    public void remove(VanillaCall vanillaCall) {
        super.remove(vanillaCall);
    }

    @Override
    public VanillaCall find(Object id) {
        return super.find(id);
    }

    @Override
    public List<VanillaCall> findAll() {
        return super.findAll();
    }

    @Override
    public List<VanillaCall> findRange(int[] range) {
        return super.findRange(range);
    }

    @Override
    public int count() {
        return super.count();
    }
}
