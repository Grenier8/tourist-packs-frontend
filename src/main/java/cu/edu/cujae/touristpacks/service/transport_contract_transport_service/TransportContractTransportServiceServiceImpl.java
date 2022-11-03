package cu.edu.cujae.touristpacks.service.transport_contract_transport_service;

import java.util.ArrayList;
import java.util.List;

import cu.edu.cujae.touristpacks.dto.HotelChainDto;
import cu.edu.cujae.touristpacks.dto.HotelDto;
import cu.edu.cujae.touristpacks.dto.ProvinceDto;
import cu.edu.cujae.touristpacks.dto.TransportContractDto;
import cu.edu.cujae.touristpacks.dto.TransportContractTransportServiceDto;
import cu.edu.cujae.touristpacks.dto.TransportServiceDto;
import cu.edu.cujae.touristpacks.service.transport_contract.ITransportContractService;
import cu.edu.cujae.touristpacks.service.transport_service.ITransportServiceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransportContractTransportServiceServiceImpl implements ITransportContractTransportServiceService {

	@Autowired
    private ITransportContractService tContractservice;
    
    @Autowired
    private ITransportServiceService tServiceservice;
	
    @Override
    public List<TransportContractTransportServiceDto> getTransportContractTransportServices() {
        List<TransportContractDto> tContract = tContractservice.getTransportContracts();
        List<TransportServiceDto> tService = tServiceservice.getTransportServices();

        List<TransportContractTransportServiceDto> contracts = new ArrayList<>();
        contracts.add(new TransportContractTransportServiceDto(1, tContract.get(0), tService.get(0)));
        contracts.add(new TransportContractTransportServiceDto(2, tContract.get(1), tService.get(1)));
        return contracts;
    }

    @Override
    public TransportContractTransportServiceDto getTransportContractTransportServiceById(
            int transportContractTransportServiceId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void createTransportContractTransportService(
            TransportContractTransportServiceDto transportContractTransportService) {
        // TODO Auto-generated method stub

    }

    @Override
    public void updateTransportContractTransportService(
            TransportContractTransportServiceDto transportContractTransportService) {
        // TODO Auto-generated method stub

    }

    @Override
    public void deleteTransportContractTransportService(int idTransportContractTransportService) {
        // TODO Auto-generated method stub

    }

}
