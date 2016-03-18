/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ensimag.projetjava.stateless;

import fr.ensimag.projetjava.entity.ParamDate;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author yeungd
 */
@Local
public interface ParamDateFacadeLocal {

    void create(ParamDate paramDate);

    void edit(ParamDate paramDate);

    void remove(ParamDate paramDate);

    ParamDate find(Object id);

    List<ParamDate> findAll();

    List<ParamDate> findRange(int[] range);

    int count();
    
}
