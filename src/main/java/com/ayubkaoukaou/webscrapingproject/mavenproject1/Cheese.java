/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ayubkaoukaou.webscrapingproject.mavenproject1;

import java.util.Set;
import javax.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

/** Represents a Cheese. 
    Java annotation is used for the mapping. */


@Entity
@Table(name="cheese")
public class Cheese {
   
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   
   @Column(name = "id")
    private int id;
   
   @Column(name = "product_type")
    private String productType;
   
   @OneToMany (mappedBy="cheese")
   private Set<PriceComparison> PriceComparison;
   
 
   
   
 
    
   
   
   /** Cheese and product type getters and setters. */
   public Cheese () {
       
   }
   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public Set<PriceComparison> getPriceComparison() {
        return PriceComparison;
    }

    public void setPriceComparison(Set<PriceComparison> PriceComparison) {
        this.PriceComparison = PriceComparison;
    }
    
    
    
   /** Returns a String representation of Cheese */
    public String toString() {
        String str = "Cheese. id: " + id + "; productType: " + productType;
        return str;
    }
    
}
