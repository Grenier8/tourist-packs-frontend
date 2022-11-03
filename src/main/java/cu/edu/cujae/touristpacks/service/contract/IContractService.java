package cu.edu.cujae.touristpacks.service.contract;

import java.util.List;

import cu.edu.cujae.touristpacks.dto.ContractDto;

public interface IContractService {
    List<ContractDto> getContracts();

    ContractDto getContractById(int contractId);

    ContractDto getContractByName(String contractName);

    void createContract(ContractDto contract);

    void updateContract(ContractDto contract);

    void deleteContract(int idContract);
}
