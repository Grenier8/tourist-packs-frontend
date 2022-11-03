package cu.edu.cujae.touristpacks.service.transport_service_tourist_pack;

import java.util.List;

import cu.edu.cujae.touristpacks.dto.TransportServiceTouristPackDto;

public interface ITransportServiceTouristPackService {
    List<TransportServiceTouristPackDto> getTransportServiceTouristPacks();

    TransportServiceTouristPackDto getTransportServiceTouristPackById(int transportServiceTouristPackId);

    void createTransportServiceTouristPack(TransportServiceTouristPackDto transportServiceTouristPack);

    void updateTransportServiceTouristPack(TransportServiceTouristPackDto transportServiceTouristPack);

    void deleteTransportServiceTouristPack(int idTransportServiceTouristPack);
}
