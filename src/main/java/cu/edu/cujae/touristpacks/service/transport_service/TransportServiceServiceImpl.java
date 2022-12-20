package cu.edu.cujae.touristpacks.service.transport_service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cu.edu.cujae.touristpacks.dto.TransportServiceDto;
import cu.edu.cujae.touristpacks.security.CurrentUserUtils;
import cu.edu.cujae.touristpacks.service.transport_modality.ITransportModalityService;
import cu.edu.cujae.touristpacks.service.vehicle.IVehicleService;
import cu.edu.cujae.touristpacks.utils.ApiRestMapper;
import cu.edu.cujae.touristpacks.utils.RestService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriTemplate;

@Service
public class TransportServiceServiceImpl implements ITransportServiceService {

    private String endpoint = "/api/v1/transport_services/";

    @Autowired
    private RestService restService;

    @Autowired
    IVehicleService vehicleService;

    @Autowired
    ITransportModalityService transportModalityService;

    @Override
    public List<TransportServiceDto> getTransportServices() {
        List<TransportServiceDto> list = new ArrayList<>();
        try {
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            ApiRestMapper<TransportServiceDto> apiRestMapper = new ApiRestMapper<>();
            String response = (String) restService
                    .GET(endpoint + "", params, String.class, CurrentUserUtils.getTokenBearer()).getBody();
            list = apiRestMapper.mapList(response, TransportServiceDto.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public TransportServiceDto getTransportServiceById(int transportServiceId) {
        TransportServiceDto transportService = null;

        try {
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            ApiRestMapper<TransportServiceDto> apiRestMapper = new ApiRestMapper<>();

            UriTemplate template = new UriTemplate(endpoint + "{id}");
            String uri = template.expand(transportServiceId).toString();
            String response = (String) restService.GET(uri, params, String.class, CurrentUserUtils.getTokenBearer())
                    .getBody();
            transportService = apiRestMapper.mapOne(response, TransportServiceDto.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return transportService;
    }

    @Override
    public TransportServiceDto getTransportServiceByName(String transportServiceName) {
        TransportServiceDto transportService = null;

        try {
            String uri = endpoint + "name/{name}";
            Map<String, String> map = new HashMap<>();
            map.put("name", transportServiceName);

            String response = (String) restService.GETEntity(
                    uri, map,
                    String.class, CurrentUserUtils.getTokenBearer()).getBody();

            ApiRestMapper<TransportServiceDto> apiRestMapper = new ApiRestMapper<>();
            transportService = apiRestMapper.mapOne(response, TransportServiceDto.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return transportService;
    }

    @Override
    public void createTransportService(TransportServiceDto transportService) {
        restService.POST(endpoint + "", transportService, String.class, CurrentUserUtils.getTokenBearer()).getBody();
    }

    @Override
    public void updateTransportService(TransportServiceDto transportService) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        restService.PUT(endpoint + "", params, transportService, String.class, CurrentUserUtils.getTokenBearer())
                .getBody();
    }

    @Override
    public void deleteTransportService(int idTransportService) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        UriTemplate template = new UriTemplate(endpoint + "{id}");
        String uri = template.expand(idTransportService).toString();
        restService.DELETE(uri, params, String.class, CurrentUserUtils.getTokenBearer()).getBody();
    }

}
