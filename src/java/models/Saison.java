/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author jean-gustave
 */

@XmlRootElement
@ManagedBean
@SessionScoped
public class Saison implements Serializable {

    private int id;
    @NotNull
    private String nom;
   
    private Date dateDebut;
    @Future
    private Date dateFin;
    
    private Collection<Competition> competionCollection;
    private Collection<Entrainement> entrainementCollection;
    public Saison() {
        
    }
    
    @PostConstruct
    public void init()
    {
        nom = "Saison " + Calendar.getInstance().get(Calendar.YEAR);
    }

    public Saison(int id) {
        this.id = id;
    }

    public Saison(int id, String nom, Date dateDebut, Date dateFin) {
        this.id = id;
        this.nom = nom;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
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

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public Collection<Competition> getCompetionCollection() {
        return competionCollection;
    }

    public void setCompetionCollection(Collection<Competition> competionCollection) {
        this.competionCollection = competionCollection;
    }

    public Collection<Entrainement> getEntrainementCollection() {
        return entrainementCollection;
    }

    public void setEntrainementCollection(Collection<Entrainement> entrainementCollection) {
        this.entrainementCollection = entrainementCollection;
    }
    
    

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Saison)) {
            return false;
        }
        Saison other = (Saison) object;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.id+"-"+this.nom+"-"+this.dateDebut+"-"+this.dateFin;
    }
    
    
}
