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

import cu.edu.cujae.touristpacks.dto.ProvinceDto;
import cu.edu.cujae.touristpacks.service.province.IProvinceService;

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
        provinces = provinces == null ? service.getProvinces() : provinces;
    }

    public void openNew() {
        this.selectedProvince = new ProvinceDto();
    }

    public void openForEdit() {

    }

    public void saveProvince() {
        if (this.selectedProvince.getIdProvince() == 0) {
            int r = (int) (Math.random() * 10000);

            this.selectedProvince.setIdProvince(r);
            this.provinces.add(this.selectedProvince);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Provincia insertada"));
        } else {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Provincia modificada"));
        }

        PrimeFaces.current().executeScript("PF('manageProvinceDialog').hide()");
        PrimeFaces.current().ajax().update("form:dt-provinces");

    }

    public void deleteProvince() {

        this.provinces.remove(this.selectedProvince);
        this.selectedProvince = null;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Provincia eliminada"));
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
