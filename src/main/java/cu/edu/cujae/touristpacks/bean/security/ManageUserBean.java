package cu.edu.cujae.touristpacks.bean.security;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cu.edu.cujae.touristpacks.dto.UserDto;
import cu.edu.cujae.touristpacks.service.security.IRoleService;
import cu.edu.cujae.touristpacks.service.security.IUserService;
import cu.edu.cujae.touristpacks.utils.JsfUtils;

@Component
@ManagedBean
@ViewScoped
public class ManageUserBean {

    private List<UserDto> users;
    private UserDto selectedUser;

    private String selectedRoleName;

    @Autowired
    private IUserService service;

    @Autowired
    private IRoleService roleService;

    public ManageUserBean() {

    }

    @PostConstruct
    public void init() {
        users = service.getUsers();
    }

    public void openNew() {
        this.selectedUser = new UserDto();
    }

    public void openForEdit() {

    }

    public void saveUser() {
        selectedUser.setRole(roleService.getRoleByName(selectedRoleName));

        if (this.selectedUser.getIdUser() == 0) {
            service.createUser(selectedUser);

            JsfUtils.addInfoMessageFromBundle("message_inserted_user");
        } else {
            service.updateUser(selectedUser);

            JsfUtils.addInfoMessageFromBundle("message_updated_user");
        }

        users = service.getUsers();

        PrimeFaces.current().executeScript("PF('manageUserDialog').hide()");
        PrimeFaces.current().ajax().update("form:dt-users");

    }

    public void deleteUser() {

        service.deleteUser(selectedUser.getIdUser());
        this.selectedUser = null;

        users = service.getUsers();

        JsfUtils.addInfoMessageFromBundle("message_deleted_user");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-users");

    }

    public List<UserDto> getUsers() {
        return this.users;
    }

    public void setUsers(List<UserDto> users) {
        this.users = users;
    }

    public UserDto getSelectedUser() {
        return this.selectedUser;
    }

    public void setSelectedUser(UserDto selectedUser) {
        this.selectedUser = selectedUser;
    }

    public IUserService getService() {
        return this.service;
    }

    public void setService(IUserService service) {
        this.service = service;
    }

    public String getSelectedRoleName() {
        return this.selectedRoleName;
    }

    public void setSelectedRoleName(String selectedRoleName) {
        this.selectedRoleName = selectedRoleName;
    }

    public IRoleService getRoleService() {
        return this.roleService;
    }

    public void setRoleService(IRoleService roleService) {
        this.roleService = roleService;
    }

}