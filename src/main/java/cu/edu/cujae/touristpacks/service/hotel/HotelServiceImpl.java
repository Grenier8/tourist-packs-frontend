package cu.edu.cujae.touristpacks.service.hotel;

import cu.edu.cujae.touristpacks.dto.HotelDto;
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
public class HotelServiceImpl implements IHotelService {

    private String endpoint = "/api/v1/hotels/";

    @Autowired
    private RestService restService;

    @Override
    public List<HotelDto> getHotels() {
        List<HotelDto> list = new ArrayList<>();
        try {
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            ApiRestMapper<HotelDto> apiRestMapper = new ApiRestMapper<>();
            String response = (String) restService.GET(endpoint + "", params, String.class).getBody();
            list = apiRestMapper.mapList(response, HotelDto.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public HotelDto getHotelById(int idHotel) {
        HotelDto hotel = null;

        try {
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            ApiRestMapper<HotelDto> apiRestMapper = new ApiRestMapper<>();

            UriTemplate template = new UriTemplate(endpoint + "{idHotel}");
            String uri = template.expand(idHotel).toString();
            String response = (String) restService.GET(uri, params, String.class).getBody();
            hotel = apiRestMapper.mapOne(response, HotelDto.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hotel;
    }

    @Override
    public HotelDto getHotelByName(String hotelName) {
        HotelDto hotel = null;

        try {
            String uri = endpoint + "name/{name}";
            Map<String, String> map = new HashMap<>();
            map.put("name", hotelName);

            String response = (String) restService.GETEntity(
                    uri, map,
                    String.class).getBody();

            ApiRestMapper<HotelDto> apiRestMapper = new ApiRestMapper<>();
            hotel = apiRestMapper.mapOne(response, HotelDto.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hotel;
    }

    @Override
    public void createHotel(HotelDto hotel) {
        restService.POST(endpoint + "", hotel, String.class).getBody();
    }

    @Override
    public void updateHotel(HotelDto hotel) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        restService.PUT(endpoint + "", params, hotel, String.class).getBody();
    }

    @Override
    public void deleteHotel(int idHotel) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        UriTemplate template = new UriTemplate(endpoint + "{idHotel}");
        String uri = template.expand(idHotel).toString();
        restService.DELETE(uri, params, String.class, null).getBody();
    }

}
