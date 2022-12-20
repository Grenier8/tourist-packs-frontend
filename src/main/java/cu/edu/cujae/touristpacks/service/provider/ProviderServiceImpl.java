package cu.edu.cujae.touristpacks.service.provider;

import cu.edu.cujae.touristpacks.dto.ProviderDto;
import cu.edu.cujae.touristpacks.security.CurrentUserUtils;
import cu.edu.cujae.touristpacks.utils.ApiRestMapper;
import cu.edu.cujae.touristpacks.utils.RestService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProviderServiceImpl implements IProviderService {

    private String endpoint = "/api/v1/providers/";

    @Autowired
    private RestService restService;

    @Override
    public List<ProviderDto> getProviders() {
        List<ProviderDto> list = new ArrayList<>();
        try {
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            ApiRestMapper<ProviderDto> apiRestMapper = new ApiRestMapper<>();
            String response = (String) restService
                    .GET(endpoint + "", params, String.class, CurrentUserUtils.getTokenBearer()).getBody();
            list = apiRestMapper.mapList(response, ProviderDto.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public ProviderDto getProviderById(int providerId) {
        ProviderDto provider = null;

        try {
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            ApiRestMapper<ProviderDto> apiRestMapper = new ApiRestMapper<>();

            UriTemplate template = new UriTemplate(endpoint + "{id}");
            String uri = template.expand(providerId).toString();
            String response = (String) restService.GET(uri, params, String.class, CurrentUserUtils.getTokenBearer())
                    .getBody();
            provider = apiRestMapper.mapOne(response, ProviderDto.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return provider;
    }

    @Override
    public ProviderDto getProviderByName(String providerName) {
        ProviderDto provider = null;

        try {
            String uri = endpoint + "name/{name}";
            Map<String, String> map = new HashMap<>();
            map.put("name", providerName);

            String response = (String) restService.GETEntity(
                    uri, map,
                    String.class, CurrentUserUtils.getTokenBearer()).getBody();

            ApiRestMapper<ProviderDto> apiRestMapper = new ApiRestMapper<>();
            provider = apiRestMapper.mapOne(response, ProviderDto.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return provider;
    }

    @Override
    public void createProvider(ProviderDto provider) {
        restService.POST(endpoint + "", provider, String.class, CurrentUserUtils.getTokenBearer()).getBody();
    }

    @Override
    public void updateProvider(ProviderDto provider) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        restService.PUT(endpoint + "", params, provider, String.class, CurrentUserUtils.getTokenBearer()).getBody();

    }

    @Override
    public void deleteProvider(int idProvider) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        UriTemplate template = new UriTemplate(endpoint + "{id}");
        String uri = template.expand(idProvider).toString();
        restService.DELETE(uri, params, String.class, CurrentUserUtils.getTokenBearer()).getBody();
    }
}
