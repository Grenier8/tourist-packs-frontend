package cu.edu.cujae.touristpacks.service.transport_service_tourist_pack;

import java.util.ArrayList;
import java.util.List;

import cu.edu.cujae.touristpacks.dto.TouristPackDto;
import cu.edu.cujae.touristpacks.dto.TransportServiceDto;
import cu.edu.cujae.touristpacks.dto.TransportServiceTouristPackDto;
import cu.edu.cujae.touristpacks.service.tourist_pack.ITouristPackService;
import cu.edu.cujae.touristpacks.service.tourist_pack.TouristPackServiceImpl;
import cu.edu.cujae.touristpacks.service.transport_service.ITransportServiceService;
import cu.edu.cujae.touristpacks.service.transport_service.TransportServiceServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransportServiceTouristPackServiceImpl implements ITransportServiceTouristPackService {

    @Autowired
    ITouristPackService touristPackService;

    @Autowired
    ITransportServiceService transportServiceService;

    @Override
    public List<TransportServiceTouristPackDto> getTransportServiceTouristPacks() {
        List<TransportServiceTouristPackDto> list = new ArrayList<>();

        TransportServiceDto transportService1 = transportServiceService.getTransportServices().get(0);
        TransportServiceDto transportService2 = transportServiceService.getTransportServices().get(1);

        TouristPackDto touristPack1 = touristPackService.getTouristPacks().get(0);
        TouristPackDto touristPack2 = touristPackService.getTouristPacks().get(1);

        list.add(new TransportServiceTouristPackDto(1, transportService1, touristPack1));
        list.add(new TransportServiceTouristPackDto(2, transportService2, touristPack2));

        return list;
    }

    @Override
    public TransportServiceTouristPackDto getTransportServiceTouristPackById(int transportServiceTouristPackId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void createTransportServiceTouristPack(TransportServiceTouristPackDto transportServiceTouristPack) {
        // TODO Auto-generated method stub

    }

    @Override
    public void updateTransportServiceTouristPack(TransportServiceTouristPackDto transportServiceTouristPack) {
        // TODO Auto-generated method stub

    }

    @Override
    public void deleteTransportServiceTouristPack(int idTransportServiceTouristPack) {
        // TODO Auto-generated method stub

    }

}
