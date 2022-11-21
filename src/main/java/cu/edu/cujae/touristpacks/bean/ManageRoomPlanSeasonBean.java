package cu.edu.cujae.touristpacks.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cu.edu.cujae.touristpacks.dto.RoomPlanSeasonDto;
import cu.edu.cujae.touristpacks.service.room_plan_season.IRoomPlanSeasonService;

@Component
@ManagedBean
@ViewScoped
public class ManageRoomPlanSeasonBean {

    private List<RoomPlanSeasonDto> roomPlanSeasons;
    private RoomPlanSeasonDto selectedRoomPlanSeason;

    @Autowired
    private IRoomPlanSeasonService service;

    public ManageRoomPlanSeasonBean() {

    }

    @PostConstruct
    public void init() {
        roomPlanSeasons = service.getRoomPlanSeasons();
    }

    public void openNew() {
        this.selectedRoomPlanSeason = new RoomPlanSeasonDto();
    }

    public void openForEdit() {

    }

    public void saveRoomPlanSeason() {
        if (this.selectedRoomPlanSeason.getIdRoomPlanSeason() == 0) {
            service.createRoomPlanSeason(selectedRoomPlanSeason);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Espanolo insertada"));
        } else {
            service.updateRoomPlanSeason(selectedRoomPlanSeason);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Espanolo modificada"));
        }

        roomPlanSeasons = service.getRoomPlanSeasons();

        PrimeFaces.current().executeScript("PF('manageRoomPlanSeasonDialog').hide()");
        PrimeFaces.current().ajax().update("form:dt-roomPlanSeasons");

    }

    public void deleteRoomPlanSeason() {

        service.deleteRoomPlanSeason(selectedRoomPlanSeason.getIdRoomPlanSeason());
        this.selectedRoomPlanSeason = null;

        roomPlanSeasons = service.getRoomPlanSeasons();

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Espanolo eliminada"));
        PrimeFaces.current().ajax().update("form:messages", "form:dt-roomPlanSeasons");

    }

    public List<RoomPlanSeasonDto> getRoomPlanSeasons() {
        return this.roomPlanSeasons;
    }

    public void setRoomPlanSeasons(List<RoomPlanSeasonDto> roomPlanSeasons) {
        this.roomPlanSeasons = roomPlanSeasons;
    }

    public RoomPlanSeasonDto getSelectedRoomPlanSeason() {
        return this.selectedRoomPlanSeason;
    }

    public void setSelectedRoomPlanSeason(RoomPlanSeasonDto selectedRoomPlanSeason) {
        this.selectedRoomPlanSeason = selectedRoomPlanSeason;
    }

    public IRoomPlanSeasonService getService() {
        return this.service;
    }

    public void setService(IRoomPlanSeasonService service) {
        this.service = service;
    }

}