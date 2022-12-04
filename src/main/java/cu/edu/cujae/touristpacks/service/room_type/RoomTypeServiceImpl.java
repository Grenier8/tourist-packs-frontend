package cu.edu.cujae.touristpacks.service.room_type;

import cu.edu.cujae.touristpacks.dto.RoomTypeDto;
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
public class RoomTypeServiceImpl implements IRoomTypeService {

    private String endpoint = "/api/v1/room_types/";

    @Autowired
    private RestService restService;

    @Override
    public List<RoomTypeDto> getRoomTypes() {
        List<RoomTypeDto> list = new ArrayList<>();
        try {
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            ApiRestMapper<RoomTypeDto> apiRestMapper = new ApiRestMapper<>();
            String response = (String) restService.GET(endpoint + "", params, String.class).getBody();
            list = apiRestMapper.mapList(response, RoomTypeDto.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public RoomTypeDto getRoomTypeById(int idRoomType) {
        RoomTypeDto roomType = null;

        try {
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            ApiRestMapper<RoomTypeDto> apiRestMapper = new ApiRestMapper<>();

            UriTemplate template = new UriTemplate(endpoint + "{idRoomType}");
            String uri = template.expand(idRoomType).toString();
            String response = (String) restService.GET(uri, params, String.class).getBody();
            roomType = apiRestMapper.mapOne(response, RoomTypeDto.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return roomType;
    }

    @Override
    public RoomTypeDto getRoomTypeByName(String roomTypeName) {
        RoomTypeDto roomType = null;

        try {
            String uri = endpoint + "name/{name}";
            Map<String, String> map = new HashMap<>();
            map.put("name", roomTypeName);

            String response = (String) restService.GETEntity(
                    uri, map,
                    String.class).getBody();

            ApiRestMapper<RoomTypeDto> apiRestMapper = new ApiRestMapper<>();
            roomType = apiRestMapper.mapOne(response, RoomTypeDto.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return roomType;
    }

    @Override
    public void createRoomType(RoomTypeDto roomType) {
        restService.POST(endpoint + "", roomType, String.class).getBody();
    }

    @Override
    public void updateRoomType(RoomTypeDto roomType) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        restService.PUT(endpoint + "", params, roomType, String.class).getBody();
    }

    @Override
    public void deleteRoomType(int idRoomType) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        UriTemplate template = new UriTemplate(endpoint + "{idRoomType}");
        String uri = template.expand(idRoomType).toString();
        restService.DELETE(uri, params, String.class, null).getBody();
    }

}
