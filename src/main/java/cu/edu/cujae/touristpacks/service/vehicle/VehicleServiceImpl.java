package cu.edu.cujae.touristpacks.service.vehicle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriTemplate;

import cu.edu.cujae.touristpacks.dto.VehicleDto;
import cu.edu.cujae.touristpacks.security.CurrentUserUtils;
import cu.edu.cujae.touristpacks.utils.ApiRestMapper;
import cu.edu.cujae.touristpacks.utils.RestService;

@Service
public class VehicleServiceImpl implements IVehicleService {

	private String endpoint = "/api/v1/vehicles/";

	@Autowired
	private RestService restService;

	@Override
	public List<VehicleDto> getVehicles() {
		List<VehicleDto> list = new ArrayList<>();
		try {
			MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
			ApiRestMapper<VehicleDto> apiRestMapper = new ApiRestMapper<>();
			String response = (String) restService
					.GET(endpoint + "", params, String.class, CurrentUserUtils.getTokenBearer()).getBody();
			list = apiRestMapper.mapList(response, VehicleDto.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public VehicleDto getVehicleById(int vehicleId) {
		VehicleDto vehicle = null;

		try {
			MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
			ApiRestMapper<VehicleDto> apiRestMapper = new ApiRestMapper<>();

			UriTemplate template = new UriTemplate(endpoint + "{id}");
			String uri = template.expand(vehicleId).toString();
			String response = (String) restService.GET(uri, params, String.class, CurrentUserUtils.getTokenBearer())
					.getBody();
			vehicle = apiRestMapper.mapOne(response, VehicleDto.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vehicle;
	}

	@Override
	public VehicleDto getVehicleByPlate(String vehiclePlate) {
		VehicleDto vehicle = null;

		try {
			String uri = endpoint + "plate/{plate}";
			Map<String, String> map = new HashMap<>();
			map.put("plate", vehiclePlate);

			String response = (String) restService.GETEntity(
					uri, map,
					String.class, CurrentUserUtils.getTokenBearer()).getBody();

			ApiRestMapper<VehicleDto> apiRestMapper = new ApiRestMapper<>();
			vehicle = apiRestMapper.mapOne(response, VehicleDto.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vehicle;
	}

	@Override
	public void createVehicle(VehicleDto vehicle) {
		restService.POST(endpoint + "", vehicle, String.class, CurrentUserUtils.getTokenBearer()).getBody();
	}

	@Override
	public void updateVehicle(VehicleDto vehicle) {
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		restService.PUT(endpoint + "", params, vehicle, String.class, CurrentUserUtils.getTokenBearer()).getBody();
	}

	@Override
	public void deleteVehicle(int id) {
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		UriTemplate template = new UriTemplate(endpoint + "{id}");
		String uri = template.expand(id).toString();
		restService.DELETE(uri, params, String.class, CurrentUserUtils.getTokenBearer()).getBody();
	}

}
