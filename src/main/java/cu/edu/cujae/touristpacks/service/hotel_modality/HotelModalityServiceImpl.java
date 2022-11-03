package cu.edu.cujae.touristpacks.service.hotel_modality;

import java.util.ArrayList;
import java.util.List;

import cu.edu.cujae.touristpacks.dto.HotelModalityDto;

import org.springframework.stereotype.Service;

@Service
public class HotelModalityServiceImpl implements IHotelModalityService {

    @Override
    public List<HotelModalityDto> getHotelModalities() {
        List<HotelModalityDto> list = new ArrayList<>();

        list.add(new HotelModalityDto(1, "Mod1"));
        list.add(new HotelModalityDto(2, "Mod2"));

        return list;
    }

    @Override
    public HotelModalityDto getHotelModalityById(int hotelModalityId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public HotelModalityDto getHotelModalityByName(String hotelModalityName) {
        return getHotelModalities().stream().filter(r -> r.getHotelModalityName().equals(hotelModalityName)).findFirst()
                .get();
    }

    @Override
    public void createHotelModality(HotelModalityDto hotelModality) {
        // TODO Auto-generated method stub

    }

    @Override
    public void updateHotelModality(HotelModalityDto hotelModality) {
        // TODO Auto-generated method stub

    }

    @Override
    public void deleteHotelModality(int idHotelModality) {
        // TODO Auto-generated method stub

    }

}
