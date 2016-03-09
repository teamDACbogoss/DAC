/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ensimag.projetjava.stateless;

import fr.ensimag.projetjava.Portfolio;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author malacarc
 */
@Local
public interface PortfolioFacadeLocal {

    void create(Portfolio portfolio);

    void edit(Portfolio portfolio);

    void remove(Portfolio portfolio);

    Portfolio find(Object id);

    List<Portfolio> findAll();

    List<Portfolio> findRange(int[] range);

    int count();
    
}
