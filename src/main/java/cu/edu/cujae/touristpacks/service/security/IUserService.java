package cu.edu.cujae.touristpacks.service.security;

import java.io.IOException;
import java.util.List;

import cu.edu.cujae.touristpacks.dto.security.UserDto;

public interface IUserService {
	List<UserDto> getUsers();

	UserDto getUserById(int userId);

	void createUser(UserDto user);

	void updateUser(UserDto user);

	void deleteUser(int iduser);
}
