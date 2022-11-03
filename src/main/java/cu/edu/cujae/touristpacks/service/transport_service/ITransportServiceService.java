package cu.edu.cujae.touristpacks.service.transport_service;

import java.util.List;

import cu.edu.cujae.touristpacks.dto.TransportServiceDto;

public interface ITransportServiceService {
    List<TransportServiceDto> getTransportServices();

    TransportServiceDto getTransportServiceById(int transportServiceId);

    TransportServiceDto getTransportServiceByName(String transportServiceName);

    void createTransportService(TransportServiceDto transportService);

    void updateTransportService(TransportServiceDto transportService);

    void deleteTransportService(int idTransportService);
}
