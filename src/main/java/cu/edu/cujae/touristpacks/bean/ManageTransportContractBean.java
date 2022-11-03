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
import cu.edu.cujae.touristpacks.service.transport_contract.ITransportContractService;

@Component
@ManagedBean
@ViewScoped
public class ManageTransportContractBean {

    private List<TransportContractDto> transportContracts;
    private TransportContractDto selectedTransportContract;

    @Autowired
    private ITransportContractService service;

    public ManageTransportContractBean() {

    }

    @PostConstruct
    public void init() {
        transportContracts = transportContracts == null ? service.getTransportContracts() : transportContracts;
    }

    public void openNew() {
        this.selectedTransportContract = new TransportContractDto();
    }

    public void openForEdit() {

    }

    public void saveTransportContract() {
        if (this.selectedTransportContract.getIdTransportContract() == 0) {
            int r = (int) (Math.random() * 10000);

            this.selectedTransportContract.setIdTransportContract(r);
            this.transportContracts.add(this.selectedTransportContract);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Contrato de Transporte insertado"));
        } else {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Contrato de Transporte modificado"));
        }

        PrimeFaces.current().executeScript("PF('manageTransportContractDialog').hide()");
        PrimeFaces.current().ajax().update("form:dt-transportContracts");

    }

    public void deleteTransportContract() {

        this.transportContracts.remove(this.selectedTransportContract);
        this.selectedTransportContract = null;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Contrato de Transporte eliminado"));
        PrimeFaces.current().ajax().update("form:messages", "form:dt-transportContracts");

    }

    public List<TransportContractDto> getTransportContracts() {
        return transportContracts;
    }

    public void setTransportContracts(List<TransportContractDto> transportContracts) {
        this.transportContracts = transportContracts;
    }

    public TransportContractDto getSelectedTransportContract() {
        return selectedTransportContract;
    }

    public void setSelectedTransportContract(TransportContractDto selectedTransportContract) {
        this.selectedTransportContract = selectedTransportContract;
    }

    public ITransportContractService getService() {
        return service;
    }

    public void setService(ITransportContractService service) {
        this.service = service;
    }
}
