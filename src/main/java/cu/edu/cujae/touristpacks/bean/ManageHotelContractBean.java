package cu.edu.cujae.touristpacks.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;

import cu.edu.cujae.touristpacks.dto.HotelContractDto;
import cu.edu.cujae.touristpacks.dto.HotelDto;
import cu.edu.cujae.touristpacks.service.hotel.IHotelService;
import cu.edu.cujae.touristpacks.service.hotel_contract.IHotelContractService;

import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@ManagedBean
@ViewScoped
public class ManageHotelContractBean {

    private List<HotelContractDto> hotelContracts;
    private HotelContractDto selectedHotelContract;
    private String selectedHotelName;

    @Autowired
    private IHotelContractService service;

    @Autowired
    private IHotelService hotelService;

    public ManageHotelContractBean() {

    }

    @PostConstruct
    public void init() {
        hotelContracts = hotelContracts == null ? service.getHotelContracts() : hotelContracts;
    }

    public void openNew() {
        this.selectedHotelContract = new HotelContractDto();
    }

    public void openForEdit() {

    }

    public void saveHotelContract() {
        if (this.selectedHotelContract.getIdHotelContract() == 0) {
            int r = (int) (Math.random() * 10000);
            this.selectedHotelContract.setIdHotelContract(r);

            HotelDto hotel = hotelService.getHotelByName(selectedHotelName);
            this.selectedHotelContract.setHotel(hotel);

            this.hotelContracts.add(this.selectedHotelContract);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Contrato de hotel insertado"));
        } else {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Contrato de hotel modificado"));
        }

        PrimeFaces.current().executeScript("PF('manageHotelContractDialog').hide()");
        PrimeFaces.current().ajax().update("form:dt-hotelContracts");

    }

    public void deleteHotelContract() {

        this.hotelContracts.remove(this.selectedHotelContract);
        this.selectedHotelContract = null;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Contrato de hotel eliminado"));
        PrimeFaces.current().ajax().update("form:messages", "form:dt-hotelContracts");

    }

    public List<HotelContractDto> getHotelContracts() {
        return hotelContracts;
    }

    public void setHotelContracts(List<HotelContractDto> hotelContracts) {
        this.hotelContracts = hotelContracts;
    }

    public HotelContractDto getSelectedHotelContract() {
        return selectedHotelContract;
    }

    public void setSelectedHotelContract(HotelContractDto selectedHotelContract) {
        this.selectedHotelContract = selectedHotelContract;
    }

    public IHotelContractService getService() {
        return service;
    }

    public void setService(IHotelContractService service) {
        this.service = service;
    }

    public String getSelectedHotelName() {
        return this.selectedHotelName;
    }

    public void setSelectedHotelName(String selectedHotelName) {
        this.selectedHotelName = selectedHotelName;
    }
}
