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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.MediaType;
import models.Saison;
import utlities.json.SaisonJsonUtility;
import utlities.xml.SaisonXmlUtility;

/**
 *
 * @author jean-gustave
 */
public class SaisonRestClient implements RestInterface<Saison> {
    private SaisonJsonUtility saisonJsonUtility;
    private SaisonXmlUtility saisonXmlUtility;
    public SaisonRestClient() {
        saisonJsonUtility = new SaisonJsonUtility();
        saisonXmlUtility = new SaisonXmlUtility(Saison.class);
    }
    
    

    @Override
    public Saison get(String url) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Saison> getAll(String url) throws Exception {
         try {
            HttpURLConnection connection =(HttpURLConnection) HttpResources.GetInstance().getUrlConnection(url,"GET",MediaType.APPLICATION_JSON);
            
            if(connection.getResponseCode() ==HttpURLConnection.HTTP_OK){
                BufferedReader  in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String  responseLine;
                StringBuffer response = new StringBuffer();
                while((responseLine = in.readLine()) != null)
                {
                    response.append(responseLine);
                }
                in.close();
                return saisonJsonUtility.fromJsonStringList(response.toString());
            }

        } catch (Exception ex) {
            Logger.getLogger(SportRestClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public boolean add(Saison item, String url) throws Exception {
        try {
            String jsonEntity = saisonXmlUtility.toXmlString(item);
            HttpURLConnection connection =(HttpURLConnection) HttpResources.GetInstance().getUrlConnection(url,"POST",MediaType.APPLICATION_XML);
            
            OutputStreamWriter out = new   OutputStreamWriter(connection.getOutputStream());
            out.write(jsonEntity);
            out.flush();
            out.close();
            int statusCode = connection.getResponseCode() ;
            if(statusCode == HttpURLConnection.HTTP_ACCEPTED){
                return true;
            }

        } catch (Exception ex) {
            Logger.getLogger(SportRestClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }

    @Override
    public boolean delete(Saison item, String url) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
