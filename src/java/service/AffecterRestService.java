/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.MediaType;
import models.Affecter;
import tilities.http.HttpOutPutStreamUtility;
import utlities.json.AffecterJsonUtility;
import utlities.xml.AffecterXmlUtility;

/**
 *
 * @author jean-gustave
 */
public class AffecterRestService implements RestInterface<Affecter> {

    private AffecterJsonUtility affecterJsonUtility;
    private AffecterXmlUtility affecterXmlUtility;

    public AffecterRestService() {
        affecterJsonUtility = new AffecterJsonUtility();
        affecterXmlUtility = new AffecterXmlUtility(Affecter.class);
    }

    @Override
    public Affecter get(String url) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Affecter> getAll(String url) throws Exception {
        try {
            HttpURLConnection connection = (HttpURLConnection) HttpResources.GetInstance().getUrlConnection(url, "GET", "application/json");

            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String responseLine;
                StringBuffer response = new StringBuffer();
                while ((responseLine = in.readLine()) != null) {
                    response.append(responseLine);
                }
                in.close();
                return affecterJsonUtility.fromJsonStringList(response.toString());
            }

        } catch (Exception ex) {
            Logger.getLogger(SportRestClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public boolean add(Affecter item, String url) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(Affecter item, String url) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean validerAffectation(String url, Affecter affectation) {
        if (affectation == null || affectation.getAffecterPK() == null || url == null) {
            return false;
        }
        try {
            String jsonEntity = affecterJsonUtility.toJsonString(affectation);
            HttpURLConnection connection = (HttpURLConnection) HttpResources.GetInstance().getUrlConnection(url, "PUT", MediaType.APPLICATION_JSON);
            HttpOutPutStreamUtility.writeContent(jsonEntity, connection.getOutputStream());
            int codeStatus = connection.getResponseCode();
            if (codeStatus == HttpURLConnection.HTTP_OK) {
                return true;
            }
        } catch (Exception e) {
        }
        return false;
    }

}
