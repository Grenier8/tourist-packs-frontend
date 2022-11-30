package cu.edu.cujae.touristpacks.service.diary_activity;

import cu.edu.cujae.touristpacks.dto.DiaryActivityDto;
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
public class DiaryActivityServiceImpl implements IDiaryActivityService {

    private String endpoint = "/api/v1/diary_activities/";

    @Autowired
    private RestService restService;

    @Override
    public List<DiaryActivityDto> getDiaryActivities() {
        List<DiaryActivityDto> list = new ArrayList<>();
        try {
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            ApiRestMapper<DiaryActivityDto> apiRestMapper = new ApiRestMapper<>();
            String response = (String) restService.GET(endpoint, params, String.class).getBody();
            list = apiRestMapper.mapList(response, DiaryActivityDto.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public DiaryActivityDto getDiaryActivityById(int idDiaryActivity) {
        DiaryActivityDto diaryActivity = null;

        try {
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            ApiRestMapper<DiaryActivityDto> apiRestMapper = new ApiRestMapper<>();

            UriTemplate template = new UriTemplate(endpoint + "/{idDiaryActivity}");
            String uri = template.expand(idDiaryActivity).toString();
            String response = (String) restService.GET(uri, params, String.class).getBody();
            diaryActivity = apiRestMapper.mapOne(response, DiaryActivityDto.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return diaryActivity;
    }

    @Override
    public DiaryActivityDto getDiaryActivityByName(String diaryActivityName) {
        DiaryActivityDto diaryActivity = null;

        try {
            String uri = endpoint + "name/{name}";
            Map<String, String> map = new HashMap<>();
            map.put("name", diaryActivityName);

            String response = (String) restService.GETEntity(
                    uri, map,
                    String.class).getBody();

            ApiRestMapper<DiaryActivityDto> apiRestMapper = new ApiRestMapper<>();
            diaryActivity = apiRestMapper.mapOne(response, DiaryActivityDto.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return diaryActivity;
    }

    @Override
    public void createDiaryActivity(DiaryActivityDto diaryActivity) {
        restService.POST(endpoint + "", diaryActivity, String.class).getBody();
    }

    @Override
    public void updateDiaryActivity(DiaryActivityDto diaryActivity) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        restService.PUT(endpoint + "", params, diaryActivity, String.class).getBody();
    }

    @Override
    public void deleteDiaryActivity(int idDiaryActivity) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        UriTemplate template = new UriTemplate(endpoint + "/{idDiaryActivity}");
        String uri = template.expand(idDiaryActivity).toString();
        restService.DELETE(uri, params, String.class, null).getBody();
    }

}
