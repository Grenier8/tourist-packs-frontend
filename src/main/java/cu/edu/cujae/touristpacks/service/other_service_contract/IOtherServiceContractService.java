package cu.edu.cujae.touristpacks.service.other_service_contract;

import java.util.List;

import cu.edu.cujae.touristpacks.dto.OtherServiceContractDto;

public interface IOtherServiceContractService {
    List<OtherServiceContractDto> getOtherServiceContracts();

    OtherServiceContractDto getOtherServiceContractById(int otherServiceContractId);

    OtherServiceContractDto getOtherServiceContractByName(String otherServiceContractName);

    void createOtherServiceContract(OtherServiceContractDto otherServiceContract);

    void updateOtherServiceContract(OtherServiceContractDto otherServiceContract);

    void deleteOtherServiceContract(int idOtherServiceContract);
}
