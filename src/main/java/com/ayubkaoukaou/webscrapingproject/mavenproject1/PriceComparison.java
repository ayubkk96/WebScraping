/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ayubkaoukaou.webscrapingproject.mavenproject1;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

/** Represents a PriceComparison. 
    Java annotation is used for the mapping. */


@Entity
@Table(name="price_comparison")
public class PriceComparison implements Serializable {
    
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
     
   
   @Column(name = "id")
    private int id;
   
   @Column (name = "price")
   private double price;
   
   @Column (name = "description")
   private String description;
   
    @Column (name = "url")
   private String url;
    
    @Column(name = "weight")
   private int weight;
    
    @ManyToOne
    @JoinColumn(name="cheese_id", nullable=false)
    private Cheese cheese;
    
  
   
    
    /** Empty constructor. */
    public PriceComparison() {
    }
    

    
    
    /** Getters and setters. */
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cheese getCheeseId() {
        return cheese;
    }

  

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Cheese getCheese() {
        return cheese;
    }

    public void setCheese(Cheese cheese) {
        this.cheese = cheese;
    }
   
    
    /** Returns a String representation of PriceComparison */
    public String toString() {
        String str = "PriceComparison. id: " + id + "; cheeseId: " + cheese + 
                "; price: " + price + "; description: " + description + "; url: " 
                + url + "; weight: " + weight;
        return str;
    }

   
   
   
     
    
}
