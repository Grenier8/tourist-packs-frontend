package cu.edu.cujae.touristpacks.service.security;

import cu.edu.cujae.touristpacks.dto.RoleDto;
import cu.edu.cujae.touristpacks.utils.ApiRestMapper;
import cu.edu.cujae.touristpacks.utils.RestService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RoleServiceImpl implements IRoleService {

    private String endpoint = "/api/v1/roles/";

    @Autowired
    private RestService restService;

    @Override
    public List<RoleDto> getRoles() {
        List<RoleDto> list = new ArrayList<>();
        try {
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            ApiRestMapper<RoleDto> apiRestMapper = new ApiRestMapper<>();
            String response = (String) restService.GET(endpoint + "", params, String.class).getBody();
            list = apiRestMapper.mapList(response, RoleDto.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public RoleDto getRoleByName(String roleName) {
        RoleDto role = null;

        try {
            String uri = endpoint + "name/{name}";
            Map<String, String> map = new HashMap<>();
            map.put("name", roleName);

            String response = (String) restService.GETEntity(
                    uri, map,
                    String.class).getBody();

            ApiRestMapper<RoleDto> apiRestMapper = new ApiRestMapper<>();
            role = apiRestMapper.mapOne(response, RoleDto.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return role;
    }

    @Override
    public void createRole(RoleDto role) {
        restService.POST(endpoint + "", role, String.class).getBody();
    }

    @Override
    public void updateRole(RoleDto role) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        restService.PUT(endpoint + "", params, role, String.class).getBody();
    }

    @Override
    public void deleteRole(int idRole) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        UriTemplate template = new UriTemplate(endpoint + "{idRole}");
        String uri = template.expand(idRole).toString();
        restService.DELETE(uri, params, String.class, null).getBody();
    }

    @Override
    public List<RoleDto> getRolesByUser(int idRole) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public RoleDto getRolesById(int iRole) {
        // TODO Auto-generated method stub
        return null;
    }

}
