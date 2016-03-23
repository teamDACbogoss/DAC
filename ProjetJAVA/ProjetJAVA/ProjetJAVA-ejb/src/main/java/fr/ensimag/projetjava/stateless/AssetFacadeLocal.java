/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ensimag.projetjava.stateless;

import fr.ensimag.projetjava.entity.Asset;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author malacarc
 */
@Local
public interface AssetFacadeLocal {

    void create(Asset asset);

    void edit(Asset asset);

    void remove(Asset asset);

    Asset find(Object id);

    List<Asset> findAll();

    List<Asset> findRange(int[] range);

    int count();
    
}
