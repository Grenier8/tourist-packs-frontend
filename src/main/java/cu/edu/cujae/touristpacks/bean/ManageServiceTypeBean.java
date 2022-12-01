package cu.edu.cujae.touristpacks.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;

import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cu.edu.cujae.touristpacks.dto.ServiceTypeDto;
import cu.edu.cujae.touristpacks.service.service_type.IServiceTypeService;
import cu.edu.cujae.touristpacks.utils.JsfUtils;

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
        serviceTypes = service.getServiceTypes();
    }

    public void openNew() {
        this.selectedServiceType = new ServiceTypeDto();
    }

    public void openForEdit() {

    }

    public void saveServiceType() {
        if (this.selectedServiceType.getIdServiceType() == 0) {
            service.createServiceType(selectedServiceType);

            JsfUtils.addInfoMessageFromBundle("message_inserted_service_type");
        } else {
            service.updateServiceType(selectedServiceType);

            JsfUtils.addInfoMessageFromBundle("message_updated_service_type");
        }

        serviceTypes = service.getServiceTypes();

        PrimeFaces.current().executeScript("PF('manageServiceTypeDialog').hide()");
        PrimeFaces.current().ajax().update("form:dt-serviceTypes");

    }

    public void deleteServiceType() {

        service.deleteServiceType(selectedServiceType.getIdServiceType());
        this.selectedServiceType = null;

        serviceTypes = service.getServiceTypes();

        JsfUtils.addInfoMessageFromBundle("message_deleted_service_type");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-serviceTypes");

    }

    public List<ServiceTypeDto> getServiceTypes() {
        return this.serviceTypes;
    }

    public void setServiceTypes(List<ServiceTypeDto> serviceTypes) {
        this.serviceTypes = serviceTypes;
    }

    public ServiceTypeDto getSelectedServiceType() {
        return this.selectedServiceType;
    }

    public void setSelectedServiceType(ServiceTypeDto selectedServiceType) {
        this.selectedServiceType = selectedServiceType;
    }

    public IServiceTypeService getService() {
        return this.service;
    }

    public void setService(IServiceTypeService service) {
        this.service = service;
    }

}