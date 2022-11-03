package cu.edu.cujae.touristpacks.service.tm_hour_km;

import java.util.List;

import cu.edu.cujae.touristpacks.dto.TmHourKmDto;

public interface ITmHourKmService {
    List<TmHourKmDto> getTmHourKms();

    TmHourKmDto getTmHourKmById(int tmHourKmId);

    TmHourKmDto getTmHourKmByName(String tmHourKmName);

    void createTmHourKm(TmHourKmDto tmHourKm);

    void updateTmHourKm(TmHourKmDto tmHourKm);

    void deleteTmHourKm(int idTmHourKm);
}
