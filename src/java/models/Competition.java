/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author jean-gustave
 */
@XmlRootElement
@ManagedBean
@SessionScoped
public class Competition implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    @NotNull(message ="le nom est requis")
    private String nom;
    @Future(message = "la date doit être dans le futur")
    private Date dateCompetition;
    @NotNull
    private String rue;
    @NotNull
    private int numero;
    @NotNull
    private int codePostal;
    @NotNull
    private String localite;

    private int statut;

    private Saison saison;
    
    private Sport sport;

    private Collection<Utilisateur> utilisateurCollection;

    private Collection<Groupe> groupeCollection;

    public Competition() {
        utilisateurCollection = new ArrayList<>();
    }

    public Competition(int id) {
        this.id = id;
    }

    public Competition(int id, String nom, Date dateCompetition, String rue,
            int codePostal, int numero, String localite) {
        this.id = id;
        this.nom = nom;
        this.dateCompetition = dateCompetition;
        this.rue = rue;
        this.codePostal = codePostal;
        this.localite = localite;
        this.numero = numero;
    }

    @PostConstruct
    public void init() {
        this.statut = 0;
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

    public Date getDateCompetition() {
        return dateCompetition;
    }

    public void setDateCompetition(Date dateCompetition) {
        this.dateCompetition = dateCompetition;
    }

    public String getRue() {
        return rue;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(int codePostal) {
        this.codePostal = codePostal;
    }

    public String getLocalite() {
        return localite;
    }

    public void setLocalite(String localite) {
        this.localite = localite;
    }

    public int getStatut() {
        return statut;
    }

    public void setStatut(int statut) {
        this.statut = statut;
    }

    public Saison getSaison() {
        return saison;
    }

    public void setSaison(Saison saison) {
        this.saison = saison;
    }

    public Sport getSport() {
        return sport;
    }

    public void setSport(Sport sport) {
        this.sport = sport;
    }
    

    @XmlTransient
    @JsonIgnore
    public Collection<Utilisateur> getUtilisateurCollection() {
        return utilisateurCollection;
    }

    public void setUtilisateurCollection(Collection<Utilisateur> utilisateurCollection) {
        this.utilisateurCollection = utilisateurCollection;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<Groupe> getGroupeCollection() {
        return groupeCollection;
    }

    public void setGroupeCollection(Collection<Groupe> groupeCollection) {
        this.groupeCollection = groupeCollection;
    }


    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Competition)) {
            return false;
        }
        Competition other = (Competition) object;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.id+"/"+this.nom+"/"+this.rue+"/"+this.numero+"/"+this.localite+"/"+this.dateCompetition+"/"+this.numero+"/"+this.codePostal+"/"+this.statut;
    }

}
