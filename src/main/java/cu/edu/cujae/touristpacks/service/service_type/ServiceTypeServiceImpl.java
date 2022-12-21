package cu.edu.cujae.touristpacks.service.service_type;

import cu.edu.cujae.touristpacks.dto.ServiceTypeDto;
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
public class ServiceTypeServiceImpl implements IServiceTypeService {

    private String endpoint = "/api/v1/service_types/";

    @Autowired
    private RestService restService;

    @Override
    public List<ServiceTypeDto> getServiceTypes() {
        List<ServiceTypeDto> list = new ArrayList<>();
        try {
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            ApiRestMapper<ServiceTypeDto> apiRestMapper = new ApiRestMapper<>();
            String response = (String) restService
                    .GET(endpoint + "", params, String.class, CurrentUserUtils.getTokenBearer()).getBody();
            list = apiRestMapper.mapList(response, ServiceTypeDto.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public ServiceTypeDto getServiceTypeById(int idServiceType) {
        ServiceTypeDto serviceType = null;

        try {
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            ApiRestMapper<ServiceTypeDto> apiRestMapper = new ApiRestMapper<>();

            UriTemplate template = new UriTemplate(endpoint + "{idServiceType}");
            String uri = template.expand(idServiceType).toString();
            String response = (String) restService.GET(uri, params, String.class, CurrentUserUtils.getTokenBearer())
                    .getBody();
            serviceType = apiRestMapper.mapOne(response, ServiceTypeDto.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return serviceType;
    }

    @Override
    public ServiceTypeDto getServiceTypeByName(String serviceTypeName) {
        ServiceTypeDto serviceType = null;

        try {
            String uri = endpoint + "name/{name}";
            Map<String, String> map = new HashMap<>();
            map.put("name", serviceTypeName);

            String response = (String) restService.GETEntity(
                    uri, map,
                    String.class, CurrentUserUtils.getTokenBearer()).getBody();

            ApiRestMapper<ServiceTypeDto> apiRestMapper = new ApiRestMapper<>();
            serviceType = apiRestMapper.mapOne(response, ServiceTypeDto.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return serviceType;
    }

    @Override
    public void createServiceType(ServiceTypeDto serviceType) {
        restService.POST(endpoint + "", serviceType, String.class, CurrentUserUtils.getTokenBearer()).getBody();
    }

    @Override
    public void updateServiceType(ServiceTypeDto serviceType) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        restService.PUT(endpoint + "", params, serviceType, String.class, CurrentUserUtils.getTokenBearer()).getBody();
    }

    @Override
    public void deleteServiceType(int idServiceType) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        UriTemplate template = new UriTemplate(endpoint + "{idServiceType}");
        String uri = template.expand(idServiceType).toString();
        restService.DELETE(uri, params, String.class, CurrentUserUtils.getTokenBearer()).getBody();
    }

}
