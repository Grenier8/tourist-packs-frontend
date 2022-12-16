package cu.edu.cujae.touristpacks.service.contract;

import cu.edu.cujae.touristpacks.dto.ContractDto;
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
public class ContractServiceImpl implements IContractService {

    private String endpoint = "/api/v1/contracts/";

    @Autowired
    private RestService restService;

    @Override
    public List<ContractDto> getContracts() {
        List<ContractDto> list = new ArrayList<>();
        try {
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            ApiRestMapper<ContractDto> apiRestMapper = new ApiRestMapper<>();
            String response = (String) restService
                    .GET(endpoint + "", params, String.class, CurrentUserUtils.getTokenBearer()).getBody();
            list = apiRestMapper.mapList(response, ContractDto.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public ContractDto getContractById(int idContract) {
        ContractDto contract = null;

        try {
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            ApiRestMapper<ContractDto> apiRestMapper = new ApiRestMapper<>();

            UriTemplate template = new UriTemplate(endpoint + "{idContract}");
            String uri = template.expand(idContract).toString();
            String response = (String) restService.GET(uri, params, String.class).getBody();
            contract = apiRestMapper.mapOne(response, ContractDto.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return contract;
    }

    @Override
    public ContractDto getContractByTitle(String contractTitle) {
        ContractDto contract = null;

        try {
            String uri = endpoint + "title/{title}";
            Map<String, String> map = new HashMap<>();
            map.put("title", contractTitle);

            String response = (String) restService.GETEntity(
                    uri, map,
                    String.class).getBody();

            ApiRestMapper<ContractDto> apiRestMapper = new ApiRestMapper<>();
            contract = apiRestMapper.mapOne(response, ContractDto.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return contract;
    }

    @Override
    public void createContract(ContractDto contract) {
        restService.POST(endpoint + "", contract, String.class).getBody();
    }

    @Override
    public void updateContract(ContractDto contract) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        restService.PUT(endpoint + "", params, contract, String.class).getBody();
    }

    @Override
    public void deleteContract(int idContract) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        UriTemplate template = new UriTemplate(endpoint + "{idContract}");
        String uri = template.expand(idContract).toString();
        restService.DELETE(uri, params, String.class, null).getBody();
    }

}
