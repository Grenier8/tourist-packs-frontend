package cu.edu.cujae.touristpacks.service.security;

import java.util.List;

import cu.edu.cujae.touristpacks.dto.UserDto;

public interface UserService {
	List<UserDto> getUsers();
	UserDto getUserById(String userId);
	void createUser(UserDto user);
	void updateUser(UserDto user);
	void deleteUser(String id);
}
