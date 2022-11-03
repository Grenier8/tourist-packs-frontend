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
import cu.edu.cujae.touristpacks.service.travel.ITravelService;

@Component
@ManagedBean
@ViewScoped
public class ManageTravelBean {

    private List<TravelDto> travels;
    private TravelDto selectedTravel;

    @Autowired
    private ITravelService service;

    public ManageTravelBean() {

    }

    @PostConstruct
    public void init() {
        travels = travels == null ? service.getTravels() : travels;
    }

    public void openNew() {
        this.selectedTravel = new TravelDto();
    }

    public void openForEdit() {

    }

    public void saveTravel() {
        if (this.selectedTravel.getIdTravel() == 0) {
            int r = (int) (Math.random() * 10000);

            this.selectedTravel.setIdTravel(r);
            this.travels.add(this.selectedTravel);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Viaje insertado"));
        } else {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Viaje modificado"));
        }

        PrimeFaces.current().executeScript("PF('manageTravelDialog').hide()");
        PrimeFaces.current().ajax().update("form:dt-travels");

    }

    public void deleteTravel() {

        this.travels.remove(this.selectedTravel);
        this.selectedTravel = null;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Viaje eliminado"));
        PrimeFaces.current().ajax().update("form:messages", "form:dt-travels");

    }

    public List<TravelDto> getTravels() {
        return travels;
    }

    public void setTravels(List<TravelDto> travels) {
        this.travels = travels;
    }

    public TravelDto getSelectedTravel() {
        return selectedTravel;
    }

    public void setSelectedTravel(TravelDto selectedTravel) {
        this.selectedTravel = selectedTravel;
    }

    public ITravelService getService() {
        return service;
    }

    public void setService(ITravelService service) {
        this.service = service;
    }
}
