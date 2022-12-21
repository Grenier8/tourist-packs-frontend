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
import cu.edu.cujae.touristpacks.utils.JsfUtils;

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

    public void openNew() {
        this.selectedTmHourKm = new TmHourKmDto();
    }

    public void openForEdit() {

    }

    public void saveTmHourKm() {
        if (this.selectedTmHourKm.getIdTmHourKm() == 0) {
            service.createTmHourKm(selectedTmHourKm);

            JsfUtils.addInfoMessageFromBundle("message_inserted_tmHourKm");
        } else {
            service.updateTmHourKm(selectedTmHourKm);

            JsfUtils.addInfoMessageFromBundle("message_updated_tmHourKm");
        }

        tmHourKms = service.getTmHourKms();

        PrimeFaces.current().executeScript("PF('manageTmHourKmDialog').hide()");
        PrimeFaces.current().ajax().update("form:dt-tmHourKms");

    }

    public void deleteTmHourKm() {

        service.deleteTmHourKm(selectedTmHourKm.getIdTmHourKm());
        this.selectedTmHourKm = null;

        tmHourKms = service.getTmHourKms();

        JsfUtils.addInfoMessageFromBundle("message_deleted_tmHourKm");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-tmHourKms");

    }

    public List<TmHourKmDto> getTmHourKms() {
        tmHourKms = service.getTmHourKms();
        return this.tmHourKms;
    }

    public void setTmHourKms(List<TmHourKmDto> tmHourKms) {
        this.tmHourKms = tmHourKms;
    }

    public TmHourKmDto getSelectedTmHourKm() {
        return this.selectedTmHourKm;
    }

    public void setSelectedTmHourKm(TmHourKmDto selectedTmHourKm) {
        this.selectedTmHourKm = selectedTmHourKm;
    }

    public ITmHourKmService getService() {
        return this.service;
    }

    public void setService(ITmHourKmService service) {
        this.service = service;
    }
}
