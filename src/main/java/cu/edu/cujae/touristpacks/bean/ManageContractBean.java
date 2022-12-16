package cu.edu.cujae.touristpacks.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;

import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cu.edu.cujae.touristpacks.dto.ContractDto;
import cu.edu.cujae.touristpacks.service.contract.IContractService;
import cu.edu.cujae.touristpacks.utils.JsfUtils;

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

    public void updateContracts() {
        contracts = service.getContracts();
    }

    public List<ContractDto> getContracts() {
        return this.contracts;
    }

    public void setContracts(List<ContractDto> contracts) {
        this.contracts = contracts;
    }

    public ContractDto getSelectedContract() {
        return this.selectedContract;
    }

    public void setSelectedContract(ContractDto selectedContract) {
        this.selectedContract = selectedContract;
    }

    public IContractService getService() {
        return this.service;
    }

    public void setService(IContractService service) {
        this.service = service;
    }

}