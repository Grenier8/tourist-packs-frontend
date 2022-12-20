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
import cu.edu.cujae.touristpacks.utils.JsfUtils;

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

    public void openNew() {
        this.selectedTransportModality = new TransportModalityDto();
    }

    public void openForEdit() {

    }

    public void saveTransportModality() {
        if (this.selectedTransportModality.getIdTransportModality() == 0) {
            service.createTransportModality(selectedTransportModality);

            JsfUtils.addInfoMessageFromBundle("message_inserted_transportModality");
        } else {
            service.updateTransportModality(selectedTransportModality);

            JsfUtils.addInfoMessageFromBundle("message_updated_transportModality");
        }

        transportModalities = service.getTransportModalities();

        PrimeFaces.current().executeScript("PF('manageTransportModalityDialog').hide()");
        PrimeFaces.current().ajax().update("form:dt-transportModalitys");

    }

    public void deleteTransportModality() {

        service.deleteTransportModality(selectedTransportModality.getIdTransportModality());
        this.selectedTransportModality = null;

        transportModalities = service.getTransportModalities();

        JsfUtils.addInfoMessageFromBundle("message_deleted_transportModality");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-transportModalitys");

    }

    public List<TransportModalityDto> getTransportModalitys() {
        transportModalities = service.getTransportModalities();
        return this.transportModalities;
    }

    public void setTransportModalitys(List<TransportModalityDto> transportModalities) {
        this.transportModalities = transportModalities;
    }

    public TransportModalityDto getSelectedTransportModality() {
        return this.selectedTransportModality;
    }

    public void setSelectedTransportModality(TransportModalityDto selectedTransportModality) {
        this.selectedTransportModality = selectedTransportModality;
    }

    public ITransportModalityService getService() {
        return this.service;
    }

    public void setService(ITransportModalityService service) {
        this.service = service;
    }
}
