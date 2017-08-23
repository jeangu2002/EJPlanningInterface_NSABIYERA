/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.beans;

import java.util.ArrayList;
import java.util.Locale;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import models.Affecter;
import models.AffecterPK;
import models.Entrainement;
import models.Groupe;
import models.Saison;
import models.Sport;
import models.Utilisateur;
import service.AffecterRestService;
import service.EntrainementRestClient;
import service.urls.RestUrls;

/**
 *
 * @author jean-gustave
 */
@ManagedBean
@SessionScoped
public class ControllerEntrainementManagedBean extends ControllerManagedBean<Entrainement> {

    private EntrainementRestClient entrainementRestClient;
    @ManagedProperty(value = "#{entrainement}")
    private Entrainement entrainementBean;
    private Entrainement selectedEntrainement;
    private ArrayList<Entrainement> filtres;

    public ControllerEntrainementManagedBean() {
        entrainementRestClient = new EntrainementRestClient();

    }

    @Override
    public ArrayList<Entrainement> getAll() throws Exception {
        return entrainementRestClient.getAll(RestUrls.ENTRAINEMENT_URL);
    }

    @Override
    public boolean add(Entrainement item) throws Exception {
        return entrainementRestClient.add(item, RestUrls.ENTRAINEMENT_URL);
    }

    @Override
    public boolean delete(Entrainement item) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Entrainement get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void edit(Entrainement item) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Entrainement getEntrainementBean() {
        return entrainementBean;
    }

    public void setEntrainementBean(Entrainement entrainementBean) {
        this.entrainementBean = entrainementBean;
    }

    public ArrayList<Sport> getSportList() throws Exception {
        return entrainementRestClient.getSportList();
    }

    public ArrayList<Groupe> getGroupList() throws Exception {
        return entrainementRestClient.getGroupList();
    }

    public ArrayList<Entrainement> getAffectationsNonConfirmees() throws Exception {
        return entrainementRestClient.getAll(RestUrls.ENTRAINEMENT_CANDIDATURES_URL);
    }

    /**
     *
     * @throws Exception
     */
    public ArrayList<Saison> getSaisonList() throws Exception {
        return entrainementRestClient.getSaisons();
    }

    public ArrayList<Utilisateur> getAvailableMoniteurs() {
        return entrainementRestClient.getAvailableMoniteurs();
    }

    @Override
    public String redirectToCreatePage() {
        return "/views/entrainement/create.xhtml?faces-redirect=true";
    }

    @Override
    public String redirectToListPage() {
        return "/views/entrainement/entrainement_list.xhtml?faces-redirect=true";
    }

    public void addEntrainement(Entrainement entrainement) throws Exception {
        if (entrainementRestClient.add(entrainement, RestUrls.ENTRAINEMENT_URL)) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Info ", "L'entainement a été sauvegardé avec succès"));
        } else {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Info ", "L'entrainement n'a pas été sauvegardé"));
        }
    }

    public void valider(Entrainement entrainement, Utilisateur moniteur) {
            Affecter affectation = new Affecter();
            affectation.setEntrainement(entrainement);
            affectation.setUtilisateur(moniteur);
            affectation.setStatut(1);
            AffecterPK pk = new AffecterPK(entrainement.getId(), moniteur.getId());
            affectation.setAffecterPK(pk);
            boolean result = new AffecterRestService().validerAffectation(RestUrls.AFFECTER_URL+"/"+affectation.getAffecterPK().getIdMoniteur(),affectation);
            if(result)
            {
                FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Info ", "La candidature a été validée avec succès"));
            }else
            {
                FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Info ", "La candidature n'a pas été validée"));
            }
    }

    public EntrainementRestClient getEntrainementRestClient() {
        return entrainementRestClient;
    }

    public void setEntrainementRestClient(EntrainementRestClient entrainementRestClient) {
        this.entrainementRestClient = entrainementRestClient;
    }

    public ArrayList<Entrainement> getFiltres() {
        return filtres;
    }

    public void setFiltres(ArrayList<Entrainement> filtres) {
        this.filtres = filtres;
    }
    

    public boolean filterByDate(Object value, Object filter, Locale locale) {
        String filterText = (filter == null) ? null : filter.toString().trim();
        if (filterText == null || filterText.equals("")) {
            return true;
        }

        if (value == null) {
            return false;
        }

        return ((Comparable) value).compareTo(Integer.valueOf(filterText)) > 0;
    }
    
    public boolean filterByGroupe(Object value, Object filter, Locale locale)
    {
        String filterText = (filter == null) ? null : filter.toString();
        if (filterText == null || filterText.equals("")) {
            return true;
        }

        if (value == null) {
            return false;
        }

        return ((String) value).contains(filterText);
    }

    public Entrainement getSelectedEntrainement() {
        return selectedEntrainement;
    }

    public void setSelectedEntrainement(Entrainement selectedEntrainement) {
        this.selectedEntrainement = selectedEntrainement;
    }

}
