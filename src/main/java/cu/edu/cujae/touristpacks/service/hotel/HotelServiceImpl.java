package cu.edu.cujae.touristpacks.service.hotel;

import cu.edu.cujae.touristpacks.dto.HotelChainDto;
import cu.edu.cujae.touristpacks.dto.HotelDto;
import cu.edu.cujae.touristpacks.dto.ProvinceDto;
import cu.edu.cujae.touristpacks.service.hotel_chain.HotelChainServiceImpl;
import cu.edu.cujae.touristpacks.service.hotel_chain.IHotelChainService;
import cu.edu.cujae.touristpacks.service.province.IProvinceService;
import cu.edu.cujae.touristpacks.service.province.ProvinceServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HotelServiceImpl implements IHotelService {

    @Autowired
    IHotelChainService hotelChainService;

    @Autowired
    IProvinceService provinceService;

    @Override
    public List<HotelDto> getHotels() {
        List<HotelDto> hotels = new ArrayList<>();

        HotelChainDto hotelChain1 = hotelChainService.getHotelChains().get(0);
        HotelChainDto hotelChain2 = hotelChainService.getHotelChains().get(1);

        ProvinceDto province1 = provinceService.getProvinces().get(0);
        ProvinceDto province2 = provinceService.getProvinces().get(1);

        hotels.add(new HotelDto(1, "Royalton Cayo Santa Maria", "Caibarien Villa Clara, 52600", 5, "042350900",
                "040350950", "cayosantamaria@royalton.com", 5.4, 2, 122, 1, "cayo",
                hotelChain1, province1));
        hotels.add(new HotelDto(2, "Hotel Nacional de Cuba", "Calle 21 y O, 10400", 5, "78363564", "78652558",
                "hotelnacional@grancaribe.com", 16, 0, 426, 2, "ciudad",
                hotelChain2, province2));
        return hotels;
    }

    @Override
    public HotelDto getHotelById(int hotelId) {
        return getHotels().stream().filter(r -> r.getIdHotel() == hotelId).findFirst().get();
    }

    @Override
    public void createHotel(HotelDto hotel) {
        // TODO Auto-generated method stub

    }

    @Override
    public void updateHotel(HotelDto hotel) {
        // TODO Auto-generated method stub

    }

    @Override
    public void deleteHotel(int id) {
        // TODO Auto-generated method stub

    }

    @Override
    public HotelDto getHotelByName(String hotelName) {
        return getHotels().stream().filter(r -> r.getHotelName().equals(hotelName)).findFirst().get();
    }

}
