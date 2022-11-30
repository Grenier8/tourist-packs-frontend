package cu.edu.cujae.touristpacks.service.dtb_table;

import cu.edu.cujae.touristpacks.dto.MayorDto;
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
public class MayorServiceImpl implements IMayorService {

    private String endpoint = "/api/v1/dtb_tables/";

    @Autowired
    private RestService restService;

    @Override
    public List<MayorDto> getMayors() {
        List<MayorDto> list = new ArrayList<>();
        try {
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            ApiRestMapper<MayorDto> apiRestMapper = new ApiRestMapper<>();
            String response = (String) restService.GET(endpoint, params, String.class).getBody();
            list = apiRestMapper.mapList(response, MayorDto.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public MayorDto getMayorById(int idMayor) {
        MayorDto minor = null;

        try {
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            ApiRestMapper<MayorDto> apiRestMapper = new ApiRestMapper<>();

            UriTemplate template = new UriTemplate(endpoint + "/{idMayor}");
            String uri = template.expand(idMayor).toString();
            String response = (String) restService.GET(uri, params, String.class).getBody();
            minor = apiRestMapper.mapOne(response, MayorDto.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return minor;
    }

    @Override
    public MayorDto getMayorByName(String minorName) {
        MayorDto minor = null;

        try {
            String uri = endpoint + "name/{name}";
            Map<String, String> map = new HashMap<>();
            map.put("name", minorName);

            String response = (String) restService.GETEntity(
                    uri, map,
                    String.class).getBody();

            ApiRestMapper<MayorDto> apiRestMapper = new ApiRestMapper<>();
            minor = apiRestMapper.mapOne(response, MayorDto.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return minor;
    }

    @Override
    public void createMayor(MayorDto minor) {
        restService.POST(endpoint + "", minor, String.class).getBody();
    }

    @Override
    public void updateMayor(MayorDto minor) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        restService.PUT(endpoint + "", params, minor, String.class).getBody();
    }

    @Override
    public void deleteMayor(int idMayor) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        UriTemplate template = new UriTemplate(endpoint + "/{idMayor}");
        String uri = template.expand(idMayor).toString();
        restService.DELETE(uri, params, String.class, null).getBody();
    }

}
