package cu.edu.cujae.touristpacks.service.travel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cu.edu.cujae.touristpacks.dto.TravelDto;
import cu.edu.cujae.touristpacks.security.CurrentUserUtils;
import cu.edu.cujae.touristpacks.utils.ApiRestMapper;
import cu.edu.cujae.touristpacks.utils.RestService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriTemplate;

@Service
public class TravelServiceImpl implements ITravelService {

    private String endpoint = "/api/v1/travels/";

    @Autowired
    private RestService restService;

    @Override
    public List<TravelDto> getTravels() {
        List<TravelDto> list = new ArrayList<>();
        try {
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            ApiRestMapper<TravelDto> apiRestMapper = new ApiRestMapper<>();
            String response = (String) restService
                    .GET(endpoint + "", params, String.class, CurrentUserUtils.getTokenBearer()).getBody();
            list = apiRestMapper.mapList(response, TravelDto.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public TravelDto getTravelById(int travelId) {
        TravelDto travel = null;

        try {
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            ApiRestMapper<TravelDto> apiRestMapper = new ApiRestMapper<>();

            UriTemplate template = new UriTemplate(endpoint + "{id}");
            String uri = template.expand(travelId).toString();
            String response = (String) restService.GET(uri, params, String.class, CurrentUserUtils.getTokenBearer())
                    .getBody();
            travel = apiRestMapper.mapOne(response, TravelDto.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return travel;
    }

    @Override
    public TravelDto getTravelByName(String travelName) {
        TravelDto travel = null;

        try {
            String uri = endpoint + "name/{name}";
            Map<String, String> map = new HashMap<>();
            map.put("name", travelName);

            String response = (String) restService.GETEntity(
                    uri, map,
                    String.class, CurrentUserUtils.getTokenBearer()).getBody();

            ApiRestMapper<TravelDto> apiRestMapper = new ApiRestMapper<>();
            travel = apiRestMapper.mapOne(response, TravelDto.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return travel;
    }

    @Override
    public void createTravel(TravelDto travel) {
        restService.POST(endpoint + "", travel, String.class, CurrentUserUtils.getTokenBearer()).getBody();
    }

    @Override
    public void updateTravel(TravelDto travel) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        restService.PUT(endpoint + "", params, travel, String.class, CurrentUserUtils.getTokenBearer()).getBody();
    }

    @Override
    public void deleteTravel(int idTravel) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        UriTemplate template = new UriTemplate(endpoint + "{id}");
        String uri = template.expand(idTravel).toString();
        restService.DELETE(uri, params, String.class, CurrentUserUtils.getTokenBearer()).getBody();
    }

}
