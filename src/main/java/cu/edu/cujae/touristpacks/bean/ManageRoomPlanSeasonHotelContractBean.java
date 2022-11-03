package cu.edu.cujae.touristpacks.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;

import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cu.edu.cujae.touristpacks.dto.HotelContractDto;
import cu.edu.cujae.touristpacks.dto.RoomPlanSeasonDto;
import cu.edu.cujae.touristpacks.dto.RoomPlanSeasonHotelContractDto;
import cu.edu.cujae.touristpacks.service.hotel_contract.IHotelContractService;
import cu.edu.cujae.touristpacks.service.room_plan_season.IRoomPlanSeasonService;
import cu.edu.cujae.touristpacks.service.room_plan_season_hotel_contract.IRoomPlanSeasonHotelContractService;

@Component
@ManagedBean
@ViewScoped
public class ManageRoomPlanSeasonHotelContractBean {

    private List<RoomPlanSeasonHotelContractDto> roomPlanSeasonHotelContracts;
    private RoomPlanSeasonHotelContractDto selectedRoomPlanSeasonHotelContract;
    private String selectedRoomPlanSeasonName;
    private String selectedHotelContractTitle;

    @Autowired
    private IRoomPlanSeasonHotelContractService service;

    @Autowired
    private IRoomPlanSeasonService roomPlanSeasonService;

    @Autowired
    private IHotelContractService hotelContractService;

    public ManageRoomPlanSeasonHotelContractBean() {
    }

    @PostConstruct
    public void init() {
        roomPlanSeasonHotelContracts = roomPlanSeasonHotelContracts == null ? service.getRoomPlanSeasonHotelContracts()
                : roomPlanSeasonHotelContracts;
    }

    public void openNew() {
        this.selectedRoomPlanSeasonHotelContract = new RoomPlanSeasonHotelContractDto();
    }

    public void openForEdit() {
        // TODO document why this method is empty
    }

    public void saveRoomPlanSeasonHotelContract() {
        if (this.selectedRoomPlanSeasonHotelContract.getIdRoomPlanSeasonHotelContract() == 0) {
            int r = (int) (Math.random() * 10000);
            this.selectedRoomPlanSeasonHotelContract.setIdRoomPlanSeasonHotelContract(r);

            RoomPlanSeasonDto roomPlanSeason = roomPlanSeasonService
                    .getRoomPlanSeasonByName(selectedRoomPlanSeasonName);
            this.selectedRoomPlanSeasonHotelContract.setRoomPlanSeason(roomPlanSeason);

            HotelContractDto hotelContract = hotelContractService.getHotelContractByName(selectedHotelContractTitle);
            this.selectedRoomPlanSeasonHotelContract.setHotelContract(hotelContract);

            this.roomPlanSeasonHotelContracts.add(this.selectedRoomPlanSeasonHotelContract);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cambiar insertada"));
        } else {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cambiar modificada"));
        }

        PrimeFaces.current().executeScript("PF('manageRoomPlanSeasonHotelContractDialog').hide()");
        PrimeFaces.current().ajax().update("form:dt-roomPlanSeasonHotelContracts");

    }

    public void deleteRoomPlanSeasonHotelContract() {

        this.roomPlanSeasonHotelContracts.remove(this.selectedRoomPlanSeasonHotelContract);
        this.selectedRoomPlanSeasonHotelContract = null;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cambiar eliminada"));
        PrimeFaces.current().ajax().update("form:messages", "form:dt-roomPlanSeasonHotelContracts");

    }

    public List<RoomPlanSeasonHotelContractDto> getRoomPlanSeasonHotelContracts() {
        return roomPlanSeasonHotelContracts;
    }

    public void setRoomPlanSeasonHotelContracts(List<RoomPlanSeasonHotelContractDto> roomPlanSeasonHotelContracts) {
        this.roomPlanSeasonHotelContracts = roomPlanSeasonHotelContracts;
    }

    public RoomPlanSeasonHotelContractDto getSelectedRoomPlanSeasonHotelContract() {
        return selectedRoomPlanSeasonHotelContract;
    }

    public void setSelectedRoomPlanSeasonHotelContract(
            RoomPlanSeasonHotelContractDto selectedRoomPlanSeasonHotelContract) {
        this.selectedRoomPlanSeasonHotelContract = selectedRoomPlanSeasonHotelContract;
    }

    public IRoomPlanSeasonHotelContractService getService() {
        return service;
    }

    public void setService(IRoomPlanSeasonHotelContractService service) {
        this.service = service;
    }

    public String getSelectedRoomPlanSeasonName() {
        return this.selectedRoomPlanSeasonName;
    }

    public void setSelectedRoomPlanSeasonName(String selectedRoomPlanSeasonName) {
        this.selectedRoomPlanSeasonName = selectedRoomPlanSeasonName;
    }

    public String getSelectedHotelContractTitle() {
        return this.selectedHotelContractTitle;
    }

    public void setSelectedHotelContractTitle(String selectedHotelContractTitle) {
        this.selectedHotelContractTitle = selectedHotelContractTitle;
    }

    public IRoomPlanSeasonService getRoomPlanSeasonService() {
        return this.roomPlanSeasonService;
    }

    public void setRoomPlanSeasonService(IRoomPlanSeasonService roomPlanSeasonService) {
        this.roomPlanSeasonService = roomPlanSeasonService;
    }

    public IHotelContractService getHotelContractService() {
        return this.hotelContractService;
    }

    public void setHotelContractService(IHotelContractService hotelContractService) {
        this.hotelContractService = hotelContractService;
    }

}
