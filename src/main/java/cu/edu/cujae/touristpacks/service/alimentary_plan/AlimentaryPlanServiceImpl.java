package cu.edu.cujae.touristpacks.service.alimentary_plan;

import cu.edu.cujae.touristpacks.dto.AlimentaryPlanDto;
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
public class AlimentaryPlanServiceImpl implements IAlimentaryPlanService {

    private String endpoint = "/api/v1/alimentary_plans/";

    @Autowired
    private RestService restService;

    @Override
    public List<AlimentaryPlanDto> getAlimentaryPlans() {
        List<AlimentaryPlanDto> list = new ArrayList<>();
        try {
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            ApiRestMapper<AlimentaryPlanDto> apiRestMapper = new ApiRestMapper<>();
            String response = (String) restService
                    .GET(endpoint + "", params, String.class, CurrentUserUtils.getTokenBearer()).getBody();
            list = apiRestMapper.mapList(response, AlimentaryPlanDto.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public AlimentaryPlanDto getAlimentaryPlanById(int idAlimentaryPlan) {
        AlimentaryPlanDto alimentaryPlan = null;

        try {
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            ApiRestMapper<AlimentaryPlanDto> apiRestMapper = new ApiRestMapper<>();

            UriTemplate template = new UriTemplate(endpoint + "{idAlimentaryPlan}");
            String uri = template.expand(idAlimentaryPlan).toString();
            String response = (String) restService.GET(uri, params, String.class, CurrentUserUtils.getTokenBearer())
                    .getBody();
            alimentaryPlan = apiRestMapper.mapOne(response, AlimentaryPlanDto.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return alimentaryPlan;
    }

    @Override
    public AlimentaryPlanDto getAlimentaryPlanByName(String alimentaryPlanName) {
        AlimentaryPlanDto alimentaryPlan = null;

        try {
            String uri = endpoint + "name/{name}";
            Map<String, String> map = new HashMap<>();
            map.put("name", alimentaryPlanName);

            String response = (String) restService.GETEntity(
                    uri, map,
                    String.class, CurrentUserUtils.getTokenBearer()).getBody();

            ApiRestMapper<AlimentaryPlanDto> apiRestMapper = new ApiRestMapper<>();
            alimentaryPlan = apiRestMapper.mapOne(response, AlimentaryPlanDto.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return alimentaryPlan;
    }

    @Override
    public void createAlimentaryPlan(AlimentaryPlanDto alimentaryPlan) {
        restService.POST(endpoint + "", alimentaryPlan, String.class, CurrentUserUtils.getTokenBearer()).getBody();
    }

    @Override
    public void updateAlimentaryPlan(AlimentaryPlanDto alimentaryPlan) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        restService.PUT(endpoint + "", params, alimentaryPlan, String.class, CurrentUserUtils.getTokenBearer())
                .getBody();
    }

    @Override
    public void deleteAlimentaryPlan(int idAlimentaryPlan) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        UriTemplate template = new UriTemplate(endpoint + "{idAlimentaryPlan}");
        String uri = template.expand(idAlimentaryPlan).toString();
        restService.DELETE(uri, params, String.class, CurrentUserUtils.getTokenBearer()).getBody();
    }

}
