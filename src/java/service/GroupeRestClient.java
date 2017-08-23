/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.MediaType;
import models.Groupe;
import tilities.http.HttpOutPutStreamUtility;
import utlities.json.GroupeJsonUtility;
import utlities.xml.GroupeXmlUtility;

/**
 *
 * @author jean-gustave
 */
public class GroupeRestClient implements RestInterface<Groupe> {

    private GroupeJsonUtility groupeJsonUtility;
    private GroupeXmlUtility groupeXmlUtility;

    public GroupeRestClient() {
        groupeJsonUtility = new GroupeJsonUtility();
        groupeXmlUtility = new GroupeXmlUtility(Groupe.class);
    }
    /**
     * gets a group using Rest service 
     * @param url Rest service URL
     * @return a user. Rest returns a 204 status code if no user was found
     */
    @Override
    public Groupe get(String url) {
        Groupe groupe =null;
        if(url == null)
            return null;
        try {
             groupe = groupeJsonUtility.fromJsonString(url);
        } catch (IOException ex) {
            Logger.getLogger(GroupeRestClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        return groupe;
    }
/**
 * gets all groups using Rest service
 * @param url Rest service URL
 * @return a list of groups. Rest returns a 204 status code i case no group was found
 * @throws Exception 
 */
    @Override
    public ArrayList<Groupe> getAll(String url) throws Exception {
         try {
            HttpURLConnection connection =(HttpURLConnection) HttpResources.GetInstance().getUrlConnection(url,"GET","application/json");
            
            if(connection.getResponseCode() ==HttpURLConnection.HTTP_OK){
                return groupeJsonUtility.fromJsonStringList(HttpOutPutStreamUtility.readContent(connection.getInputStream()));
            }

        } catch (Exception ex) {
            Logger.getLogger(SportRestClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
/**
 * add a user to database using Rest service
 * @param item user model
 * @param url Rest service URL
 * @return a boolean value indicating whether the new user was added or not
 * @throws Exception 
 */
    @Override
    public boolean add(Groupe item, String url) throws Exception {
        try {
            String jsonEntity = groupeXmlUtility.toXmlString(item);
            HttpURLConnection connection =(HttpURLConnection) HttpResources.GetInstance().getUrlConnection(url,"POST",MediaType.APPLICATION_XML);
            
            OutputStreamWriter out = new   OutputStreamWriter(connection.getOutputStream());
            out.write(jsonEntity);
            out.flush();
            out.close();
            
            if(connection.getResponseCode() == HttpURLConnection.HTTP_OK){
                return true;
            }

        } catch (Exception ex) {
            Logger.getLogger(SportRestClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
/**
 * deletes a user from the database using Rest service
 * @param item user model object
 * @param url rest URL
 * @return a boolean value indicating whether a user was deleted or not
 */
    @Override
    public boolean delete(Groupe item, String url) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
