package cu.edu.cujae.touristpacks.service.hotel_modality;

import java.util.List;

import cu.edu.cujae.touristpacks.dto.HotelModalityDto;

public interface IHotelModalityService {
    List<HotelModalityDto> getHotelModalities();

    HotelModalityDto getHotelModalityById(int hotelModalityId);

    HotelModalityDto getHotelModalityByName(String hotelModalityName);

    void createHotelModality(HotelModalityDto hotelModality);

    void updateHotelModality(HotelModalityDto hotelModality);

    void deleteHotelModality(int idHotelModality);
}
