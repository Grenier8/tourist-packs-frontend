
package cu.edu.cujae.touristpacks.service.transport_contract;

import java.util.ArrayList;
import java.util.List;
import cu.edu.cujae.touristpacks.dto.TransportContractDto;
import org.springframework.stereotype.Service;

@Service
public class TransportContractServiceImpl implements ITransportContractService {

    @Override
    public List<TransportContractDto> getTransportContracts() {
        return null;
    }

    @Override
    public TransportContractDto getTransportContractById(int transportContractId) {
        return getTransportContracts().stream().filter(r -> r.getIdTransportContract() == transportContractId)
                .findFirst().get();
    }

    @Override
    public TransportContractDto getTransportContractByTitle(String transportContractTitle) {
        return getTransportContracts().stream().filter(r -> r.getContractTitle().equals(transportContractTitle))
                .findFirst().get();
    }

    @Override
    public void createTransportContract(TransportContractDto transportContract) {
        // TODO Auto-generated method stub

    }

    @Override
    public void updateTransportContract(TransportContractDto transportContract) {
        // TODO Auto-generated method stub

    }

    @Override
    public void deleteTransportContract(int idTransportContract) {
        // TODO Auto-generated method stub

    }

}
