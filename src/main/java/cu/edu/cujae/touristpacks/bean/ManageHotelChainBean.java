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

import cu.edu.cujae.touristpacks.dto.HotelChainDto;
import cu.edu.cujae.touristpacks.service.hotel_chain.IHotelChainService;

@Component
@ManagedBean
@ViewScoped
public class ManageHotelChainBean {

    private List<HotelChainDto> hotelChains;
    private HotelChainDto selectedHotelChain;

    @Autowired
    private IHotelChainService service;

    public ManageHotelChainBean() {

    }

    @PostConstruct
    public void init() {
        hotelChains = service.getHotelChains();
    }

    public void openNew() {
        this.selectedHotelChain = new HotelChainDto();
    }

    public void openForEdit() {

    }

    public void saveHotelChain() {
        if (this.selectedHotelChain.getIdHotelChain() == 0) {
            service.createHotelChain(selectedHotelChain);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cadena hotelera insertada"));
        } else {
            service.updateHotelChain(selectedHotelChain);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cadena hotelera modificada"));
        }

        hotelChains = service.getHotelChains();

        PrimeFaces.current().executeScript("PF('manageHotelChainDialog').hide()");
        PrimeFaces.current().ajax().update("form:dt-hotelChains");

    }

    public void deleteHotelChain() {

        service.deleteHotelChain(selectedHotelChain.getIdHotelChain());
        this.selectedHotelChain = null;

        hotelChains = service.getHotelChains();

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cadena hotelera eliminada"));
        PrimeFaces.current().ajax().update("form:messages", "form:dt-hotelChains");

    }

    public List<HotelChainDto> getHotelChains() {
        return this.hotelChains;
    }

    public void setHotelChains(List<HotelChainDto> hotelChains) {
        this.hotelChains = hotelChains;
    }

    public HotelChainDto getSelectedHotelChain() {
        return this.selectedHotelChain;
    }

    public void setSelectedHotelChain(HotelChainDto selectedHotelChain) {
        this.selectedHotelChain = selectedHotelChain;
    }

    public IHotelChainService getService() {
        return this.service;
    }

    public void setService(IHotelChainService service) {
        this.service = service;
    }

}