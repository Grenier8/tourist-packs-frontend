package cu.edu.cujae.touristpacks.service.hotel;

import cu.edu.cujae.touristpacks.dto.HotelDto;

import java.util.List;

public interface IHotelService {
    List<HotelDto> getHotels();

    HotelDto getHotelById(int hotelId);

    HotelDto getHotelByName(String hotelName);

    void createHotel(HotelDto hotel);

    void updateHotel(HotelDto hotel);

    void deleteHotel(int id);
}
