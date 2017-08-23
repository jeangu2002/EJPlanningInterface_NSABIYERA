/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utlities.json;

import java.io.IOException;
import java.util.ArrayList;
import models.Competition;
import models.Sport;

/**
 *
 * @author jean-gustave
 */
public class CompetitionJsonUtility extends JsonUtility<Competition>{

    @Override
    public Competition fromJsonString(String jsonEntity) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String toJsonString(Competition entity) throws Exception {
        return super.toJsonString(entity);
    }

    @Override
    public ArrayList fromJsonStringList(String jsonString) throws IOException {
       ArrayList<Competition> list;
        if(jsonString != null)
        {
           list  = mapper.readValue(jsonString, mapper.getTypeFactory().constructCollectionType(ArrayList.class,Competition.class));
        }
        else
        {
            throw new IOException("jsonString cannot be null");
        }
        
        return list;
    }
    
}
