package cu.edu.cujae.touristpacks.service.province;

import cu.edu.cujae.touristpacks.dto.ProvinceDto;
import cu.edu.cujae.touristpacks.security.CurrentUserUtils;
import cu.edu.cujae.touristpacks.utils.ApiRestMapper;
import cu.edu.cujae.touristpacks.utils.RestService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProvinceServiceImpl implements IProvinceService {

    private String endpoint = "/api/v1/provinces/";

    @Autowired
    private RestService restService;

    @Override
    public List<ProvinceDto> getProvinces() {
        List<ProvinceDto> list = new ArrayList<>();
        try {
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            ApiRestMapper<ProvinceDto> apiRestMapper = new ApiRestMapper<>();
            String response = (String) restService
                    .GET(endpoint + "", params, String.class, CurrentUserUtils.getTokenBearer()).getBody();
            list = apiRestMapper.mapList(response, ProvinceDto.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public ProvinceDto getProvinceById(int idProvince) {
        ProvinceDto province = null;

        try {
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            ApiRestMapper<ProvinceDto> apiRestMapper = new ApiRestMapper<>();

            UriTemplate template = new UriTemplate(endpoint + "{id}");
            String uri = template.expand(idProvince).toString();
            String response = (String) restService.GET(uri, params, String.class).getBody();
            province = apiRestMapper.mapOne(response, ProvinceDto.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return province;
    }

    @Override
    public ProvinceDto getProvinceByName(String provinceName) {
        ProvinceDto province = null;

        try {
            String uri = endpoint + "name/{name}";
            Map<String, String> map = new HashMap<>();
            map.put("name", provinceName);

            String response = (String) restService.GETEntity(
                    uri, map,
                    String.class).getBody();

            ApiRestMapper<ProvinceDto> apiRestMapper = new ApiRestMapper<>();
            province = apiRestMapper.mapOne(response, ProvinceDto.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return province;
    }

    @Override
    public void createProvince(ProvinceDto province) {
        restService.POST(endpoint + "", province, String.class).getBody();
    }

    @Override
    public void updateProvince(ProvinceDto province) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        restService.PUT(endpoint + "", params, province, String.class).getBody();
    }

    @Override
    public void deleteProvince(int idProvince) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        UriTemplate template = new UriTemplate(endpoint + "{id}");
        String uri = template.expand(idProvince).toString();
        restService.DELETE(uri, params, String.class, null).getBody();
    }

}
