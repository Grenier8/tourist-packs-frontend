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

import cu.edu.cujae.touristpacks.dto.TmKmDto;
import cu.edu.cujae.touristpacks.service.tm_km.ITmKmService;

@Component
@ManagedBean
@ViewScoped
public class ManageTmKmBean {

    private List<TmKmDto> tmKms;
    private TmKmDto selectedTmKm;

    @Autowired
    private ITmKmService service;

    public ManageTmKmBean() {

    }

    @PostConstruct
    public void init() {
        tmKms = tmKms == null ? service.getTmKms() : tmKms;
    }

    public void openNew() {
        this.selectedTmKm = new TmKmDto();
    }

    public void openForEdit() {

    }

    public void saveTmKm() {
        if (this.selectedTmKm.getIdTmKm() == 0) {
            int r = (int) (Math.random() * 10000);

            this.selectedTmKm.setIdTmKm(r);
            this.tmKms.add(this.selectedTmKm);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Modalidad de Transporte por Km insertada"));
        } else {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Modalidad de Transporte por Km modificada"));
        }

        PrimeFaces.current().executeScript("PF('manageTmKmDialog').hide()");
        PrimeFaces.current().ajax().update("form:dt-tmKms");

    }

    public void deleteTmKm() {

        this.tmKms.remove(this.selectedTmKm);
        this.selectedTmKm = null;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Modalidad de Transporte por Km eliminada"));
        PrimeFaces.current().ajax().update("form:messages", "form:dt-tmKms");

    }

    public List<TmKmDto> getTmKms() {
        return tmKms;
    }

    public void setTmKms(List<TmKmDto> tmKms) {
        this.tmKms = tmKms;
    }

    public TmKmDto getSelectedTmKm() {
        return selectedTmKm;
    }

    public void setSelectedTmKm(TmKmDto selectedTmKm) {
        this.selectedTmKm = selectedTmKm;
    }

    public ITmKmService getService() {
        return service;
    }

    public void setService(ITmKmService service) {
        this.service = service;
    }
}
