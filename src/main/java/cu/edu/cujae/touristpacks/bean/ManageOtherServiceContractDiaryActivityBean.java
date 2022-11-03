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

import cu.edu.cujae.touristpacks.dto.OtherServiceContractDiaryActivityDto;
import cu.edu.cujae.touristpacks.service.other_service_contract_diary_activity.IOtherServiceContractDiaryActivityService;

@Component
@ManagedBean
@ViewScoped
public class ManageOtherServiceContractDiaryActivityBean {

    private List<OtherServiceContractDiaryActivityDto> otherServiceContractDiaryActivities;
    private OtherServiceContractDiaryActivityDto selectedOtherServiceContractDiaryActivity;

    @Autowired
    private IOtherServiceContractDiaryActivityService service;

    public ManageOtherServiceContractDiaryActivityBean() {

    }

    @PostConstruct
    public void init() {
        otherServiceContractDiaryActivities = otherServiceContractDiaryActivities == null
                ? service.getOtherServiceContractDiaryActivities()
                : otherServiceContractDiaryActivities;
    }

    public void openNew() {
        this.selectedOtherServiceContractDiaryActivity = new OtherServiceContractDiaryActivityDto();
    }

    public void openForEdit() {

    }

    public void saveOtherServiceContractDiaryActivity() {
        if (this.selectedOtherServiceContractDiaryActivity.getIdOtherServiceContractDiaryActivity() == 0) {
            int r = (int) (Math.random() * 10000);

            this.selectedOtherServiceContractDiaryActivity.setIdOtherServiceContractDiaryActivity(r);
            this.otherServiceContractDiaryActivities.add(this.selectedOtherServiceContractDiaryActivity);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cambiar insertada"));
        } else {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cambiar modificada"));
        }

        PrimeFaces.current().executeScript("PF('manageOtherServiceContractDiaryActivityDialog').hide()");
        PrimeFaces.current().ajax().update("form:dt-otherServiceContractDiaryActivities");

    }

    public void deleteOtherServiceContractDiaryActivity() {

        this.otherServiceContractDiaryActivities.remove(this.selectedOtherServiceContractDiaryActivity);
        this.selectedOtherServiceContractDiaryActivity = null;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cambiar eliminada"));
        PrimeFaces.current().ajax().update("form:messages", "form:dt-otherServiceContractDiaryActivities");

    }

    public List<OtherServiceContractDiaryActivityDto> getOtherServiceContractDiaryActivitys() {
        return otherServiceContractDiaryActivities;
    }

    public void setOtherServiceContractDiaryActivitys(
            List<OtherServiceContractDiaryActivityDto> otherServiceContractDiaryActivities) {
        this.otherServiceContractDiaryActivities = otherServiceContractDiaryActivities;
    }

    public OtherServiceContractDiaryActivityDto getSelectedOtherServiceContractDiaryActivity() {
        return selectedOtherServiceContractDiaryActivity;
    }

    public void setSelectedOtherServiceContractDiaryActivity(
            OtherServiceContractDiaryActivityDto selectedOtherServiceContractDiaryActivity) {
        this.selectedOtherServiceContractDiaryActivity = selectedOtherServiceContractDiaryActivity;
    }

    public IOtherServiceContractDiaryActivityService getService() {
        return service;
    }

    public void setService(IOtherServiceContractDiaryActivityService service) {
        this.service = service;
    }
}
