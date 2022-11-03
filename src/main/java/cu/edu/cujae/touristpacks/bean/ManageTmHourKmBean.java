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

import cu.edu.cujae.touristpacks.dto.TmHourKmDto;
import cu.edu.cujae.touristpacks.service.tm_hour_km.ITmHourKmService;

@Component
@ManagedBean
@ViewScoped
public class ManageTmHourKmBean {

    private List<TmHourKmDto> tmHourKms;
    private TmHourKmDto selectedTmHourKm;

    @Autowired
    private ITmHourKmService service;

    public ManageTmHourKmBean() {

    }

    @PostConstruct
    public void init() {
        tmHourKms = tmHourKms == null ? service.getTmHourKms() : tmHourKms;
    }

    public void openNew() {
        this.selectedTmHourKm = new TmHourKmDto();
    }

    public void openForEdit() {

    }

    public void saveTmHourKm() {
        if (this.selectedTmHourKm.getIdTmHourKm() == 0) {
            int r = (int) (Math.random() * 10000);

            this.selectedTmHourKm.setIdTmHourKm(r);
            this.tmHourKms.add(this.selectedTmHourKm);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Modalidad de transporte por Km-Hora insertada"));
        } else {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Modalidad de transporte por Km-Hora modificada"));
        }

        PrimeFaces.current().executeScript("PF('manageTmHourKmDialog').hide()");
        PrimeFaces.current().ajax().update("form:dt-tmHourKms");

    }

    public void deleteTmHourKm() {

        this.tmHourKms.remove(this.selectedTmHourKm);
        this.selectedTmHourKm = null;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Modalidad de transporte por Km-Hora eliminada"));
        PrimeFaces.current().ajax().update("form:messages", "form:dt-tmHourKms");

    }

    public List<TmHourKmDto> getTmHourKms() {
        return tmHourKms;
    }

    public void setTmHourKms(List<TmHourKmDto> tmHourKms) {
        this.tmHourKms = tmHourKms;
    }

    public TmHourKmDto getSelectedTmHourKm() {
        return selectedTmHourKm;
    }

    public void setSelectedTmHourKm(TmHourKmDto selectedTmHourKm) {
        this.selectedTmHourKm = selectedTmHourKm;
    }

    public ITmHourKmService getService() {
        return service;
    }

    public void setService(ITmHourKmService service) {
        this.service = service;
    }
}
