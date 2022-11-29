package cu.edu.cujae.touristpacks.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cu.edu.cujae.touristpacks.dto.OtherServiceContractDto;
import cu.edu.cujae.touristpacks.service.other_service_contract.IOtherServiceContractService;
import cu.edu.cujae.touristpacks.utils.JsfUtils;

@Component
@ManagedBean
@ViewScoped
public class ManageOtherServiceContractBean {

    private List<OtherServiceContractDto> otherServiceContracts;
    private OtherServiceContractDto selectedOtherServiceContract;

    @Autowired
    private IOtherServiceContractService service;

    public ManageOtherServiceContractBean() {

    }

    @PostConstruct
    public void init() {
        otherServiceContracts = service.getOtherServiceContracts();
    }

    public void openNew() {
        this.selectedOtherServiceContract = new OtherServiceContractDto();
    }

    public void openForEdit() {

    }

    public void saveOtherServiceContract() {
        if (this.selectedOtherServiceContract.getIdOtherServiceContract() == 0) {
            service.createOtherServiceContract(selectedOtherServiceContract);

            JsfUtils.addInfoMessageFromBundle("message_inserted_other_service_contract");
        } else {
            service.updateOtherServiceContract(selectedOtherServiceContract);

            JsfUtils.addInfoMessageFromBundle("message_updated_other_service_contract");
        }

        otherServiceContracts = service.getOtherServiceContracts();

        PrimeFaces.current().executeScript("PF('manageOtherServiceContractDialog').hide()");
        PrimeFaces.current().ajax().update("form:dt-otherServiceContracts");

    }

    public void deleteOtherServiceContract() {

        service.deleteOtherServiceContract(selectedOtherServiceContract.getIdOtherServiceContract());
        this.selectedOtherServiceContract = null;

        otherServiceContracts = service.getOtherServiceContracts();

        JsfUtils.addInfoMessageFromBundle("message_deleted_other_service_contract");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-otherServiceContracts");

    }

    public List<OtherServiceContractDto> getOtherServiceContracts() {
        return this.otherServiceContracts;
    }

    public void setOtherServiceContracts(List<OtherServiceContractDto> otherServiceContracts) {
        this.otherServiceContracts = otherServiceContracts;
    }

    public OtherServiceContractDto getSelectedOtherServiceContract() {
        return this.selectedOtherServiceContract;
    }

    public void setSelectedOtherServiceContract(OtherServiceContractDto selectedOtherServiceContract) {
        this.selectedOtherServiceContract = selectedOtherServiceContract;
    }

    public IOtherServiceContractService getService() {
        return this.service;
    }

    public void setService(IOtherServiceContractService service) {
        this.service = service;
    }

}