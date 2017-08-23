/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.beans;

import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import models.Saison;
import service.SaisonRestClient;
import service.urls.RestUrls;

/**
 *
 * @author jean-gustave
 */
@ManagedBean
@SessionScoped
public class ControllerSaisonManagedBean extends ControllerManagedBean<Saison>{

    private SaisonRestClient saisonRestClient;
    @ManagedProperty(value="#{saison}")
    private Saison saisonBean;

    public ControllerSaisonManagedBean() {
        saisonRestClient = new SaisonRestClient();
    }

    public Saison getSaisonBean() {
        return saisonBean;
    }

    public void setSaisonBean(Saison saisonBean) {
        this.saisonBean = saisonBean;
    }
    
    
    
    @Override
    public ArrayList<Saison> getAll() throws Exception {
        return saisonRestClient.getAll(RestUrls.SAISON_URL);
    }

    @Override
    public boolean add(Saison item) throws Exception {
        return saisonRestClient.add(item, RestUrls.SAISON_URL);
    }

    @Override
    public boolean delete(Saison item) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Saison get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void edit(Saison item) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String redirectToCreatePage() {
        return "/views/saison/create.xhtml?faces-redirect=true";
    }

    @Override
    public String redirectToListPage() {
        return "/views/saison/saison_list.xhtml?faces-redirect=true";
    }
    
    public void addSaison(Saison nouvelleSaison) throws Exception
    {
        if(add(nouvelleSaison))
        {
            
            FacesContext context = FacesContext.getCurrentInstance();
          context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, 
                  "Info ","La saison a été créée avec succès") );
        }else
        {
            FacesContext context = FacesContext.getCurrentInstance();
           context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                   "Erreur ","La saison n'a pas été créée") );
        }
    }
    
}
