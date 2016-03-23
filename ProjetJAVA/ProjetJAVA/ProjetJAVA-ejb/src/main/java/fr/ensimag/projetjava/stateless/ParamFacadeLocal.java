/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ensimag.projetjava.stateless;

import fr.ensimag.projetjava.entity.Param;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author yeungd
 */
@Local
public interface ParamFacadeLocal {

    void create(Param param);

    void edit(Param param);

    void remove(Param param);

    Param find(Object id);

    List<Param> findAll();

    List<Param> findRange(int[] range);

    int count();
    
}
