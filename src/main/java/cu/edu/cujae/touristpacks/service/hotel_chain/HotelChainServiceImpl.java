package cu.edu.cujae.touristpacks.service.hotel_chain;

import cu.edu.cujae.touristpacks.dto.HotelChainDto;
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
public class HotelChainServiceImpl implements IHotelChainService {

    private String endpoint = "/api/v1/hotel_chains/";

    @Autowired
    private RestService restService;

    @Override
    public List<HotelChainDto> getHotelChains() {
        List<HotelChainDto> list = new ArrayList<HotelChainDto>();
        try {
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            ApiRestMapper<HotelChainDto> apiRestMapper = new ApiRestMapper<>();
            String response = (String) restService.GET(endpoint + "", params, String.class).getBody();
            list = apiRestMapper.mapList(response, HotelChainDto.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public HotelChainDto getHotelChainById(int idHotelChain) {
        HotelChainDto hotelChain = null;

        try {
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            ApiRestMapper<HotelChainDto> apiRestMapper = new ApiRestMapper<>();

            UriTemplate template = new UriTemplate(endpoint + "/{idHotelChain}");
            String uri = template.expand(idHotelChain).toString();
            String response = (String) restService.GET(uri, params, String.class).getBody();
            hotelChain = apiRestMapper.mapOne(response, HotelChainDto.class);
        } catch (Exception e) {
            // TODO: handle exception
        }
        return hotelChain;
    }

    @Override
    public HotelChainDto getHotelChainByName(String hotelChainName) {
        HotelChainDto hotelChain = null;

        try {
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            ApiRestMapper<HotelChainDto> apiRestMapper = new ApiRestMapper<>();

            UriTemplate template = new UriTemplate(endpoint + "/name/{hotelChainName}");
            String uri = template.expand(hotelChainName).toString();
            String response = (String) restService.GET(uri, params, String.class).getBody();
            hotelChain = apiRestMapper.mapOne(response, HotelChainDto.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hotelChain;
    }

    @Override
    public void createHotelChain(HotelChainDto hotelChain) {
        restService.POST(endpoint + "", hotelChain, String.class).getBody();
    }

    @Override
    public void updateHotelChain(HotelChainDto hotelChain) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        restService.PUT(endpoint + "", params, hotelChain, String.class).getBody();
    }

    @Override
    public void deleteHotelChain(int idHotelChain) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        UriTemplate template = new UriTemplate(endpoint + "/{idHotelChain}");
        String uri = template.expand(idHotelChain).toString();
        restService.DELETE(uri, params, String.class, null).getBody();
    }

}