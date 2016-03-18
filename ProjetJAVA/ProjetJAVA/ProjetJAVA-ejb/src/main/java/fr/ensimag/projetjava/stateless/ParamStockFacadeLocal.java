/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ensimag.projetjava.stateless;

import fr.ensimag.projetjava.entity.ParamStock;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author yeungd
 */
@Local
public interface ParamStockFacadeLocal {

    void create(ParamStock paramStock);

    void edit(ParamStock paramStock);

    void remove(ParamStock paramStock);

    ParamStock find(Object id);

    List<ParamStock> findAll();

    List<ParamStock> findRange(int[] range);

    int count();
    
}
