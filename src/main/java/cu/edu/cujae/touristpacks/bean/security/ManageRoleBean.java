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

import cu.edu.cujae.touristpacks.dto.security.RoleDto;
import cu.edu.cujae.touristpacks.service.security.IRoleService;
import cu.edu.cujae.touristpacks.utils.JsfUtils;

@Component
@ManagedBean
@ViewScoped
public class ManageRoleBean {

    private List<RoleDto> roles;
    private RoleDto selectedRole;

    @Autowired
    private IRoleService service;

    public ManageRoleBean() {

    }

    public void openNew() {
        this.selectedRole = new RoleDto();
    }

    public void openForEdit() {

    }

    public void saveRole() {
        if (this.selectedRole.getIdRole() == 0) {
            service.createRole(selectedRole);

            JsfUtils.addInfoMessageFromBundle("message_inserted_role");
        } else {
            service.updateRole(selectedRole);

            JsfUtils.addInfoMessageFromBundle("message_updated_role");
        }

        roles = service.getRoles();

        PrimeFaces.current().executeScript("PF('manageRoleDialog').hide()");
        PrimeFaces.current().ajax().update("form:dt-roles");

    }

    public void deleteRole() {

        service.deleteRole(selectedRole.getIdRole());
        this.selectedRole = null;

        roles = service.getRoles();

        JsfUtils.addInfoMessageFromBundle("message_deleted_role");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-roles");

    }

    public List<RoleDto> getRoles() {
        roles = service.getRoles();
        return this.roles;
    }

    public void setRoles(List<RoleDto> roles) {
        this.roles = roles;
    }

    public RoleDto getSelectedRole() {
        return this.selectedRole;
    }

    public void setSelectedRole(RoleDto selectedRole) {
        this.selectedRole = selectedRole;
    }

    public IRoleService getService() {
        return this.service;
    }

    public void setService(IRoleService service) {
        this.service = service;
    }

}