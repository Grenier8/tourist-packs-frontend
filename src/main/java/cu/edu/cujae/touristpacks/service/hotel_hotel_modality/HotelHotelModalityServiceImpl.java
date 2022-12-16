package cu.edu.cujae.touristpacks.service.hotel_hotel_modality;

import cu.edu.cujae.touristpacks.dto.HotelHotelModalityDto;
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
import java.util.List;

@Service
public class HotelHotelModalityServiceImpl implements IHotelHotelModalityService {

    private String endpoint = "/api/v1/hotel_hotel_modalities/";

    @Autowired
    private RestService restService;

    @Override
    public List<HotelHotelModalityDto> getHotelHotelModalities() {
        List<HotelHotelModalityDto> list = new ArrayList<>();
        try {
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            ApiRestMapper<HotelHotelModalityDto> apiRestMapper = new ApiRestMapper<>();
            String response = (String) restService
                    .GET(endpoint + "", params, String.class, CurrentUserUtils.getTokenBearer()).getBody();
            list = apiRestMapper.mapList(response, HotelHotelModalityDto.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public HotelHotelModalityDto getHotelHotelModalityById(int idHotelHotelModality) {
        HotelHotelModalityDto hotelHotelModality = null;

        try {
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            ApiRestMapper<HotelHotelModalityDto> apiRestMapper = new ApiRestMapper<>();

            UriTemplate template = new UriTemplate(endpoint + "{idHotelHotelModality}");
            String uri = template.expand(idHotelHotelModality).toString();
            String response = (String) restService.GET(uri, params, String.class).getBody();
            hotelHotelModality = apiRestMapper.mapOne(response, HotelHotelModalityDto.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hotelHotelModality;
    }

    @Override
    public void createHotelHotelModality(HotelHotelModalityDto hotelHotelModality) {
        restService.POST(endpoint + "", hotelHotelModality, String.class).getBody();
    }

    @Override
    public void updateHotelHotelModality(HotelHotelModalityDto hotelHotelModality) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        restService.PUT(endpoint + "", params, hotelHotelModality, String.class).getBody();
    }

    @Override
    public void deleteHotelHotelModality(int idHotelHotelModality) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        UriTemplate template = new UriTemplate(endpoint + "{idHotelHotelModality}");
        String uri = template.expand(idHotelHotelModality).toString();
        restService.DELETE(uri, params, String.class, null).getBody();
    }

}
