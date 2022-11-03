package cu.edu.cujae.touristpacks.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;

import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cu.edu.cujae.touristpacks.dto.RoleDto;
import cu.edu.cujae.touristpacks.dto.UserDto;
import cu.edu.cujae.touristpacks.service.security.RoleService;
import cu.edu.cujae.touristpacks.service.security.UserService;
import cu.edu.cujae.touristpacks.utils.JsfUtils;

@Component
@ManagedBean
@ViewScoped
public class ManageUserBean {

	private UserDto userDto;
	private UserDto selectedUser;
	private List<UserDto> users;
	private Long[] selectedRoles;

	private List<RoleDto> roles;

	@Autowired
	private UserService userService;

	@Autowired
	private RoleService roleService;

	public ManageUserBean() {

	}

	@PostConstruct
	public void init() {
		users = users == null ? userService.getUsers() : users;
		roles = roleService.getRoles();
	}

	public void openNew() {
		this.selectedUser = new UserDto();
		this.selectedRoles = null;
	}

	public void openForEdit() {
		List<RoleDto> roles = this.selectedUser.getRoles();
		this.selectedRoles = roles.stream().map(r -> r.getId()).toArray(Long[]::new);
	}

	public void saveUser() {
		if (this.selectedUser.getId() == null) {
			this.selectedUser.setId(UUID.randomUUID().toString().replaceAll("-", "").substring(0, 9));
			this.selectedUser.setNewRecord(true);
			List<RoleDto> rolesToAdd = new ArrayList<RoleDto>();
			for (int i = 0; i < this.selectedRoles.length; i++) {
				rolesToAdd.add(roleService.getRolesById(selectedRoles[i]));
			}

			this.users.add(this.selectedUser);
			JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_INFO, "message_user_added"); // Este code permite
																									// mostrar un
																									// mensaje exitoso
																									// (FacesMessage.SEVERITY_INFO)
																									// obteniendo el
																									// mensage desde el
																									// fichero de
																									// recursos, con la
																									// llave
																									// message_user_added
		} else {
			JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_INFO, "message_user_edited");
		}

		PrimeFaces.current().executeScript("PF('manageUserDialog').hide()");// Este code permite cerrar el dialog cuyo
																			// id es manageUserDialog. Este
																			// identificador es el widgetVar
		PrimeFaces.current().ajax().update("form:dt-users");// Este code es para refrescar el componente con id dt-users
															// que se encuentra dentro del formulario con id form
	}

	public void deleteUser() {
		try {
			this.users.remove(this.selectedUser);
			this.selectedUser = null;
			JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_INFO, "message_user_removed");
			PrimeFaces.current().ajax().update("form:dt-users");// Este code es para refrescar el componente con id
																// dt-users que se encuentra dentro del formulario con
																// id form
		} catch (Exception e) {
			JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_ERROR, "message_error");
		}

	}

	public UserDto getUserDto() {
		return userDto;
	}

	public void setUserDto(UserDto userDto) {
		this.userDto = userDto;
	}

	public UserDto getSelectedUser() {
		return selectedUser;
	}

	public void setSelectedUser(UserDto selectedUser) {
		this.selectedUser = selectedUser;
	}

	public List<UserDto> getUsers() {
		return users;
	}

	public void setUsers(List<UserDto> users) {
		this.users = users;
	}

	public Long[] getSelectedRoles() {
		return selectedRoles;
	}

	public void setSelectedRoles(Long[] selectedRoles) {
		this.selectedRoles = selectedRoles;
	}

	public List<RoleDto> getRoles() {
		return roles;
	}

	public void setRoles(List<RoleDto> roles) {
		this.roles = roles;
	}

}
