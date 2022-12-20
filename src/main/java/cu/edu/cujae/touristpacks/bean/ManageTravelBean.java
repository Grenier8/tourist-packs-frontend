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

import cu.edu.cujae.touristpacks.dto.TravelDto;
import cu.edu.cujae.touristpacks.service.tm_travel.ITmTravelService;
import cu.edu.cujae.touristpacks.service.travel.ITravelService;
import cu.edu.cujae.touristpacks.utils.JsfUtils;

@Component
@ManagedBean
@ViewScoped
public class ManageTravelBean {

    private List<TravelDto> travels;
    private TravelDto selectedTravel;

    private String selectedTravelName;

    @Autowired
    private ITravelService service;

    @Autowired
    private ITmTravelService serviceTmTravel;

    public ManageTravelBean() {

    }

    public void openNew() {
        this.selectedTravel = new TravelDto();
    }

    public void openForEdit() {

    }

    public void saveTravel() {

        selectedTravel.setTmodalityTravel(serviceTmTravel.getTmTravelByName(selectedTravelName));

        if (this.selectedTravel.getIdTravel() == 0) {
            service.createTravel(selectedTravel);

            JsfUtils.addInfoMessageFromBundle("message_inserted_travel");
        } else {
            service.updateTravel(selectedTravel);

            JsfUtils.addInfoMessageFromBundle("message_updated_travel");
        }

        travels = service.getTravels();

        PrimeFaces.current().executeScript("PF('manageTravelDialog').hide()");
        PrimeFaces.current().ajax().update("form:dt-travels");

    }

    public void deleteTravel() {

        service.deleteTravel(selectedTravel.getIdTravel());
        this.selectedTravel = null;

        travels = service.getTravels();

        JsfUtils.addInfoMessageFromBundle("message_deleted_travel");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-travels");

    }

    public List<TravelDto> getTravels() {
        travels = service.getTravels();
        return this.travels;
    }

    public void setTravels(List<TravelDto> travels) {
        this.travels = travels;
    }

    public TravelDto getSelectedTravel() {
        return this.selectedTravel;
    }

    public void setSelectedTravel(TravelDto selectedTravel) {
        this.selectedTravel = selectedTravel;
    }

    public ITravelService getService() {
        return this.service;
    }

    public void setService(ITravelService service) {
        this.service = service;
    }

    public String getSelectedTravelName() {
        return selectedTravelName;
    }

    public void setSelectedTravelName(String selectedTravelName) {
        this.selectedTravelName = selectedTravelName;
    }
}
