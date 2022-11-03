package cu.edu.cujae.touristpacks.service.room_type;

import java.util.List;

import cu.edu.cujae.touristpacks.dto.RoomTypeDto;

public interface IRoomTypeService {
    List<RoomTypeDto> getRoomTypes();

    RoomTypeDto getRoomTypeById(int roomTypeId);

    RoomTypeDto getRoomTypeByName(String roomTypeName);

    void createRoomType(RoomTypeDto roomType);

    void updateRoomType(RoomTypeDto roomType);

    void deleteRoomType(int idRoomType);
}
