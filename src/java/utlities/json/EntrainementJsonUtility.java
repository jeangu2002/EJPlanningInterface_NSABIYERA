/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utlities.json;

import java.io.IOException;
import java.util.ArrayList;
import models.Entrainement;

/**
 *
 * @author jean-gustave
 */
public class EntrainementJsonUtility extends JsonUtility<Entrainement> {

    @Override
    public Entrainement fromJsonString(String jsonEntity) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String toJsonString(Entrainement item) throws Exception {
        return super.toJsonString(item);
    }

    @Override
    public ArrayList<Entrainement> fromJsonStringList(String jsonString) throws IOException {
        ArrayList<Entrainement> list;
        if(jsonString != null)
        {
           list  = mapper.readValue(jsonString, mapper.getTypeFactory().constructCollectionType(ArrayList.class,Entrainement.class));
        }
        else
        {
            throw new IOException("jsonString cannot be null");
        }
        
        return list;
    }
    
}
