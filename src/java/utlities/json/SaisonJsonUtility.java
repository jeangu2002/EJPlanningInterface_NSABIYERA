/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utlities.json;

import java.io.IOException;
import java.util.ArrayList;
import models.Saison;

/**
 *
 * @author jean-gustave
 */
public class SaisonJsonUtility extends JsonUtility<Saison>{

    @Override
    public Saison fromJsonString(String jsonEntity) throws IOException {
       Saison saison = mapper.readValue(jsonEntity, Saison.class);
       return saison;
    }
    @Override
    public String toJsonString(Saison saison) throws Exception
    {
        return super.toJsonString(saison);
    }
    
    @Override
    public ArrayList<Saison> fromJsonStringList(String jsonString) throws IOException
    {
       ArrayList<Saison> list;
        if(jsonString != null)
        {
           list  = mapper.readValue(jsonString, mapper.getTypeFactory().constructCollectionType(ArrayList.class,Saison.class));
        }
        else
        {
            throw new IOException("jsonString cannot be null");
        }
        
        return list;
    }
}
