/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.MediaType;
import models.Competition;
import tilities.http.HttpOutPutStreamUtility;
import utlities.json.CompetitionJsonUtility;
import utlities.xml.CompetitionXmlUtility;

/**
 *
 * @author jean-gustave
 */
public class CompetitionRestClient implements RestInterface<Competition> {

    private CompetitionJsonUtility competitionJsonUtiliy;
    private CompetitionXmlUtility competitionXmlUtility;
    
    public CompetitionRestClient()
    {
        competitionJsonUtiliy = new CompetitionJsonUtility();
        competitionXmlUtility = new CompetitionXmlUtility(Competition.class);
    }

    @Override
    public Competition get(String url) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Competition> getAll(String url) throws Exception {
         try {
            HttpURLConnection connection =(HttpURLConnection) HttpResources.GetInstance().getUrlConnection(url,"GET","application/json");
            
            if(connection.getResponseCode() ==HttpURLConnection.HTTP_OK){
                return competitionJsonUtiliy.fromJsonStringList(HttpOutPutStreamUtility.readContent(connection.getInputStream()));
            }

        } catch (Exception ex) {
            Logger.getLogger(SportRestClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public boolean add(Competition item, String url) throws Exception {
        try {
            String jsonEntity = competitionJsonUtiliy.toJsonString(item);
            HttpURLConnection connection =(HttpURLConnection) HttpResources.GetInstance().getUrlConnection(url,"POST",MediaType.APPLICATION_JSON);
            HttpOutPutStreamUtility.writeContent(jsonEntity, connection.getOutputStream());
            int codeStatus = connection.getResponseCode();
            if(codeStatus ==HttpURLConnection.HTTP_OK){
                return true;
            }

        } catch (Exception ex) {
            Logger.getLogger(SportRestClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }

    @Override
    public boolean delete(Competition item, String url) {
        try {
            String jsonEntity = competitionJsonUtiliy.toJsonString(item);
            HttpURLConnection connection =(HttpURLConnection) HttpResources.GetInstance().getUrlConnection(url,"DELETE",MediaType.APPLICATION_JSON);
            HttpOutPutStreamUtility.writeContent(jsonEntity, connection.getOutputStream());
            int codeStatus = connection.getResponseCode();
            if(codeStatus ==HttpURLConnection.HTTP_OK){
                return true;
            }

        } catch (Exception ex) {
            Logger.getLogger(SportRestClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
    
    public boolean update(Competition item, String url)
    {
         try {
            String jsonEntity = competitionJsonUtiliy.toJsonString(item);
            HttpURLConnection connection =(HttpURLConnection) HttpResources.GetInstance().getUrlConnection(url,"PUT",MediaType.APPLICATION_JSON);
            HttpOutPutStreamUtility.writeContent(jsonEntity, connection.getOutputStream());
            int codeStatus = connection.getResponseCode();
            if(codeStatus ==HttpURLConnection.HTTP_OK){
                return true;
            }

        } catch (Exception ex) {
            Logger.getLogger(SportRestClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
}
