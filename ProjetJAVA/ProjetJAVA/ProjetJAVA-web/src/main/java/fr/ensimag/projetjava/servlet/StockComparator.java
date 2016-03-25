/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ensimag.projetjava.servlet;

import fr.ensimag.projetjava.entity.Stock;
import java.util.Comparator;

/**
 *
 * @author josib
 */
public class StockComparator implements Comparator<Stock> {

    @Override
    public int compare(Stock t, Stock t1) {
        return t.getName().compareToIgnoreCase(t1.getName());
    }
    
}
