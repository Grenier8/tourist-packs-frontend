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
import cu.edu.cujae.touristpacks.utils.JsfUtils;

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

    public void openNew() {
        this.selectedTmKm = new TmKmDto();
    }

    public void openForEdit() {

    }

    public void saveTmKm() {
        if (this.selectedTmKm.getIdTmKm() == 0) {
            service.createTmKm(selectedTmKm);

            JsfUtils.addInfoMessageFromBundle("message_inserted_tmKm");
        } else {
            service.updateTmKm(selectedTmKm);

            JsfUtils.addInfoMessageFromBundle("message_updated_tmKm");
        }

        tmKms = service.getTmKms();

        PrimeFaces.current().executeScript("PF('manageTmKmDialog').hide()");
        PrimeFaces.current().ajax().update("form:dt-tmKms");

    }

    public void deleteTmKm() {

        service.deleteTmKm(selectedTmKm.getIdTmKm());
        this.selectedTmKm = null;

        tmKms = service.getTmKms();

        JsfUtils.addInfoMessageFromBundle("message_deleted_tmKm");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-tmKms");

    }

    public List<TmKmDto> getTmKms() {
        tmKms = service.getTmKms();
        return this.tmKms;
    }

    public void setTmKms(List<TmKmDto> tmKms) {
        this.tmKms = tmKms;
    }

    public TmKmDto getSelectedTmKm() {
        return this.selectedTmKm;
    }

    public void setSelectedTmKm(TmKmDto selectedTmKm) {
        this.selectedTmKm = selectedTmKm;
    }

    public ITmKmService getService() {
        return this.service;
    }

    public void setService(ITmKmService service) {
        this.service = service;
    }
}
