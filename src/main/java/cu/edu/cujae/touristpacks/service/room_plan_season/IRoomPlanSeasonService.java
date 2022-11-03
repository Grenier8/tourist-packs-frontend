package cu.edu.cujae.touristpacks.service.room_plan_season;

import java.util.List;

import cu.edu.cujae.touristpacks.dto.RoomPlanSeasonDto;

public interface IRoomPlanSeasonService {
    List<RoomPlanSeasonDto> getRoomPlanSeasons();

    RoomPlanSeasonDto getRoomPlanSeasonById(int roomPlanSeasonId);

    RoomPlanSeasonDto getRoomPlanSeasonByName(String roomPlanSeasonName);

    void createRoomPlanSeason(RoomPlanSeasonDto roomPlanSeason);

    void updateRoomPlanSeason(RoomPlanSeasonDto roomPlanSeason);

    void deleteRoomPlanSeason(int idRoomPlanSeason);
}
