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
import models.Utilisateur;
import service.urls.RestUrls;
import tilities.http.HttpOutPutStreamUtility;
import utilities.password.PasswordHasher;
import utlities.json.UtilisateurJsonUtility;
import utlities.xml.UtilisateurXmlUtility;

/**
 *
 * @author jean-gustave
 */
public class UtlilisateurRestClient implements RestInterface<Utilisateur> {
    private UtilisateurJsonUtility utilisateurJsonUtility;
    private UtilisateurXmlUtility utilisateurXmlUtility;

    public UtlilisateurRestClient() {
        this.utilisateurJsonUtility = new UtilisateurJsonUtility();
        this.utilisateurXmlUtility = new UtilisateurXmlUtility(Utilisateur.class);
    }
    
    /**
     * gets a user using Rest service
     * @param url Rest URL
     * @return user. The Rest service returns a 204 status code in case no user was found
     */
    @Override
    public Utilisateur get(String url) {
        Utilisateur utilisateur = null;
       if(url != null)
        {
            try {
                utilisateur =  utilisateurJsonUtility.fromJsonString(url);
            } catch (IOException ex) {
                Logger.getLogger(SportRestClient.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
       return utilisateur;
    }
/**
 * gets all user from Rest service
 * @param url Rest URL to be called
 * @return a list of user. The Rest service returns a 204 status code i case no user was found
 * @throws Exception 
 */
    @Override
    public ArrayList<Utilisateur> getAll(String url) throws Exception {
        try {
            HttpURLConnection connection =(HttpURLConnection) HttpResources.GetInstance().getUrlConnection(url,"GET","application/json");
            
            if(connection.getResponseCode() ==HttpURLConnection.HTTP_OK){  
                return utilisateurJsonUtility.fromJsonStringList(HttpOutPutStreamUtility.readContent(connection.getInputStream()));
            }

        } catch (Exception ex) {
            Logger.getLogger(SportRestClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
/**
 * Adds a user to the database using Rest service
 * @param item user model
 * @param url Rest URL to call the Rest service
 * @return boolean
 * @throws Exception 
 */
    @Override
    public boolean add(Utilisateur item, String url) throws Exception {
         try {
            String jsonEntity = utilisateurXmlUtility.toXmlString(item);
            HttpURLConnection connection =(HttpURLConnection) HttpResources.GetInstance().getUrlConnection(url,"POST",MediaType.APPLICATION_XML);
            
            OutputStreamWriter out = new   OutputStreamWriter(connection.getOutputStream());
            out.write(jsonEntity);
            out.flush();
            out.close();
            int responseCode = connection.getResponseCode();
            if(responseCode == HttpURLConnection.HTTP_OK){
                return true;
            }

        } catch (Exception ex) {
            Logger.getLogger(SportRestClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }

    @Override
    public boolean delete(Utilisateur item, String url) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public Utilisateur authenticate(String username, String password) {
        try{
            String url = RestUrls.UTILISATEUR_URL+"/"+username+"/"+ PasswordHasher.encryptPassword(password);
            HttpURLConnection connection =(HttpURLConnection) HttpResources.GetInstance().getUrlConnection(url,"GET", MediaType.APPLICATION_JSON);
            int statusCode = connection.getResponseCode();
            if( statusCode == HttpURLConnection.HTTP_ACCEPTED)
            {
                return utilisateurJsonUtility.fromJsonString(HttpOutPutStreamUtility.readContent(connection.getInputStream()));
            }

        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
    
}
