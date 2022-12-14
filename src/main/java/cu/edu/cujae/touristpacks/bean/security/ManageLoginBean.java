package cu.edu.cujae.touristpacks.bean.security;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import cu.edu.cujae.touristpacks.dto.security.UserAuthenticatedDto;
import cu.edu.cujae.touristpacks.security.CurrentUserUtils;
import cu.edu.cujae.touristpacks.security.UserPrincipal;
import cu.edu.cujae.touristpacks.service.security.IAuthService;
import cu.edu.cujae.touristpacks.service.security.IUserService;
import cu.edu.cujae.touristpacks.utils.JsfUtils;

@Component
@ManagedBean
@ViewScoped
public class ManageLoginBean {

    @Autowired
    private IUserService userService;

    private String username;
    private String password;

    @Autowired
    private IAuthService authService;

    public ManageLoginBean() {

    }

    public String login() {
        try {
            UserAuthenticatedDto userAuthenticated = authService.login(username, password);
            UserDetails userDetails = UserPrincipal.create(userAuthenticated);
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails,
                    null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);

        } catch (Exception e) {
            JsfUtils.addMessageFromBundle("securityMessages", FacesMessage.SEVERITY_INFO,
                    "message_invalid_credentials");
            return null;
        }
        return "login";

    }

    public String logout() {
        return dispatchToUrl("/logout");
    }

    public String getUserLogued() {
        return CurrentUserUtils.getFullName();
    }

    private String dispatchToUrl(String url) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();
        HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        try {
            dispatcher.forward(request, response);
            facesContext.responseComplete();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    protected HttpServletRequest getRequest() {
        return (HttpServletRequest) getFacesContext().getExternalContext().getRequest();
    }

    protected FacesContext getFacesContext() {
        return FacesContext.getCurrentInstance();
    }

    public IUserService getUserService() {
        return this.userService;
    }

    public void setUserService(IUserService userService) {
        this.userService = userService;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}