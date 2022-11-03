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
import cu.edu.cujae.touristpacks.dto.VehicleDto;
import cu.edu.cujae.touristpacks.service.vehicle.IVehicleService;

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

    @PostConstruct
    public void init() {
        vehicles = vehicles == null ? service.getVehicles() : vehicles;
    }

    public void openNew() {
        this.selectedVehicle = new VehicleDto();
    }

    public void openForEdit() {

    }

    public void saveVehicle() {
        if (this.selectedVehicle.getIdVehicle() == 0) {
            int r = (int) (Math.random() * 10000);

            this.selectedVehicle.setIdVehicle(r);
            this.vehicles.add(this.selectedVehicle);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Vehiculo insertado"));
        } else {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Vehiculo modificado"));
        }

        PrimeFaces.current().executeScript("PF('manageVehicleDialog').hide()");
        PrimeFaces.current().ajax().update("form:dt-vehicles");
    }

    public void deleteVehicle() {

        this.vehicles.remove(this.selectedVehicle);
        this.selectedVehicle = null;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Vehiculo eliminado"));
        PrimeFaces.current().ajax().update("form:messages", "form:dt-vehicles");

    }

    public List<VehicleDto> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<VehicleDto> vehicles) {
        this.vehicles = vehicles;
    }

    public VehicleDto getSelectedVehicle() {
        return selectedVehicle;
    }

    public void setSelectedVehicle(VehicleDto selectedVehicle) {
        this.selectedVehicle = selectedVehicle;
    }

    public IVehicleService getService() {
        return service;
    }

    public void setService(IVehicleService service) {
        this.service = service;
    }

}
