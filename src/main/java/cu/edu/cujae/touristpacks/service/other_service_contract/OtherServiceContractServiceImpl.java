package cu.edu.cujae.touristpacks.service.other_service_contract;

import cu.edu.cujae.touristpacks.dto.OtherServiceContractDto;
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
public class OtherServiceContractServiceImpl implements IOtherServiceContractService {

    private String endpoint = "/api/v1/other_service_contracts/";

    @Autowired
    private RestService restService;

    @Override
    public List<OtherServiceContractDto> getOtherServiceContracts() {
        List<OtherServiceContractDto> list = new ArrayList<>();
        try {
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            ApiRestMapper<OtherServiceContractDto> apiRestMapper = new ApiRestMapper<>();
            String response = (String) restService
                    .GET(endpoint + "", params, String.class, CurrentUserUtils.getTokenBearer()).getBody();
            list = apiRestMapper.mapList(response, OtherServiceContractDto.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public OtherServiceContractDto getOtherServiceContractById(int idOtherServiceContract) {
        OtherServiceContractDto otherServiceContract = null;

        try {
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            ApiRestMapper<OtherServiceContractDto> apiRestMapper = new ApiRestMapper<>();

            UriTemplate template = new UriTemplate(endpoint + "{idOtherServiceContract}");
            String uri = template.expand(idOtherServiceContract).toString();
            String response = (String) restService.GET(uri, params, String.class).getBody();
            otherServiceContract = apiRestMapper.mapOne(response, OtherServiceContractDto.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return otherServiceContract;
    }

    @Override
    public OtherServiceContractDto getOtherServiceContractByTitle(String otherServiceContractTitle) {
        OtherServiceContractDto otherServiceContract = null;

        try {
            String uri = endpoint + "/title/{title}";
            Map<String, String> map = new HashMap<>();
            map.put("title", otherServiceContractTitle);

            String response = (String) restService.GETEntity(
                    uri, map,
                    String.class).getBody();

            ApiRestMapper<OtherServiceContractDto> apiRestMapper = new ApiRestMapper<>();
            otherServiceContract = apiRestMapper.mapOne(response, OtherServiceContractDto.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return otherServiceContract;
    }

    @Override
    public void createOtherServiceContract(OtherServiceContractDto otherServiceContract) {
        restService.POST(endpoint + "", otherServiceContract, String.class).getBody();
    }

    @Override
    public void updateOtherServiceContract(OtherServiceContractDto otherServiceContract) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        restService.PUT(endpoint + "", params, otherServiceContract, String.class).getBody();
    }

    @Override
    public void deleteOtherServiceContract(int idOtherServiceContract) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        UriTemplate template = new UriTemplate(endpoint + "{idOtherServiceContract}");
        String uri = template.expand(idOtherServiceContract).toString();
        restService.DELETE(uri, params, String.class, null).getBody();
    }

}
