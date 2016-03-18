/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ensimag.projetjava.stateless;

import fr.ensimag.projetjava.entity.FinancialOption;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author randont
 */
@Local
public interface FinancialOptionFacadeLocal {

    void create(FinancialOption financialOption);

    void edit(FinancialOption financialOption);

    void remove(FinancialOption financialOption);

    FinancialOption find(Object id);

    List<FinancialOption> findAll();

    List<FinancialOption> findRange(int[] range);

    int count();
    
}
