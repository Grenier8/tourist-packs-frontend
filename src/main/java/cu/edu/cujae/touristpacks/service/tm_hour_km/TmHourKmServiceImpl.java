package cu.edu.cujae.touristpacks.service.tm_hour_km;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cu.edu.cujae.touristpacks.dto.TmHourKmDto;
import cu.edu.cujae.touristpacks.security.CurrentUserUtils;
import cu.edu.cujae.touristpacks.utils.ApiRestMapper;
import cu.edu.cujae.touristpacks.utils.RestService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriTemplate;

@Service
public class TmHourKmServiceImpl implements ITmHourKmService {

    private String endpoint = "/api/v1/tm_hour_kms/";

    @Autowired
    private RestService restService;

    @Override
    public List<TmHourKmDto> getTmHourKms() {
        List<TmHourKmDto> list = new ArrayList<>();
        try {
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            ApiRestMapper<TmHourKmDto> apiRestMapper = new ApiRestMapper<>();
            String response = (String) restService
                    .GET(endpoint + "", params, String.class, CurrentUserUtils.getTokenBearer()).getBody();
            list = apiRestMapper.mapList(response, TmHourKmDto.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public TmHourKmDto getTmHourKmById(int tmHourKmId) {
        TmHourKmDto tmHourKm = null;

        try {
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            ApiRestMapper<TmHourKmDto> apiRestMapper = new ApiRestMapper<>();

            UriTemplate template = new UriTemplate(endpoint + "{id}");
            String uri = template.expand(tmHourKmId).toString();
            String response = (String) restService.GET(uri, params, String.class, CurrentUserUtils.getTokenBearer())
                    .getBody();
            tmHourKm = apiRestMapper.mapOne(response, TmHourKmDto.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tmHourKm;
    }

    @Override
    public TmHourKmDto getTmHourKmByName(String tmHourKmName) {
        TmHourKmDto tmHourKm = null;

        try {
            String uri = endpoint + "name/{name}";
            Map<String, String> map = new HashMap<>();
            map.put("name", tmHourKmName);

            String response = (String) restService.GETEntity(
                    uri, map,
                    String.class, CurrentUserUtils.getTokenBearer()).getBody();

            ApiRestMapper<TmHourKmDto> apiRestMapper = new ApiRestMapper<>();
            tmHourKm = apiRestMapper.mapOne(response, TmHourKmDto.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tmHourKm;
    }

    @Override
    public void createTmHourKm(TmHourKmDto tmHourKm) {
        restService.POST(endpoint + "", tmHourKm, String.class, CurrentUserUtils.getTokenBearer()).getBody();
    }

    @Override
    public void updateTmHourKm(TmHourKmDto tmHourKm) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        restService.PUT(endpoint + "", params, tmHourKm, String.class, CurrentUserUtils.getTokenBearer()).getBody();
    }

    @Override
    public void deleteTmHourKm(int idTmHourKm) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        UriTemplate template = new UriTemplate(endpoint + "{id}");
        String uri = template.expand(idTmHourKm).toString();
        restService.DELETE(uri, params, String.class, CurrentUserUtils.getTokenBearer()).getBody();
    }

}
