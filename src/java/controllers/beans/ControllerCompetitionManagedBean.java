/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.beans;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import models.Competition;
import models.Disponibilite;
import models.Saison;
import models.Sport;
import service.CompetitionRestClient;
import service.DisponibiliteRestClient;
import service.EntrainementRestClient;
import service.SportRestClient;
import service.urls.RestUrls;

/**
 *
 * @author jean-gustave
 */

@ManagedBean
@SessionScoped
public class ControllerCompetitionManagedBean extends ControllerManagedBean<Competition> {
    @ManagedProperty(value="#{competition}")
    private Competition competitionBean;
    private ArrayList<Competition> filtres;
    private Competition selectedCompetition;
    private ArrayList<Sport> sportList;
    private ArrayList<Saison> saisonList;
    
    private CompetitionRestClient competitionRestClient;

    public ControllerCompetitionManagedBean() {
        competitionRestClient = new CompetitionRestClient();
    }

    public Competition getCompetitionBean() {
        return competitionBean;
    }

    public void setCompetitionBean(Competition competitionBean) {
        this.competitionBean = competitionBean;
    }
 
    
    
    @Override
    public ArrayList<Competition> getAll() throws Exception {
        return competitionRestClient.getAll(RestUrls.COMPETITION_URL);
    }

    @Override
    public boolean add(Competition item) throws Exception {
       if(item == null)
           throw new Exception("competition must be set");
       return competitionRestClient.add(item, RestUrls.COMPETITION_URL);
       
           
    }

    @Override
    public boolean delete(Competition item) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Competition get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String redirectToCreatePage() {
        return "/views/competition/create.xhtml?faces-redirect=true";
        
    }

    @Override
    public String redirectToListPage() {
        return "/views/competition/competition_list.xhtml?faces-redirect=true";
    }

    @Override
    public void edit(Competition item) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void addCompetition(Competition competition) throws Exception
    {
        if(add(competition))
        {
            competitionBean = new Competition();
           FacesContext context = FacesContext.getCurrentInstance();
          context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, 
                  "Info ","La compétition a été créé avec succès") );
        }else{
            FacesContext context = FacesContext.getCurrentInstance();
           context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                   "Erreur ","La compétition n'a pas pu être créée") );
        }
    }

    public ArrayList<Competition> getFiltres() {
        return filtres;
    }

    public void setFiltres(ArrayList<Competition> filtres) {
        this.filtres = filtres;
    }
    
    
    
    public ArrayList<Saison> getSaisonList() throws Exception
    {
        if(saisonList != null)
            return saisonList;
        else
        {
            saisonList = new EntrainementRestClient().getSaisons();
             return saisonList;
        }
       
    }

    
    public ArrayList<Sport>  getSports() throws Exception {
        if(sportList != null)
            return sportList;
        else
        {
            sportList = new SportRestClient().getAll(RestUrls.SPORT_URL);
            return sportList;
        }
    }

    public Competition getSelectedCompetition() {
        return selectedCompetition;
    }

    public void setSelectedCompetition(Competition selectedCompetition) {
        this.selectedCompetition = selectedCompetition;
    }
    
    public String redirectToCandidatures()
    {
         return "/views/competition/candidatures.xhtml?faces-redirect=true";
    }
    
    public ArrayList<Disponibilite> getDisponibilites()
    {
        try {
            ArrayList<Disponibilite> disponibinibilites = new DisponibiliteRestClient().getAll(RestUrls.DISPONIBILITE_URL);
            return disponibinibilites;
        } catch (Exception ex) {
            Logger.getLogger(ControllerCompetitionManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public void valider(Disponibilite disponibilite, Competition competition)
    {
        if(competition != null && competition.getId() != 0)
        {
            competition.getUtilisateurCollection().add(disponibilite.getMoniteur());
            boolean result = competitionRestClient.update(competition, RestUrls.COMPETITION_URL);
            
        }else
        {
             FacesContext context = FacesContext.getCurrentInstance();
          context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, 
                  "Info ","Séléctionner une compétition") );
        }
        
        
    }
    
}
