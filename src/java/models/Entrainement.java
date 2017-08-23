/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.validation.constraints.Future;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author jean-gustave
 */

@ManagedBean
@SessionScoped
@XmlRootElement
public class Entrainement implements Serializable {

    private static final long serialVersionUID = 1L;
   
    private int id;
    @Future
    private Date dateEntrainement;
    private int statut;

    public Date getDateEntrainement() {
        return dateEntrainement;
    }

    public void setDateEntrainement(Date dateEntrainement) {
        this.dateEntrainement = dateEntrainement;
    }

    public int getStatut() {
        return statut;
    }

    public void setStatut(int statut) {
        this.statut = statut;
    }
    
    private Groupe groupe;

    private Collection<Utilisateur> utilisateurCollection;
    private ArrayList<Sport> sports;
    private Saison saison;
    @XmlTransient
    @JsonIgnore
    private Collection<Utilisateur> moniteurs;
    
    private Collection<Entrainement> entrainementCollection;
    
    public Entrainement() {
    }

    public Entrainement(int id) {
        this.id = id;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public Groupe getGroupe() {
        return groupe;
    }

    public void setGroupe(Groupe groupe) {
        this.groupe = groupe;
    }
    
    
    public Collection<Utilisateur> getUtilisateurCollection() {
        return utilisateurCollection;
    }

    public void setUtilisateurCollection(Collection<Utilisateur> utilisateurCollection) {
        this.utilisateurCollection = utilisateurCollection;
    }

    public ArrayList<Sport> getSports() {
        return sports;
    }

    /**
     *
     * @param sport
     */

    public Collection<Utilisateur> getMoniteurs() {
        return this.moniteurs;
    }

    public void setMoniteurs(Collection<Utilisateur> moniteurs) {
        this.moniteurs = moniteurs;
    }
     @XmlTransient
    public Collection<Entrainement> getEntrainementCollection() {
        return entrainementCollection;
    }

    public void setEntrainementCollection(Collection<Entrainement> entrainementCollection) {
        this.entrainementCollection = entrainementCollection;
    }

    public Saison getSaison() {
        return saison;
    }

    public void setSaison(Saison saison) {
        this.saison = saison;
    }

    public void setSports(ArrayList<Sport> sport) {
        this.sports = sport;
    }
    
    
    
}
