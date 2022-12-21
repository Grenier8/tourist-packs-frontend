package cu.edu.cujae.touristpacks.service.other_service_contract;

import java.util.List;

import cu.edu.cujae.touristpacks.dto.OtherServiceContractDto;

public interface IOtherServiceContractService {
    List<OtherServiceContractDto> getOtherServiceContracts();

    OtherServiceContractDto getOtherServiceContractById(int otherServiceContractId);

    OtherServiceContractDto getOtherServiceContractByTitle(String otherServiceContractName);

    void createOtherServiceContract(OtherServiceContractDto otherServiceContract);

    void updateOtherServiceContract(OtherServiceContractDto otherServiceContract);

    void deleteOtherServiceContract(int idOtherServiceContract);

    List<OtherServiceContractDto> get1121OtherServiceContracts();
}
