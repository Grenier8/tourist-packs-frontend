package cu.edu.cujae.touristpacks.service.security;

import cu.edu.cujae.touristpacks.dto.security.UserAuthenticatedDto;

public interface IAuthService {

	UserAuthenticatedDto login(String username, String password);

}
