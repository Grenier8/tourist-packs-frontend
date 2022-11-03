package cu.edu.cujae.touristpacks.service.transport_contract;

import java.util.List;

import cu.edu.cujae.touristpacks.dto.TransportContractDto;

public interface ITransportContractService {
    List<TransportContractDto> getTransportContracts();

    TransportContractDto getTransportContractById(int transportContractId);

    TransportContractDto getTransportContractByTitle(String transportContracTitle);

    void createTransportContract(TransportContractDto transportContract);

    void updateTransportContract(TransportContractDto transportContract);

    void deleteTransportContract(int idTransportContract);
}
