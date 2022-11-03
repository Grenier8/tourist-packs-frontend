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

import cu.edu.cujae.touristpacks.dto.TransportContractDto;
import cu.edu.cujae.touristpacks.dto.TransportContractTransportServiceDto;
import cu.edu.cujae.touristpacks.dto.TransportServiceDto;
import cu.edu.cujae.touristpacks.service.transport_contract.ITransportContractService;
import cu.edu.cujae.touristpacks.service.transport_contract_transport_service.ITransportContractTransportServiceService;
import cu.edu.cujae.touristpacks.service.transport_service.ITransportServiceService;

@Component
@ManagedBean
@ViewScoped
public class ManageTransportContractTransportServiceBean {

    private List<TransportContractTransportServiceDto> transportContractTransportService;
    private TransportContractTransportServiceDto selectedTransportContractTransportService;

    private String selectedTransportContract;
    private String selectedTransportService;

    @Autowired
    private ITransportContractTransportServiceService service;

    @Autowired
    private ITransportContractService tContractservice;

    @Autowired
    private ITransportServiceService tServiceservice;

    public ManageTransportContractTransportServiceBean() {

    }

    @PostConstruct
    public void init() {
        transportContractTransportService = transportContractTransportService == null
                ? service.getTransportContractTransportServices()
                : transportContractTransportService;
    }

    public void openNew() {
        this.selectedTransportContractTransportService = new TransportContractTransportServiceDto();
    }

    public void openForEdit() {

    }

    public void saveTransportContractTransportService() {
        if (this.selectedTransportContractTransportService.getIdTransportContractTransportService() == 0) {
            int r = (int) (Math.random() * 10000);

            this.selectedTransportContractTransportService.setIdTransportContractTransportService(r);
            //

            TransportContractDto transportContract = tContractservice
                    .getTransportContractByTitle(selectedTransportContract);
            this.selectedTransportContractTransportService.setTransportContract(transportContract);

            TransportServiceDto transportService = tServiceservice.getTransportServiceByName(selectedTransportService);
            this.selectedTransportContractTransportService.setTransportService(transportService);

            this.transportContractTransportService.add(this.selectedTransportContractTransportService);

            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Contrato de Transporte y Servicio insertado"));
        } else {

            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Contrato de Transporte y Servicio modificado"));
        }

        PrimeFaces.current().executeScript("PF('manageTransportContractTransportServiceDialog').hide()");
        PrimeFaces.current().ajax().update("form:dt-transportContractTransportServices");

    }

    public void deleteTransportContractTransportService() {

        this.transportContractTransportService.remove(this.selectedTransportContractTransportService);
        this.selectedTransportContractTransportService = null;
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage("Contrato de Transporte y Servicio eliminado"));
        PrimeFaces.current().ajax().update("form:messages", "form:dt-transportContractTransportServices");

    }

    public List<TransportContractTransportServiceDto> getTransportContractTransportServices() {
        return transportContractTransportService;
    }

    public void setTransportContractTransportServices(
            List<TransportContractTransportServiceDto> transportContractTransportService) {
        this.transportContractTransportService = transportContractTransportService;
    }

    public TransportContractTransportServiceDto getSelectedTransportContractTransportService() {
        return selectedTransportContractTransportService;
    }

    public void setSelectedTransportContractTransportService(
            TransportContractTransportServiceDto selectedTransportContractTransportService) {
        this.selectedTransportContractTransportService = selectedTransportContractTransportService;
    }

    public ITransportContractTransportServiceService getService() {
        return service;
    }

    public void setService(ITransportContractTransportServiceService service) {
        this.service = service;
    }

    public String getSelectedTransportContract() {
        return selectedTransportContract;
    }

    public void setSelectedTransportContract(String selectedTransportContract) {
        this.selectedTransportContract = selectedTransportContract;
    }

    public String getSelectedTransportService() {
        return selectedTransportService;
    }

    public void setSelectedTransportService(String selectedTransportService) {
        this.selectedTransportService = selectedTransportService;
    }
}
