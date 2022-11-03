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

import cu.edu.cujae.touristpacks.dto.TransportServiceDto;
import cu.edu.cujae.touristpacks.service.transport_service.ITransportServiceService;

@Component
@ManagedBean
@ViewScoped
public class ManageTransportServiceBean {

    private List<TransportServiceDto> transportServices;
    private TransportServiceDto selectedTransportService;

    @Autowired
    private ITransportServiceService service;

    public ManageTransportServiceBean() {

    }

    @PostConstruct
    public void init() {
        transportServices = transportServices == null ? service.getTransportServices() : transportServices;
    }

    public void openNew() {
        this.selectedTransportService = new TransportServiceDto();
    }

    public void openForEdit() {

    }

    public void saveTransportService() {
        if (this.selectedTransportService.getIdTransportService() == 0) {
            int r = (int) (Math.random() * 10000);

            this.selectedTransportService.setIdTransportService(r);
            this.transportServices.add(this.selectedTransportService);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Servicio de Transporte insertado"));
        } else {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Servicio de Transporte modificado"));
        }

        PrimeFaces.current().executeScript("PF('manageTransportServiceDialog').hide()");
        PrimeFaces.current().ajax().update("form:dt-transportServices");

    }

    public void deleteTransportService() {

        this.transportServices.remove(this.selectedTransportService);
        this.selectedTransportService = null;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Servicio de Transporte eliminado"));
        PrimeFaces.current().ajax().update("form:messages", "form:dt-transportServices");

    }

    public List<TransportServiceDto> getTransportServices() {
        return transportServices;
    }

    public void setTransportServices(List<TransportServiceDto> transportServices) {
        this.transportServices = transportServices;
    }

    public TransportServiceDto getSelectedTransportService() {
        return selectedTransportService;
    }

    public void setSelectedTransportService(TransportServiceDto selectedTransportService) {
        this.selectedTransportService = selectedTransportService;
    }

    public ITransportServiceService getService() {
        return service;
    }

    public void setService(ITransportServiceService service) {
        this.service = service;
    }
}
