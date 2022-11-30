package cu.edu.cujae.touristpacks.service.hotel_modality;

import cu.edu.cujae.touristpacks.dto.HotelModalityDto;
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
public class HotelModalityServiceImpl implements IHotelModalityService {

    private String endpoint = "/api/v1/hotel_modalities/";

    @Autowired
    private RestService restService;

    @Override
    public List<HotelModalityDto> getHotelModalities() {
        List<HotelModalityDto> list = new ArrayList<>();
        try {
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            ApiRestMapper<HotelModalityDto> apiRestMapper = new ApiRestMapper<>();
            String response = (String) restService.GET(endpoint, params, String.class).getBody();
            list = apiRestMapper.mapList(response, HotelModalityDto.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public HotelModalityDto getHotelModalityById(int idHotelModality) {
        HotelModalityDto hotelModality = null;

        try {
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            ApiRestMapper<HotelModalityDto> apiRestMapper = new ApiRestMapper<>();

            UriTemplate template = new UriTemplate(endpoint + "{idHotelModality}");
            String uri = template.expand(idHotelModality).toString();
            String response = (String) restService.GET(uri, params, String.class).getBody();
            hotelModality = apiRestMapper.mapOne(response, HotelModalityDto.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hotelModality;
    }

    @Override
    public HotelModalityDto getHotelModalityByName(String hotelModalityName) {
        HotelModalityDto hotelModality = null;

        try {
            String uri = endpoint + "name/{name}";
            Map<String, String> map = new HashMap<>();
            map.put("name", hotelModalityName);

            String response = (String) restService.GETEntity(
                    uri, map,
                    String.class).getBody();

            ApiRestMapper<HotelModalityDto> apiRestMapper = new ApiRestMapper<>();
            hotelModality = apiRestMapper.mapOne(response, HotelModalityDto.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hotelModality;
    }

    @Override
    public void createHotelModality(HotelModalityDto hotelModality) {
        restService.POST(endpoint + "", hotelModality, String.class).getBody();
    }

    @Override
    public void updateHotelModality(HotelModalityDto hotelModality) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        restService.PUT(endpoint + "", params, hotelModality, String.class).getBody();
    }

    @Override
    public void deleteHotelModality(int idHotelModality) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        UriTemplate template = new UriTemplate(endpoint + "{idHotelModality}");
        String uri = template.expand(idHotelModality).toString();
        restService.DELETE(uri, params, String.class, null).getBody();
    }

}
