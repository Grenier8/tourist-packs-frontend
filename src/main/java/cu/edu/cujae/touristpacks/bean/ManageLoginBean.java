package cu.edu.cujae.touristpacks.bean;

import java.io.Console;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.DatatypeConverter;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cu.edu.cujae.touristpacks.dto.UserDto;
import cu.edu.cujae.touristpacks.service.security.IUserService;

@Component
@ManagedBean
@ViewScoped
public class ManageLoginBean {

    @Autowired
    private IUserService userService;

    private String username;
    private String password;

    public ManageLoginBean() {

    }

    public void authenticate() {
        List<UserDto> list = userService.getUsers();

        for (UserDto user : list) {
            System.out.println("user " + user.getPassword());
            System.out.println("inserted " + getMd5Hash(password));
            if (username.equalsIgnoreCase(user.getUsername()) && getMd5Hash(password).equals(user.getPassword())) {
                try {
                    getFacesContext().getExternalContext().redirect(getRequest().getContextPath() +
                            "/pages/welcome/welcome.jsf");
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                break;
            }
        }

    }

    private String getMd5Hash(String password) {
        MessageDigest md;
        String md5Hash = "";
        try {
            md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            byte[] digest = md.digest();
            md5Hash = DatatypeConverter
                    .printHexBinary(digest).toUpperCase();
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return md5Hash;
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