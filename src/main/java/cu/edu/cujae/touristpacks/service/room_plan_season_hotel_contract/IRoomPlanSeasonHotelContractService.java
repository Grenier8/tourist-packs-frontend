package cu.edu.cujae.touristpacks.service.room_plan_season_hotel_contract;

import java.util.List;

import cu.edu.cujae.touristpacks.dto.RoomPlanSeasonHotelContractDto;

public interface IRoomPlanSeasonHotelContractService {
    List<RoomPlanSeasonHotelContractDto> getRoomPlanSeasonHotelContracts();

    RoomPlanSeasonHotelContractDto getRoomPlanSeasonHotelContractById(int roomPlanSeasonHotelContractId);

    void createRoomPlanSeasonHotelContract(RoomPlanSeasonHotelContractDto roomPlanSeasonHotelContract);

    void updateRoomPlanSeasonHotelContract(RoomPlanSeasonHotelContractDto roomPlanSeasonHotelContract);

    void deleteRoomPlanSeasonHotelContract(int idRoomPlanSeasonHotelContract);
}
