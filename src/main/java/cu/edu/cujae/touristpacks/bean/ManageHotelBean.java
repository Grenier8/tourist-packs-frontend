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

import cu.edu.cujae.touristpacks.dto.HotelDto;
import cu.edu.cujae.touristpacks.service.hotel.IHotelService;

@Component
@ManagedBean
@ViewScoped
public class ManageHotelBean {

    private List<HotelDto> hotels;
    private HotelDto selectedHotel;

    @Autowired
    private IHotelService service;

    public ManageHotelBean() {

    }

    @PostConstruct
    public void init() {
        hotels = service.getHotels();
    }

    public void openNew() {
        this.selectedHotel = new HotelDto();
    }

    public void openForEdit() {

    }

    public void saveHotel() {
        if (this.selectedHotel.getIdHotel() == 0) {
            service.createHotel(selectedHotel);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Hotel insertado"));
        } else {
            service.updateHotel(selectedHotel);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Hotel modificado"));
        }

        hotels = service.getHotels();

        PrimeFaces.current().executeScript("PF('manageHotelDialog').hide()");
        PrimeFaces.current().ajax().update("form:dt-hotels");

    }

    public void deleteHotel() {

        service.deleteHotel(selectedHotel.getIdHotel());
        this.selectedHotel = null;

        hotels = service.getHotels();

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Hotel eliminado"));
        PrimeFaces.current().ajax().update("form:messages", "form:dt-hotels");

    }

    public List<HotelDto> getHotels() {
        return this.hotels;
    }

    public void setHotels(List<HotelDto> hotels) {
        this.hotels = hotels;
    }

    public HotelDto getSelectedHotel() {
        return this.selectedHotel;
    }

    public void setSelectedHotel(HotelDto selectedHotel) {
        this.selectedHotel = selectedHotel;
    }

    public IHotelService getService() {
        return this.service;
    }

    public void setService(IHotelService service) {
        this.service = service;
    }

}