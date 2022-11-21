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

import cu.edu.cujae.touristpacks.dto.MayorDto;
import cu.edu.cujae.touristpacks.service.dtb_table.IMayorService;

@Component
@ManagedBean
@ViewScoped
public class ManageMayorBean {

    private List<MayorDto> minors;
    private MayorDto selectedMayor;

    @Autowired
    private IMayorService service;

    public ManageMayorBean() {

    }

    @PostConstruct
    public void init() {
        minors = service.getMayors();
    }

    public void openNew() {
        this.selectedMayor = new MayorDto();
    }

    public void openForEdit() {

    }

    public void saveMayor() {
        if (this.selectedMayor.getIdMayor() == 0) {
            service.createMayor(selectedMayor);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Espanolo insertada"));
        } else {
            service.updateMayor(selectedMayor);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Espanolo modificada"));
        }

        minors = service.getMayors();

        PrimeFaces.current().executeScript("PF('manageMayorDialog').hide()");
        PrimeFaces.current().ajax().update("form:dt-minors");

    }

    public void deleteMayor() {

        service.deleteMayor(selectedMayor.getIdMayor());
        this.selectedMayor = null;

        minors = service.getMayors();

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Espanolo eliminada"));
        PrimeFaces.current().ajax().update("form:messages", "form:dt-minors");

    }

    public List<MayorDto> getMayors() {
        return this.minors;
    }

    public void setMayors(List<MayorDto> minors) {
        this.minors = minors;
    }

    public MayorDto getSelectedMayor() {
        return this.selectedMayor;
    }

    public void setSelectedMayor(MayorDto selectedMayor) {
        this.selectedMayor = selectedMayor;
    }

    public IMayorService getService() {
        return this.service;
    }

    public void setService(IMayorService service) {
        this.service = service;
    }

}