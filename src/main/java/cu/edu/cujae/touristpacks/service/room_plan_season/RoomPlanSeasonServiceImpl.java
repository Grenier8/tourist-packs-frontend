package cu.edu.cujae.touristpacks.service.room_plan_season;

import java.util.ArrayList;
import java.util.List;

import cu.edu.cujae.touristpacks.dto.AlimentaryPlanDto;
import cu.edu.cujae.touristpacks.dto.RoomPlanSeasonDto;
import cu.edu.cujae.touristpacks.dto.RoomTypeDto;
import cu.edu.cujae.touristpacks.dto.SeasonDto;
import cu.edu.cujae.touristpacks.service.alimentary_plan.AlimentaryPlanServiceImpl;
import cu.edu.cujae.touristpacks.service.alimentary_plan.IAlimentaryPlanService;
import cu.edu.cujae.touristpacks.service.room_type.IRoomTypeService;
import cu.edu.cujae.touristpacks.service.room_type.RoomTypeServiceImpl;
import cu.edu.cujae.touristpacks.service.season.ISeasonService;
import cu.edu.cujae.touristpacks.service.season.SeasonServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomPlanSeasonServiceImpl implements IRoomPlanSeasonService {

    @Autowired
    IRoomTypeService roomTypeService;

    @Autowired
    IAlimentaryPlanService alimentaryPlanService;

    @Autowired
    ISeasonService seasonService;

    @Override
    public List<RoomPlanSeasonDto> getRoomPlanSeasons() {
        List<RoomPlanSeasonDto> list = new ArrayList<>();

        RoomTypeDto roomType1 = roomTypeService.getRoomTypes().get(0);
        RoomTypeDto roomType2 = roomTypeService.getRoomTypes().get(1);

        AlimentaryPlanDto alimentaryPlan1 = alimentaryPlanService.getAlimentaryPlans().get(0);
        AlimentaryPlanDto alimentaryPlan2 = alimentaryPlanService.getAlimentaryPlans().get(1);

        SeasonDto season1 = seasonService.getSeasons().get(0);
        SeasonDto season2 = seasonService.getSeasons().get(1);

        list.add(new RoomPlanSeasonDto(1, "RPS1", 12, roomType1, alimentaryPlan1, season1));
        list.add(new RoomPlanSeasonDto(2, "RPS2", 15, roomType2, alimentaryPlan2, season2));

        return list;
    }

    @Override
    public RoomPlanSeasonDto getRoomPlanSeasonById(int roomPlanSeasonId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public RoomPlanSeasonDto getRoomPlanSeasonByName(String roomPlanSeasonName) {
        return getRoomPlanSeasons().stream().filter(r -> r.getRoomPlanSeasonName().equals(roomPlanSeasonName))
                .findFirst().get();
    }

    @Override
    public void createRoomPlanSeason(RoomPlanSeasonDto roomPlanSeason) {
        // TODO Auto-generated method stub

    }

    @Override
    public void updateRoomPlanSeason(RoomPlanSeasonDto roomPlanSeason) {
        // TODO Auto-generated method stub

    }

    @Override
    public void deleteRoomPlanSeason(int idRoomPlanSeason) {
        // TODO Auto-generated method stub

    }

}
