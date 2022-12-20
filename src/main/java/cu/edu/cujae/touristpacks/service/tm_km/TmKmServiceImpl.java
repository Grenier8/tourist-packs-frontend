package cu.edu.cujae.touristpacks.service.tm_km;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cu.edu.cujae.touristpacks.dto.TmKmDto;
import cu.edu.cujae.touristpacks.security.CurrentUserUtils;
import cu.edu.cujae.touristpacks.utils.ApiRestMapper;
import cu.edu.cujae.touristpacks.utils.RestService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriTemplate;

@Service
public class TmKmServiceImpl implements ITmKmService {

    private String endpoint = "/api/v1/tm_kms/";

    @Autowired
    private RestService restService;

    @Override
    public List<TmKmDto> getTmKms() {
        List<TmKmDto> list = new ArrayList<>();
        try {
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            ApiRestMapper<TmKmDto> apiRestMapper = new ApiRestMapper<>();
            String response = (String) restService
                    .GET(endpoint + "", params, String.class, CurrentUserUtils.getTokenBearer()).getBody();
            list = apiRestMapper.mapList(response, TmKmDto.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public TmKmDto getTmKmById(int tmKmId) {
        TmKmDto tmKm = null;

        try {
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            ApiRestMapper<TmKmDto> apiRestMapper = new ApiRestMapper<>();

            UriTemplate template = new UriTemplate(endpoint + "{id}");
            String uri = template.expand(tmKmId).toString();
            String response = (String) restService.GET(uri, params, String.class, CurrentUserUtils.getTokenBearer())
                    .getBody();
            tmKm = apiRestMapper.mapOne(response, TmKmDto.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tmKm;
    }

    @Override
    public TmKmDto getTmKmByName(String tmKmName) {
        TmKmDto tmKm = null;

        try {
            String uri = endpoint + "name/{name}";
            Map<String, String> map = new HashMap<>();
            map.put("name", tmKmName);

            String response = (String) restService.GETEntity(
                    uri, map,
                    String.class, CurrentUserUtils.getTokenBearer()).getBody();

            ApiRestMapper<TmKmDto> apiRestMapper = new ApiRestMapper<>();
            tmKm = apiRestMapper.mapOne(response, TmKmDto.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tmKm;
    }

    @Override
    public void createTmKm(TmKmDto tmKm) {
        restService.POST(endpoint + "", tmKm, String.class, CurrentUserUtils.getTokenBearer()).getBody();
    }

    @Override
    public void updateTmKm(TmKmDto tmKm) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        restService.PUT(endpoint + "", params, tmKm, String.class, CurrentUserUtils.getTokenBearer()).getBody();
    }

    @Override
    public void deleteTmKm(int idTmKm) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        UriTemplate template = new UriTemplate(endpoint + "{id}");
        String uri = template.expand(idTmKm).toString();
        restService.DELETE(uri, params, String.class, CurrentUserUtils.getTokenBearer()).getBody();
    }

}
