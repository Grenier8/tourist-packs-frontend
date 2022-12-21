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
import cu.edu.cujae.touristpacks.utils.JsfUtils;

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

    public void openNew() {
        this.selectedRoomType = new RoomTypeDto();
    }

    public void openForEdit() {

    }

    public void saveRoomType() {
        if (this.selectedRoomType.getIdRoomType() == 0) {
            service.createRoomType(selectedRoomType);

            JsfUtils.addInfoMessageFromBundle("message_inserted_room_type");
        } else {
            service.updateRoomType(selectedRoomType);

            JsfUtils.addInfoMessageFromBundle("message_updated_room_type");
        }

        roomTypes = service.getRoomTypes();

        PrimeFaces.current().executeScript("PF('manageRoomTypeDialog').hide()");
        PrimeFaces.current().ajax().update("form:dt-roomTypes");

    }

    public void deleteRoomType() {

        service.deleteRoomType(selectedRoomType.getIdRoomType());
        this.selectedRoomType = null;

        roomTypes = service.getRoomTypes();

        JsfUtils.addInfoMessageFromBundle("message_deleted_room_type");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-roomTypes");

    }

    public List<RoomTypeDto> getRoomTypes() {
        roomTypes = service.getRoomTypes();
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