/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utlities.xml;

import static com.sun.org.apache.xerces.internal.util.FeatureState.is;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;


/**
 *
 * @author jean-gustave
 */
public abstract class XmlUtility<T> {
    final Class<T> parameterClass;

    public XmlUtility(Class<T> parameterClass) {
        
        this.parameterClass = parameterClass;
    }
    
    
    public T fromXmlString(String xmlString) throws Exception
    {
        if(xmlString == null)
            throw new Exception("xml cannot be null");
        
        JAXBContext jaxbContext = JAXBContext.newInstance(parameterClass);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        T result = (T) jaxbUnmarshaller.unmarshal(new StringReader(xmlString));
        return result;
    }
    
    public String toXmlString(T item) throws JAXBException
    {
        JAXBContext jaxbContext = JAXBContext.newInstance(parameterClass);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        StringWriter sw = new StringWriter();
        jaxbMarshaller.marshal(item,sw);
        return sw.toString();
    }
    
    public ArrayList<T> fromXmlStringList(String xmlStringList) throws JAXBException
    {
        JAXBContext jaxbContext = JAXBContext.newInstance(parameterClass);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        return new ArrayList<T>();
    }
}
