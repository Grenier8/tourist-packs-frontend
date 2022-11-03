package cu.edu.cujae.touristpacks.service.season;

import cu.edu.cujae.touristpacks.dto.SeasonDto;

import java.util.List;

public interface ISeasonService {
    List<SeasonDto> getSeasons();

    SeasonDto getSeasonById(int seasonId);

    SeasonDto getSeasonByName(String seasonName);

    void createSeason(SeasonDto season);

    void updateSeason(SeasonDto season);

    void deleteSeason(int id);
}
