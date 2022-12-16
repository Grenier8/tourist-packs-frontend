package cu.edu.cujae.touristpacks.bean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;

import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cu.edu.cujae.touristpacks.dto.HotelDto;
import cu.edu.cujae.touristpacks.dto.HotelModalityDto;
import cu.edu.cujae.touristpacks.service.hotel.IHotelService;
import cu.edu.cujae.touristpacks.service.hotel_chain.IHotelChainService;
import cu.edu.cujae.touristpacks.service.hotel_modality.IHotelModalityService;
import cu.edu.cujae.touristpacks.service.province.IProvinceService;
import cu.edu.cujae.touristpacks.utils.JsfUtils;

@Component
@ManagedBean
@ViewScoped
public class ManageHotelBean {

    private List<HotelDto> hotels;
    private HotelDto selectedHotel;

    private String selectedHotelChainName;
    private String selectedProvinceName;
    private List<String> selectedHotelModalitiesNames;

    @Autowired
    private IHotelService service;

    @Autowired
    private IHotelChainService hotelChainService;

    @Autowired
    private IProvinceService provinceService;

    @Autowired
    private IHotelModalityService hotelModalityService;

    public ManageHotelBean() {

    }

    public void openNew() {
        this.selectedHotel = new HotelDto();
    }

    public void openForEdit() {

    }

    public void saveHotel() {
        selectedHotel.setHotelChain(hotelChainService.getHotelChainByName(selectedHotelChainName));
        selectedHotel.setProvince(provinceService.getProvinceByName(selectedProvinceName));

        List<HotelModalityDto> hotelModalities = new ArrayList<>();
        for (String name : selectedHotelModalitiesNames) {
            hotelModalities.add(hotelModalityService.getHotelModalityByName(name));
        }
        selectedHotel.setHotelModalities(hotelModalities);

        if (this.selectedHotel.getIdHotel() == 0) {
            service.createHotel(selectedHotel);

            JsfUtils.addInfoMessageFromBundle("message_inserted_hotel");
        } else {
            service.updateHotel(selectedHotel);

            JsfUtils.addInfoMessageFromBundle("message_updated_hotel");
        }

        hotels = service.getHotels();

        PrimeFaces.current().executeScript("PF('manageHotelDialog').hide()");
        PrimeFaces.current().ajax().update("form:dt-hotels");

    }

    public void deleteHotel() {

        service.deleteHotel(selectedHotel.getIdHotel());
        this.selectedHotel = null;

        hotels = service.getHotels();

        JsfUtils.addInfoMessageFromBundle("message_deleted_hotel");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-hotels");

    }

    public String getHotelModalitiesNames(HotelDto hotel) {
        String names = "";
        for (HotelModalityDto hotelModality : hotel.getHotelModalities()) {
            names += hotelModality.getHotelModalityName() + ",";
        }
        return names.length() > 0 ? names.substring(0, names.length() - 1) : names;
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

    public IHotelService getService() {
        return this.service;
    }

    public void setService(IHotelService service) {
        this.service = service;
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

    public List<String> getSelectedHotelModalitiesNames() {
        return this.selectedHotelModalitiesNames;
    }

    public void setSelectedHotelModalitiesNames(List<String> selectedHotelModalitiesNames) {
        this.selectedHotelModalitiesNames = selectedHotelModalitiesNames;
    }

    public IHotelModalityService getHotelModalityService() {
        return this.hotelModalityService;
    }

    public void setHotelModalityService(IHotelModalityService hotelModalityService) {
        this.hotelModalityService = hotelModalityService;
    }

}