package cu.edu.cujae.touristpacks.service.travel;

import java.util.List;

import cu.edu.cujae.touristpacks.dto.TravelDto;

public interface ITravelService {
    List<TravelDto> getTravels();

    TravelDto getTravelById(int travelId);

    TravelDto getTravelByName(String travelName);

    void createTravel(TravelDto travel);

    void updateTravel(TravelDto travel);

    void deleteTravel(int idTravel);
}
