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
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import models.Utilisateur;
import service.UtlilisateurRestClient;
import service.urls.RestUrls;
import utilities.password.PasswordHasher;

/**
 *
 * @author jean-gustave
 */
@ManagedBean
@SessionScoped
public class ControllerUtilisateurManagedBean extends ControllerManagedBean<Utilisateur> {

    @ManagedProperty(value = "#{utilisateur}")
    private Utilisateur utilisateurBean;
    private UtlilisateurRestClient utilisateurRestClient;
    public boolean isLoggedIn;

    public ControllerUtilisateurManagedBean() {
        utilisateurRestClient = new UtlilisateurRestClient();
        isLoggedIn = false;
    }

    @Override
    public ArrayList<Utilisateur> getAll() throws Exception {
        return utilisateurRestClient.getAll(RestUrls.UTILISATEUR_URL);
    }

    @Override
    public boolean add(Utilisateur item) throws Exception {
        String hashedPassword = PasswordHasher.encryptPassword(item.getMotDePasse());
        item.setMotDePasse(hashedPassword);
        return utilisateurRestClient.add(item, RestUrls.UTILISATEUR_URL);

    }

    @Override
    public boolean delete(Utilisateur item) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Utilisateur get(int id) {
        String url = RestUrls.UTILISATEUR_URL + "/" + id;
        return utilisateurRestClient.get(url);
    }

    @Override
    public String redirectToCreatePage() {
        return "/views/utilisateur/create.xhtml?faces-redirect=true";
    }

    @Override
    public String redirectToListPage() {
        return "/views/utilisateur/utilisateur_list.xhtml?faces-redirect=true";
    }

    public String login(String pseudo, String motDePasse) {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();

        try {
            Utilisateur utilisateur = utilisateurRestClient.authenticate(this.utilisateurBean.getPseudo(), this.utilisateurBean.getMotDePasse());
            if (utilisateur == null) {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Erreur: ", "Login ou mot de passe incorrect"));
            } else if (utilisateur.getType().equals("moniteur")) {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Erreur: ", "Vous n'avez pas les permissions nécessaires pour accèder à cette page"));
            } else {
                context.getExternalContext().getSessionMap().put("pseudo", utilisateur.getPseudo());
                isLoggedIn = true;
                return "/views/calendrier/calendrier.xhtml?faces-redirect=true";
            }
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage("Login failed."));
            return "error";
        }
        return "/views/utilisateur/login.xhtml?faces-redirect=true";
    }

    public void logout() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        try {
            request.logout();
        } catch (ServletException e) {
            context.addMessage(null, new FacesMessage("Logout failed."));
        }
    }

    public Utilisateur getUtilisateurBean() {
        return utilisateurBean;
    }

    public void setUtilisateurBean(Utilisateur utilisateurBean) {
        this.utilisateurBean = utilisateurBean;
    }

    @Override
    public void edit(Utilisateur item) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void addUtilisateur(Utilisateur item) throws Exception {
        String password = item.getMotDePasse();
        if (this.add(item)) {
            utilisateurBean = new Utilisateur();
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Info ", "L'utilisateur a été ajouté avec succès"));
        } else {
            item.setMotDePasse(password);
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Erreur ", "L'utilisateur n'a pas été ajouté"));
        }
    }

    public String redirectToCandidaturesPage() {
        return "/views/entrainement/candidatures.xhtml";
    }

}
