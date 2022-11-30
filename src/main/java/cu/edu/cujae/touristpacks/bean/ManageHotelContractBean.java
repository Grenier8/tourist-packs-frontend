package cu.edu.cujae.touristpacks.bean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;

import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cu.edu.cujae.touristpacks.dto.HotelContractDto;
import cu.edu.cujae.touristpacks.dto.HotelDto;
import cu.edu.cujae.touristpacks.dto.RoomPlanSeasonDto;
import cu.edu.cujae.touristpacks.service.hotel.IHotelService;
import cu.edu.cujae.touristpacks.service.hotel_contract.IHotelContractService;
import cu.edu.cujae.touristpacks.service.room_plan_season.IRoomPlanSeasonService;
import cu.edu.cujae.touristpacks.utils.JsfUtils;

@Component
@ManagedBean
@ViewScoped
public class ManageHotelContractBean {

    private List<HotelContractDto> hotelContracts;
    private HotelContractDto selectedHotelContract;

    private String selectedHotelName;
    private List<String> selectedRoomPlanSeasonsNames;

    @Autowired
    private IHotelContractService service;

    @Autowired
    private IHotelService hotelService;

    @Autowired
    private IRoomPlanSeasonService roomPlanSeasonService;

    public ManageHotelContractBean() {

    }

    @PostConstruct
    public void init() {
        hotelContracts = service.getHotelContracts();
    }

    public void openNew() {
        this.selectedHotelContract = new HotelContractDto();
    }

    public void openForEdit() {

    }

    public void saveHotelContract() {
        selectedHotelContract.setHotel(hotelService.getHotelByName(selectedHotelName));

        List<RoomPlanSeasonDto> roomPlanSeasons = new ArrayList<>();
        for (String name : selectedRoomPlanSeasonsNames) {
            roomPlanSeasons.add(roomPlanSeasonService.getRoomPlanSeasonByName(name));
        }
        selectedHotelContract.setRoomPlanSeasons(roomPlanSeasons);

        if (this.selectedHotelContract.getIdHotelContract() == 0) {
            service.createHotelContract(selectedHotelContract);

            JsfUtils.addInfoMessageFromBundle("message_inserted_hotel_contract");
        } else {
            service.updateHotelContract(selectedHotelContract);

            JsfUtils.addInfoMessageFromBundle("message_updated_hotel_contract");
        }

        hotelContracts = service.getHotelContracts();

        PrimeFaces.current().executeScript("PF('manageHotelContractDialog').hide()");
        PrimeFaces.current().ajax().update("form:dt-hotelContracts");

    }

    public void deleteHotelContract() {

        service.deleteHotelContract(selectedHotelContract.getIdHotelContract());
        this.selectedHotelContract = null;

        hotelContracts = service.getHotelContracts();

        JsfUtils.addInfoMessageFromBundle("message_deleted_hotel_contract");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-hotelContracts");

    }

    public String getHotelModalitiesNames(HotelContractDto hotelContract) {
        String names = "";
        for (RoomPlanSeasonDto roomPlanSeason : hotelContract.getRoomPlanSeasons()) {
            names += roomPlanSeason.getRoomPlanSeasonName() + ",";
        }
        return names.substring(0, names.length() - 1);
    }

    public List<HotelContractDto> getHotelContracts() {
        return this.hotelContracts;
    }

    public void setHotelContracts(List<HotelContractDto> hotelContracts) {
        this.hotelContracts = hotelContracts;
    }

    public HotelContractDto getSelectedHotelContract() {
        return this.selectedHotelContract;
    }

    public void setSelectedHotelContract(HotelContractDto selectedHotelContract) {
        this.selectedHotelContract = selectedHotelContract;
    }

    public IHotelContractService getService() {
        return this.service;
    }

    public void setService(IHotelContractService service) {
        this.service = service;
    }

    public String getSelectedHotelName() {
        return this.selectedHotelName;
    }

    public void setSelectedHotelName(String selectedHotelName) {
        this.selectedHotelName = selectedHotelName;
    }

    public List<String> getSelectedRoomPlanSeasonsNames() {
        return this.selectedRoomPlanSeasonsNames;
    }

    public void setSelectedRoomPlanSeasonsNames(List<String> selectedRoomPlanSeasonsNames) {
        this.selectedRoomPlanSeasonsNames = selectedRoomPlanSeasonsNames;
    }

    public IHotelService getHotelService() {
        return this.hotelService;
    }

    public void setHotelService(IHotelService hotelService) {
        this.hotelService = hotelService;
    }

    public IRoomPlanSeasonService getRoomPlanSeasonService() {
        return this.roomPlanSeasonService;
    }

    public void setRoomPlanSeasonService(IRoomPlanSeasonService roomPlanSeasonService) {
        this.roomPlanSeasonService = roomPlanSeasonService;
    }

}