package cu.edu.cujae.touristpacks.service.hotel_contract;

import java.util.List;

import cu.edu.cujae.touristpacks.dto.HotelContractDto;

public interface IHotelContractService {
    List<HotelContractDto> getHotelContracts();

    HotelContractDto getHotelContractById(int hotelContractId);

    HotelContractDto getHotelContractByTitle(String hotelContractName);

    void createHotelContract(HotelContractDto hotelContract);

    void updateHotelContract(HotelContractDto hotelContract);

    void deleteHotelContract(int idHotelContract);
}
