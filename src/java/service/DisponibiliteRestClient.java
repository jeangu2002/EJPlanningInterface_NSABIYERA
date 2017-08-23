/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Disponibilite;
import tilities.http.HttpOutPutStreamUtility;
import utlities.json.DisponibiliteJsonUtility;

/**
 *
 * @author jean-gustave
 */
public class DisponibiliteRestClient implements RestInterface<Disponibilite>{
    private DisponibiliteJsonUtility disponibiliteJsonUtility;

    public DisponibiliteRestClient() {
        disponibiliteJsonUtility = new DisponibiliteJsonUtility();
    }
    
        
    @Override
    public Disponibilite get(String url) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Disponibilite> getAll(String url) throws Exception {
        try {
            HttpURLConnection connection =(HttpURLConnection) HttpResources.GetInstance().getUrlConnection(url,"GET","application/json");
            
            if(connection.getResponseCode() ==HttpURLConnection.HTTP_OK){
                return disponibiliteJsonUtility.fromJsonStringList(HttpOutPutStreamUtility.readContent(connection.getInputStream()));
            }

        } catch (Exception ex) {
            Logger.getLogger(SportRestClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public boolean add(Disponibilite item, String url) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(Disponibilite item, String url) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
