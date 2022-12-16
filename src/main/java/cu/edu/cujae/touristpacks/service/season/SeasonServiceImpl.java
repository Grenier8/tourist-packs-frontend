package cu.edu.cujae.touristpacks.service.season;

import cu.edu.cujae.touristpacks.dto.SeasonDto;
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
public class SeasonServiceImpl implements ISeasonService {

    private String endpoint = "/api/v1/seasons/";

    @Autowired
    private RestService restService;

    @Override
    public List<SeasonDto> getSeasons() {
        List<SeasonDto> list = new ArrayList<>();
        try {
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            ApiRestMapper<SeasonDto> apiRestMapper = new ApiRestMapper<>();
            String response = (String) restService
                    .GET(endpoint + "", params, String.class, CurrentUserUtils.getTokenBearer()).getBody();
            list = apiRestMapper.mapList(response, SeasonDto.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public SeasonDto getSeasonById(int idSeason) {
        SeasonDto season = null;

        try {
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            ApiRestMapper<SeasonDto> apiRestMapper = new ApiRestMapper<>();

            UriTemplate template = new UriTemplate(endpoint + "{idSeason}");
            String uri = template.expand(idSeason).toString();
            String response = (String) restService.GET(uri, params, String.class).getBody();
            season = apiRestMapper.mapOne(response, SeasonDto.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return season;
    }

    @Override
    public SeasonDto getSeasonByName(String seasonName) {
        SeasonDto season = null;

        try {
            String uri = endpoint + "name/{name}";
            Map<String, String> map = new HashMap<>();
            map.put("name", seasonName);

            String response = (String) restService.GETEntity(
                    uri, map,
                    String.class).getBody();

            ApiRestMapper<SeasonDto> apiRestMapper = new ApiRestMapper<>();
            season = apiRestMapper.mapOne(response, SeasonDto.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return season;
    }

    @Override
    public void createSeason(SeasonDto season) {
        restService.POST(endpoint + "", season, String.class).getBody();
    }

    @Override
    public void updateSeason(SeasonDto season) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        restService.PUT(endpoint + "", params, season, String.class).getBody();
    }

    @Override
    public void deleteSeason(int idSeason) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        UriTemplate template = new UriTemplate(endpoint + "{idSeason}");
        String uri = template.expand(idSeason).toString();
        restService.DELETE(uri, params, String.class, null).getBody();
    }

}
