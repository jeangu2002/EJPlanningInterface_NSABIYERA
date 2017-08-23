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
import models.Groupe;

/**
 *
 * @author jean-gustave
 */
@FacesConverter("groupeconverter")
public class GroupeConverter implements Converter{

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Groupe groupe = new Groupe();
        String[] values = value.split(" ");
        groupe.setId(Integer.parseInt(values[0]));
        groupe.setNom(values[1]);
        return groupe;
    
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
       Groupe groupe = (Groupe) value;
       return groupe.toString();
    }
    
}
