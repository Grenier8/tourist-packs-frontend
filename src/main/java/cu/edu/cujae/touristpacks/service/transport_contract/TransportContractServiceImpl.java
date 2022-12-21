package cu.edu.cujae.touristpacks.service.transport_contract;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cu.edu.cujae.touristpacks.dto.TransportContractDto;
import cu.edu.cujae.touristpacks.security.CurrentUserUtils;
import cu.edu.cujae.touristpacks.utils.ApiRestMapper;
import cu.edu.cujae.touristpacks.utils.RestService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriTemplate;

@Service
public class TransportContractServiceImpl implements ITransportContractService {

    private String endpoint = "/api/v1/transport_contracts/";

    @Autowired
    private RestService restService;

    @Override
    public List<TransportContractDto> getTransportContracts() {
        List<TransportContractDto> list = new ArrayList<>();
        try {
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            ApiRestMapper<TransportContractDto> apiRestMapper = new ApiRestMapper<>();
            String response = (String) restService
                    .GET(endpoint + "", params, String.class, CurrentUserUtils.getTokenBearer()).getBody();
            list = apiRestMapper.mapList(response, TransportContractDto.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public TransportContractDto getTransportContractById(int transportContractId) {
        TransportContractDto transportContract = null;

        try {
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            ApiRestMapper<TransportContractDto> apiRestMapper = new ApiRestMapper<>();

            UriTemplate template = new UriTemplate(endpoint + "{id}");
            String uri = template.expand(transportContractId).toString();
            String response = (String) restService.GET(uri, params, String.class, CurrentUserUtils.getTokenBearer())
                    .getBody();
            transportContract = apiRestMapper.mapOne(response, TransportContractDto.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return transportContract;
    }

    @Override
    public TransportContractDto getTransportContractByTitle(String transportContractTitle) {
        TransportContractDto transportContract = null;

        try {
            String uri = endpoint + "title/{title}";
            Map<String, String> map = new HashMap<>();
            map.put("title", transportContractTitle);

            String response = (String) restService.GETEntity(
                    uri, map,
                    String.class, CurrentUserUtils.getTokenBearer()).getBody();

            ApiRestMapper<TransportContractDto> apiRestMapper = new ApiRestMapper<>();
            transportContract = apiRestMapper.mapOne(response, TransportContractDto.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return transportContract;
    }

    @Override
    public void createTransportContract(TransportContractDto transportContract) {
        restService.POST(endpoint + "", transportContract, String.class, CurrentUserUtils.getTokenBearer()).getBody();
    }

    @Override
    public void updateTransportContract(TransportContractDto transportContract) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        restService.PUT(endpoint + "", params, transportContract, String.class, CurrentUserUtils.getTokenBearer())
                .getBody();
    }

    @Override
    public void deleteTransportContract(int idTransportContract) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        UriTemplate template = new UriTemplate(endpoint + "{id}");
        String uri = template.expand(idTransportContract).toString();
        restService.DELETE(uri, params, String.class, CurrentUserUtils.getTokenBearer()).getBody();
    }

}
