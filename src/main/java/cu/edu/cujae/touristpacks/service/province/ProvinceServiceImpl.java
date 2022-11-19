package cu.edu.cujae.touristpacks.service.province;

import cu.edu.cujae.touristpacks.dto.ProvinceDto;
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
            String response = (String) restService.GET(endpoint, params, String.class).getBody();
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

            UriTemplate template = new UriTemplate(endpoint + "/{idProvince}");
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
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            ApiRestMapper<ProvinceDto> apiRestMapper = new ApiRestMapper<>();

            UriTemplate template = new UriTemplate(endpoint + "/name/{provinceName}");
            String uri = template.expand(provinceName).toString();
            String response = (String) restService.GET(uri, params, String.class).getBody();
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
        UriTemplate template = new UriTemplate(endpoint + "/{idProvince}");
        String uri = template.expand(idProvince).toString();
        restService.DELETE(uri, params, String.class, null).getBody();
    }

}
