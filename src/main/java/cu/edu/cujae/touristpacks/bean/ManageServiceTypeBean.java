package cu.edu.cujae.touristpacks.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;

import cu.edu.cujae.touristpacks.service.service_type.IServiceTypeService;
import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cu.edu.cujae.touristpacks.dto.ServiceTypeDto;

@Component
@ManagedBean
@ViewScoped
public class ManageServiceTypeBean {

    private List<ServiceTypeDto> serviceTypes;
    private ServiceTypeDto selectedServiceType;

    @Autowired
    private IServiceTypeService service;

    public ManageServiceTypeBean() {

    }

    @PostConstruct
    public void init() {
        serviceTypes = serviceTypes == null ? service.getServiceTypes() : serviceTypes;
    }

    public void openNew() {
        this.selectedServiceType = new ServiceTypeDto();
    }

    public void openForEdit() {

    }

    public void saveServiceType() {
        if (this.selectedServiceType.getIdServiceType() == 0) {
            int r = (int) (Math.random() * 10000);

            this.selectedServiceType.setIdServiceType(r);
            this.serviceTypes.add(this.selectedServiceType);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cambiar insertada"));
        } else {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cambiar modificada"));
        }

        PrimeFaces.current().executeScript("PF('manageServiceTypeDialog').hide()");
        PrimeFaces.current().ajax().update("form:dt-serviceTypes");

    }

    public void deleteServiceType() {

        this.serviceTypes.remove(this.selectedServiceType);
        this.selectedServiceType = null;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cambiar eliminada"));
        PrimeFaces.current().ajax().update("form:messages", "form:dt-serviceTypes");

    }

    public List<ServiceTypeDto> getServiceTypes() {
        return serviceTypes;
    }

    public void setServiceTypes(List<ServiceTypeDto> serviceTypes) {
        this.serviceTypes = serviceTypes;
    }

    public ServiceTypeDto getSelectedServiceType() {
        return selectedServiceType;
    }

    public void setSelectedServiceType(ServiceTypeDto selectedServiceType) {
        this.selectedServiceType = selectedServiceType;
    }

    public IServiceTypeService getService() {
        return service;
    }

    public void setService(IServiceTypeService service) {
        this.service = service;
    }
}
