/**
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jean-gustave
 */

@XmlRootElement
public class Affecter implements Serializable {

    protected AffecterPK affecterPK;

    private int statut;

    private Entrainement entrainement;

    private Utilisateur utilisateur;

    public Affecter() {
    }

    public Affecter(AffecterPK affecterPK) {
        this.affecterPK = affecterPK;
    }

    public Affecter(AffecterPK affecterPK, int statut) {
        this.affecterPK = affecterPK;
        this.statut = statut;
    }

    public Affecter(int idEntrainement, int idMoniteur) {
        this.affecterPK = new AffecterPK(idEntrainement, idMoniteur);
    }

    public AffecterPK getAffecterPK() {
        return affecterPK;
    }

    public void setAffecterPK(AffecterPK affecterPK) {
        this.affecterPK = affecterPK;
    }

    public int getStatut() {
        return statut;
    }

    public void setStatut(int statut) {
        this.statut = statut;
    }

    public Entrainement getEntrainement() {
        return entrainement;
    }

    public void setEntrainement(Entrainement entrainement) {
        this.entrainement = entrainement;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (affecterPK != null ? affecterPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Affecter)) {
            return false;
        }
        Affecter other = (Affecter) object;
        if ((this.affecterPK == null && other.affecterPK != null) || (this.affecterPK != null && !this.affecterPK.equals(other.affecterPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Affecter[ affecterPK=" + affecterPK + " ]";
    }
    
}
