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

import cu.edu.cujae.touristpacks.dto.TmTravelDto;
import cu.edu.cujae.touristpacks.service.tm_travel.ITmTravelService;
import cu.edu.cujae.touristpacks.utils.JsfUtils;

@Component
@ManagedBean
@ViewScoped
public class ManageTmTravelBean {

    private List<TmTravelDto> tmTravels;
    private TmTravelDto selectedTmTravel;

    @Autowired
    private ITmTravelService service;

    public ManageTmTravelBean() {

    }

    public void openNew() {
        this.selectedTmTravel = new TmTravelDto();
    }

    public void openForEdit() {

    }

    public void saveTmTravel() {
        if (this.selectedTmTravel.getIdTmTravel() == 0) {
            service.createTmTravel(selectedTmTravel);

            JsfUtils.addInfoMessageFromBundle("message_inserted_tmTravel");
        } else {
            service.updateTmTravel(selectedTmTravel);

            JsfUtils.addInfoMessageFromBundle("message_updated_tmTravel");
        }

        tmTravels = service.getTmTravels();

        PrimeFaces.current().executeScript("PF('manageTmTravelDialog').hide()");
        PrimeFaces.current().ajax().update("form:dt-tmTravels");

    }

    public void deleteTmTravel() {

        service.deleteTmTravel(selectedTmTravel.getIdTmTravel());
        this.selectedTmTravel = null;

        tmTravels = service.getTmTravels();

        JsfUtils.addInfoMessageFromBundle("message_deleted_tmTravel");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-tmTravels");

    }

    public List<TmTravelDto> getTmTravels() {
        tmTravels = service.getTmTravels();
        return this.tmTravels;
    }

    public void setTmTravels(List<TmTravelDto> tmTravels) {
        this.tmTravels = tmTravels;
    }

    public TmTravelDto getSelectedTmTravel() {
        return this.selectedTmTravel;
    }

    public void setSelectedTmTravel(TmTravelDto selectedTmTravel) {
        this.selectedTmTravel = selectedTmTravel;
    }

    public ITmTravelService getService() {
        return this.service;
    }

    public void setService(ITmTravelService service) {
        this.service = service;
    }
}
