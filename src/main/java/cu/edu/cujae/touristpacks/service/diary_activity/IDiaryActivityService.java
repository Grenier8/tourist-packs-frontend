package cu.edu.cujae.touristpacks.service.diary_activity;

import java.util.List;

import cu.edu.cujae.touristpacks.dto.DiaryActivityDto;

public interface IDiaryActivityService {
    List<DiaryActivityDto> getDiaryActivities();

    DiaryActivityDto getDiaryActivityById(int diaryActivityId);

    DiaryActivityDto getDiaryActivityByName(String diaryActivityName);

    void createDiaryActivity(DiaryActivityDto diaryActivity);

    void updateDiaryActivity(DiaryActivityDto diaryActivity);

    void deleteDiaryActivity(int idDiaryActivity);
}
