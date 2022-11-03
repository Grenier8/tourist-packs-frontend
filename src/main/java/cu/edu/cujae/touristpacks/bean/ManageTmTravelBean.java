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

    @PostConstruct
    public void init() {
        tmTravels = tmTravels == null ? service.getTmTravels() : tmTravels;
    }

    public void openNew() {
        this.selectedTmTravel = new TmTravelDto();
    }

    public void openForEdit() {

    }

    public void saveTmTravel() {
        if (this.selectedTmTravel.getIdTmTravel() == 0) {
            int r = (int) (Math.random() * 10000);

            this.selectedTmTravel.setIdTmTravel(r);
            this.tmTravels.add(this.selectedTmTravel);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Modalidad de Transporte insertada"));
        } else {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Modalidad de Transporte modificada"));
        }

        PrimeFaces.current().executeScript("PF('manageTmTravelDialog').hide()");
        PrimeFaces.current().ajax().update("form:dt-tmTravels");

    }

    public void deleteTmTravel() {

        this.tmTravels.remove(this.selectedTmTravel);
        this.selectedTmTravel = null;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Modalidad de Transporte eliminada"));
        PrimeFaces.current().ajax().update("form:messages", "form:dt-tmTravels");

    }

    public List<TmTravelDto> getTmTravels() {
        return tmTravels;
    }

    public void setTmTravels(List<TmTravelDto> tmTravels) {
        this.tmTravels = tmTravels;
    }

    public TmTravelDto getSelectedTmTravel() {
        return selectedTmTravel;
    }

    public void setSelectedTmTravel(TmTravelDto selectedTmTravel) {
        this.selectedTmTravel = selectedTmTravel;
    }

    public ITmTravelService getService() {
        return service;
    }

    public void setService(ITmTravelService service) {
        this.service = service;
    }
}
