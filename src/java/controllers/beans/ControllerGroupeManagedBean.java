/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import models.Groupe;
import org.primefaces.event.RowEditEvent;
import service.GroupeRestClient;
import service.urls.RestUrls;

/**
 *
 * @author jean-gustave
 */
@ManagedBean
@SessionScoped
public class ControllerGroupeManagedBean extends ControllerManagedBean<Groupe> implements Serializable{
    @ManagedProperty(value="#{groupe}")
    private Groupe groupeBean;
    private GroupeRestClient groupeRestClient;

    public ControllerGroupeManagedBean() {
        groupeRestClient = new GroupeRestClient();
    }
    
    

    public GroupeRestClient getGroupeRestClient() {
        return groupeRestClient;
    }

    public void setGroupeRestClient() {
        this.groupeRestClient = new GroupeRestClient();
    }
    
    

    public Groupe getGroupeBean() {
        return groupeBean;
    }

    public void setGroupeBean(Groupe groupeBean) {
        this.groupeBean = groupeBean;
    }
    
    @Override
    public ArrayList getAll() throws Exception {
        return groupeRestClient.getAll(RestUrls.GROUPE_URL);
    }

    @Override
    public boolean add(Groupe item) throws Exception {
        boolean result = groupeRestClient.add(item, RestUrls.GROUPE_URL);
        if( result)
        {
            groupeBean.setNom(null);
            FacesContext context = FacesContext.getCurrentInstance();
          context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, 
                  "Info ","Le groupe a été créé avec succès") );
        }else
        {
            FacesContext context = FacesContext.getCurrentInstance();
           context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                   "Erreur ","Le groupe n'a pas été créé") );
        }
        return false;
    }

    @Override
    public boolean delete(Groupe item) {
       return false;
    }

    @Override
    public Groupe get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String redirectToCreatePage() {
        return "/views/groupe/create.xhtml?faces-redirect=true";
    }

    @Override
    public String redirectToListPage() {
        return "/views/groupe/groupe_list.xhtml?faces-redirect=true";
    }

    @Override
    public void edit(Groupe item) {
        
    }
    
    public void onRowEdit(RowEditEvent event)
    {
        FacesMessage msg = new FacesMessage("Group Edited", ((Groupe) event.getObject()).getNom());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    public void onRowCancel(RowEditEvent event)
    {
        FacesMessage msg = new FacesMessage("Group Edited", ((Groupe) event.getObject()).getNom());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
}
