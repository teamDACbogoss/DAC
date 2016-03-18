/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ensimag.projetjava.stateless;

import fr.ensimag.projetjava.entity.VanillaCall;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author malacarc
 */
@Local
public interface VanillaCallFacadeLocal {

    void create(VanillaCall vanillaCall);

    void edit(VanillaCall vanillaCall);

    void remove(VanillaCall vanillaCall);

    VanillaCall find(Object id);

    List<VanillaCall> findAll();

    List<VanillaCall> findRange(int[] range);

    int count();
    
}
