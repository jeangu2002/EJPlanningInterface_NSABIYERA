/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.beans;

import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import models.Sport;
import service.SportRestClient;
import service.urls.RestUrls;

/**
 *
 * @author jean-gustave
 */
@ManagedBean
@SessionScoped
public class ControllerSportManagedBean extends ControllerManagedBean<Sport> implements Serializable{
    
    private SportRestClient sportRestClient;
    @ManagedProperty(value="#{sport}")
    private Sport sportBean;
 
    public ControllerSportManagedBean() {
        sportRestClient = new SportRestClient();
        sportBean = new Sport();
    }
    
    /**
     *
     * @return
     * @throws IOException
     */
    @Override
    public ArrayList<Sport> getAll() throws Exception {
        
            return sportRestClient.getAll(RestUrls.SPORT_URL);     
    }

    /**
     *
     * @param item
     * @return
     * @throws Exception
     */
    @Override
    public boolean add(Sport item) throws Exception {
            return sportRestClient.add(item, RestUrls.SPORT_URL);
    }

    @Override
    public boolean delete(Sport item) {
        return sportRestClient.delete(item, RestUrls.SPORT_URL+"/"+item.getId());
        
    }

    @Override
    public Sport get(int id) {
        String url = RestUrls.SPORT_URL+"/"+id;
        return sportRestClient.get(url);
    }

    public Sport getSportBean() {
        return sportBean;
    }

    public void setSportBean(Sport sportBean) {
        this.sportBean = sportBean;
    }
    
    public void addSport() throws Exception
    {
      sportBean.setId(0);
      boolean result = sportRestClient.add(sportBean,RestUrls.SPORT_URL);
      if(result)
      {
          sportBean.setNom(null);
          FacesContext context = FacesContext.getCurrentInstance();
          context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, 
                  "Info ","L'activité a été ajoutée avec succès") );
      }else
      {
           FacesContext context = FacesContext.getCurrentInstance();
           context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                   "Erreur ","L'activité n'a pas été ajoutée") );
      }
    }
    
    /**
     *
     * @return
     */
    @Override
    public String redirectToCreatePage()
    {
         return "/views/sport/create.xhtml?faces-redirect=true";
    }

    @Override
    public String redirectToListPage() {
        return "/views/sport/sport_list.xhtml?faces-redirect=true"; 
    }

    @Override
    public void edit(Sport item) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    public void deleteSport(Sport sport)
    {
        if(delete(sport))
        {
            FacesContext context = FacesContext.getCurrentInstance();
          context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, 
                  "Info ",sport.getNom()+" a été supprimé") );
        }else
        {
            FacesContext context = FacesContext.getCurrentInstance();
          context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                  "Info ","Le sport n'a pas pu être supprimé") );
        }
    }
    
    
    
    
}
