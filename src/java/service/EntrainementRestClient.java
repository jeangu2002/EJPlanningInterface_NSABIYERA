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
import javax.resource.NotSupportedException;
import javax.ws.rs.core.MediaType;
import models.Affecter;
import models.Entrainement;
import models.Groupe;
import models.Saison;
import models.Sport;
import models.Utilisateur;
import service.urls.RestUrls;
import tilities.http.HttpOutPutStreamUtility;
import utlities.json.EntrainementJsonUtility;
import utlities.xml.EntrainementXmlUtility;

/**
 *
 * @author jean-gustave
 */
public class EntrainementRestClient implements RestInterface<Entrainement> {
    private EntrainementJsonUtility entrainementJsonUtility;
    private EntrainementXmlUtility entrainementXmlUtility;

    public EntrainementRestClient() {
        entrainementJsonUtility = new EntrainementJsonUtility();
        entrainementXmlUtility = new EntrainementXmlUtility(Entrainement.class);
    }
    
    
/**
 * gets entrainement from the database using Rest service
 * @param url Rest URL
 * @return an instance of Entrainenemt. Rest service will send a 204 status code if none was found
 */
    @Override
    public Entrainement get(String url) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ArrayList<Entrainement> getAll(String url) throws Exception {
        try {
            HttpURLConnection connection =(HttpURLConnection) HttpResources.GetInstance().getUrlConnection(url,"GET","application/json");
            
            if(connection.getResponseCode() ==HttpURLConnection.HTTP_OK){
                return entrainementJsonUtility.fromJsonStringList(HttpOutPutStreamUtility.readContent(connection.getInputStream()));
            }

        } catch (Exception ex) {
            Logger.getLogger(SportRestClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public boolean add(Entrainement item, String url) throws Exception {
        try {
            String jsonEntity = entrainementXmlUtility.toXmlString(item);
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

    @Override
    public boolean delete(Entrainement item, String url) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public ArrayList<Sport> getSportList() throws Exception
    {
        return new SportRestClient().getAll(RestUrls.SPORT_URL);
    }

    public ArrayList<Groupe> getGroupList() throws Exception {
        return new GroupeRestClient().getAll(RestUrls.GROUPE_URL);
    }
    
    public ArrayList<Saison> getSaisons() throws Exception
    {
        return new SaisonRestClient().getAll(RestUrls.SAISON_URL);
    }

    public ArrayList<Utilisateur> getAvailableMoniteurs() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public ArrayList<Affecter> getAffectationNonConfirmees(String url) throws Exception
    {
        return new AffecterRestService().getAll(url);
    }
    
}
