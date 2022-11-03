package cu.edu.cujae.touristpacks.service.hotel_hotel_modality;

import java.util.ArrayList;
import java.util.List;

import cu.edu.cujae.touristpacks.dto.HotelDto;
import cu.edu.cujae.touristpacks.dto.HotelHotelModalityDto;
import cu.edu.cujae.touristpacks.dto.HotelModalityDto;
import cu.edu.cujae.touristpacks.service.hotel.IHotelService;
import cu.edu.cujae.touristpacks.service.hotel_modality.IHotelModalityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HotelHotelModalityServiceImpl implements IHotelHotelModalityService {

    @Autowired
    IHotelService hotelService;

    @Autowired
    IHotelModalityService hotelModalityService;

    @Override
    public List<HotelHotelModalityDto> getHotelHotelModalities() {
        List<HotelHotelModalityDto> list = new ArrayList<>();

        HotelDto hotel1 = hotelService.getHotels().get(0);
        HotelDto hotel2 = hotelService.getHotels().get(1);

        HotelModalityDto hotelModality1 = hotelModalityService.getHotelModalities().get(0);
        HotelModalityDto hotelModality2 = hotelModalityService.getHotelModalities().get(1);

        list.add(new HotelHotelModalityDto(1, hotel1, hotelModality1));
        list.add(new HotelHotelModalityDto(2, hotel2, hotelModality2));

        return list;
    }

    @Override
    public HotelHotelModalityDto getHotelHotelModalityById(int hotelHotelModalityId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void createHotelHotelModality(HotelHotelModalityDto hotelHotelModality) {
        // TODO Auto-generated method stub

    }

    @Override
    public void updateHotelHotelModality(HotelHotelModalityDto hotelHotelModality) {
        // TODO Auto-generated method stub

    }

    @Override
    public void deleteHotelHotelModality(int idHotelHotelModality) {
        // TODO Auto-generated method stub

    }

}
