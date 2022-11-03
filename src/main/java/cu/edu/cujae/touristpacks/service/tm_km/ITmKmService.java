package cu.edu.cujae.touristpacks.service.tm_km;

import java.util.List;

import cu.edu.cujae.touristpacks.dto.TmKmDto;

public interface ITmKmService {
    List<TmKmDto> getTmKms();

    TmKmDto getTmKmById(int tmKmId);

    TmKmDto getTmKmByName(String tmKmName);

    void createTmKm(TmKmDto tmKm);

    void updateTmKm(TmKmDto tmKm);

    void deleteTmKm(int idTmKm);
}
