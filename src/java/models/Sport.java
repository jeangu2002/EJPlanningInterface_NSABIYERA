/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.codehaus.jackson.*;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 *
 * @author jean-gustave
 */
@ManagedBean
@SessionScoped
@XmlRootElement
public class Sport implements Serializable {

    private static final long serialVersionUID = 1L;
    @JsonProperty("id")
    private int id;
    @NotNull
    @JsonProperty("nom")
    private String nom;
    
    private Collection<Entrainement> entrainementCollection;

    public Sport() {
    }

    public Sport(int id) {
        this.id = id;
    }

    public Sport(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public Sport(String nom) {
        this.nom = nom;
    }
    
 
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    @XmlTransient
    public Collection<Entrainement> getEntrainementCollection() {
        return entrainementCollection;
    }

    public void setEntrainementCollection(Collection<Entrainement> entrainementCollection) {
        this.entrainementCollection = entrainementCollection;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Sport))
            return false;
        Sport sport = (Sport) obj;
        if(sport != null && sport.nom.equals(this.nom))
                return true;;
        return false;
    }

    @Override
    public String toString() {
       return this.id+" "+this.nom;
    }
    
    
    

    
    
}
