package cu.edu.cujae.touristpacks.service.diary_activity_tourist_pack;

import cu.edu.cujae.touristpacks.dto.DiaryActivityTouristPackDto;
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
public class DiaryActivityTouristPackServiceImpl implements IDiaryActivityTouristPackService {

    private String endpoint = "/api/v1/diary_activity_tourist_packs/";

    @Autowired
    private RestService restService;

    @Override
    public List<DiaryActivityTouristPackDto> getDiaryActivityTouristPacks() {
        List<DiaryActivityTouristPackDto> list = new ArrayList<>();
        try {
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            ApiRestMapper<DiaryActivityTouristPackDto> apiRestMapper = new ApiRestMapper<>();
            String response = (String) restService.GET(endpoint + "", params, String.class).getBody();
            list = apiRestMapper.mapList(response, DiaryActivityTouristPackDto.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public DiaryActivityTouristPackDto getDiaryActivityTouristPackById(int idDiaryActivityTouristPack) {
        DiaryActivityTouristPackDto diaryActivityTouristPack = null;

        try {
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            ApiRestMapper<DiaryActivityTouristPackDto> apiRestMapper = new ApiRestMapper<>();

            UriTemplate template = new UriTemplate(endpoint + "{idDiaryActivityTouristPack}");
            String uri = template.expand(idDiaryActivityTouristPack).toString();
            String response = (String) restService.GET(uri, params, String.class).getBody();
            diaryActivityTouristPack = apiRestMapper.mapOne(response, DiaryActivityTouristPackDto.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return diaryActivityTouristPack;
    }

    @Override
    public void createDiaryActivityTouristPack(DiaryActivityTouristPackDto diaryActivityTouristPack) {
        restService.POST(endpoint + "", diaryActivityTouristPack, String.class).getBody();
    }

    @Override
    public void updateDiaryActivityTouristPack(DiaryActivityTouristPackDto diaryActivityTouristPack) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        restService.PUT(endpoint + "", params, diaryActivityTouristPack, String.class).getBody();
    }

    @Override
    public void deleteDiaryActivityTouristPack(int idDiaryActivityTouristPack) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        UriTemplate template = new UriTemplate(endpoint + "{idDiaryActivityTouristPack}");
        String uri = template.expand(idDiaryActivityTouristPack).toString();
        restService.DELETE(uri, params, String.class, null).getBody();
    }

}
