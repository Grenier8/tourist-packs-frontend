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
        roomTypes = service.getRoomTypes();
    }

    public void openNew() {
        this.selectedRoomType = new RoomTypeDto();
    }

    public void openForEdit() {

    }

    public void saveRoomType() {
        if (this.selectedRoomType.getIdRoomType() == 0) {
            service.createRoomType(selectedRoomType);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Espanolo insertada"));
        } else {
            service.updateRoomType(selectedRoomType);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Espanolo modificada"));
        }

        roomTypes = service.getRoomTypes();

        PrimeFaces.current().executeScript("PF('manageRoomTypeDialog').hide()");
        PrimeFaces.current().ajax().update("form:dt-roomTypes");

    }

    public void deleteRoomType() {

        service.deleteRoomType(selectedRoomType.getIdRoomType());
        this.selectedRoomType = null;

        roomTypes = service.getRoomTypes();

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Espanolo eliminada"));
        PrimeFaces.current().ajax().update("form:messages", "form:dt-roomTypes");

    }

    public List<RoomTypeDto> getRoomTypes() {
        return this.roomTypes;
    }

    public void setRoomTypes(List<RoomTypeDto> roomTypes) {
        this.roomTypes = roomTypes;
    }

    public RoomTypeDto getSelectedRoomType() {
        return this.selectedRoomType;
    }

    public void setSelectedRoomType(RoomTypeDto selectedRoomType) {
        this.selectedRoomType = selectedRoomType;
    }

    public IRoomTypeService getService() {
        return this.service;
    }

    public void setService(IRoomTypeService service) {
        this.service = service;
    }

}