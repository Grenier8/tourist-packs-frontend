package cu.edu.cujae.touristpacks.service.diary_activity_tourist_pack;

import java.util.List;

import cu.edu.cujae.touristpacks.dto.DiaryActivityTouristPackDto;

public interface IDiaryActivityTouristPackService {
    List<DiaryActivityTouristPackDto> getDiaryActivityTouristPacks();

    DiaryActivityTouristPackDto getDiaryActivityTouristPackById(int diaryActivityTouristPackId);

    void createDiaryActivityTouristPack(DiaryActivityTouristPackDto diaryActivityTouristPack);

    void updateDiaryActivityTouristPack(DiaryActivityTouristPackDto diaryActivityTouristPack);

    void deleteDiaryActivityTouristPack(int idDiaryActivityTouristPack);
}
