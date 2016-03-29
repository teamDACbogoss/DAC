/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ensimag.projetjava.entity;

import java.io.Serializable;
import java.util.Random;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author josib
 */
@Entity
public abstract class Asset implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    //protected Long id;
    protected String name;
    protected String emissionDate;
    protected String maturityDate;


    public String getEmissionDate() {
        Random r = new Random();
        int jour = r.nextInt(27)+1;
                int mois = r.nextInt(11)+1;
    int annee = r.nextInt(10)+1990;
        
        return Integer.toString(annee)+"-"+Integer.toString(mois)+"-"+Integer.toString(jour);
    }

    public void setEmissionDate(String emissionDate) {
        this.emissionDate = emissionDate;
    }

    public String getMaturityDate() {
        Random r = new Random();
        int jour = r.nextInt(27)+1;
                int mois = r.nextInt(11)+1;
        int annee = r.nextInt(10)+2015;
        
        return Integer.toString(annee)+"-"+Integer.toString(mois)+"-"+Integer.toString(jour); 
    }
    
    

    public void setMaturityDate(String maturityDate) {
        
        this.maturityDate = maturityDate;
    }
    
    //private int quantite
    /*
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    */
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (name != null ? name.hashCode() : 0);
        return hash;
    }
    
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Asset)) {
            return false;
        }
        FinancialOption other = (FinancialOption) object;
        return !((this.name == null && other.name != null) || (this.name != null && !this.name.equals(other.name)));
    }
    
    public abstract double getPrice(java.util.Calendar date);

    /**
     *
     * @param date
     * @return
     */
    public abstract double getPricePrevious(java.util.Calendar date);
    

}
