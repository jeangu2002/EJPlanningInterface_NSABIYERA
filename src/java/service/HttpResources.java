/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 *
 * @author jean-gustave
 */
public class HttpResources {
    
    private HttpResources(){  
    }
    
    private static HttpResources INSTANCE = new HttpResources();
    public static HttpResources GetInstance()
    {
        return INSTANCE;
    }
    
    /**
     *
     * @param uri
     * @param method
     * @return
     */
    public HttpURLConnection getUrlConnection(String uri, String method, String accept) throws MalformedURLException, IOException
    {
       HttpURLConnection connection = (HttpURLConnection) new URL(uri).openConnection();
        connection.setRequestMethod(method);
        connection.setRequestProperty("Content-Type",accept);
        connection.setRequestProperty("Accept", accept);
        if(method.equals("POST") || method.equals("PUT"))
            connection.setDoOutput(true);
       return connection;
    }
    
    
    
    
}
