package cu.edu.cujae.touristpacks.bean;

import java.util.ArrayList;
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
import cu.edu.cujae.touristpacks.dto.TransportServiceDto;
import cu.edu.cujae.touristpacks.service.provider.IProviderService;
import cu.edu.cujae.touristpacks.service.transport_contract.ITransportContractService;
import cu.edu.cujae.touristpacks.service.transport_service.ITransportServiceService;
import cu.edu.cujae.touristpacks.utils.JsfUtils;

@Component
@ManagedBean
@ViewScoped
public class ManageTransportContractBean {

    private List<TransportContractDto> transportContracts;
    private TransportContractDto selectedTransportContract;

    private String selectedProviderName;
    private List<String> selectedTransportServicesNames;

    @Autowired
    private ITransportContractService service;

    @Autowired
    private IProviderService providerService;

    @Autowired
    private ITransportServiceService transportServiceService;

    public ManageTransportContractBean() {

    }

    public void openNew() {
        this.selectedTransportContract = new TransportContractDto();
    }

    public void openForEdit() {

    }

    public void saveTransportContract() {
        selectedTransportContract.setProvider(providerService.getProviderByName(selectedProviderName));

        List<TransportServiceDto> transportServices = new ArrayList<>();
        for (String name : selectedTransportServicesNames) {
            transportServices.add(transportServiceService.getTransportServiceByName(name));
        }
        selectedTransportContract.setTransportServices(transportServices);

        if (this.selectedTransportContract.getIdTransportContract() == 0) {

            service.createTransportContract(selectedTransportContract);

            JsfUtils.addInfoMessageFromBundle("message_inserted_transportContract");
        } else {
            service.updateTransportContract(selectedTransportContract);

            JsfUtils.addInfoMessageFromBundle("message_updated_transportContract");
        }

        transportContracts = service.getTransportContracts();

        PrimeFaces.current().executeScript("PF('manageTransportContractDialog').hide()");
        PrimeFaces.current().ajax().update("form:dt-transportContracts");

    }

    public void deleteTransportContract() {

        service.deleteTransportContract(selectedTransportContract.getIdTransportContract());
        this.selectedTransportContract = null;

        transportContracts = service.getTransportContracts();

        JsfUtils.addInfoMessageFromBundle("message_deleted_transportContract");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-transportContracts");

    }

    public String getTransportServicesNames(TransportContractDto transportContract) {
        String names = "";
        for (TransportServiceDto transportService : transportContract.getTransportServices()) {
            names += transportService.getTransportServiceName() + ",";
        }
        return names.length() > 0 ? names.substring(0, names.length() - 1) : names;
    }

    public String getSelectedProviderName() {
        return selectedProviderName;
    }

    public void setSelectedProviderName(String selectedProviderName) {
        this.selectedProviderName = selectedProviderName;
    }

    public List<TransportContractDto> getTransportContracts() {
        transportContracts = service.getTransportContracts();
        return this.transportContracts;
    }

    public void setTransportContracts(List<TransportContractDto> transportContracts) {
        this.transportContracts = transportContracts;
    }

    public TransportContractDto getSelectedTransportContract() {
        return this.selectedTransportContract;
    }

    public void setSelectedTransportContract(TransportContractDto selectedTransportContract) {
        this.selectedTransportContract = selectedTransportContract;
    }

    public ITransportContractService getService() {
        return this.service;
    }

    public void setService(ITransportContractService service) {
        this.service = service;
    }

    public IProviderService getProviderService() {
        return providerService;
    }

    public void setProviderService(IProviderService serviceProvider) {
        this.providerService = serviceProvider;
    }

    public List<String> getSelectedTransportServicesNames() {
        return this.selectedTransportServicesNames;
    }

    public void setSelectedTransportServicesNames(List<String> selectedTransportServicesNames) {
        this.selectedTransportServicesNames = selectedTransportServicesNames;
    }

    public ITransportServiceService getTransportServiceService() {
        return this.transportServiceService;
    }

    public void setTransportServiceService(ITransportServiceService transportServiceService) {
        this.transportServiceService = transportServiceService;
    }

}
