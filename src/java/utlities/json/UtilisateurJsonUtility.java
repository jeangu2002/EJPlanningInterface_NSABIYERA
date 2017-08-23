/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utlities.json;

import java.io.IOException;
import java.util.ArrayList;
import models.Utilisateur;

/**
 *
 * @author jean-gustave
 */
public class UtilisateurJsonUtility extends JsonUtility<Utilisateur>{

    @Override
    public Utilisateur fromJsonString(String jsonEntity) throws IOException {
        Utilisateur utilisateur = mapper.readValue(jsonEntity, Utilisateur.class);
        return utilisateur;
    }

    @Override
    public String toJsonString(Utilisateur item) throws Exception {
        return super.toJsonString(item);
    }

    @Override
    public ArrayList<Utilisateur> fromJsonStringList(String jsonString) throws IOException {
        ArrayList<Utilisateur> list;
        if(jsonString != null)
        {
           list  = mapper.readValue(jsonString, mapper.getTypeFactory().constructCollectionType(ArrayList.class,Utilisateur.class));
        }
        else
        {
            throw new IOException("jsonString cannot be null");
        }
        
        return list;
    }

}
