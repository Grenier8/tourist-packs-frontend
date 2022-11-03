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

import cu.edu.cujae.touristpacks.dto.HotelDto;
import cu.edu.cujae.touristpacks.dto.RoomPlanSeasonDto;
import cu.edu.cujae.touristpacks.dto.TouristPackDto;
import cu.edu.cujae.touristpacks.service.hotel.IHotelService;
import cu.edu.cujae.touristpacks.service.room_plan_season.IRoomPlanSeasonService;
import cu.edu.cujae.touristpacks.service.tourist_pack.ITouristPackService;

@Component
@ManagedBean
@ViewScoped
public class ManageTouristPackBean {

    private List<TouristPackDto> touristPacks;
    private TouristPackDto selectedTouristPack;
    private String selectedHotelName;
    private String selectedRoomPlanSeasonName;

    @Autowired
    private ITouristPackService service;

    @Autowired
    private IHotelService hotelService;

    @Autowired
    private IRoomPlanSeasonService roomPlanSeasonService;

    public ManageTouristPackBean() {

    }

    @PostConstruct
    public void init() {
        touristPacks = touristPacks == null ? service.getTouristPacks() : touristPacks;
    }

    public void openNew() {
        this.selectedTouristPack = new TouristPackDto();
    }

    public void openForEdit() {

    }

    public void saveTouristPack() {
        if (this.selectedTouristPack.getIdTouristPack() == 0) {
            int r = (int) (Math.random() * 10000);
            this.selectedTouristPack.setIdTouristPack(r);

            HotelDto hotel = hotelService.getHotelByName(selectedHotelName);
            this.selectedTouristPack.setHotel(hotel);

            RoomPlanSeasonDto roomPlanSeason = roomPlanSeasonService
                    .getRoomPlanSeasonByName(selectedRoomPlanSeasonName);
            this.selectedTouristPack.setRoomPlanSeason(roomPlanSeason);

            this.touristPacks.add(this.selectedTouristPack);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cambiar insertada"));
        } else {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cambiar modificada"));
        }

        PrimeFaces.current().executeScript("PF('manageTouristPackDialog').hide()");
        PrimeFaces.current().ajax().update("form:dt-touristPacks");

    }

    public void deleteTouristPack() {

        this.touristPacks.remove(this.selectedTouristPack);
        this.selectedTouristPack = null;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cambiar eliminada"));
        PrimeFaces.current().ajax().update("form:messages", "form:dt-touristPacks");

    }

    public List<TouristPackDto> getTouristPacks() {
        return touristPacks;
    }

    public void setTouristPacks(List<TouristPackDto> touristPacks) {
        this.touristPacks = touristPacks;
    }

    public TouristPackDto getSelectedTouristPack() {
        return selectedTouristPack;
    }

    public void setSelectedTouristPack(TouristPackDto selectedTouristPack) {
        this.selectedTouristPack = selectedTouristPack;
    }

    public ITouristPackService getService() {
        return service;
    }

    public void setService(ITouristPackService service) {
        this.service = service;
    }

    public String getSelectedHotelName() {
        return this.selectedHotelName;
    }

    public void setSelectedHotelName(String selectedHotelName) {
        this.selectedHotelName = selectedHotelName;
    }

    public String getSelectedRoomPlanSeasonName() {
        return this.selectedRoomPlanSeasonName;
    }

    public void setSelectedRoomPlanSeasonName(String selectedRoomPlanSeasonName) {
        this.selectedRoomPlanSeasonName = selectedRoomPlanSeasonName;
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
