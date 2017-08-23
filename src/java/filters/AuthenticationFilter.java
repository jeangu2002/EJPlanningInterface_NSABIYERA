/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filters;

import controllers.beans.ControllerUtilisateurManagedBean;
import java.io.IOException;
import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author jean-gustave
 */
@WebFilter(filterName = "AuthenticationFilter", urlPatterns = "*.xhtml", dispatcherTypes = DispatcherType.REQUEST)
public class AuthenticationFilter implements Filter {

    public AuthenticationFilter() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {

            // check whether session variable is set
            HttpServletRequest req = (HttpServletRequest) request;
            HttpServletResponse res = (HttpServletResponse) response;
            req.setCharacterEncoding("UTF-8");
            res.setCharacterEncoding("UTF-8");
            HttpSession ses = req.getSession(false);
            ControllerUtilisateurManagedBean utilisateurManageBean = (ControllerUtilisateurManagedBean) ((HttpServletRequest)request).getSession().getAttribute("controllerUtilisateurManagedbean");
            String loginUrl = req.getContextPath() + "/faces/views/utilisateur/login.xhtml";
            //  allow user to proccede if url is login.xhtml or user logged in or user is accessing any page in //public folder
            String reqURI = req.getRequestURI();
            if ((utilisateurManageBean != null && utilisateurManageBean.isLoggedIn) 
                    ||reqURI.indexOf("/login.xhtml") >= 0 
                    || (ses != null && ses.getAttribute("pseudo") != null 
                    || reqURI.contains("javax.faces.resource"))) {
                chain.doFilter(req, res);
            } else // user didn't log in but asking for a page that is not allowed so take user to login page
            {
                res.sendRedirect(loginUrl);  // Anonymous user. Redirect to login page
            }
        } catch (Throwable t) {
            System.out.println(t.getMessage());
        }
    }

    @Override
    public void destroy() {

    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

}
