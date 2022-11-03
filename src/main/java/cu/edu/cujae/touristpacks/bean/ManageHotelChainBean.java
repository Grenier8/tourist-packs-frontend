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
        hotelChains = hotelChains == null ? service.getHotelChains() : hotelChains;
    }

    public void openNew() {
        this.selectedHotelChain = new HotelChainDto();
    }

    public void openForEdit() {

    }

    public void saveHotelChain() {
        if (this.selectedHotelChain.getIdHotelChain() == 0) {
            int r = (int) (Math.random() * 10000);

            this.selectedHotelChain.setIdHotelChain(r);
            this.hotelChains.add(this.selectedHotelChain);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cadena hotelera insertada"));
        } else {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cadena hotelera modificada"));
        }

        PrimeFaces.current().executeScript("PF('manageHotelChainDialog').hide()");
        PrimeFaces.current().ajax().update("form:dt-hotelChains");

    }

    public void deleteHotelChain() {

        this.hotelChains.remove(this.selectedHotelChain);
        this.selectedHotelChain = null;
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