/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utlities.json;

import java.io.IOException;
import java.util.ArrayList;
import models.Sport;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

/**
 *
 * @author jean-gustave
 */
public abstract class JsonUtility<T> {
    protected ObjectMapper mapper = new ObjectMapper();

    /**
     *
     * @param jsonEntity
     * @return
     */
    public abstract T fromJsonString(String jsonEntity) throws IOException;
    
    /**
     *
     * @param entity
     * @return
     * @throws Exception
     */
    public String toJsonString(T entity) throws Exception{
        
      String jsonEntity = null;
        if(entity != null)
        {
           jsonEntity  = mapper.writeValueAsString(entity);
        }
        else
        {
            throw new Exception("entity cannot be null");
        }
        
        return jsonEntity;
    }
    
    public abstract ArrayList<T> fromJsonStringList(String jsonString) throws IOException;
    
}
