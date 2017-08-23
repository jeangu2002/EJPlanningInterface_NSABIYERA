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
@XmlRootElement
@ManagedBean
@SessionScoped
public class Utilisateur implements Serializable {
    @JsonProperty("id")
    private int id;
    @JsonProperty("nom")
    @Pattern(regexp = "^[a-zA-Z][a-zA-Z-]+$",message = "Le nom doit être composés de lettres ")
    private String nom;
    @Pattern(regexp = "^[a-zA-Z][a-zA-Z-]+$", message = "Le nom doit être composés de lettres " )
    @JsonProperty("prenom")
    private String prenom;
    @Pattern(regexp = "[a-zA-Z.-]+$")
    @JsonProperty("pseudo")
    private String pseudo;
    @NotNull
    @Pattern(regexp = "[a-zA-Z0-9.*@#&|]+",message = "le mot mot de passe doit être composé de chifres, lettres et des caractères suivants: .*@#&")
    @JsonProperty("motDePasse")
    private String motDePasse;
    @JsonProperty("type")
    private String type;

    private Collection<Entrainement> entrainementCollection;


    public Utilisateur() {
    }

    public Utilisateur(int id) {
        this.id = id;
    }

    public Utilisateur(int id, String nom, String prenom, String pseudo, String motDePasse, String type) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.pseudo = pseudo;
        this.motDePasse = motDePasse;
        this.type = type;
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

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @XmlTransient
    public Collection<Entrainement> getEntrainementCollection() {
        return entrainementCollection;
    }

    public void setEntrainementCollection(Collection<Entrainement> entrainementCollection) {
        this.entrainementCollection = entrainementCollection;
    }
 
    
}
