package cu.edu.cujae.touristpacks.service.hotel_chain;

import cu.edu.cujae.touristpacks.dto.HotelChainDto;

import java.util.List;

public interface IHotelChainService {
    List<HotelChainDto> getHotelChains();

    HotelChainDto getHotelChainById(int hotelChainId);

    HotelChainDto getHotelChainByName(String hotelChainName);

    void createHotelChain(HotelChainDto hotelChain);

    void updateHotelChain(HotelChainDto hotelChain);

    void deleteHotelChain(int id);
}
