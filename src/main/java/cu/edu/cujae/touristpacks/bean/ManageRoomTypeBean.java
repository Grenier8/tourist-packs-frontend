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

import cu.edu.cujae.touristpacks.dto.RoomTypeDto;
import cu.edu.cujae.touristpacks.service.room_type.IRoomTypeService;

@Component
@ManagedBean
@ViewScoped
public class ManageRoomTypeBean {

    private List<RoomTypeDto> roomTypes;
    private RoomTypeDto selectedRoomType;

    @Autowired
    private IRoomTypeService service;

    public ManageRoomTypeBean() {

    }

    @PostConstruct
    public void init() {
        roomTypes = roomTypes == null ? service.getRoomTypes() : roomTypes;
    }

    public void openNew() {
        this.selectedRoomType = new RoomTypeDto();
    }

    public void openForEdit() {

    }

    public void saveRoomType() {
        if (this.selectedRoomType.getIdRoomType() == 0) {
            int r = (int) (Math.random() * 10000);

            this.selectedRoomType.setIdRoomType(r);
            this.roomTypes.add(this.selectedRoomType);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cambiar insertada"));
        } else {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cambiar modificada"));
        }

        PrimeFaces.current().executeScript("PF('manageRoomTypeDialog').hide()");
        PrimeFaces.current().ajax().update("form:dt-roomTypes");

    }

    public void deleteRoomType() {

        this.roomTypes.remove(this.selectedRoomType);
        this.selectedRoomType = null;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cambiar eliminada"));
        PrimeFaces.current().ajax().update("form:messages", "form:dt-roomTypes");

    }

    public List<RoomTypeDto> getRoomTypes() {
        return roomTypes;
    }

    public void setRoomTypes(List<RoomTypeDto> roomTypes) {
        this.roomTypes = roomTypes;
    }

    public RoomTypeDto getSelectedRoomType() {
        return selectedRoomType;
    }

    public void setSelectedRoomType(RoomTypeDto selectedRoomType) {
        this.selectedRoomType = selectedRoomType;
    }

    public IRoomTypeService getService() {
        return service;
    }

    public void setService(IRoomTypeService service) {
        this.service = service;
    }
}
