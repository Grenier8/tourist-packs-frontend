package cu.edu.cujae.touristpacks.service.tourist_pack;

import java.util.List;

import cu.edu.cujae.touristpacks.dto.TouristPackDto;

public interface ITouristPackService {
    List<TouristPackDto> getTouristPacks();

    TouristPackDto getTouristPackById(int touristPackId);

    TouristPackDto getTouristPackByName(String touristPackName);

    void createTouristPack(TouristPackDto touristPack);

    void updateTouristPack(TouristPackDto touristPack);

    void deleteTouristPack(int idTouristPack);
}
