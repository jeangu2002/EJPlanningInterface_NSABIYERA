/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author jean-gustave
 */
@Embeddable
public class AffecterPK implements Serializable {

    
    private int idEntrainement;

    private int idMoniteur;

    public AffecterPK() {
    }

    public AffecterPK(int idEntrainement, int idMoniteur) {
        this.idEntrainement = idEntrainement;
        this.idMoniteur = idMoniteur;
    }

    public int getIdEntrainement() {
        return idEntrainement;
    }

    public void setIdEntrainement(int idEntrainement) {
        this.idEntrainement = idEntrainement;
    }

    public int getIdMoniteur() {
        return idMoniteur;
    }

    public void setIdMoniteur(int idMoniteur) {
        this.idMoniteur = idMoniteur;
    }


    @Override
    public String toString() {
        return "entity.AffecterPK[ idEntrainement=" + idEntrainement + ", idMoniteur=" + idMoniteur + " ]";
    }
    
}
