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

import cu.edu.cujae.touristpacks.dto.HotelModalityDto;
import cu.edu.cujae.touristpacks.service.hotel_modality.IHotelModalityService;

@Component
@ManagedBean
@ViewScoped
public class ManageHotelModalityBean {

    private List<HotelModalityDto> hotelModalities;
    private HotelModalityDto selectedHotelModality;

    @Autowired
    private IHotelModalityService service;

    public ManageHotelModalityBean() {

    }

    @PostConstruct
    public void init() {
        hotelModalities = hotelModalities == null ? service.getHotelModalities() : hotelModalities;
    }

    public void openNew() {
        this.selectedHotelModality = new HotelModalityDto();
    }

    public void openForEdit() {

    }

    public void saveHotelModality() {
        if (this.selectedHotelModality.getIdHotelModality() == 0) {
            int r = (int) (Math.random() * 10000);

            this.selectedHotelModality.setIdHotelModality(r);
            this.hotelModalities.add(this.selectedHotelModality);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cambiar insertada"));
        } else {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cambiar modificada"));
        }

        PrimeFaces.current().executeScript("PF('manageHotelModalityDialog').hide()");
        PrimeFaces.current().ajax().update("form:dt-hotelModalities");

    }

    public void deleteHotelModality() {

        this.hotelModalities.remove(this.selectedHotelModality);
        this.selectedHotelModality = null;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cambiar eliminada"));
        PrimeFaces.current().ajax().update("form:messages", "form:dt-hotelModalities");

    }

    public List<HotelModalityDto> getHotelModalities() {
        return hotelModalities;
    }

    public void setHotelModalities(List<HotelModalityDto> hotelModalities) {
        this.hotelModalities = hotelModalities;
    }

    public HotelModalityDto getSelectedHotelModality() {
        return selectedHotelModality;
    }

    public void setSelectedHotelModality(HotelModalityDto selectedHotelModality) {
        this.selectedHotelModality = selectedHotelModality;
    }

    public IHotelModalityService getService() {
        return service;
    }

    public void setService(IHotelModalityService service) {
        this.service = service;
    }
}
