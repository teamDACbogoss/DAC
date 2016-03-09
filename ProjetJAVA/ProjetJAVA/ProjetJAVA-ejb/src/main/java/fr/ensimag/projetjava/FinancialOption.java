/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ensimag.projetjava;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 *
 * @author josib
 */
@Entity
public abstract class FinancialOption extends Asset {
    @ManyToOne
    protected ParamDouble strike;
    @ManyToOne
    protected ParamStock underlying;
    @ManyToOne
    protected ParamDate maturity;
}
