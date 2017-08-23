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
import models.Sport;
import tilities.http.HttpOutPutStreamUtility;
import utlities.json.SportJsonUtility;
import utlities.xml.SportXmlUtility;

/**
 *
 * @author jean-gustave
 */
public class SportRestClient implements RestInterface<Sport> {
    private SportJsonUtility sportJsonUtility;
    private SportXmlUtility sportXmlUtility;
    public SportRestClient() {
        sportJsonUtility = new SportJsonUtility();
        sportXmlUtility = new SportXmlUtility(Sport.class);
    }
    /**
     * gets a sport using Rest service
     * @param url Rest service URL
     * @return a sport. Rest service returns a 204 status code if no sport was found
     */
    @Override
    public Sport get(String url) {
        Sport sport = null;
        if(url != null)
        {
            try {
                sport =  sportXmlUtility.fromXmlString(url);
            } catch (Exception ex) {
                Logger.getLogger(SportRestClient.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return sport;
    }
/**
 * gets all sports from the database using Rest service 
 * @param url Rest URL
 * @return a list of available sports. Rest returns a 204 in case none was found
 * @throws Exception 
 */
    @Override
    public ArrayList<Sport> getAll(String url) throws Exception {
      try {
            HttpURLConnection connection =(HttpURLConnection) HttpResources.GetInstance().getUrlConnection(url,"GET",MediaType.APPLICATION_JSON);
            
            if(connection.getResponseCode() ==HttpURLConnection.HTTP_OK){
                return sportJsonUtility.fromJsonStringList(HttpOutPutStreamUtility.readContent(connection.getInputStream()));
            }

        } catch (Exception ex) {
            Logger.getLogger(SportRestClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
/**
 * adds a sport to the database using Rest service
 * @param item sport model object
 * @param url Rest URL
 * @return a boolean valueu indicating wether the sport was added or not
 * @throws Exception 
 */
    @Override
    public boolean add(Sport item, String url) throws Exception {
        try {
            String jsonEntity = sportXmlUtility.toXmlString(item);
            HttpURLConnection connection =(HttpURLConnection) HttpResources.GetInstance().getUrlConnection(url,"POST",MediaType.APPLICATION_XML);
            
            OutputStreamWriter out = new   OutputStreamWriter(connection.getOutputStream());
            out.write(jsonEntity);
            out.flush();
            out.close();
            
            if(connection.getResponseCode() == HttpURLConnection.HTTP_ACCEPTED){
                return true;
            }

        } catch (Exception ex) {
            Logger.getLogger(SportRestClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
/**
 * deletes a sport from the database
 * @param item sport model object
 * @param url REst URL
 * @return 
 */
    @Override
    public boolean delete(Sport item, String url) {
        try {
            String jsonEntity = sportXmlUtility.toXmlString(item);
            HttpURLConnection connection =(HttpURLConnection) HttpResources.GetInstance().getUrlConnection(url,"DELETE",MediaType.APPLICATION_XML);
            HttpOutPutStreamUtility.writeContent(jsonEntity, connection.getOutputStream());
            if(connection.getResponseCode() == HttpURLConnection.HTTP_OK){
                return true;
            }

        } catch (Exception ex) {
            Logger.getLogger(SportRestClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
    
}
