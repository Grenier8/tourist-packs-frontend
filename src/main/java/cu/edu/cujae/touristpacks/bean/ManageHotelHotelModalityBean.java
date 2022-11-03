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
import cu.edu.cujae.touristpacks.dto.HotelHotelModalityDto;
import cu.edu.cujae.touristpacks.dto.HotelModalityDto;
import cu.edu.cujae.touristpacks.service.hotel.IHotelService;
import cu.edu.cujae.touristpacks.service.hotel_hotel_modality.IHotelHotelModalityService;
import cu.edu.cujae.touristpacks.service.hotel_modality.IHotelModalityService;

@Component
@ManagedBean
@ViewScoped
public class ManageHotelHotelModalityBean {

    private List<HotelHotelModalityDto> hotelHotelModalities;
    private HotelHotelModalityDto selectedHotelHotelModality;
    private String selectedHotelName;
    private String selectedHotelModalityName;

    @Autowired
    private IHotelHotelModalityService service;

    @Autowired
    private IHotelService hotelService;

    @Autowired
    private IHotelModalityService hotelModalityService;

    public ManageHotelHotelModalityBean() {

    }

    @PostConstruct
    public void init() {
        hotelHotelModalities = hotelHotelModalities == null ? service.getHotelHotelModalities() : hotelHotelModalities;
    }

    public void openNew() {
        this.selectedHotelHotelModality = new HotelHotelModalityDto();
    }

    public void openForEdit() {

    }

    public void saveHotelHotelModality() {
        if (this.selectedHotelHotelModality.getIdHotelHotelModality() == 0) {
            int r = (int) (Math.random() * 10000);
            this.selectedHotelHotelModality.setIdHotelHotelModality(r);

            HotelDto hotel = hotelService.getHotelByName(selectedHotelName);
            this.selectedHotelHotelModality.setHotel(hotel);

            HotelModalityDto hotelModality = hotelModalityService.getHotelModalityByName(selectedHotelModalityName);
            this.selectedHotelHotelModality.setHotelModality(hotelModality);

            this.hotelHotelModalities.add(this.selectedHotelHotelModality);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cambiar insertada"));
        } else {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cambiar modificada"));
        }

        PrimeFaces.current().executeScript("PF('manageHotelHotelModalityDialog').hide()");
        PrimeFaces.current().ajax().update("form:dt-hotelHotelModalities");

    }

    public void deleteHotelHotelModality() {

        this.hotelHotelModalities.remove(this.selectedHotelHotelModality);
        this.selectedHotelHotelModality = null;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cambiar eliminada"));
        PrimeFaces.current().ajax().update("form:messages", "form:dt-hotelHotelModalities");

    }

    public List<HotelHotelModalityDto> getHotelHotelModalitys() {
        return hotelHotelModalities;
    }

    public void setSelectedHotelHotelModality(HotelHotelModalityDto selectedHotelHotelModality) {
        this.selectedHotelHotelModality = selectedHotelHotelModality;
    }

    public IHotelHotelModalityService getService() {
        return service;
    }

    public void setService(IHotelHotelModalityService service) {
        this.service = service;
    }

    public List<HotelHotelModalityDto> getHotelHotelModalities() {
        return this.hotelHotelModalities;
    }

    public void setHotelHotelModalities(List<HotelHotelModalityDto> hotelHotelModalities) {
        this.hotelHotelModalities = hotelHotelModalities;
    }

    public HotelHotelModalityDto getSelectedHotelHotelModality() {
        return this.selectedHotelHotelModality;
    }

    public String getSelectedHotelName() {
        return this.selectedHotelName;
    }

    public void setSelectedHotelName(String selectedHotelName) {
        this.selectedHotelName = selectedHotelName;
    }

    public String getSelectedHotelModalityName() {
        return this.selectedHotelModalityName;
    }

    public void setSelectedHotelModalityName(String selectedHotelModalityName) {
        this.selectedHotelModalityName = selectedHotelModalityName;
    }

    public IHotelModalityService getHotelModalityService() {
        return this.hotelModalityService;
    }

    public void setHotelModalityService(IHotelModalityService hotelModalityService) {
        this.hotelModalityService = hotelModalityService;
    }

}
