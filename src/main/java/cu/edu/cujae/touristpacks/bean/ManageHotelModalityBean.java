package cu.edu.cujae.touristpacks.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;

import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cu.edu.cujae.touristpacks.dto.HotelModalityDto;
import cu.edu.cujae.touristpacks.service.hotel_modality.IHotelModalityService;
import cu.edu.cujae.touristpacks.utils.JsfUtils;

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
        hotelModalities = service.getHotelModalities();
    }

    public void openNew() {
        this.selectedHotelModality = new HotelModalityDto();
    }

    public void openForEdit() {

    }

    public void saveHotelModality() {
        if (this.selectedHotelModality.getIdHotelModality() == 0) {
            service.createHotelModality(selectedHotelModality);

            JsfUtils.addInfoMessageFromBundle("message_inserted_hotel_modality");
        } else {
            service.updateHotelModality(selectedHotelModality);

            JsfUtils.addInfoMessageFromBundle("message_updated_hotel_modality");
        }

        hotelModalities = service.getHotelModalities();

        PrimeFaces.current().executeScript("PF('manageHotelModalityDialog').hide()");
        PrimeFaces.current().ajax().update("form:dt-hotelModalities");

    }

    public void deleteHotelModality() {

        service.deleteHotelModality(selectedHotelModality.getIdHotelModality());
        this.selectedHotelModality = null;

        hotelModalities = service.getHotelModalities();

        JsfUtils.addInfoMessageFromBundle("message_deleted_hotel_modality");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-hotelModalities");

    }

    public List<HotelModalityDto> getHotelModalities() {
        return this.hotelModalities;
    }

    public void setHotelModalities(List<HotelModalityDto> hotelModalities) {
        this.hotelModalities = hotelModalities;
    }

    public HotelModalityDto getSelectedHotelModality() {
        return this.selectedHotelModality;
    }

    public void setSelectedHotelModality(HotelModalityDto selectedHotelModality) {
        this.selectedHotelModality = selectedHotelModality;
    }

    public IHotelModalityService getService() {
        return this.service;
    }

    public void setService(IHotelModalityService service) {
        this.service = service;
    }

}