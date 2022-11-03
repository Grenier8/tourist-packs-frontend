package cu.edu.cujae.touristpacks.service.diary_activity_tourist_pack;

import java.util.ArrayList;
import java.util.List;

import cu.edu.cujae.touristpacks.dto.DiaryActivityDto;
import cu.edu.cujae.touristpacks.dto.DiaryActivityTouristPackDto;
import cu.edu.cujae.touristpacks.dto.TouristPackDto;
import cu.edu.cujae.touristpacks.service.diary_activity.DiaryActivityServiceImpl;
import cu.edu.cujae.touristpacks.service.diary_activity.IDiaryActivityService;
import cu.edu.cujae.touristpacks.service.tourist_pack.ITouristPackService;
import cu.edu.cujae.touristpacks.service.tourist_pack.TouristPackServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiaryActivityTouristPackServiceImpl implements IDiaryActivityTouristPackService {

    @Autowired
    IDiaryActivityService diaryActivityService;

    @Autowired
    ITouristPackService touristPackService;

    @Override
    public List<DiaryActivityTouristPackDto> getDiaryActivityTouristPacks() {
        List<DiaryActivityTouristPackDto> list = new ArrayList<>();

        TouristPackDto touristPack1 = touristPackService.getTouristPacks().get(0);
        TouristPackDto touristPack2 = touristPackService.getTouristPacks().get(1);

        DiaryActivityDto diaryActivity1 = diaryActivityService.getDiaryActivities().get(0);
        DiaryActivityDto diaryActivity2 = diaryActivityService.getDiaryActivities().get(1);

        list.add(new DiaryActivityTouristPackDto(1, diaryActivity1, touristPack1));
        list.add(new DiaryActivityTouristPackDto(2, diaryActivity2, touristPack2));

        return list;
    }

    @Override
    public DiaryActivityTouristPackDto getDiaryActivityTouristPackById(int diaryActivityTouristPackId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void createDiaryActivityTouristPack(DiaryActivityTouristPackDto diaryActivityTouristPack) {
        // TODO Auto-generated method stub

    }

    @Override
    public void updateDiaryActivityTouristPack(DiaryActivityTouristPackDto diaryActivityTouristPack) {
        // TODO Auto-generated method stub

    }

    @Override
    public void deleteDiaryActivityTouristPack(int idDiaryActivityTouristPack) {
        // TODO Auto-generated method stub

    }

}
