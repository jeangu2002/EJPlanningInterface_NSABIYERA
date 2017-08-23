/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models.converters;

import controllers.beans.ControllerEntrainementManagedBean;
import controllers.beans.ControllerSportManagedBean;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import models.Sport;
import org.apache.jasper.tagplugins.jstl.ForEach;
import service.SportRestClient;
import service.urls.RestUrls;

/**
 *
 * @author jean-gustave
 */
@FacesConverter("sportconverter")
public class SportConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
         
        try {
            String[] values = value.split(" ");
            Sport cSport = new Sport();
            cSport.setId(Integer.parseInt(values[0]));
            cSport.setNom(values[1]);
            return cSport;
        } catch (Exception ex) {
            Logger.getLogger(SportConverter.class.getName()).log(Level.SEVERE, null, ex);
        }
                
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        //Sport sport = (Sport) value;
        Sport sport = (Sport) value;
        String toString = sport.toString();
       return toString;
    }
    
}
