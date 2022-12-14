package cu.edu.cujae.touristpacks.service.hotel_contract;

import cu.edu.cujae.touristpacks.dto.HotelContractDto;
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
public class HotelContractServiceImpl implements IHotelContractService {

    private String endpoint = "/api/v1/hotel_contracts/";

    @Autowired
    private RestService restService;

    @Override
    public List<HotelContractDto> getHotelContracts() {
        List<HotelContractDto> list = new ArrayList<>();
        try {
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            ApiRestMapper<HotelContractDto> apiRestMapper = new ApiRestMapper<>();
            String response = (String) restService
                    .GET(endpoint + "", params, String.class, CurrentUserUtils.getTokenBearer()).getBody();
            list = apiRestMapper.mapList(response, HotelContractDto.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public HotelContractDto getHotelContractById(int idHotelContract) {
        HotelContractDto hotelContract = null;

        try {
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            ApiRestMapper<HotelContractDto> apiRestMapper = new ApiRestMapper<>();

            UriTemplate template = new UriTemplate(endpoint + "{idHotelContract}");
            String uri = template.expand(idHotelContract).toString();
            String response = (String) restService.GET(uri, params, String.class, CurrentUserUtils.getTokenBearer())
                    .getBody();
            hotelContract = apiRestMapper.mapOne(response, HotelContractDto.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hotelContract;
    }

    @Override
    public HotelContractDto getHotelContractByTitle(String hotelContractTitle) {
        HotelContractDto hotelContract = null;

        try {
            String uri = endpoint + "title/{title}";
            Map<String, String> map = new HashMap<>();
            map.put("title", hotelContractTitle);

            String response = (String) restService.GETEntity(
                    uri, map,
                    String.class, CurrentUserUtils.getTokenBearer()).getBody();

            ApiRestMapper<HotelContractDto> apiRestMapper = new ApiRestMapper<>();
            hotelContract = apiRestMapper.mapOne(response, HotelContractDto.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hotelContract;
    }

    @Override
    public void createHotelContract(HotelContractDto hotelContract) {
        restService.POST(endpoint + "", hotelContract, String.class, CurrentUserUtils.getTokenBearer()).getBody();
    }

    @Override
    public void updateHotelContract(HotelContractDto hotelContract) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        restService.PUT(endpoint + "", params, hotelContract, String.class, CurrentUserUtils.getTokenBearer())
                .getBody();
    }

    @Override
    public void deleteHotelContract(int idHotelContract) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        UriTemplate template = new UriTemplate(endpoint + "{idHotelContract}");
        String uri = template.expand(idHotelContract).toString();
        restService.DELETE(uri, params, String.class, CurrentUserUtils.getTokenBearer()).getBody();
    }

}
