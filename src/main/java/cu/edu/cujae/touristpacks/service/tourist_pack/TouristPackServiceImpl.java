package cu.edu.cujae.touristpacks.service.tourist_pack;

import cu.edu.cujae.touristpacks.dto.TouristPackDto;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TouristPackServiceImpl implements ITouristPackService {

    private String endpoint = "/api/v1/tourist_packs/";

    @Autowired
    private RestService restService;

    @Override
    public List<TouristPackDto> getTouristPacks() {
        List<TouristPackDto> list = new ArrayList<>();
        try {
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            ApiRestMapper<TouristPackDto> apiRestMapper = new ApiRestMapper<>();
            String response = (String) restService
                    .GET(endpoint + "", params, String.class, CurrentUserUtils.getTokenBearer()).getBody();
            list = apiRestMapper.mapList(response, TouristPackDto.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public TouristPackDto getTouristPackById(int idTouristPack) {
        TouristPackDto touristPacks = null;

        try {
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            ApiRestMapper<TouristPackDto> apiRestMapper = new ApiRestMapper<>();

            UriTemplate template = new UriTemplate(endpoint + "{idTouristPack}");
            String uri = template.expand(idTouristPack).toString();
            String response = (String) restService.GET(uri, params, String.class).getBody();
            touristPacks = apiRestMapper.mapOne(response, TouristPackDto.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return touristPacks;
    }

    @Override
    public TouristPackDto getTouristPackByName(String touristPackName) {
        TouristPackDto touristPack = null;

        try {
            String uri = endpoint + "name/{name}";
            Map<String, String> map = new HashMap<>();
            map.put("name", touristPackName);

            String response = (String) restService.GETEntity(
                    uri, map,
                    String.class).getBody();

            ApiRestMapper<TouristPackDto> apiRestMapper = new ApiRestMapper<>();
            touristPack = apiRestMapper.mapOne(response, TouristPackDto.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return touristPack;
    }

    @Override
    public void createTouristPack(TouristPackDto touristPacks) {
        restService.POST(endpoint + "", touristPacks, String.class).getBody();
    }

    @Override
    public void updateTouristPack(TouristPackDto touristPacks) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        restService.PUT(endpoint + "", params, touristPacks, String.class).getBody();
    }

    @Override
    public void deleteTouristPack(int idTouristPack) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        UriTemplate template = new UriTemplate(endpoint + "{idTouristPack}");
        String uri = template.expand(idTouristPack).toString();
        restService.DELETE(uri, params, String.class, null).getBody();
    }

}
