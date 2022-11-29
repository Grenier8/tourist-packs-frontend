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

import cu.edu.cujae.touristpacks.dto.HotelContractDto;
import cu.edu.cujae.touristpacks.service.hotel_contract.IHotelContractService;
import cu.edu.cujae.touristpacks.utils.JsfUtils;

@Component
@ManagedBean
@ViewScoped
public class ManageHotelContractBean {

    private List<HotelContractDto> hotelContracts;
    private HotelContractDto selectedHotelContract;

    @Autowired
    private IHotelContractService service;

    public ManageHotelContractBean() {

    }

    @PostConstruct
    public void init() {
        hotelContracts = service.getHotelContracts();
    }

    public void openNew() {
        this.selectedHotelContract = new HotelContractDto();
    }

    public void openForEdit() {

    }

    public void saveHotelContract() {
        if (this.selectedHotelContract.getIdHotelContract() == 0) {
            service.createHotelContract(selectedHotelContract);

            JsfUtils.addInfoMessageFromBundle("message_inserted_hotel_contract");
        } else {
            service.updateHotelContract(selectedHotelContract);

            JsfUtils.addInfoMessageFromBundle("message_updated_hotel_contract");
        }

        hotelContracts = service.getHotelContracts();

        PrimeFaces.current().executeScript("PF('manageHotelContractDialog').hide()");
        PrimeFaces.current().ajax().update("form:dt-hotelContracts");

    }

    public void deleteHotelContract() {

        service.deleteHotelContract(selectedHotelContract.getIdHotelContract());
        this.selectedHotelContract = null;

        hotelContracts = service.getHotelContracts();

        JsfUtils.addInfoMessageFromBundle("message_deleted_hotel_contract");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-hotelContracts");

    }

    public List<HotelContractDto> getHotelContracts() {
        return this.hotelContracts;
    }

    public void setHotelContracts(List<HotelContractDto> hotelContracts) {
        this.hotelContracts = hotelContracts;
    }

    public HotelContractDto getSelectedHotelContract() {
        return this.selectedHotelContract;
    }

    public void setSelectedHotelContract(HotelContractDto selectedHotelContract) {
        this.selectedHotelContract = selectedHotelContract;
    }

    public IHotelContractService getService() {
        return this.service;
    }

    public void setService(IHotelContractService service) {
        this.service = service;
    }

}