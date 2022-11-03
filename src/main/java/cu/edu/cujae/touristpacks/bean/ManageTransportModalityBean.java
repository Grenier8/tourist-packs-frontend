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

import cu.edu.cujae.touristpacks.dto.TransportModalityDto;
import cu.edu.cujae.touristpacks.service.transport_modality.ITransportModalityService;

@Component
@ManagedBean
@ViewScoped
public class ManageTransportModalityBean {

    private List<TransportModalityDto> transportModalities;
    private TransportModalityDto selectedTransportModality;

    @Autowired
    private ITransportModalityService service;

    public ManageTransportModalityBean() {

    }

    @PostConstruct
    public void init() {
        transportModalities = transportModalities == null ? service.getTransportModalities() : transportModalities;
    }

    public void openNew() {
        this.selectedTransportModality = new TransportModalityDto();
    }

    public void openForEdit() {

    }

    public void saveTransportModality() {
        if (this.selectedTransportModality.getIdTransportModality() == 0) {
            int r = (int) (Math.random() * 10000);

            this.selectedTransportModality.setIdTransportModality(r);
            this.transportModalities.add(this.selectedTransportModality);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Modalidad de Transporte insertada"));
        } else {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Modalidad de Transporte modificada"));
        }

        PrimeFaces.current().executeScript("PF('manageTransportModalityDialog').hide()");
        PrimeFaces.current().ajax().update("form:dt-transportModalitys");

    }

    public void deleteTransportModality() {

        this.transportModalities.remove(this.selectedTransportModality);
        this.selectedTransportModality = null;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Modalidad de Transporte eliminada"));
        PrimeFaces.current().ajax().update("form:messages", "form:dt-transportModalitys");

    }

    public List<TransportModalityDto> getTransportModalitys() {
        return transportModalities;
    }

    public void setTransportModalitys(List<TransportModalityDto> transportModalities) {
        this.transportModalities = transportModalities;
    }

    public TransportModalityDto getSelectedTransportModality() {
        return selectedTransportModality;
    }

    public void setSelectedTransportModality(TransportModalityDto selectedTransportModality) {
        this.selectedTransportModality = selectedTransportModality;
    }

    public ITransportModalityService getService() {
        return service;
    }

    public void setService(ITransportModalityService service) {
        this.service = service;
    }
}
