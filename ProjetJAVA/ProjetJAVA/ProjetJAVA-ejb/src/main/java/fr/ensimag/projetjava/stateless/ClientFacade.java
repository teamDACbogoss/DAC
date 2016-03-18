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
    
    public void create(Client client) {
        super.create(client);
    }

    public void edit(Client client) {
        super.edit(client);
    }

    public void remove(Client client) {
        super.remove(client);
    }

    public Client find(Object id) {
        return super.find(id);
    }

    public List<Client> findAll() {
        return super.findAll();
    }

    public List<Client> findRange(int[] range) {
        return super.findRange(range);
    }

    public int count() {
        return super.count();
    }
}
