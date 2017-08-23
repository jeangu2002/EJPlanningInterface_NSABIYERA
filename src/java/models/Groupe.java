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
import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 *
 * @author jean-gustave
 */
@ManagedBean
@SessionScoped
@XmlRootElement
public class Groupe implements Serializable {
    @JsonProperty("id")
    private int id;
    @NotNull
    @Pattern(regexp = "^[a-zA-Z0_9- /]+$")
    @JsonProperty("nom")
    private String nom;
    @JsonProperty("entrainements")
    private Collection<Entrainement> entrainementCollection;
   
    private Collection<Competition> competitionCollection;
    
    public Groupe() {
    }

    public Groupe(int id) {
        this.id = id;
    }

    public Groupe(int id, String nom) {
        this.id = id;
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
    @XmlTransient
    public Collection<Competition> getCompetitionCollection() {
        return competitionCollection;
    }

    public void setCompetitionCollection(Collection<Competition> competitionCollection) {
        this.competitionCollection = competitionCollection;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Groupe)) {
            return false;
        }
        Groupe other = (Groupe) object;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.id+" "+this.nom;
    }
    
    
    
}
