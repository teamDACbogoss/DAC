/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ensimag.projetjava.stateless;

import fr.ensimag.projetjava.entity.Client;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 *
 * @author malacarc
 */
@Stateless
public class ClientFacade extends AbstractFacade<Client> implements ClientFacadeLocal {
    @PersistenceContext(unitName = "ProjetJAVA_PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ClientFacade() {
        super(Client.class);
    }
    
    @Override
    public void create(Client client) {
        super.create(client);
    }

    @Override
    public void edit(Client client) {
        super.edit(client);
    }

    @Override
    public void remove(Client client) {
        super.remove(client);
    }

    @Override
    public Client find(Object id) {
        return super.find(id);
    }

    @Override
    public List<Client> findAll() {
        return super.findAll();
    }

    @Override
    public List<Client> findRange(int[] range) {
        return super.findRange(range);
    }

    @Override
    public int count() {
        return super.count();
    }
}
