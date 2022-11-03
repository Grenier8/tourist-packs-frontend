package cu.edu.cujae.touristpacks.service.tm_travel;

import java.util.List;

import cu.edu.cujae.touristpacks.dto.TmTravelDto;

public interface ITmTravelService {
    List<TmTravelDto> getTmTravels();

    TmTravelDto getTmTravelById(int tmTravelId);

    TmTravelDto getTmTravelByName(String tmTravelName);

    void createTmTravel(TmTravelDto tmTravel);

    void updateTmTravel(TmTravelDto tmTravel);

    void deleteTmTravel(int idTmTravel);
}
