package cu.edu.cujae.touristpacks.service.transport_contract_transport_service;

import java.util.List;

import cu.edu.cujae.touristpacks.dto.TransportContractTransportServiceDto;

public interface ITransportContractTransportServiceService {
    List<TransportContractTransportServiceDto> getTransportContractTransportServices();

    TransportContractTransportServiceDto getTransportContractTransportServiceById(
            int transportContractTransportServiceId);

    void createTransportContractTransportService(
            TransportContractTransportServiceDto transportContractTransportService);

    void updateTransportContractTransportService(
            TransportContractTransportServiceDto transportContractTransportService);

    void deleteTransportContractTransportService(int idTransportContractTransportService);
}
