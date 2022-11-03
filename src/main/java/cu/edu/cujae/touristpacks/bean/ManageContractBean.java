package cu.edu.cujae.touristpacks.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;

import cu.edu.cujae.touristpacks.dto.ContractDto;
import cu.edu.cujae.touristpacks.service.contract.IContractService;
import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@ManagedBean
@ViewScoped
public class ManageContractBean {

    private List<ContractDto> contracts;
    private ContractDto selectedContract;

    @Autowired
    private IContractService service;

    public ManageContractBean() {

    }

    @PostConstruct
    public void init() {
        contracts = contracts == null ? service.getContracts() : contracts;
    }

    public void openNew() {
        this.selectedContract = new ContractDto();
    }

    public void openForEdit() {

    }

    public void saveContract() {
        if (this.selectedContract.getIdContract() == 0) {
            int r = (int) (Math.random() * 10000);

            this.selectedContract.setIdContract(r);
            this.contracts.add(this.selectedContract);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cambiar insertada"));
        } else {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cambiar modificada"));
        }

        PrimeFaces.current().executeScript("PF('manageContractDialog').hide()");
        PrimeFaces.current().ajax().update("form:dt-contracts");

    }

    public void deleteContract() {

        this.contracts.remove(this.selectedContract);
        this.selectedContract = null;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cambiar eliminada"));
        PrimeFaces.current().ajax().update("form:messages", "form:dt-contracts");

    }

    public List<ContractDto> getContracts() {
        return contracts;
    }

    public void setContracts(List<ContractDto> contracts) {
        this.contracts = contracts;
    }

    public ContractDto getSelectedContract() {
        return selectedContract;
    }

    public void setSelectedContract(ContractDto selectedContract) {
        this.selectedContract = selectedContract;
    }

    public IContractService getService() {
        return service;
    }

    public void setService(IContractService service) {
        this.service = service;
    }
}
