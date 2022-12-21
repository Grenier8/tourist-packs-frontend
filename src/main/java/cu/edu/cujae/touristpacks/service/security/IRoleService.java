package cu.edu.cujae.touristpacks.service.security;

import java.util.List;

import cu.edu.cujae.touristpacks.dto.security.RoleDto;

public interface IRoleService {
	List<RoleDto> getRoles();

	List<RoleDto> getRolesByUser(int idRole);

	RoleDto getRolesById(int iRole);

	RoleDto getRoleByName(String roleName);

	void createRole(RoleDto user);

	void updateRole(RoleDto user);

	void deleteRole(int iduser);
}
