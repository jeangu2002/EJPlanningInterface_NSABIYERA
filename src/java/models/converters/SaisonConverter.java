/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models.converters;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import models.Saison;

/**
 *
 * @author jean-gustave
 */
@FacesConverter("saisonconverter")
public class SaisonConverter implements Converter{

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        try {
            Saison saison = new Saison();
            String[] values = value.split("-");
            saison.setId(Integer.parseInt(values[0]));
            saison.setNom(values[1]);
            //saison.setDateDebut(new SimpleDateFormat().parse(values[1]));
             //saison.setDateFin(new SimpleDateFormat().parse(values[2]));
             return saison;
        } catch (Exception ex) {
            Logger.getLogger(SaisonConverter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Saison saison = (Saison) value;
        return saison.toString();
    }
    
}
