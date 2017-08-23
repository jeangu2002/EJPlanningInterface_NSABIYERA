/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utlities.json;

import java.io.IOException;
import java.util.ArrayList;
import models.Sport;

/**
 *
 * @author jean-gustave
 */
public class SportJsonUtility extends JsonUtility<Sport> {
    
    @Override
    public Sport fromJsonString(String jsonEntity) throws IOException
    {
        Sport sport = mapper.readValue(jsonEntity, Sport.class);
        return sport;
    }
    
    @Override
    public String toJsonString(Sport sport) throws Exception
    {
        return super.toJsonString(sport);
    }

    @Override
    public ArrayList<Sport> fromJsonStringList(String jsonString) throws IOException {
        ArrayList<Sport> list;
        if(jsonString != null)
        {
           list  = mapper.readValue(jsonString, mapper.getTypeFactory().constructCollectionType(ArrayList.class,Sport.class));
        }
        else
        {
            throw new IOException("jsonString cannot be null");
        }
        
        return list;
    }
}
