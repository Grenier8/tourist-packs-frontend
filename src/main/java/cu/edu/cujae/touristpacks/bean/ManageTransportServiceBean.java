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
import cu.edu.cujae.touristpacks.dto.TransportServiceDto;
import cu.edu.cujae.touristpacks.service.transport_modality.ITransportModalityService;
import cu.edu.cujae.touristpacks.service.transport_service.ITransportServiceService;
import cu.edu.cujae.touristpacks.service.vehicle.IVehicleService;
import cu.edu.cujae.touristpacks.utils.JsfUtils;

@Component
@ManagedBean
@ViewScoped
public class ManageTransportServiceBean {

    private List<TransportServiceDto> transportServices;
    private TransportServiceDto selectedTransportService;

    private String selectedVehiclePlate;

    private String selectedTModalityName;

    @Autowired
    private ITransportServiceService service;

    @Autowired
    private IVehicleService serviceVehicle;

    @Autowired
    private ITransportModalityService serviceTModality;

    public ManageTransportServiceBean() {

    }

    public void openNew() {
        this.selectedTransportService = new TransportServiceDto();
    }

    public void openForEdit() {

    }

    public void saveTransportService() {

        selectedTransportService.setVehicle(serviceVehicle.getVehicleByPlate(selectedVehiclePlate));
        selectedTransportService.setTmodality(serviceTModality.getTransportModalityByName(selectedTModalityName));

        if (this.selectedTransportService.getIdTransportService() == 0) {
            service.createTransportService(selectedTransportService);

            JsfUtils.addInfoMessageFromBundle("message_inserted_transportService");
        } else {
            service.updateTransportService(selectedTransportService);

            JsfUtils.addInfoMessageFromBundle("message_updated_transportService");
        }

        transportServices = service.getTransportServices();

        PrimeFaces.current().executeScript("PF('manageTransportServiceDialog').hide()");
        PrimeFaces.current().ajax().update("form:dt-transportServices");

    }

    public void deleteTransportService() {

        service.deleteTransportService(selectedTransportService.getIdTransportService());
        this.selectedTransportService = null;

        transportServices = service.getTransportServices();

        JsfUtils.addInfoMessageFromBundle("message_deleted_transportService");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-transportServices");

    }

    public List<TransportServiceDto> getTransportServices() {
        transportServices = service.getTransportServices();
        return this.transportServices;
    }

    public void setTransportServices(List<TransportServiceDto> transportServices) {
        this.transportServices = transportServices;
    }

    public TransportServiceDto getSelectedTransportService() {
        return this.selectedTransportService;
    }

    public void setSelectedTransportService(TransportServiceDto selectedTransportService) {
        this.selectedTransportService = selectedTransportService;
    }

    public ITransportServiceService getService() {
        return this.service;
    }

    public void setService(ITransportServiceService service) {
        this.service = service;
    }

    public String getSelectedVehiclePlate() {
        return selectedVehiclePlate;
    }

    public void setSelectedVehiclePlate(String selectedVehiclePlate) {
        this.selectedVehiclePlate = selectedVehiclePlate;
    }

    public String getSelectedTModalityName() {
        return selectedTModalityName;
    }

    public void setSelectedTModalityName(String selectedTModalityName) {
        this.selectedTModalityName = selectedTModalityName;
    }
}
