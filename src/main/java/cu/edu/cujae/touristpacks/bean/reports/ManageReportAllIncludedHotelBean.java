package cu.edu.cujae.touristpacks.bean.reports;

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
import cu.edu.cujae.touristpacks.utils.JsfUtils;

@Component
@ManagedBean
@ViewScoped
public class ManageReportAllIncludedHotelBean {

    private List<HotelDto> hotels;
    private HotelDto selectedHotel;

    @Autowired
    private IHotelService service;

    public ManageReportAllIncludedHotelBean() {

    }

    public List<HotelDto> getHotels() {
        hotels = service.getAllIncludedHotels();
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