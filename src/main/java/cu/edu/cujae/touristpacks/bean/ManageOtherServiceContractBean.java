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

import cu.edu.cujae.touristpacks.dto.OtherServiceContractDto;
import cu.edu.cujae.touristpacks.service.other_service_contract.IOtherServiceContractService;

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
        otherServiceContracts = otherServiceContracts == null ? service.getOtherServiceContracts()
                : otherServiceContracts;
    }

    public void openNew() {
        this.selectedOtherServiceContract = new OtherServiceContractDto();
    }

    public void openForEdit() {

    }

    public void saveOtherServiceContract() {
        if (this.selectedOtherServiceContract.getIdOtherServiceContract() == 0) {
            int r = (int) (Math.random() * 10000);

            this.selectedOtherServiceContract.setIdOtherServiceContract(r);
            this.otherServiceContracts.add(this.selectedOtherServiceContract);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cambiar insertada"));
        } else {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cambiar modificada"));
        }

        PrimeFaces.current().executeScript("PF('manageOtherServiceContractDialog').hide()");
        PrimeFaces.current().ajax().update("form:dt-otherServiceContracts");

    }

    public void deleteOtherServiceContract() {

        this.otherServiceContracts.remove(this.selectedOtherServiceContract);
        this.selectedOtherServiceContract = null;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cambiar eliminada"));
        PrimeFaces.current().ajax().update("form:messages", "form:dt-otherServiceContracts");

    }

    public List<OtherServiceContractDto> getOtherServiceContracts() {
        return otherServiceContracts;
    }

    public void setOtherServiceContracts(List<OtherServiceContractDto> otherServiceContracts) {
        this.otherServiceContracts = otherServiceContracts;
    }

    public OtherServiceContractDto getSelectedOtherServiceContract() {
        return selectedOtherServiceContract;
    }

    public void setSelectedOtherServiceContract(OtherServiceContractDto selectedOtherServiceContract) {
        this.selectedOtherServiceContract = selectedOtherServiceContract;
    }

    public IOtherServiceContractService getService() {
        return service;
    }

    public void setService(IOtherServiceContractService service) {
        this.service = service;
    }
}
