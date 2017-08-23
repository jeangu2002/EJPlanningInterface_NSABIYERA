/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utlities.json;

import java.io.IOException;
import java.util.ArrayList;
import models.Groupe;
import models.Sport;

/**
 *
 * @author jean-gustave
 */
public class GroupeJsonUtility extends JsonUtility<Groupe>{

    @Override
    public Groupe fromJsonString(String jsonEntity) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String toJsonString(Groupe entity) throws Exception {
        return super.toJsonString(entity);
    }

    @Override
    public ArrayList<Groupe> fromJsonStringList(String jsonString) throws IOException {
        ArrayList<Groupe> list;
        if(jsonString != null)
        {
           list  = mapper.readValue(jsonString, mapper.getTypeFactory().constructCollectionType(ArrayList.class,Groupe.class));
        }
        else
        {
            throw new IOException("jsonString cannot be null");
        }
        
        return list;
    }
    
}
