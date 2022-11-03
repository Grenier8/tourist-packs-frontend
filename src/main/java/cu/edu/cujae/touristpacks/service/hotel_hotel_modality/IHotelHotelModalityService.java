package cu.edu.cujae.touristpacks.service.hotel_hotel_modality;

import java.util.List;

import cu.edu.cujae.touristpacks.dto.HotelHotelModalityDto;

public interface IHotelHotelModalityService {
    List<HotelHotelModalityDto> getHotelHotelModalities();

    HotelHotelModalityDto getHotelHotelModalityById(int hotelHotelModalityId);

    void createHotelHotelModality(HotelHotelModalityDto hotelHotelModality);

    void updateHotelHotelModality(HotelHotelModalityDto hotelHotelModality);

    void deleteHotelHotelModality(int idHotelHotelModality);
}
