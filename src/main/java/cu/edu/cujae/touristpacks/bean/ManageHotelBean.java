package cu.edu.cujae.touristpacks.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;

import cu.edu.cujae.touristpacks.service.hotel.IHotelService;
import cu.edu.cujae.touristpacks.service.hotel_chain.IHotelChainService;
import cu.edu.cujae.touristpacks.service.province.IProvinceService;

import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cu.edu.cujae.touristpacks.dto.HotelChainDto;
import cu.edu.cujae.touristpacks.dto.HotelDto;
import cu.edu.cujae.touristpacks.dto.ProvinceDto;

@Component
@ManagedBean
@ViewScoped
public class ManageHotelBean {

    private List<HotelDto> hotels;
    private HotelDto selectedHotel;

    private String selectedHotelChainName;
    private String selectedProvinceName;

    @Autowired
    private IHotelService service;

    @Autowired
    private IHotelChainService hotelChainService;

    @Autowired
    private IProvinceService provinceService;

    public ManageHotelBean() {

    }

    @PostConstruct
    public void init() {
        hotels = hotels == null ? service.getHotels() : hotels;
    }

    public void openNew() {
        this.selectedHotel = new HotelDto();
    }

    public void openForEdit() {

    }

    public void saveHotel() {
        if (this.selectedHotel.getIdHotel() == 0) {
            int r = (int) (Math.random() * 10000);
            this.selectedHotel.setIdHotel(r);

            HotelChainDto hotelChain = hotelChainService.getHotelChainByName(selectedHotelChainName);
            this.selectedHotel.setHotelChain(hotelChain);

            ProvinceDto province = provinceService.getProvinceByName(selectedProvinceName);
            this.selectedHotel.setProvince(province);

            this.hotels.add(this.selectedHotel);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Hotel insertado"));
        } else {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Hotel modificado"));
        }

        PrimeFaces.current().executeScript("PF('manageHotelDialog').hide()");
        PrimeFaces.current().ajax().update("form:dt-hotels");

    }

    public void deleteHotel() {

        this.hotels.remove(this.selectedHotel);
        this.selectedHotel = null;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Hotel eliminado"));
        PrimeFaces.current().ajax().update("form:messages", "form:dt-hotels");

    }

    public List<HotelDto> getHotels() {
        return this.hotels;
    }

    public void setHotels(List<HotelDto> hotels) {
        this.hotels = hotels;
    }

    public HotelDto getSelectedHotel() {
        return this.selectedHotel;
    }

    public void setSelectedHotel(HotelDto selectedHotel) {
        this.selectedHotel = selectedHotel;
    }

    public String getSelectedHotelChainName() {
        return this.selectedHotelChainName;
    }

    public void setSelectedHotelChainName(String selectedHotelChainName) {
        this.selectedHotelChainName = selectedHotelChainName;
    }

    public String getSelectedProvinceName() {
        return this.selectedProvinceName;
    }

    public void setSelectedProvinceName(String selectedProvinceName) {
        this.selectedProvinceName = selectedProvinceName;
    }

    public IHotelService getService() {
        return this.service;
    }

    public void setService(IHotelService service) {
        this.service = service;
    }

    public IHotelChainService getHotelChainService() {
        return this.hotelChainService;
    }

    public void setHotelChainService(IHotelChainService hotelChainService) {
        this.hotelChainService = hotelChainService;
    }

    public IProvinceService getProvinceService() {
        return this.provinceService;
    }

    public void setProvinceService(IProvinceService provinceService) {
        this.provinceService = provinceService;
    }

}
