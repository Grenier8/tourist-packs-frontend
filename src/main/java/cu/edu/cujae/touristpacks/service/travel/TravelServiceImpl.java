package cu.edu.cujae.touristpacks.service.travel;

import java.util.ArrayList;
import java.util.List;
import cu.edu.cujae.touristpacks.dto.TmTravelDto;
import cu.edu.cujae.touristpacks.dto.TravelDto;

import org.springframework.stereotype.Service;

@Service
public class TravelServiceImpl implements ITravelService {

    @Override
    public List<TravelDto> getTravels() {
        return null;
    }

    @Override
    public TravelDto getTravelById(int travelId) {
        return getTravels().stream().filter(r -> r.getIdTravel() == travelId).findFirst().get();
    }

    @Override
    public TravelDto getTravelByName(String travelName) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void createTravel(TravelDto travel) {
        // TODO Auto-generated method stub

    }

    @Override
    public void updateTravel(TravelDto travel) {
        // TODO Auto-generated method stub

    }

    @Override
    public void deleteTravel(int idTravel) {
        // TODO Auto-generated method stub

    }

}
