/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tilities.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

/**
 *
 * @author jean-gustave
 */
public class HttpOutPutStreamUtility {
    /**
     * Read data content from Http Get requests
     * @param input connection input stream
     * @return String
     * @throws IOException 
     */
    public static String readContent(InputStream  input) throws IOException
    {
        if(input == null)
            return null;
        BufferedReader  in = new BufferedReader(new InputStreamReader(input));
                String  responseLine;
                StringBuffer response = new StringBuffer();
                while((responseLine = in.readLine()) != null)
                {
                    response.append(responseLine);
                }
                in.close();
                return response.toString();
    }
    
    /**
     *writes HTTP post content
     * @param deserializedEntity
     * @param stream
     * @throws IOException
     */
    public static void writeContent(String deserializedEntity, OutputStream stream) throws IOException
    {
        OutputStreamWriter out = new   OutputStreamWriter(stream);
            out.write(deserializedEntity);
            out.flush();
            out.close();
    }
    
}
