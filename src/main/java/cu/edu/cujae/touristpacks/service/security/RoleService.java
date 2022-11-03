package cu.edu.cujae.touristpacks.service.security;

import java.util.List;

import cu.edu.cujae.touristpacks.dto.RoleDto;

public interface RoleService {
	List<RoleDto> getRoles();
	List<RoleDto> getRolesByUser(Long userId);
	List<RoleDto> getRolesByName(String name);
	RoleDto getRolesById(Long roleId);
}
