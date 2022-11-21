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

import cu.edu.cujae.touristpacks.dto.TouristPackDto;
import cu.edu.cujae.touristpacks.service.tourist_pack.ITouristPackService;

@Component
@ManagedBean
@ViewScoped
public class ManageTouristPackBean {

    private List<TouristPackDto> touristPacks;
    private TouristPackDto selectedTouristPack;

    @Autowired
    private ITouristPackService service;

    public ManageTouristPackBean() {

    }

    @PostConstruct
    public void init() {
        touristPacks = service.getTouristPacks();
    }

    public void openNew() {
        this.selectedTouristPack = new TouristPackDto();
    }

    public void openForEdit() {

    }

    public void saveTouristPack() {
        if (this.selectedTouristPack.getIdTouristPack() == 0) {
            service.createTouristPack(selectedTouristPack);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Espanolo insertada"));
        } else {
            service.updateTouristPack(selectedTouristPack);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Espanolo modificada"));
        }

        touristPacks = service.getTouristPacks();

        PrimeFaces.current().executeScript("PF('manageTouristPackDialog').hide()");
        PrimeFaces.current().ajax().update("form:dt-touristPacks");

    }

    public void deleteTouristPack() {

        service.deleteTouristPack(selectedTouristPack.getIdTouristPack());
        this.selectedTouristPack = null;

        touristPacks = service.getTouristPacks();

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Espanolo eliminada"));
        PrimeFaces.current().ajax().update("form:messages", "form:dt-touristPacks");

    }

    public List<TouristPackDto> getTouristPacks() {
        return this.touristPacks;
    }

    public void setTouristPacks(List<TouristPackDto> touristPacks) {
        this.touristPacks = touristPacks;
    }

    public TouristPackDto getSelectedTouristPack() {
        return this.selectedTouristPack;
    }

    public void setSelectedTouristPack(TouristPackDto selectedTouristPack) {
        this.selectedTouristPack = selectedTouristPack;
    }

    public ITouristPackService getService() {
        return this.service;
    }

    public void setService(ITouristPackService service) {
        this.service = service;
    }

}