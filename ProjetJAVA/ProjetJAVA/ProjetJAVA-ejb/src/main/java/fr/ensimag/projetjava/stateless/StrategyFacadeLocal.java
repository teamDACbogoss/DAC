/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ensimag.projetjava.stateless;

import fr.ensimag.projetjava.entity.Strategy;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author randont
 */
@Local
public interface StrategyFacadeLocal {
    
    void create(Strategy strat);

    void edit(Strategy strat);

    void remove(Strategy strat);

    Strategy find(Object id);

    List<Strategy> findAll(); 
    
    List<Strategy> findRange(int[] range);
    
     int count();
}
