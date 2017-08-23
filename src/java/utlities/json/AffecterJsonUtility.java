/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utlities.json;

import java.io.IOException;
import java.util.ArrayList;
import models.Affecter;

/**
 *
 * @author jean-gustave
 */
public class AffecterJsonUtility extends JsonUtility<Affecter> {

    @Override
    public Affecter fromJsonString(String jsonEntity) throws IOException {
        return mapper.readValue(jsonEntity, Affecter.class);
    }

    @Override
    public ArrayList<Affecter> fromJsonStringList(String jsonString) throws IOException {
       ArrayList<Affecter> list;
        if(jsonString != null)
        {
           list  = mapper.readValue(jsonString, mapper.getTypeFactory().constructCollectionType(ArrayList.class,Affecter.class));
        }
        else
        {
            throw new IOException("jsonString cannot be null");
        }
        
        return list;
    }
    
}
