package cu.edu.cujae.touristpacks.service.tm_travel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cu.edu.cujae.touristpacks.dto.TmTravelDto;
import cu.edu.cujae.touristpacks.security.CurrentUserUtils;
import cu.edu.cujae.touristpacks.utils.ApiRestMapper;
import cu.edu.cujae.touristpacks.utils.RestService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriTemplate;
import org.springframework.web.client.RestTemplate;

@Service
public class TmTravelServiceImpl implements ITmTravelService {

    private String endpoint = "/api/v1/tm_travels/";

    @Autowired
    private RestService restService;

    @Override
    public List<TmTravelDto> getTmTravels() {
        List<TmTravelDto> list = new ArrayList<>();
        try {
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            ApiRestMapper<TmTravelDto> apiRestMapper = new ApiRestMapper<>();
            String response = (String) restService
                    .GET(endpoint + "", params, String.class, CurrentUserUtils.getTokenBearer()).getBody();
            list = apiRestMapper.mapList(response, TmTravelDto.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public TmTravelDto getTmTravelById(int tmTravelId) {
        TmTravelDto tmTravel = null;

        try {
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            ApiRestMapper<TmTravelDto> apiRestMapper = new ApiRestMapper<>();

            UriTemplate template = new UriTemplate(endpoint + "{id}");
            String uri = template.expand(tmTravelId).toString();
            String response = (String) restService.GET(uri, params, String.class, CurrentUserUtils.getTokenBearer())
                    .getBody();
            tmTravel = apiRestMapper.mapOne(response, TmTravelDto.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tmTravel;
    }

    @Override
    public TmTravelDto getTmTravelByName(String tmTravelName) {
        TmTravelDto tmTravel = null;

        try {
            String uri = endpoint + "name/{name}";
            Map<String, String> map = new HashMap<>();
            map.put("name", tmTravelName);

            String response = (String) restService.GETEntity(
                    uri, map,
                    String.class, CurrentUserUtils.getTokenBearer()).getBody();

            ApiRestMapper<TmTravelDto> apiRestMapper = new ApiRestMapper<>();
            tmTravel = apiRestMapper.mapOne(response, TmTravelDto.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tmTravel;
    }

    @Override
    public void createTmTravel(TmTravelDto tmTravel) {
        restService.POST(endpoint + "", tmTravel, String.class, CurrentUserUtils.getTokenBearer()).getBody();
    }

    @Override
    public void updateTmTravel(TmTravelDto tmTravel) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        restService.PUT(endpoint + "", params, tmTravel, String.class, CurrentUserUtils.getTokenBearer()).getBody();
    }

    @Override
    public void deleteTmTravel(int idTmTravel) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        UriTemplate template = new UriTemplate(endpoint + "{id}");
        String uri = template.expand(idTmTravel).toString();
        restService.DELETE(uri, params, String.class, CurrentUserUtils.getTokenBearer()).getBody();
    }

}
