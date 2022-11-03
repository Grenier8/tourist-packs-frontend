package cu.edu.cujae.touristpacks.service.transport_modality;

import java.util.List;

import cu.edu.cujae.touristpacks.dto.TransportModalityDto;

public interface ITransportModalityService {
    List<TransportModalityDto> getTransportModalities();

    TransportModalityDto getTransportModalityById(int transportModalityId);

    TransportModalityDto getTransportModalityByName(String transportModalityName);

    void createTransportModality(TransportModalityDto transportModality);

    void updateTransportModality(TransportModalityDto transportModality);

    void deleteTransportModality(int idTransportModality);
}
