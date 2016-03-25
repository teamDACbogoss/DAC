/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ensimag.projetjava.servlet;

import fr.ensimag.projetjava.entity.Client;
import fr.ensimag.projetjava.stateless.ClientFacadeLocal;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author josib
 */
@Named(value = "gestionComptes")
@RequestScoped
public class gestionComptes {
    
    @EJB
    private fr.ensimag.projetjava.stateless.ClientFacadeLocal clientFacade;

    private List<Client> listClients;
    private String msgerr;
    private String msgok;
    
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

    public ClientFacadeLocal getClientFacade() {
        return clientFacade;
    }

    public void setClientFacade(ClientFacadeLocal clientFacade) {     
        this.clientFacade = clientFacade;
    }

    public List<Client> getListClients() {
        listClients = new LinkedList<>();
        for(Client cl : clientFacade.findAll()) {
            listClients.add(cl);
        }
        Collections.sort(listClients, new ClientComparator());
        return listClients;
    }

    public void setListClients(List<Client> listClients) {
        this.listClients = listClients;
    }
    
    /**
     * Creates a new instance of gestionComptes
     */
    public gestionComptes() {
        msgerr = "";
        msgok = "";
    }

    public String supprimerCompte(String name) {
        Client cl = clientFacade.find(name);
        if (cl == null) {
            msgerr = "Ce compte n'existe pas";
            msgok = "";
        } else {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            sessionBean session = (sessionBean)facesContext.getApplication()
                    .createValueBinding("#{sessionBean}").getValue(facesContext);
            Client moi = clientFacade.find(session.getName());
            if (moi == null) {
                msgerr = "Vous n'êtes pas connecté";
                msgok = "";
            } else {
                if (moi.getEmail().equals(cl.getEmail())) {
                    msgerr = "Impossible de supprimer son propre compte";
                    msgok = "";
                } else {
                    clientFacade.remove(cl);
                    msgerr = "";
                    msgok = "Compte " + cl.getEmail() + " supprimé";
                }
            }
        }
        return "";
    }
    
    public String rendreAdmin(String name) {
        Client cl = clientFacade.find(name);
        if (cl == null) {
            msgerr = "Ce compte n'existe pas";
            msgok = "";
        } else {
            cl.setIsAdmin(true);
            clientFacade.edit(cl);
            msgerr = "";
            msgok = "Compte " + cl.getEmail() + " maintenant administrateur";
        }
        return "";
    }
    
    public String rendreNonAdmin(String name) {
        Client cl = clientFacade.find(name);
        if (cl == null) {
            msgerr = "Ce compte n'existe pas";
            msgok = "";
        } else {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            sessionBean session = (sessionBean)facesContext.getApplication()
                    .createValueBinding("#{sessionBean}").getValue(facesContext);
            Client moi = clientFacade.find(session.getName());
            if (moi == null) {
                msgerr = "Vous n'êtes pas connecté";
                msgok = "";
            } else {
                if (moi.getEmail().equals(cl.getEmail())) {
                    msgerr = "Impossible de s'enlever le statut administrateur";
                    msgok = "";
                } else {
                    cl.setIsAdmin(false);
                    clientFacade.edit(cl);
                    msgerr = "";
                    msgok = "Compte " + cl.getEmail() + " maintenant non administrateur";
                }
            }
        }
        return "";
    }
    
    public String crediter(String name) {
        Client cl = clientFacade.find(name);
        if (cl == null) {
            msgerr = "Le compte " + name + " n'existe pas";
            msgok = "";
        } else {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            sessionBean session = (sessionBean)facesContext.getApplication()
                    .createValueBinding("#{sessionBean}").getValue(facesContext);
            Client moi = clientFacade.find(session.getName());
            if (moi == null) {
                msgerr = "Vous n'êtes pas connecté";
                msgok = "";
            } else {
                if (moi.getEmail().equals(cl.getEmail())) {
                    msgerr = "Vous ne pouvez pas vous créditer vous-même";
                    msgok = "";
                } else {
                    double monCash = moi.getCash();
                    double echange = 1000.0;
                    if (monCash < echange) {
                        msgerr = "Vous n'avez pas assez d'argent";
                        msgok = "";
                    } else {
                        moi.setCash(monCash - echange);
                        cl.setCash(cl.getCash() + echange);
                        clientFacade.edit(moi);
                        clientFacade.edit(cl);
                        msgerr = "";
                        msgok = "Compte " + cl.getEmail() + " crédité de 1000€";
                    }
                }
            }
        }
        return "";
    }
    
    public String emprunter(String name) {
        Client cl = clientFacade.find(name);
        if (cl == null) {
            msgerr = "Ce compte n'existe pas";
            msgok = "";
        } else {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            sessionBean session = (sessionBean)facesContext.getApplication()
                    .createValueBinding("#{sessionBean}").getValue(facesContext);
            Client moi = clientFacade.find(session.getName());
            if (moi == null) {
                msgerr = "Vous n'êtes pas connecté";
                msgok = "";
            } else {
                if (moi.getEmail().equals(cl.getEmail())) {
                    msgerr = "Vous ne pouvez pas emprunter à vous-même";
                    msgok = "";
                } else {
                    double sonCash = cl.getCash();
                    double echange = 1000.0;
                    if (sonCash < echange) {
                        msgerr = "L'utilisateur n'a pas assez d'argent";
                        msgok = "";
                    } else {
                        cl.setCash(sonCash - echange);
                        moi.setCash(moi.getCash() + echange);
                        clientFacade.edit(cl);
                        clientFacade.edit(moi);
                        msgerr = "";
                        msgok = "Compte " + cl.getEmail() + " emprunté de 1000€";
                    }
                }
            }
        }
        return "";
    }
    
}
