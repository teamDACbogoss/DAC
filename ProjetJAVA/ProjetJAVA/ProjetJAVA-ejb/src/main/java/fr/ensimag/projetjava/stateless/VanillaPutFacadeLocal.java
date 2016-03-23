/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ensimag.projetjava.stateless;

import fr.ensimag.projetjava.entity.VanillaPut;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author malacarc
 */
@Local
public interface VanillaPutFacadeLocal {

    void create(VanillaPut vanillaPut);

    void edit(VanillaPut vanillaPut);

    void remove(VanillaPut vanillaPut);

    VanillaPut find(Object id);

    List<VanillaPut> findAll();

    List<VanillaPut> findRange(int[] range);

    int count();
    
}
