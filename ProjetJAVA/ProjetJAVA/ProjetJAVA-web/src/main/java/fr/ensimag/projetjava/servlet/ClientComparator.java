/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ensimag.projetjava.servlet;

import fr.ensimag.projetjava.entity.Client;
import java.util.Comparator;

/**
 *
 * @author josib
 */
public class ClientComparator implements Comparator<Client> {

    @Override
    public int compare(Client t, Client t1) {
        return t.getEmail().compareToIgnoreCase(t1.getEmail());
    }
    
}
