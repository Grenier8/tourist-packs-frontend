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
import cu.edu.cujae.touristpacks.service.alimentary_plan.IAlimentaryPlanService;
import cu.edu.cujae.touristpacks.service.room_plan_season.IRoomPlanSeasonService;
import cu.edu.cujae.touristpacks.service.room_type.IRoomTypeService;
import cu.edu.cujae.touristpacks.service.season.ISeasonService;
import cu.edu.cujae.touristpacks.utils.JsfUtils;

@Component
@ManagedBean
@ViewScoped
public class ManageRoomPlanSeasonBean {

    private List<RoomPlanSeasonDto> roomPlanSeasons;
    private RoomPlanSeasonDto selectedRoomPlanSeason;

    private String selectedRoomTypeName;
    private String selectedAlimentaryPlanName;
    private String selectedSeasonName;

    @Autowired
    private IRoomTypeService roomTypeService;

    @Autowired
    private IAlimentaryPlanService alimentaryPlanService;

    @Autowired
    private ISeasonService seasonService;

    @Autowired
    private IRoomPlanSeasonService service;

    public ManageRoomPlanSeasonBean() {

    }

    public void openNew() {
        this.selectedRoomPlanSeason = new RoomPlanSeasonDto();
    }

    public void openForEdit() {

    }

    public void saveRoomPlanSeason() {
        selectedRoomPlanSeason.setRoomType(roomTypeService.getRoomTypeByName(selectedRoomTypeName));
        selectedRoomPlanSeason
                .setAlimentaryPlan(alimentaryPlanService.getAlimentaryPlanByName(selectedAlimentaryPlanName));
        selectedRoomPlanSeason.setSeason(seasonService.getSeasonByName(selectedSeasonName));

        if (this.selectedRoomPlanSeason.getIdRoomPlanSeason() == 0) {
            service.createRoomPlanSeason(selectedRoomPlanSeason);

            JsfUtils.addInfoMessageFromBundle("message_inserted_room_plan_season");
        } else {
            service.updateRoomPlanSeason(selectedRoomPlanSeason);

            JsfUtils.addInfoMessageFromBundle("message_updated_room_plan_season");
        }

        roomPlanSeasons = service.getRoomPlanSeasons();

        PrimeFaces.current().executeScript("PF('manageRoomPlanSeasonDialog').hide()");
        PrimeFaces.current().ajax().update("form:dt-roomPlanSeasons");

    }

    public void deleteRoomPlanSeason() {

        service.deleteRoomPlanSeason(selectedRoomPlanSeason.getIdRoomPlanSeason());
        this.selectedRoomPlanSeason = null;

        roomPlanSeasons = service.getRoomPlanSeasons();

        JsfUtils.addInfoMessageFromBundle("message_deleted_room_plan_season");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-roomPlanSeasons");

    }

    public List<RoomPlanSeasonDto> getRoomPlanSeasons() {
        roomPlanSeasons = service.getRoomPlanSeasons();
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

    public String getSelectedRoomTypeName() {
        return this.selectedRoomTypeName;
    }

    public void setSelectedRoomTypeName(String selectedRoomTypeName) {
        this.selectedRoomTypeName = selectedRoomTypeName;
    }

    public String getSelectedAlimentaryPlanName() {
        return this.selectedAlimentaryPlanName;
    }

    public void setSelectedAlimentaryPlanName(String selectedAlimentaryPlanName) {
        this.selectedAlimentaryPlanName = selectedAlimentaryPlanName;
    }

    public String getSelectedSeasonName() {
        return this.selectedSeasonName;
    }

    public void setSelectedSeasonName(String selectedSeasonName) {
        this.selectedSeasonName = selectedSeasonName;
    }

    public IRoomTypeService getRoomTypeService() {
        return this.roomTypeService;
    }

    public void setRoomTypeService(IRoomTypeService roomTypeService) {
        this.roomTypeService = roomTypeService;
    }

    public IAlimentaryPlanService getAlimentaryPlanService() {
        return this.alimentaryPlanService;
    }

    public void setAlimentaryPlanService(IAlimentaryPlanService alimentaryPlanService) {
        this.alimentaryPlanService = alimentaryPlanService;
    }

    public ISeasonService getSeasonService() {
        return this.seasonService;
    }

    public void setSeasonService(ISeasonService seasonService) {
        this.seasonService = seasonService;
    }

}