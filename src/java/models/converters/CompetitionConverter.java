/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import models.Competition;
import models.Groupe;

/**
 *
 * @author jean-gustave
 */
@FacesConverter("competitionconverter")
public class CompetitionConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Competition competititon = new Competition();
        String[] values = value.split("/");
        competititon.setId(Integer.parseInt(values[0]));
        competititon.setNom(values[1]);
        competititon.setRue(values[2]);
        competititon.setNumero(Integer.parseInt(values[3]));
        competititon.setLocalite(values[4]);
        competititon.setStatut(Integer.parseInt(values[8]));
        competititon.setCodePostal(Integer.parseInt(values[7]));
        return competititon;

    }

   @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
       Competition competition = (Competition) value;
       return  competition.toString();
    }

}
