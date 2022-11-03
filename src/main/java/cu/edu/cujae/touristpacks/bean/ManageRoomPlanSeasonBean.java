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
        roomPlanSeasons = roomPlanSeasons == null ? service.getRoomPlanSeasons() : roomPlanSeasons;
    }

    public void openNew() {
        this.selectedRoomPlanSeason = new RoomPlanSeasonDto();
    }

    public void openForEdit() {

    }

    public void saveRoomPlanSeason() {
        if (this.selectedRoomPlanSeason.getIdRoomPlanSeason() == 0) {
            int r = (int) (Math.random() * 10000);

            this.selectedRoomPlanSeason.setIdRoomPlanSeason(r);
            this.roomPlanSeasons.add(this.selectedRoomPlanSeason);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cambiar insertada"));
        } else {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cambiar modificada"));
        }

        PrimeFaces.current().executeScript("PF('manageRoomPlanSeasonDialog').hide()");
        PrimeFaces.current().ajax().update("form:dt-roomPlanSeasons");

    }

    public void deleteRoomPlanSeason() {

        this.roomPlanSeasons.remove(this.selectedRoomPlanSeason);
        this.selectedRoomPlanSeason = null;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cambiar eliminada"));
        PrimeFaces.current().ajax().update("form:messages", "form:dt-roomPlanSeasons");

    }

    public List<RoomPlanSeasonDto> getRoomPlanSeasons() {
        return roomPlanSeasons;
    }

    public void setRoomPlanSeasons(List<RoomPlanSeasonDto> roomPlanSeasons) {
        this.roomPlanSeasons = roomPlanSeasons;
    }

    public RoomPlanSeasonDto getSelectedRoomPlanSeason() {
        return selectedRoomPlanSeason;
    }

    public void setSelectedRoomPlanSeason(RoomPlanSeasonDto selectedRoomPlanSeason) {
        this.selectedRoomPlanSeason = selectedRoomPlanSeason;
    }

    public IRoomPlanSeasonService getService() {
        return service;
    }

    public void setService(IRoomPlanSeasonService service) {
        this.service = service;
    }
}
