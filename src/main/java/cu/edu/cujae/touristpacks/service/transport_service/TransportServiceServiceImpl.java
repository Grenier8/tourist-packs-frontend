package cu.edu.cujae.touristpacks.service.transport_service;

import java.util.ArrayList;
import java.util.List;

import cu.edu.cujae.touristpacks.dto.TransportModalityDto;
import cu.edu.cujae.touristpacks.dto.TransportServiceDto;
import cu.edu.cujae.touristpacks.dto.VehicleDto;
import cu.edu.cujae.touristpacks.service.transport_modality.ITransportModalityService;
import cu.edu.cujae.touristpacks.service.transport_modality.TransportModalityServiceImpl;
import cu.edu.cujae.touristpacks.service.vehicle.IVehicleService;
import cu.edu.cujae.touristpacks.service.vehicle.VehicleServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransportServiceServiceImpl implements ITransportServiceService {

    @Autowired
    IVehicleService vehicleService;

    @Autowired
    ITransportModalityService transportModalityService;

    @Override
    public List<TransportServiceDto> getTransportServices() {
        List<TransportServiceDto> list = new ArrayList<>();

        VehicleDto vehicle1 = vehicleService.getVehicles().get(0);
        VehicleDto vehicle2 = vehicleService.getVehicles().get(1);

        TransportModalityDto mod1 = transportModalityService.getTransportModalities().get(0);
        TransportModalityDto mod2 = transportModalityService.getTransportModalities().get(1);

        list.add(new TransportServiceDto(1, "Ser1", vehicle1, mod1, 12));
        list.add(new TransportServiceDto(2, "Ser2", vehicle2, mod2, 13));

        return list;
    }

    @Override
    public TransportServiceDto getTransportServiceById(int transportServiceId) {
        return getTransportServices().stream().filter(r -> r.getIdTransportService() == transportServiceId).findFirst()
                .get();
    }

    @Override
    public TransportServiceDto getTransportServiceByName(String transportServiceName) {
        return getTransportServices().stream().filter(r -> r.getTransportServiceName().equals(transportServiceName))
                .findFirst().get();
    }

    @Override
    public void createTransportService(TransportServiceDto transportService) {
        // TODO Auto-generated method stub

    }

    @Override
    public void updateTransportService(TransportServiceDto transportService) {
        // TODO Auto-generated method stub

    }

    @Override
    public void deleteTransportService(int idTransportService) {
        // TODO Auto-generated method stub

    }

}
