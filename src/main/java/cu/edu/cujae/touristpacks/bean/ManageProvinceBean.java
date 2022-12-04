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

import cu.edu.cujae.touristpacks.dto.ProvinceDto;
import cu.edu.cujae.touristpacks.service.province.IProvinceService;
import cu.edu.cujae.touristpacks.utils.JsfUtils;

@Component
@ManagedBean
@ViewScoped
public class ManageProvinceBean {

    private List<ProvinceDto> provinces;
    private ProvinceDto selectedProvince;

    @Autowired
    private IProvinceService service;

    public ManageProvinceBean() {

    }

    @PostConstruct
    public void init() {
        provinces = service.getProvinces();
    }

    public void openNew() {
        this.selectedProvince = new ProvinceDto();
    }

    public void openForEdit() {

    }

    public void saveProvince() {
        if (this.selectedProvince.getIdProvince() == 0) {
            service.createProvince(selectedProvince);

            JsfUtils.addInfoMessageFromBundle("message_inserted_province");
        } else {
            service.updateProvince(selectedProvince);

            JsfUtils.addInfoMessageFromBundle("message_updated_province");
        }

        provinces = service.getProvinces();

        PrimeFaces.current().executeScript("PF('manageProvinceDialog').hide()");
        PrimeFaces.current().ajax().update("form:dt-provinces");

    }

    public void deleteProvince() {

        service.deleteProvince(selectedProvince.getIdProvince());
        this.selectedProvince = null;

        provinces = service.getProvinces();

        JsfUtils.addInfoMessageFromBundle("message_deleted_province");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-provinces");

    }

    public List<ProvinceDto> getProvinces() {
        return this.provinces;
    }

    public void setProvinces(List<ProvinceDto> provinces) {
        this.provinces = provinces;
    }

    public ProvinceDto getSelectedProvince() {
        return this.selectedProvince;
    }

    public void setSelectedProvince(ProvinceDto selectedProvince) {
        this.selectedProvince = selectedProvince;
    }

    public IProvinceService getService() {
        return this.service;
    }

    public void setService(IProvinceService service) {
        this.service = service;
    }

}