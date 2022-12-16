package cu.edu.cujae.touristpacks.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import cu.edu.cujae.touristpacks.dto.VehicleDto;
import cu.edu.cujae.touristpacks.service.vehicle.IVehicleService;
import cu.edu.cujae.touristpacks.utils.JsfUtils;

@Component
@ManagedBean
@ViewScoped
public class ManageVehicleBean {

    private List<VehicleDto> vehicles;
    private VehicleDto selectedVehicle;

    @Autowired
    private IVehicleService service;

    public ManageVehicleBean() {

    }

    public void openNew() {
        this.selectedVehicle = new VehicleDto();
    }

    public void openForEdit() {

    }

    public void saveVehicle() {
        if (this.selectedVehicle.getIdVehicle() == 0) {
            service.createVehicle(selectedVehicle);

            JsfUtils.addInfoMessageFromBundle("message_inserted_vehicle");
        } else {
            service.updateVehicle(selectedVehicle);

            JsfUtils.addInfoMessageFromBundle("message_updated_vehicle");
        }

        vehicles = service.getVehicles();

        PrimeFaces.current().executeScript("PF('manageVehicleDialog').hide()");
        PrimeFaces.current().ajax().update("form:dt-vehicles");
    }

    public void deleteVehicle() {

        service.deleteVehicle(selectedVehicle.getIdVehicle());
        this.selectedVehicle = null;

        vehicles = service.getVehicles();

        JsfUtils.addInfoMessageFromBundle("message_deleted_vehicle");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-vehicles");

    }

    public List<VehicleDto> getVehicles() {
        return this.vehicles;
    }

    public void setVehicles(List<VehicleDto> vehicles) {
        this.vehicles = vehicles;
    }

    public VehicleDto getSelectedVehicle() {
        return this.selectedVehicle;
    }

    public void setSelectedVehicle(VehicleDto selectedVehicle) {
        this.selectedVehicle = selectedVehicle;
    }

    public IVehicleService getService() {
        return this.service;
    }

    public void setService(IVehicleService service) {
        this.service = service;
    }

}
