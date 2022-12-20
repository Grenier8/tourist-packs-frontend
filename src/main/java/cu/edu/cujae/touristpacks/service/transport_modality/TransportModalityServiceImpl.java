package cu.edu.cujae.touristpacks.service.transport_modality;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import cu.edu.cujae.touristpacks.dto.TransportModalityDto;
import cu.edu.cujae.touristpacks.utils.ApiRestMapper;
import cu.edu.cujae.touristpacks.utils.RestService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriTemplate;

@Service
public class TransportModalityServiceImpl implements ITransportModalityService {

	private String endpoint = "/api/v1/transport_modalities/";

    @Autowired
    private RestService restService;

    @Override
    public List<TransportModalityDto> getTransportModalities() {
    	List<TransportModalityDto> list = new ArrayList<>();
  	  try {
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            ApiRestMapper<TransportModalityDto> apiRestMapper = new ApiRestMapper<>();
            String response = (String) restService.GET(endpoint + "", params, String.class).getBody();
            list = apiRestMapper.mapList(response, TransportModalityDto.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public TransportModalityDto getTransportModalityById(int transportModalityId) {
    	 TransportModalityDto ble = null;

         try {
             MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
             ApiRestMapper<TransportModalityDto> apiRestMapper = new ApiRestMapper<>();

             UriTemplate template = new UriTemplate(endpoint + "/{transportModalityId}");
             String uri = template.expand(transportModalityId).toString();
             String response = (String) restService.GET(uri, params, String.class).getBody();
             ble = apiRestMapper.mapOne(response, TransportModalityDto.class);
         } catch (Exception e) {
             e.printStackTrace();
         }
         return ble;
    }

    @Override
    public TransportModalityDto getTransportModalityByName(String transportModalityName) {
    	 TransportModalityDto ble = null;

         try {
             MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
             ApiRestMapper<TransportModalityDto> apiRestMapper = new ApiRestMapper<>();

             UriTemplate template = new UriTemplate(endpoint + "/name/{transportModalityName}");
             String uri = template.expand(transportModalityName).toString();
             String response = (String) restService.GET(uri, params, String.class).getBody();
             ble = apiRestMapper.mapOne(response, TransportModalityDto.class);
         } catch (Exception e) {
             e.printStackTrace();
         }
         return ble;
    }

    @Override
    public void createTransportModality(TransportModalityDto transportModality) {
    	restService.POST(endpoint + "", transportModality, String.class).getBody();
    }

    @Override
    public void updateTransportModality(TransportModalityDto transportModality) {
    	MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        restService.PUT(endpoint + "", params, transportModality, String.class).getBody();
    }

    @Override
    public void deleteTransportModality(int idTransportModality) {
    	MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        UriTemplate template = new UriTemplate(endpoint + "/{idTransportModality}");
        String uri = template.expand(idTransportModality).toString();
        restService.DELETE(uri, params, String.class, null).getBody();
     }
    

}
