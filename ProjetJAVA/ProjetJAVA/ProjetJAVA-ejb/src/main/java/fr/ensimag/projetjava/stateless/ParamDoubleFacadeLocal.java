/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ensimag.projetjava.stateless;

import fr.ensimag.projetjava.entity.ParamDouble;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author yeungd
 */
@Local
public interface ParamDoubleFacadeLocal {

    void create(ParamDouble paramDouble);

    void edit(ParamDouble paramDouble);

    void remove(ParamDouble paramDouble);

    ParamDouble find(Object id);

    List<ParamDouble> findAll();

    List<ParamDouble> findRange(int[] range);

    int count();
    
}
