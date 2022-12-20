package cu.edu.cujae.touristpacks.service.vehicle;

import java.util.List;

import cu.edu.cujae.touristpacks.dto.VehicleDto;

public interface IVehicleService {
	List<VehicleDto> getVehicles();
	
	VehicleDto getVehicleById(int vehicleId);
	
    void createVehicle(VehicleDto vehicle);
    
    void updateVehicle(VehicleDto vehicle);
    
    void deleteVehicle(int id);
    
    VehicleDto getVehicleByPlate(String vehiclePlate);
}
