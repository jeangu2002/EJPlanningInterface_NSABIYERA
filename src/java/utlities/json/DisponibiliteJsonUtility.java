/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utlities.json;

import java.io.IOException;
import java.util.ArrayList;
import models.Disponibilite;

/**
 *
 * @author jean-gustave
 */
public class DisponibiliteJsonUtility extends JsonUtility<Disponibilite> {

    @Override
    public Disponibilite fromJsonString(String jsonEntity) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Disponibilite> fromJsonStringList(String jsonString) throws IOException {
       ArrayList<Disponibilite> list;
        if(jsonString != null)
        {
           list  = mapper.readValue(jsonString, mapper.getTypeFactory().constructCollectionType(ArrayList.class,Disponibilite.class));
        }
        else
        {
            throw new IOException("jsonString cannot be null");
        }
        
        return list;
    }
    
}
