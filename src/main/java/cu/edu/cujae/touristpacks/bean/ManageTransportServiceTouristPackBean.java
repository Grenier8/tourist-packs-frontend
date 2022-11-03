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

import cu.edu.cujae.touristpacks.dto.TouristPackDto;
import cu.edu.cujae.touristpacks.dto.TransportServiceDto;
import cu.edu.cujae.touristpacks.dto.TransportServiceTouristPackDto;
import cu.edu.cujae.touristpacks.service.tourist_pack.ITouristPackService;
import cu.edu.cujae.touristpacks.service.transport_service.ITransportServiceService;
import cu.edu.cujae.touristpacks.service.transport_service_tourist_pack.ITransportServiceTouristPackService;

@Component
@ManagedBean
@ViewScoped
public class ManageTransportServiceTouristPackBean {

    private List<TransportServiceTouristPackDto> transportServiceTouristPacks;
    private TransportServiceTouristPackDto selectedTransportServiceTouristPack;
    private String selectedTouristPackName;
    private String selectedTransportServiceName;

    @Autowired
    private ITransportServiceTouristPackService service;

    @Autowired
    private ITouristPackService touristPackService;

    @Autowired
    private ITransportServiceService transportServiceService;

    public ManageTransportServiceTouristPackBean() {

    }

    @PostConstruct
    public void init() {
        transportServiceTouristPacks = transportServiceTouristPacks == null ? service.getTransportServiceTouristPacks()
                : transportServiceTouristPacks;
    }

    public void openNew() {
        this.selectedTransportServiceTouristPack = new TransportServiceTouristPackDto();
    }

    public void openForEdit() {

    }

    public void saveTransportServiceTouristPack() {
        if (this.selectedTransportServiceTouristPack.getIdTransportServiceTouristPack() == 0) {
            int r = (int) (Math.random() * 10000);
            this.selectedTransportServiceTouristPack.setIdTransportServiceTouristPack(r);

            TouristPackDto touristPack = touristPackService.getTouristPackByName(selectedTouristPackName);
            this.selectedTransportServiceTouristPack.setTouristPack(touristPack);

            TransportServiceDto transportService = transportServiceService
                    .getTransportServiceByName(selectedTransportServiceName);
            this.selectedTransportServiceTouristPack.setTransportService(transportService);

            this.transportServiceTouristPacks.add(this.selectedTransportServiceTouristPack);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cambiar insertada"));
        } else {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cambiar modificada"));
        }

        PrimeFaces.current().executeScript("PF('manageTransportServiceTouristPackDialog').hide()");
        PrimeFaces.current().ajax().update("form:dt-transportServiceTouristPacks");

    }

    public void deleteTransportServiceTouristPack() {

        this.transportServiceTouristPacks.remove(this.selectedTransportServiceTouristPack);
        this.selectedTransportServiceTouristPack = null;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cambiar eliminada"));
        PrimeFaces.current().ajax().update("form:messages", "form:dt-transportServiceTouristPacks");

    }

    public List<TransportServiceTouristPackDto> getTransportServiceTouristPacks() {
        return transportServiceTouristPacks;
    }

    public void setTransportServiceTouristPacks(List<TransportServiceTouristPackDto> transportServiceTouristPacks) {
        this.transportServiceTouristPacks = transportServiceTouristPacks;
    }

    public TransportServiceTouristPackDto getSelectedTransportServiceTouristPack() {
        return selectedTransportServiceTouristPack;
    }

    public void setSelectedTransportServiceTouristPack(
            TransportServiceTouristPackDto selectedTransportServiceTouristPack) {
        this.selectedTransportServiceTouristPack = selectedTransportServiceTouristPack;
    }

    public ITransportServiceTouristPackService getService() {
        return service;
    }

    public void setService(ITransportServiceTouristPackService service) {
        this.service = service;
    }

    public String getSelectedTouristPackName() {
        return this.selectedTouristPackName;
    }

    public void setSelectedTouristPackName(String selectedTouristPackName) {
        this.selectedTouristPackName = selectedTouristPackName;
    }

    public String getSelectedTransportServiceName() {
        return this.selectedTransportServiceName;
    }

    public void setSelectedTransportServiceName(String selectedTransportServiceName) {
        this.selectedTransportServiceName = selectedTransportServiceName;
    }

    public ITouristPackService getTouristPackService() {
        return this.touristPackService;
    }

    public void setTouristPackService(ITouristPackService touristPackService) {
        this.touristPackService = touristPackService;
    }

    public ITransportServiceService getTransportServiceService() {
        return this.transportServiceService;
    }

    public void setTransportServiceService(ITransportServiceService transportServiceService) {
        this.transportServiceService = transportServiceService;
    }

}
