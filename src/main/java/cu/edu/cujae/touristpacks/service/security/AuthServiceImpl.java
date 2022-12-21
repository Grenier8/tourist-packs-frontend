package cu.edu.cujae.touristpacks.service.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cu.edu.cujae.touristpacks.dto.security.LoginRequestDto;
import cu.edu.cujae.touristpacks.dto.security.UserAuthenticatedDto;
import cu.edu.cujae.touristpacks.utils.ApiRestMapper;
import cu.edu.cujae.touristpacks.utils.RestService;

@Service
public class AuthServiceImpl implements IAuthService {

	@Autowired
	private RestService restService;

	@Override
	public UserAuthenticatedDto login(String username, String password) {
		UserAuthenticatedDto authenticatedDto = null;
		try {
			String response = (String) restService
					.POST("/api/v1/auth/login", new LoginRequestDto(username, password), String.class).getBody();

			ApiRestMapper<UserAuthenticatedDto> apiRestMapper = new ApiRestMapper<>();
			authenticatedDto = apiRestMapper.mapOne(response, UserAuthenticatedDto.class);
		} catch (Exception e) {
			authenticatedDto = null;
		}
		return authenticatedDto;
	}

}
