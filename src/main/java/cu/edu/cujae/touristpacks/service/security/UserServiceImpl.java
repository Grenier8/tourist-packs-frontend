package cu.edu.cujae.touristpacks.service.security;

import cu.edu.cujae.touristpacks.dto.security.UserDto;
import cu.edu.cujae.touristpacks.security.CurrentUserUtils;
import cu.edu.cujae.touristpacks.utils.ApiRestMapper;
import cu.edu.cujae.touristpacks.utils.RestService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    private String endpoint = "/api/v1/users/";

    @Autowired
    private RestService restService;

    @Override
    public List<UserDto> getUsers() {
        List<UserDto> list = new ArrayList<>();
        try {
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            ApiRestMapper<UserDto> apiRestMapper = new ApiRestMapper<>();
            String response = (String) restService
                    .GET(endpoint + "", params, String.class, CurrentUserUtils.getTokenBearer()).getBody();
            list = apiRestMapper.mapList(response, UserDto.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public UserDto getUserById(int idUser) {
        UserDto user = null;

        try {
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            ApiRestMapper<UserDto> apiRestMapper = new ApiRestMapper<>();

            UriTemplate template = new UriTemplate(endpoint + "{idUser}");
            String uri = template.expand(idUser).toString();
            String response = (String) restService.GET(uri, params, String.class, CurrentUserUtils.getTokenBearer())
                    .getBody();
            user = apiRestMapper.mapOne(response, UserDto.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public void createUser(UserDto user) {
        restService.POST(endpoint + "", user, String.class).getBody();
    }

    @Override
    public void updateUser(UserDto user) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        restService.PUT(endpoint + "", params, user, String.class, CurrentUserUtils.getTokenBearer()).getBody();
    }

    @Override
    public void deleteUser(int idUser) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        UriTemplate template = new UriTemplate(endpoint + "{idUser}");
        String uri = template.expand(idUser).toString();
        restService.DELETE(uri, params, String.class, CurrentUserUtils.getTokenBearer()).getBody();
    }

}
