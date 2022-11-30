package cu.edu.cujae.touristpacks.service.room_plan_season;

import cu.edu.cujae.touristpacks.dto.RoomPlanSeasonDto;
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
public class RoomPlanSeasonServiceImpl implements IRoomPlanSeasonService {

    private String endpoint = "/api/v1/room_plan_seasons/";

    @Autowired
    private RestService restService;

    @Override
    public List<RoomPlanSeasonDto> getRoomPlanSeasons() {
        List<RoomPlanSeasonDto> list = new ArrayList<>();
        try {
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            ApiRestMapper<RoomPlanSeasonDto> apiRestMapper = new ApiRestMapper<>();
            String response = (String) restService.GET(endpoint, params, String.class).getBody();
            list = apiRestMapper.mapList(response, RoomPlanSeasonDto.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public RoomPlanSeasonDto getRoomPlanSeasonById(int idRoomPlanSeason) {
        RoomPlanSeasonDto roomPlanSeason = null;

        try {
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            ApiRestMapper<RoomPlanSeasonDto> apiRestMapper = new ApiRestMapper<>();

            UriTemplate template = new UriTemplate(endpoint + "/{idRoomPlanSeason}");
            String uri = template.expand(idRoomPlanSeason).toString();
            String response = (String) restService.GET(uri, params, String.class).getBody();
            roomPlanSeason = apiRestMapper.mapOne(response, RoomPlanSeasonDto.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return roomPlanSeason;
    }

    @Override
    public RoomPlanSeasonDto getRoomPlanSeasonByName(String roomPlanSeasonName) {
        RoomPlanSeasonDto roomPlanSeason = null;

        try {
            String uri = endpoint + "name/{name}";
            Map<String, String> map = new HashMap<>();
            map.put("name", roomPlanSeasonName);

            String response = (String) restService.GETEntity(
                    uri, map,
                    String.class).getBody();

            ApiRestMapper<RoomPlanSeasonDto> apiRestMapper = new ApiRestMapper<>();
            roomPlanSeason = apiRestMapper.mapOne(response, RoomPlanSeasonDto.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return roomPlanSeason;
    }

    @Override
    public void createRoomPlanSeason(RoomPlanSeasonDto roomPlanSeason) {
        restService.POST(endpoint + "", roomPlanSeason, String.class).getBody();
    }

    @Override
    public void updateRoomPlanSeason(RoomPlanSeasonDto roomPlanSeason) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        restService.PUT(endpoint + "", params, roomPlanSeason, String.class).getBody();
    }

    @Override
    public void deleteRoomPlanSeason(int idRoomPlanSeason) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        UriTemplate template = new UriTemplate(endpoint + "/{idRoomPlanSeason}");
        String uri = template.expand(idRoomPlanSeason).toString();
        restService.DELETE(uri, params, String.class, null).getBody();
    }

}
